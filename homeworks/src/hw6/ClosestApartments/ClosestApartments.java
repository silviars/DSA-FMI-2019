package hw6.ClosestApartments;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

class Apartment{
    long x;
    long y;
    double distanceFromFmi;

    Apartment(long x, long y, double distanceFromFmi){
        this.x = x;
        this.y = y;
        this.distanceFromFmi = distanceFromFmi;
    }
}
class DistanceComparator implements Comparator<Apartment> {
    @Override
    public int compare(Apartment a1, Apartment a2){

        int d1 = (int) a1.distanceFromFmi;
        int d2 = (int) a2.distanceFromFmi;

        if(d1 != d2){
            return d2 - d1;
        }
        return 1;

    }
}

public class ClosestApartments {

    static double findDistance(long x, long y){
        return (0 - x)*(0- x) + (0 - y)*(0 - y);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        OutputStream out = new BufferedOutputStream(System.out);
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int placesFound = Integer.parseInt(tokenizer.nextToken());
        int placesToSee = Integer.parseInt(tokenizer.nextToken());

        PriorityQueue<Apartment> nearestToFMI = new PriorityQueue<Apartment>(placesToSee + 1, new DistanceComparator());
        long x, y;
        double distance;

        for(int i = 0; i < placesFound; i++){

            tokenizer = new StringTokenizer(reader.readLine());
            x = Long.parseLong(tokenizer.nextToken());
            y = Long.parseLong(tokenizer.nextToken());

            distance = findDistance(x,y);

            Apartment currApartment = new Apartment(x,y, distance);
            nearestToFMI.add(currApartment);

            if(nearestToFMI.size() == placesToSee + 1){
                nearestToFMI.poll();

            }

        }

        Stack<Apartment> temp = new Stack<>();

        while(!nearestToFMI.isEmpty()){
            temp.push(nearestToFMI.poll());

        }

        for(long i = 1; i <= placesToSee; i++){
            Apartment currApartment = temp.pop();
            out.write((currApartment.x + " " + currApartment.y + "\n").getBytes());

        }

        reader.close();
        out.close();
    }
}