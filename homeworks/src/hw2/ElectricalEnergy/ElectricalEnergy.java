package hw2.ElectricalEnergy;

import java.util.Arrays;
import java.util.Scanner;

public class ElectricalEnergy {
    static long energy(long[] arr, long left, long right){
        long energy = 0;
        if(left < right){
            long mid = (left+right)/2;
            energy += energy(arr, left, mid);
            energy += energy(arr, mid+1, right);
            energy += getEnergy(arr,left, mid, right);

        }
        return energy;
    }
    static long getEnergy(long[] arr, long left, long mid, long right){
        long[] la = Arrays.copyOfRange(arr, (int)left, (int)mid+1);
        long[] ra = Arrays.copyOfRange(arr, (int)mid + 1, (int)right + 1);


        long i =0, j=0, k = left, energy = 0;
        while(i < la.length && j < ra.length){
            if(la[(int)i] <= ra[(int)j]){
                arr[(int)k++] = la[(int)i++];
            } else {
                arr[(int)k++] = ra[(int)j++];
                energy += mid + 1 - left - i;
            }
        }

        while(i < la.length){
            arr[(int)k++] = la[(int)i++];
        }
        while(j < ra.length){
            arr[(int)k++] = ra[(int)j++];
        }
        return energy;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long count = sc.nextLong();
        long[] arr = new long[(int)count];
        for(long i = 0; i < count; i++){
            long el = sc.nextLong();
            arr[(int)i] = el;
        }
        System.out.println(energy(arr, 0, count - 1));
    }
}
