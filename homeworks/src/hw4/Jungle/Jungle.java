package hw4.Jungle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Jungle {

    static int getPosition(List<Long> trees, Stack<Long> temp){
        Long curr, next;
        int maxSeen = 0, fromPos = 0;

        for(int i = trees.size() - 1; i >= 1; i--){
            curr = trees.remove(i);
            next = trees.get(i - 1);

            if(temp.isEmpty() && curr > next){
                temp.push(curr);

                if( i == 1 && temp. size() >= maxSeen) {
                    maxSeen =temp.size();
                    fromPos = 0;

                }
            }

            else if(!temp.isEmpty()){

                if(curr > next) {
                    temp.push(curr);

                    if(i == 1 && temp.size() >= maxSeen) {
                        maxSeen = temp.size();
                        fromPos = 0;

                    }
                }
                else{
                    if(temp.size() >= maxSeen){
                        maxSeen = temp.size();
                        fromPos = i;

                    }
                    while( !temp.isEmpty() && next >= temp.peek()){
                        temp.pop();

                    }
                }
            }
        }
        return fromPos;
    }


    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int numOfTrees = Integer.parseInt(tokenizer.nextToken());
        tokenizer = new StringTokenizer(reader.readLine());

        List<Long> trees = new ArrayList<>();
        Stack<Long> temp = new Stack<>();

        for(int i = 0; i < numOfTrees; i++){
            trees.add(Long.parseLong(tokenizer.nextToken()));
        }

        writer.print(getPosition(trees, temp));

        reader.close();
        writer.close();
    }
}
