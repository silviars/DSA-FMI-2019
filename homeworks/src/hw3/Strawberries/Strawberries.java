package hw3.Strawberries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Strawberries {

    static int search(long[] arr, long x){
        int left = 0, right = arr.length -1;
        while(left <= right){
            int mid = left + (right - left)/2;

            if((mid == 0 && x <= arr[mid]) || (mid != 0 && x > arr[mid -1] && x <= arr[mid]))
                return mid + 1;
            if(x > arr[mid])
                left = mid + 1;
            else if (x <= arr[mid -1])
                right = mid -1;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int numOfBowls = Integer.parseInt(tokenizer.nextToken());
        long[] bowls = new long[numOfBowls];
        long sumOfBerryIndexes = 0;

        tokenizer = new StringTokenizer(reader.readLine());
        for(int i = 0; i < numOfBowls; i++){
            long bowl = Long.parseLong(tokenizer.nextToken());
            sumOfBerryIndexes += bowl;
            bowls[i] = sumOfBerryIndexes;
        }

        tokenizer = new StringTokenizer(reader.readLine());
        int numOfBerries = Integer.parseInt(tokenizer.nextToken());
        long[] strawberries = new long[numOfBerries];

        tokenizer = new StringTokenizer(reader.readLine());
        for(int i = 0; i < numOfBerries; i++){
            long strawberry = Long.parseLong(tokenizer.nextToken());
            strawberries[i] = search(bowls, strawberry);
        }

        for(long l: strawberries){
            writer.println(l);
        }

        reader.close();
        writer.close();
    }
}
