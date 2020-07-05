package hw4.TruckOrdering;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class TruckOrdering {

    static String isPossible(LinkedList<Long> source, Stack<Long> destination, Stack<Long> midStreet) {

        Long currTruck, prevTruck;

        if(source.size() == 1) return "yes";

        while(source.size() > 1){
            currTruck = source.pollFirst();
            prevTruck = source.peekFirst();

            if(destination.isEmpty() || midStreet.isEmpty()){
                if(currTruck <= prevTruck) destination.push(currTruck);
                else midStreet.push(currTruck);
            }
            else{
                if(currTruck > midStreet.peek() || currTruck < destination.peek()) return "no";

                else{
                    if(currTruck > prevTruck) midStreet.push(currTruck);
                    else destination.push(currTruck);
                }
            }
        }
        currTruck = source.pollFirst();

        if(midStreet.isEmpty() || destination.isEmpty()) return "yes";

        else if(currTruck > midStreet.peek() || currTruck < destination.peek()) return "no";

        return "yes";
    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int requests = Integer.parseInt(tokenizer.nextToken());

        for(int i = 0; i < requests; i++){
            tokenizer = new StringTokenizer(reader.readLine());
            int numOfTrucks = Integer.parseInt(tokenizer.nextToken());

            LinkedList<Long> source = new LinkedList<>();
            Stack<Long> destination = new Stack<>();
            Stack<Long> midStreet = new Stack<>();

            tokenizer = new StringTokenizer(reader.readLine());
            for(int j = 0; j < numOfTrucks; j++) {
                source.add(Long.parseLong(tokenizer.nextToken()));

            }
            writer.println(isPossible(source, destination, midStreet));
        }

        reader.close();
        writer.close();
    }
}
