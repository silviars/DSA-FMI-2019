package hw1.CloningSocks;

import java.util.Scanner;

public class CloningSocks {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int requests = scanner.nextInt();

        for (int i = 0; i < requests; i++) {
            int requestCopies = scanner.nextInt();
            int requestNormal = scanner.nextInt();

            int copiesToDo = requestCopies - (requestNormal - 1);

            if(requestCopies > 0 && requestNormal == 1){
                System.out.println("no");
            }
            else if (copiesToDo % 2 == 0  && copiesToDo >= 0 && requestCopies > -1 && requestNormal > 0) {
                System.out.println("yes");
            } else System.out.println("no");
        }
    }
}
