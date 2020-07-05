package hw5.LosBinares;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class Address{
    int streetNumber;
    int distanceFromMaazon;
    Address left, right;

    Address(int streetNumber, int distanceFromMaazon){
        this.streetNumber = streetNumber;
        this.distanceFromMaazon = distanceFromMaazon;
        this.left = null;
        this.right = null;
    }
}

public class LosBinares {

    Address maazonsOffice;

    LosBinares(){
        this.maazonsOffice = null;
    }

    Address _insertAddress(Address currAddress, int streetNumber, int distanceFromMaazon) {
        if (currAddress == null) {
            return new Address(streetNumber, distanceFromMaazon);

        } else {
            if (streetNumber < currAddress.streetNumber) {
                currAddress.left = _insertAddress(currAddress.left, streetNumber, distanceFromMaazon + 1);

            } else if(streetNumber > currAddress.streetNumber){
                currAddress.right = _insertAddress(currAddress.right, streetNumber, distanceFromMaazon + 1);

            }
        }
        return currAddress;
    }

    int _findDistanceFromMaazons(Address currAddress, int streetNumber){

        if(currAddress != null) {

            if (currAddress.streetNumber == streetNumber) {
                return currAddress.distanceFromMaazon;

            } else if (currAddress.streetNumber > streetNumber) {
                return _findDistanceFromMaazons(currAddress.left, streetNumber);

            } else {
                return _findDistanceFromMaazons(currAddress.right, streetNumber);

            }
        }
        return -1;
    }

    void insertAddress(int streetNumber){
        maazonsOffice =  _insertAddress(maazonsOffice, streetNumber, 0);
    }

    int getDistanceFromMaazons(int streetNumber){
        return _findDistanceFromMaazons(maazonsOffice, streetNumber);
    }

    public static void main(String[] args) throws IOException {
        LosBinares theCityMap = new LosBinares();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int numOfAddresses = Integer.parseInt(tokenizer.nextToken());
        int numOfDeliveryRequests = Integer.parseInt(tokenizer.nextToken());
        tokenizer = new StringTokenizer(reader.readLine());

        for(int i = 0; i < numOfAddresses; i++){
            int currAddress = Integer.parseInt(tokenizer.nextToken());
            theCityMap.insertAddress(currAddress);
        }

        tokenizer = new StringTokenizer(reader.readLine());
        for(int i = 0; i < numOfDeliveryRequests; i++){
            int currRequest = Integer.parseInt(tokenizer.nextToken());
            writer.println(theCityMap.getDistanceFromMaazons(currRequest));
        }

        reader.close();
        writer.close();
    }
}