package hw1.VodniZapasi;

import java.util.Scanner;

public class VodniZapasi {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int numOfWalls = scanner.nextInt();
        int[] walls = new int[numOfWalls];
        int currentResult = 0;
        int finalResult = 0;

        for(int i = 0; i < numOfWalls; i++){
            int wall = scanner.nextInt();
            walls[i] = wall;
        }

        for(int i = 0; i < walls.length - 1; i++){
            for(int j = 0; j < walls.length; j++){

                if(walls[i] == walls[j] || (walls[j] > walls[i])){
                    currentResult = (j-i)*walls[i];
                } else if (walls[i] > walls[j]){
                    currentResult = (j-i)*walls[j];
                }

                if(currentResult > finalResult){
                    finalResult = currentResult;
                }
            }
        }
        System.out.println(finalResult);
    }
}
