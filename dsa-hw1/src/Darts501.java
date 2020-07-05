import java.util.Scanner;

public class Darts501 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int pointsToScore = scanner.nextInt();
        int[] possibleScores = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,25,
                2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40,50,
                3,6,9,12,15,18,21,24,27,30,33,36,39,42,45,48,51,54,57,60};
        int solutions = 0;

        //one double
        for(int i = 22; i <= 42; i++){
            if(possibleScores[i] == pointsToScore){
                solutions++;
            }
        }

        //(1x,2x), (2x,2x), (3x,2x)
        for(int i = 0; i < 63; i++){

            for(int j = 22; j <= 42; j++){

                if(possibleScores[i] + possibleScores[j] == pointsToScore){
                    solutions++;
                }
            }
        }

        /*(x1,x1,x2), (x2,x2,x2), (x3,x3,x2),
        (x1,x2,x2), (x1,x3,x2), (x2,x3,x2)
        (x2,x1,x2), (x3,x1,x2), (x3,x2,x2)*/
        for(int i = 0; i <= 62; i++){

            for(int j = 0; j <= 62; j++){

                for(int k = 22; k <= 42; k++){ //x2

                    if(possibleScores[i] + possibleScores[j] + possibleScores[k] == pointsToScore){
                        solutions++;
                    }
                }
            }
        }
        System.out.println(solutions);

    }
}
