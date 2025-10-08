import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

public class Main {
    static EncoderMachine encoder = new EncoderMachine();
    static Frame frame = new Frame();
    public static void main(String[] args) {

        frame.menu();
    }
    public static void login(){
        frame.login("admin",encoder.decode("key.txt", "password.txt"));
    }
    public static void reset(){
        frame.menu();
    }
    public  static void signUp(){
        frame.signUp();
    }
    public static void newLoginInfo(String username, String password){
        String usernameFilePath = "username.txt";
        try {

            FileWriter usernameWritter = new FileWriter(usernameFilePath);
            usernameWritter.write(username);
            usernameWritter.close();
            encoder.encode(password);

        }
        catch (IOException e){
            System.out.println("File not found");
        }
    }
}