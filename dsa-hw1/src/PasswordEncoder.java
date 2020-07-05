import java.util.Scanner;

public class PasswordEncoder {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String password = scanner.next();
        StringBuilder encodedPass = new StringBuilder();
        short frequencyCounter = 1;

        for(int i = 0; i < password.length(); i++){
            if(password.charAt(i) >= 'A' && password.charAt(i) <= 'Z'){
                if(i == password.length() - 1){
                    encodedPass.append(frequencyCounter  + String.valueOf(password.charAt(i)));
                }
                else if(password.charAt(i) != password.charAt(i+1)){
                    encodedPass.append(frequencyCounter + String.valueOf(password.charAt(i)));
                    frequencyCounter = 1;
                }
                else{
                    frequencyCounter++;
                }
            } else throw new IllegalArgumentException("Wrong input");
        }
        System.out.println(encodedPass);
    }
}

