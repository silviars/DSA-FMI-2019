package hw1.TakeSDA;

import java.util.Scanner;

public class TakeSDA {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int minResult = scanner.nextInt();
        int students = scanner.nextInt(); //number of students
        int[] results = new int[students];
        double resultsSum = 0; //to find the average result later
        double averageResult;

        for(int i = 0; i < students; i++){
            int result = scanner.nextInt();
            results[i] = result;
            resultsSum += result;
        }

        averageResult = resultsSum/students;
        if(averageResult >= minResult){
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}
