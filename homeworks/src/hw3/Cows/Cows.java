package hw3.Cows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Cows {

    static boolean isPossible(long[] arr, int cows, long mid){
        int placedCows = 1;
        long currPosition = arr[0];
        for(long e: arr){
            if(e - currPosition >= mid){
                placedCows++;
                currPosition = e;

                if(placedCows == cows){
                    return true;
                }
            }
        }
        return false;
    }

    static long search(long[] arr, int cows){
        Arrays.sort(arr);
        long low = 0, high = arr[arr.length - 1], distance = 0;

        while(low <= high) {
            long mid = (low + high) / 2;

            if (!isPossible(arr, cows, mid))
                high = mid - 1;
            else {
                distance = Math.max(distance, mid);
                low = mid + 1;
            }
        }
        return distance;
    }
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int numOfSheds = Integer.parseInt(tokenizer.nextToken());
        int numOfCows = Integer.parseInt(tokenizer.nextToken());

        tokenizer = new StringTokenizer(reader.readLine());
        long[] coordinates = new long[numOfSheds];

        for(int i = 0; i < numOfSheds; i++){
            long shed = Long.parseLong(tokenizer.nextToken());
            coordinates[i] = shed;
        }

        writer.print(search(coordinates, numOfCows));

        reader.close();
        writer.close();
    }
}
