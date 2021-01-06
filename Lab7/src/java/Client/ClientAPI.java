/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;
/**
 *
 * @author colli
 */
public class ClientAPI {
    
    public ClientAPI(){
        
    }
    
    public void createUser(String userName, String password) throws MalformedURLException, IOException {
        String user = userName+" "+password;

        //Create a new String with the website address to connect to
        String query = "http://localhost:8080/WorkingWithJAX-RS/webresources/Account/createUser";
        
        //Create a new URL object equal to the query above
        URL url = new URL(query);

        //Create a connection to the URL
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        //Set the request method to POST
        connection.setRequestMethod("PUT");

        //Set the request property
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");

        //Allow connection to output
        connection.setDoOutput(true);
        DataOutputStream outputstream = new DataOutputStream(connection.getOutputStream());
        outputstream.writeBytes(user);

        //Create a new integer and set it equal to the response code
        int responseCode = connection.getResponseCode();

        //Print out the response code received
        System.out.println("PUT Response Code: " + responseCode);

        //See if a valid (200) response code was received
        if (responseCode == 200) {
            //if a valid response code was received, print out the response
            String response = getResponse(connection);
            System.out.println("PUT Response Message: " + "OK");
        } else {
            //if an invalid response code was received, print out the response code
            System.out.println("Bad Response Code: " + responseCode);
        }
    }
    
    public void deleteUser(String userName, String password) throws MalformedURLException, IOException {
        String user = userName+" "+password;
        //Create a new String with the website address to connect to
        String query = "http://localhost:8080/WorkingWithJAX-RS/webresources/Account/deleteUser";
        //Create a new URL object equal to the query above
        URL url = new URL(query);

        //Create a connection to the URL
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        //Set the request method to POST
        connection.setRequestMethod("DELETE");

        //Set the request property
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");

        //Allow connection to output
        connection.setDoOutput(true);
        DataOutputStream outputstream = new DataOutputStream(connection.getOutputStream());
        outputstream.writeBytes(user);
        //Create a new integer and set it equal to the response code
        int responseCode = connection.getResponseCode();

        //Print out the response code received
        System.out.println("DELETE Response Code: " + responseCode);

        //See if a valid (200) response code was received
        if (responseCode == 200) {
            //if a valid response code was received, print out the response
            String response = getResponse(connection);
            System.out.println("DELETE Response Message: " + "OK");
        } else {
            //if an invalid response code was received, print out the response code
            System.out.println("Bad Response Code: " + responseCode);
        }
    }
    
    public void updateUser(String userName, String oldPassword, String newPassword) throws MalformedURLException, ProtocolException, IOException {
        String userUpdate = userName+" "+oldPassword+" "+newPassword;
        //Create a new String with the website address to connect to
        String query = "http://localhost:8080/WorkingWithJAX-RS/webresources/Account/updateUser";
        //Create a new URL object equal to the query above
        URL url = new URL(query);

        //Create a connection to the URL
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        //Set the request method to POST
        connection.setRequestMethod("POST");
        //Set the request property
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");

        //Allow connection to output
        connection.setDoOutput(true);
        DataOutputStream outputstream = new DataOutputStream(connection.getOutputStream());
        outputstream.writeBytes(userUpdate);
        //Create a new integer and set it equal to the response code
        int responseCode = connection.getResponseCode();

        //Print out the response code received
        System.out.println("POST Response Code: " + responseCode);

        //See if a valid (200) response code was received
        if (responseCode == 200) {
            //if a valid response code was received, print out the response
            String response = getResponse(connection);
            System.out.println("POST Response Message: " + "OK");
        } else {
            //if an invalid response code was received, print out the response code
            System.out.println("Bad Response Code: " + responseCode);
        }
        
    }
    
    //Method to handle responses

    private String getResponse(HttpURLConnection connection) {
        //Try statement with creation of buffered reader
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));) {
            //New String object to handle the input
            String inputLine;

            //New StringBuilder object to create the entire response
            StringBuilder response = new StringBuilder();

            //Continue to run loop while buffered reader has data. Set inputLine equal to the buffered reader line
            while ((inputLine = br.readLine()) != null) {
                //Add the inputLine to the response
                response.append(inputLine);
            }

            //Close the buffered reader after done reading
            br.close();

            //Return the response
            return response.toString();
        } catch (IOException ex) {
            //Display an error if caught
            System.out.println("Error! IO Exception Error!");
        }

        //This return statement is if nothing is read from the buffered reader
        return "";
    }
    
}
