package hw7.GrandHotel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class GrandHotel {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        int totalRooms = Integer.parseInt(reader.readLine());
        long[] keysTaken = new long[totalRooms -1];

        tokenizer = new StringTokenizer(reader.readLine());
        for(int i = 0; i < totalRooms - 1; i++){
            keysTaken[i] = Long.parseLong(tokenizer.nextToken());
        }

        long[] keysNeeded = new long[totalRooms -1];

        tokenizer = new StringTokenizer(reader.readLine());
        for(int i = 0; i < totalRooms -1; i++){
            keysNeeded[i] = Long.parseLong(tokenizer.nextToken());
        }

        HashMap<Long, Long> keysOwned = new HashMap<>();
        long result = 0;

        for(int i = 0; i < totalRooms - 1; i++){

            long keyTaken = keysTaken[i];

            if(keysOwned.isEmpty() ||  !keysOwned.containsKey(keyTaken)){
                keysOwned.put(keyTaken, 1L);

            } else {
                long oldVal = keysOwned.get(keyTaken);
                keysOwned.replace(keyTaken, oldVal + 1);

            }


            long keyNeededForNextDoor = keysNeeded[i];

            if(keysOwned.containsKey(keyNeededForNextDoor) && keysOwned.get(keyNeededForNextDoor) >= 1){
                long oldValue = keysOwned.get(keyNeededForNextDoor);
                keysOwned.replace(keyNeededForNextDoor, oldValue - 1);

            } else result = result + 1;
        }

        System.out.print(result);
        reader.close();
    }
}