import javax.swing.*;
import java.awt.event.*;
import java.util.Objects;
import java.lang.Thread;

public class Frame extends JFrame implements ActionListener{
    //Sets up all the elements
    //Doesn't give the a value yet (except for JLabel
    //Different ok buttons for login and signup because they do different thing
    JButton okSignUp;
    JButton okLogin;
    //I don't need two cancel buttons because they do the same thing, go back to menu
    JButton cancel;
    JButton loginButton;
    JButton signUpButton;
    JButton exitButton;
    JTextField usernameInput;
    JTextField passwordInput;
    String username;
    String password;
    JPanel pane;
    JLabel usernameLabel = new JLabel("Username: ");
    JLabel passwordLabel = new JLabel("Password");
    JLabel loginSuccess = new JLabel("Login Successful");
    JLabel signUpSuccess = new JLabel("Account created");
    JLabel loginFail = new JLabel("Password or username incorrect");

    public Frame() {
        //Login is the name of the window
        super("Login");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Sets up the username and password
        //sets the windows to visible, meaning it is displayed
        setVisible(true);
    }
    public void menu(){
        try {
            pane.removeAll();
        }
        catch (NullPointerException e){
        }
        pane = new JPanel();
        loginButton = new JButton("Login");
        signUpButton = new JButton("Sign Up");
        exitButton = new JButton("Exit");
        pane.add(loginButton);
        loginButton.addActionListener(this);
        pane.add(signUpButton);
        signUpButton.addActionListener(this);
        pane.add(exitButton);
        exitButton.addActionListener(this);

        add(pane);
        SwingUtilities.updateComponentTreeUI(pane);

    }

    public void login(String usernamePassed, String passwordPassed){
        pane.removeAll();
        username = usernamePassed;
        password = passwordPassed;
        //JPanel is the area where all of the objects are
        //These are the buttons/ text fields
        okLogin = new JButton("OK");
        okLogin.addActionListener(this);
        cancel = new JButton("CANCEL");
        cancel.addActionListener(this);
        usernameInput = new JTextField(20);
        passwordInput = new JPasswordField(20);
        //The label is the name of the text field

        //Pane.add adds the items to the screen
        pane.add(usernameLabel);
        pane.add(usernameInput);
        pane.add(passwordLabel);
        pane.add(passwordInput);
        pane.add(okLogin);
        pane.add(cancel);
        //Not sure what this does tbh, but i think its important
        add(pane);
        SwingUtilities.updateComponentTreeUI(pane);
        setVisible(true);
    }
    public void signUp(){
        pane.removeAll();
        okSignUp = new JButton("OK");
        okSignUp.addActionListener(this);
        cancel = new JButton("CANCEL");
        cancel.addActionListener(this);
        usernameInput = new JTextField(20);
        passwordInput = new JPasswordField(20);

        pane.add(usernameLabel);
        pane.add(usernameInput);
        pane.add(passwordLabel);
        pane.add(passwordInput);
        pane.add(okSignUp);
        pane.add(cancel);
        add(pane);
        SwingUtilities.updateComponentTreeUI(pane);
        setVisible(true);

    }


    //Overrides the actionPerformed method to do cool things
    @Override
    public void actionPerformed(ActionEvent event){
        Object source = event.getSource();

        if (source == okLogin){

           if ((Objects.equals(usernameInput.getText(), username)) &&(Objects.equals(passwordInput.getText(), password))){
               //If username and password are correct, it calls authSucc method
               authSucc();

           }
           else{
               //If username and password are incorrect, computer says no
               pane.add(loginFail);
               SwingUtilities.updateComponentTreeUI(pane);
           }
        }
        else if (source == cancel) {
            //If cancel is pressed, the program goes back to menu
            Main.reset();
        }
        else if (source == loginButton){
            Main.login();

        }
        else if (source == exitButton){
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
        else if (source == signUpButton){
            Main.signUp();
        }
        else if (source == okSignUp){
            Main.newLoginInfo(usernameInput.getText(), passwordInput.getText());
            signUpped();



        }
    }

    public void authSucc(){
        //Removes all of the elememts
        pane.removeAll();
        //Displays the successful login message
        pane.add(loginSuccess);
        pane.add(exitButton);
        exitButton.addActionListener(this);
        //No clue what add(pane) does tbh
        add(pane);
        //Refreshes the frame
        SwingUtilities.updateComponentTreeUI(pane);
    }
    public void signUpped(){
        pane.removeAll();

        pane.add(signUpSuccess);
        pane.add(exitButton);
        exitButton.addActionListener(this);

        add(pane);

        SwingUtilities.updateComponentTreeUI(pane);
    }

}