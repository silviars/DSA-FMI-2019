package hw2.Graphic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Graphic {
    static void swap(long[] a1, long[] a2, int x, int y){
        a1[x] = (a1[x] + a1[y]) - (a1[y] = a1[x]);
        a2[x] = (a2[x] + a2[y]) - (a2[y] = a2[x]);
    }

    static void sort(long[] start,long[] duration, int left, int right){
        if(left < right){
            int pivot = randomizedPartition(start,duration, left, right);
            sort(start, duration, left, pivot - 1);
            sort(start, duration, pivot + 1, right);
        }
    }

    static int randomizedPartition(long[] start, long[]duration, int left, int right){
        int randomPi = (int)Math.floor(Math.random()*(right - left + 1) + left);
        swap(start, duration, right, randomPi);
        return partition(start, duration, left,right);
    }

    static int partition(long[] start, long[] duration, int left, int right){
        long piVal = start[right] + duration[right];
        int piInd = left;

        for(int i = piInd; i < right; i++){
            if(start[i]  + duration[i] < piVal){
                swap(start, duration, i, piInd);
                piInd++;
            }
        }
        swap(start, duration,right, piInd);
        return piInd;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int numOfEvents = Integer.parseInt(tokenizer.nextToken());
        long[] start = new long[numOfEvents];
        long[] duration = new long[numOfEvents];
        long attending = 0;

        for(int i =0; i<numOfEvents; i++){
            tokenizer = new StringTokenizer(reader.readLine());
            start[i] = Long.parseLong(tokenizer.nextToken());
            duration[i] = Long.parseLong(tokenizer.nextToken());
        }
        sort(start, duration, 0, numOfEvents - 1);

        long lastStart = start[0];
        long lastDuration = duration[0];

        for(int i = 0; i < numOfEvents; i++){
            if(lastDuration >= 0 && duration[i] >= 0){
                if(attending == 0) {
                    attending = 1;
                } else if(start[i] > lastStart && lastStart + lastDuration <= start[i]){
                    attending++;
                    lastStart = start[i];
                    lastDuration = duration[i];
                }
            }
        }

        writer.println(attending);

        reader.close();
        writer.close();
    }
}
