/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;


import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author colli
 */
public class Main {
    
    private ClientAPI clientAPI;
    
    public static void main(String[] args) throws Exception {
        //create new clientAPI instance
        Main main = new Main();
        main.clientAPI = new ClientAPI();
        while(true) {
            int type = main.acceptInputType();
            main.acceptInput(type);
            main.displayResponse();
        }
        
    }
    
    private int acceptInputType() {
        //Display message asking for user input
        System.out.println("What would you like to do?");
        System.out.println("Press 0 to create a new user profile.");
        System.out.println("Press 1 to delete a users profile.");
        System.out.println("Press 2 to update your password.");

        //Get user input
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();
        return Integer.parseInt(userInput);
    }
    
    public void acceptInput(int inputType) {
        try {
            String userInput1 = "";
            String userInput2 = "";
            String userInput3 = "";
            Scanner input = new Scanner(System.in);
            switch(inputType)
            {
                case 0:
                    System.out.print("Please enter a new user name...");
                    userInput1 = input.nextLine();
                    System.out.print("Please enter a new password...");
                    userInput2 = input.nextLine();
                    clientAPI.createUser(userInput1, userInput2);
                    break;
                case 1:
                    System.out.print("Please enter of the name of the user you wish to delete...");
                    userInput1 = input.nextLine();
                    System.out.print("Please enter your password to continue with delete...");
                    userInput2 = input.nextLine();
                    clientAPI.deleteUser(userInput1, userInput2);
                    break;
                case 2:
                    System.out.print("Please enter your username...");
                    userInput1 = input.nextLine();
                    System.out.print("Please enter your old password...");
                    userInput2 = input.nextLine();
                    System.out.print("Enter a new password...");
                    userInput2 = input.nextLine();
                    clientAPI.updateUser(userInput1, userInput2, userInput3);
                    break;
                default:                
                    System.out.println("Incorrect input.");
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void displayResponse() {
        
    }
}
