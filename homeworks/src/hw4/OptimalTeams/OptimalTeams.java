package hw4.OptimalTeams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class OptimalTeams {
    static long[] getOptimalTeams(LinkedList<Long> students, long extraStudents, long numOgStudents){
        LinkedList<Long> cpy;
        cpy = (LinkedList<Long>) students.clone();

        long[] result = new long[(int) numOgStudents];
        long teamNumber = 1;

        while (students.size() > 0){
            Long firstToPick = Collections.max(students);
            result[cpy.indexOf(firstToPick)] = teamNumber;
            int i, j;

            //add the extra students
            for(long counter = extraStudents; counter != 0; counter--) {
                i = students.indexOf(firstToPick) + 1;
                j = students.indexOf(firstToPick) - 1;

                if (i < students.size()) {
                    result[cpy.indexOf(students.get(i))] = teamNumber;
                    students.remove(i);

                }
                if (j >= 0) {
                    result[cpy.indexOf(students.get(j))] = teamNumber;
                    students.remove(j);

                }
            }

            students.remove(firstToPick);
            teamNumber = (teamNumber == 1) ? 2 : 1;
        }

        return result;
    }

    static void printTeams(long[] resultTeams, PrintWriter writer){
        for(Long team: resultTeams){
            writer.print(team);

        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        long numOfStudents = Integer.parseInt(tokenizer.nextToken());
        long extraStudents = Long.parseLong(tokenizer.nextToken());
        tokenizer = new StringTokenizer(reader.readLine());

        LinkedList<Long> students = new LinkedList<>();

        for(int i = 0; i < numOfStudents; i++){
            students.add(Long.parseLong(tokenizer.nextToken()));
        }

        long[] resultTeams = getOptimalTeams(students, extraStudents, numOfStudents);
        printTeams(resultTeams, writer);

        reader.close();
        writer.close();
    }
}

