package hw6.Elitism;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Elitism {
    private static PriorityQueue<Long> min;
    private static PriorityQueue<Long> max;

    public Elitism(){
        min = new PriorityQueue<>();
        max = new PriorityQueue<>(Collections.reverseOrder());
    }

    public void addCitizen(long newCitizensWealth){
        min.offer(newCitizensWealth);
        max.offer(min.poll());

        if(min.size() < max.size()){
            min.offer(max.poll());
        }
    }

    public double findMedian(){
        if(min.size() > max.size()){
            return min.peek();
        } else {
            return (min.peek() + max.peek())/2.0;
        }
    }

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        StringBuilder stringBuilder = new StringBuilder();
        Elitism elitism = new Elitism();
        long newCitizensCount = scanner.nextLong();

        for(long i = 0; i < newCitizensCount; i++){
            long newCitizenWealth = scanner.nextLong();
            elitism.addCitizen(newCitizenWealth);
            stringBuilder.append(elitism.findMedian() + "\n");

        }

        System.out.print(stringBuilder.toString());
    }
}