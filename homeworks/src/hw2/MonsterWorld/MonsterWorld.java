package hw2.MonsterWorld;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MonsterWorld {

    static void sort(long[] arr, int left, int right){
        if(left < right){
            int mid = (left+right)/2;
            sort(arr, left, mid);
            sort(arr, mid+1, right);
            merge(arr,left, mid, right);

        }
    }
    static void merge(long[] arr, int left, int mid, int right){
        long[] la = Arrays.copyOfRange(arr, left, mid+1);
        long[] ra = Arrays.copyOfRange(arr, mid + 1, right + 1);

        int i =0, j=0, k = left;
        while(i < la.length && j < ra.length){
            if(la[i] <= ra[j]){
                arr[k++] = la[i++];
            } else {
                arr[k++] = ra[j++];
            }
        }

        while(i < la.length){
            arr[k++] = la[i++];
        }
        while(j < ra.length){
            arr[k++] = ra[j++];
        }
    }

    static int removeDuplicates(long[] arr){
        if(arr.length <= 1) return arr.length;

        int size = 0;
        for(int i = 0; i < arr.length - 1; i++)
            if(arr[i] != arr[i+1])
                arr[size++] = arr[i];
        arr[size++] = arr[arr.length - 1];

        return size;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int numOfMonsters = Integer.parseInt(tokenizer.nextToken());
        long offset = Long.parseLong(tokenizer.nextToken());
        tokenizer = new StringTokenizer(reader.readLine());
        long[] monsters = new long[numOfMonsters];
        for(int i =0; i < numOfMonsters; i++){
            monsters[i] = Long.parseLong(tokenizer.nextToken());
        }

        long minShots = 0;
        long sumOfPositions = 0;
        sort(monsters, 0, numOfMonsters -1);
        numOfMonsters = removeDuplicates(monsters);
        for(long i =0; i < numOfMonsters; i++){
            sumOfPositions += monsters[(int)i];
        }
        long lastElIndex = numOfMonsters -1;


        while (sumOfPositions > 0) {
            for(long j = 0; j < lastElIndex; j++) {
                long temp = monsters[(int)j];
                monsters[(int)j] -= offset;

                if (monsters[(int)j] < 0) {
                    monsters[(int)j] = 0;
                    sumOfPositions -= temp;
                } else {
                    sumOfPositions -= offset;
                }
            }
            sumOfPositions -= monsters[(int)lastElIndex];
            lastElIndex--;
            minShots++;
        }
        writer.print(minShots);

        reader.close();
        writer.close();
    }
}
