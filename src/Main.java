import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {
    
    static Frame frame = new Frame();
    public static void main(String[] args) {

        frame.menu();
    }
    public static void login() throws NoSuchAlgorithmException {
        String username;
        String password;
        String usernameFilePath = "username.txt";
        String passwordFilePath = "password.txt";
        username = fileRead(usernameFilePath);
        password = fileRead(passwordFilePath);

        System.out.println(password);

        frame.login(username, password);

    }
    public static void reset(){
        frame.menu();
    }
    public  static void signUp(){
        frame.signUp();
    }
    public static void newLoginInfo(String username, String password){
        String usernameFilePath = "username.txt";
        String passwordFilePath = "password.txt";
        try {

            //Not the most effiecent code but it works
            //Creates new FileWriter object
            FileWriter usernameWriter = new FileWriter(usernameFilePath);
            //Writes to file
            usernameWriter.write(username);
            //Closes file
            usernameWriter.close();

            FileWriter passwordWriter = new FileWriter(passwordFilePath);
            passwordWriter.write(password);
            passwordWriter.close();

        }
        catch (IOException e){
            System.out.println("File not found");
        }


        }
        public static String fileRead(String filePath){

            String fileContents;
            try(BufferedReader fileReader = new BufferedReader(new FileReader(filePath))){
                fileContents = fileReader.readLine();
            }
            catch (IOException e){
                System.out.println("File not found");
                fileContents = "";
            }

            return fileContents;



    }
}