package hw5.StudentQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.Vector;

class Student {
    String name;
    int group;
    int inTime;
    int outTime;

    Student(String name, int group, int inTime){
        this.name = name;
        this.group = group;
        this.inTime = inTime;
        this.outTime = 0;
    }
}

public class StQueue {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int numOfStudents = Integer.parseInt(tokenizer.nextToken());
        int numOfGroups = Integer.parseInt(tokenizer.nextToken());

        String name;
        int group;
        Queue<Student>[] groups = new LinkedList[numOfGroups + 1];
        Queue<Student> inputStudents = new LinkedList<>();
        Vector<Student> ouputStudents = new Vector<>();
        Queue<Integer> groupNums = new LinkedList<>();

        for(int i = 0; i < numOfGroups + 1; i++){
            groups[i] = new LinkedList<>();
        }

        for(int inTime = 0; inTime < numOfStudents; inTime++){
            tokenizer = new StringTokenizer(reader.readLine());
            name = tokenizer.nextToken();
            group = Integer.parseInt(tokenizer.nextToken());
            inputStudents.add(new Student(name, group, inTime));

        }

        for(int i = 0; i < 2*numOfStudents + 1; i++){

            if(i < numOfStudents){
                Student currStudent = inputStudents.peek();
                inputStudents.poll();

                if(groupNums.isEmpty() || groups[currStudent.group].isEmpty()){
                    groupNums.add(currStudent.group);

                }

                groups[currStudent.group].add(currStudent);
            }

            if(i > 0 && i%2 == 0){

                int currGroup = groupNums.peek();
                Student currStudent = groups[currGroup].peek();
                ouputStudents.add(currStudent);
                groups[currGroup].poll();

                if(groups[currGroup].isEmpty()){
                    groupNums.poll();

                }

                currStudent.outTime = i;

            }
        }

        for(Student currToLeave: ouputStudents){
            writer.println(currToLeave.name + " " + currToLeave.inTime + " " + currToLeave.outTime);
        }

        reader.close();
        writer.close();
    }
}
