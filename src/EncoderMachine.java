import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;

public class EncoderMachine{
    public void encode(String password){
        //Encodes the username and password, along with a file for the key
        char [] passwordArr = password.toCharArray();
        int [] encodedPassword = new int[password.length()];
        String keyFilePath = "key.txt";
        String passwordFilePath = "password.txt";
        //Key part is the interger which is used for the encoding/ decoding
        int [] key = new int[passwordArr.length];
        int keyPart;
        for (int i = 0; i < passwordArr.length; i++) {
            //For loop for each char in password
            keyPart = (int) (Math.random() * 26);
            //Converts the char into ascii, adds key part, then converts it back into char
            encodedPassword[i] = (int) Math.pow((((int) passwordArr[i]) + keyPart), 2);
            key[i] = keyPart;

        }
        try{
            //Opens up the files to read too
            FileWriter keyOutput = new FileWriter(keyFilePath);
            FileWriter passwordOutput = new FileWriter(passwordFilePath);
            for (int i = 0; i<encodedPassword.length; i ++){
                //For each index in the array, write to another line
                //It's easier to work with then putting the whole array on one line of text
                keyOutput.write(key[i] + "\n");
                passwordOutput.write(encodedPassword[i] + "\n");
            }
            //Closes the files
            keyOutput.close();
            passwordOutput.close();
        }
        catch (IOException e){
            System.out.println("File not found");
        }


    }
    public String decode(String keyFilePath, String passwordFilePath) {
        String decodedString;
        char[] passwordArr;
        int[] encodedPassword;
        int[] key;
        ArrayList<Integer> keyArrList = new ArrayList<Integer>();
        //line is a temperey space for a line being read from the file
        String line;
        int len = 0;

        try (BufferedReader keyFileReader = new BufferedReader(new FileReader(keyFilePath))) {
            //Ok fine I'll use array lists
            //Sorry for the code ahead, it is dog shit but i couldn't figure out any other way
            while ((line = keyFileReader.readLine()) != null) {
                len++;
            }

        } catch (IOException e) {
            System.out.println("Key not found");
        }


        encodedPassword = new int[len];
        passwordArr = new char[len];
        key = fileRead(keyFilePath, len);
        encodedPassword = fileRead(passwordFilePath, len);
        //Accually decoding the password now
        for (int i = 0; i < encodedPassword.length;i++){
            //First step is square rooting each number
            //Then take away the number in the key by the corrospoding key value
            passwordArr[i] = (char)(Math.sqrt(encodedPassword[i])-key[i]);
        }
        decodedString = "";
        for (int i = 0; (i < passwordArr.length); i++){
            decodedString += passwordArr[i];
        }
        return decodedString;
    }

    public int[] fileRead(String filename, int length){
        //File contents are read from the file
        int [] fileContents = new int[length];
        String line;

        try (BufferedReader fileReader = new BufferedReader(new FileReader(filename))){
            for (int i = 0; ((line = fileReader.readLine()) != null); i++){
                //Adds each line to our output
                fileContents[i] = Integer.parseInt(line);
            }
        }

        catch (IOException e){
            System.out.println("File not found");
        }

        return fileContents;
    }

}
