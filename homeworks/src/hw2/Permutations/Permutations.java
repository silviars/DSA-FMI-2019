package hw2.Permutations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Permutations {
    private static void sort(char[] arr){
        char[] output = new char[arr.length];
        int[] countArr = new int[256];
        for(char c: arr){
            ++countArr[c];
        }
        for(int i =1; i < 256; ++i){
            countArr[i] += countArr[i-1];
        }
        for(int i = arr.length - 1; i >= 0; i--){
            output[countArr[arr[i]] - 1] = arr[i];
            //--countArr[arr[i]];
        }
        for(int i = 0; i < arr.length; ++i){
            arr[i] = output[i];
        }

    }

    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            long size = Long.parseLong(in.readLine());
            String first = in.readLine();
            String second = in.readLine();

            char[] firstArr = first.toCharArray();
            char[] secondArr = second.toCharArray();

            sort(firstArr);
            sort(secondArr);

            if(size == 0 && first.length() == 0 && second.length()== 0){
                System.out.println("yes");

            } else if(size > 0 && firstArr.length == size && secondArr.length == size) {
                for(int i = 0; i < size; i++){

                    if(firstArr[i] != secondArr[i]){
                        System.out.print("no");
                        break;
                    }
                    if(i == size - 1 && firstArr[i] == secondArr[i]){
                        System.out.print("yes");
                    }
                }
            } else System.out.println("no");

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
