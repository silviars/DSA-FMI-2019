package hw3.BalloonsAndCandies;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class BalloonsAndCandies {

    static long searchMinMaximun(long maxPossSolution, long[] arr1, long[] arr2, long ownedBalloons){
        long low = 0, high = maxPossSolution, lastPoss = high;
        while(low <= high){
            long mid = (low + high)/2;
            if(lastPoss - low == 1) return lastPoss;
            if(isPossible(mid, arr1, arr2, ownedBalloons)) {
                lastPoss = mid;
                high = mid;
            }
            else {
                low = mid;
            }
        }
        return -1;
    }


    static boolean isPossible(long mid, long[] arr1, long[] arr2, long ownedBalloons){
        long temp = 0;
        for(int i = 0; i < arr1.length; i++){
            if(arr1[i]*arr2[i] >= mid){
                temp += (arr1[i]*arr2[i] - mid)/arr2[i];
                if((arr1[i]*arr2[i] - mid) % arr2[i] != 0){
                    temp++;
                }
            }
        }
        return temp <= ownedBalloons;
    }


    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        long days = Long.parseLong(tokenizer.nextToken());
        long ownedBalloons = Long.parseLong(tokenizer.nextToken());

        long[] balloonsWanted = new long[(int)days];
        long[] candiesWanted = new long[(int)days];
        long maxPosSolution = 0;

        tokenizer = new StringTokenizer(reader.readLine());
        for(int i = 0; i < days; i++){
            balloonsWanted[i] = Long.parseLong(tokenizer.nextToken());
        }

        tokenizer = new StringTokenizer(reader.readLine());
        for(int i = 0; i < days; i++){
            candiesWanted[i] = Long.parseLong(tokenizer.nextToken());
            if(candiesWanted[i]*balloonsWanted[i] > maxPosSolution){
                maxPosSolution = candiesWanted[i]*balloonsWanted[i];
            }
        }

        long result = searchMinMaximun(maxPosSolution, balloonsWanted, candiesWanted, ownedBalloons);
        writer.print(result);

        reader.close();
        writer.close();
    }
}
