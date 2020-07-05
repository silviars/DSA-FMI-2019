package hw5.RottenFromTheCore;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Apple{
    int x, y, daysLeft;

    Apple(int x, int y, int daysLeft){
        this.x = x;
        this.y = y;
        this.daysLeft = daysLeft;
    }
}

public class RottenFromTheCore {

    static int getFreshApples(boolean[][] apples, Apple firstApple, Apple secondApple){

        Queue<Apple> queue = new LinkedList<>();
        queue.add(firstApple);

        if(secondApple.x >= 0) queue.add(secondApple);

        int rottenApples = queue.size();
        Apple curr = queue.peek();

        while(curr.daysLeft > 0){
            queue.poll();

            if(curr.x - 1 < apples.length && curr.y < apples[0].length
                    && curr.x - 1 >= 0 && curr.y >= 0){

                if(!apples[curr.x - 1][curr.y]) {
                    queue.add(new Apple(curr.x - 1, curr.y, curr.daysLeft -1));
                    apples[curr.x - 1][curr.y] = true;
                    rottenApples++;

                }
            }

            if(curr.x + 1 < apples.length && curr.y < apples[0].length
                    && curr.x + 1 >= 0 && curr.y >= 0){

                if(!apples[curr.x + 1][curr.y]) {
                    queue.add(new Apple(curr.x + 1, curr.y,curr.daysLeft -1 ));
                    apples[curr.x + 1][curr.y] = true;
                    rottenApples++;

                }
            }

            if(curr.x < apples.length && curr.y - 1 < apples[0].length
                    && curr.x >= 0 && curr.y - 1 >= 0){

                if(!apples[curr.x][curr.y - 1]) {
                    queue.add(new Apple(curr.x, curr.y - 1, curr.daysLeft -1));
                    apples[curr.x][curr.y - 1] = true;
                    rottenApples++;

                }
            }
            if(curr.x < apples.length && curr.y + 1 < apples[0].length
                    && curr.x >= 0 && curr.y + 1 >= 0){

                if(!apples[curr.x][curr.y + 1]) {
                    queue.add(new Apple(curr.x, curr.y + 1, curr.daysLeft -1));
                    apples[curr.x][curr.y + 1] = true;
                    rottenApples++;

                }
            }
            curr = queue.peek();
        }

        return apples.length*apples[0].length - rottenApples;
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int days = scanner.nextInt();

        boolean[][] apples = new boolean[x][y];

        int firstRottenAppleX = scanner.nextInt();
        int firstRottenAppleY = scanner.nextInt();
        Apple firstApple = new Apple(firstRottenAppleX -1, firstRottenAppleY -1, days);
        apples[firstRottenAppleX-1][firstRottenAppleY - 1] = true;

        boolean thereIsSecondApple = false;
        int secondRottenAppleX = -1;
        int secondRottenAppleY = -1;

        if(scanner.hasNextInt()) {
            thereIsSecondApple = true;
            secondRottenAppleX = scanner.nextInt();
            secondRottenAppleY = scanner.nextInt();
        }
        if(thereIsSecondApple){
            apples[secondRottenAppleX - 1][secondRottenAppleY - 1] = true;

        }

        Apple secondApple = new Apple(secondRottenAppleX -1, secondRottenAppleY-1, days);

        int freshApples = getFreshApples(apples, firstApple, secondApple);
        System.out.println(freshApples);
    }
}
