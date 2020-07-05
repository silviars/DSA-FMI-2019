package hw8.ChristmasMarkets;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class ChristmasMarkets {

    private HashMap<String, Integer> citiesToInts;
    private HashMap<Integer, String> intsToCities;
    private int[] parent, visited;
    private ArrayList<Integer>[] adjacencyList;
    private int startPosition, tripStart, tripEnd, counter;

    private ChristmasMarkets(int totalCities){
        citiesToInts = new HashMap<>(totalCities);
        intsToCities = new HashMap<>(totalCities);
        parent = new int[totalCities];
        visited = new int[totalCities];
        tripStart = -1;
        tripEnd = -1;
        counter = 0;

        adjacencyList = new ArrayList[totalCities];
        for(int i = 0; i < totalCities; i++){
            adjacencyList[i] = new ArrayList<>();
        }
    }

    private void addTicket(String from, String to){
        if(!containsLocation(from))
            addCity(from);
        if(!containsLocation(to))
            addCity(to);

        int _from, _to;
        _from = citiesToInts.get(from);
        _to = citiesToInts.get(to);
        adjacencyList[_from].add(_to);
    }

    private void addCity(String cityName){
        intsToCities.put(counter, cityName);
        citiesToInts.put(cityName, counter++);
    }

    private boolean containsLocation(String cityName){
        return citiesToInts.containsKey(cityName);
    }

    private void setStartPosition(String cityName){
        this.startPosition = citiesToInts.get(cityName);
    }

    private boolean findCycle(int currCity){
        visited[currCity] = 1;
        for(Integer i: adjacencyList[currCity]){
            if(visited[i] == 0){
                parent[i] = currCity;
                if(findCycle(i))
                    return true;
            }
            else if(i == startPosition) {
                tripStart = i;
                tripEnd = currCity;
                return true;
            }
        }
        visited[currCity] = 2;
        return false;
    }

    private StringBuilder getPath(){
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(startPosition);
        for(int i = tripEnd; i != tripStart; i = parent[i])
            temp.add(i);

        Collections.reverse(temp);
        StringBuilder path = new StringBuilder();
        for(Integer i: temp){
            path.append(intsToCities.get(i) + " ");
        }

        return path;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int totalCities = Integer.parseInt(tokenizer.nextToken());
        int ownedTickets = Integer.parseInt(tokenizer.nextToken());
        String CITY1, CITY2;
        ChristmasMarkets christmasMarkets = new ChristmasMarkets(totalCities);

        for(int i = 1; i <= ownedTickets; i++){
            tokenizer = new StringTokenizer(reader.readLine());
            CITY1 = tokenizer.nextToken();
            CITY2 = tokenizer.nextToken();
            christmasMarkets.addTicket(CITY1, CITY2);
        }

        tokenizer = new StringTokenizer(reader.readLine());
        String startPosition = tokenizer.nextToken();
        christmasMarkets.setStartPosition(startPosition);
        christmasMarkets.findCycle(christmasMarkets.startPosition);

        if(christmasMarkets.tripStart == -1){
            System.out.print(-1);
            return;
        }

        StringBuilder path = new StringBuilder(startPosition + " ");
        path.append(christmasMarkets.getPath());
        System.out.print(path.toString());

        reader.close();
    }
}