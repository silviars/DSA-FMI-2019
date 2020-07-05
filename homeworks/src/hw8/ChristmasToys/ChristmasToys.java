package hw8.ChristmasToys;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ChristmasToys {
    private int totalConnectedToys, subgraphs;
    private Map<Integer, ArrayList<Integer>> connections;

    private ChristmasToys(){
        this.subgraphs = 0;
        this.totalConnectedToys = 0;
        connections = new HashMap<>();
    }

    private void addPairRequest(int TOY1, int TOY2){
        if(!containsToy(TOY1)) {
            addToy(TOY1);
            totalConnectedToys++;
        }
        if(!containsToy(TOY2)) {
            addToy(TOY2);
            totalConnectedToys++;
        }

        if(!containsConnection(TOY1, TOY2))
            connect(TOY1, TOY2);
    }
    private void addToy(int toy){
        connections.put(toy, new ArrayList<>());
    }
    private boolean containsToy(int toy){
        return connections.containsKey(toy);
    }
    private boolean containsConnection(int toy1, int toy2){
        return connections.get(toy1).contains(toy2);
    }
    private void connect(int toy1, int toy2){
        connections.get(toy1).add(toy2);
        connections.get(toy2).add(toy1);
    }

    void dfs(){
        HashMap<Integer, Boolean> visited = new HashMap<>();
        for(Integer i: connections.keySet()){
            visited.put(i, false);
        }
        for(Integer i: visited.keySet()){
            if(!visited.get(i)){
                _dfs(i, visited);
                subgraphs++;
            }
        }
    }

    void _dfs(int start,HashMap<Integer, Boolean> visited){
        Stack<Integer> temp = new Stack<>();
        temp.push(start);

        while (!temp.isEmpty()){
            start = temp.peek();
            temp.pop();

            if(!visited.get(start)){
                // System.out.print(start + " ");
                visited.replace(start, true);
            }

            if(connections.containsKey(start)) {
                for (Integer i : connections.get(start)) {
                    if (!visited.get(i))
                        temp.push(i);
                }
            }
        }
    }

    int getMaxColors(int totalToys){
        return totalToys - totalConnectedToys + subgraphs;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int totalToys = Integer.parseInt(tokenizer.nextToken());
        int pairRequests = Integer.parseInt(tokenizer.nextToken());
        ChristmasToys christmasToys = new ChristmasToys();

        int TOY1, TOY2;
        for(int i = 1; i <= pairRequests; i++){
            tokenizer = new StringTokenizer(reader.readLine());
            TOY1 = Integer.parseInt(tokenizer.nextToken());
            TOY2 = Integer.parseInt(tokenizer.nextToken());
            christmasToys.addPairRequest(TOY1, TOY2);
        }

        christmasToys.dfs();
        int result = christmasToys.getMaxColors(totalToys);
        System.out.println(result);

        reader.close();
    }
}