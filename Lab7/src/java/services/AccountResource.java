/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import model.User;

/**
 * REST Web Service
 *
 * @author colli
 */
@Path("Account")
public class AccountResource {

    @Context
    private UriInfo context;

    public AccountResource() {
        
    }

    @GET
    @Produces("text/html")
    public String getUsers() throws FileNotFoundException, IOException, ClassNotFoundException {
        
        String userString = "";
        ArrayList<User> users = new ArrayList<User>();
        users = getUserList();
        for(int i = 0; i < users.size(); i ++)
        {
            userString = userString + users.get(i).getUserName();
            if(i < users.size())
            {
                userString = userString + ", ";
            }
        }
        return userString;
    }
    
    
    @DELETE
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("{id}")
    
    public String deleteUser(@PathParam("id") String userId, String password) throws IOException, FileNotFoundException, ClassNotFoundException
    {
                
        String response = "";
        /*
        String[] parts = userNameAndPassword.split(" ");
        String userName = parts[0];
        String password = parts[1];
        */
        
        ArrayList<User> users = getUserList();
        System.out.println(users.toString());
        
        for (User user : users) {
            if (user.getUserName().equals(userId)) {
                users.remove(user);
                response = "User " + userId + " was deleted";
                break;
            }
            else {
                response = "User " + userId + " does not exist!"; 
            }
        }
        
        System.out.println(users.toString());
        
        File f = new File("users.ser");
        System.out.println(f.getAbsolutePath());

        FileOutputStream fout = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        
        for(int i = 0; i < users.size(); i++) {
            oos.writeObject(users.get(i));
        }

        fout.close();
        oos.close();
        
        return response; 
    }
    
    @PUT
    @Path("createUser")
    @Consumes("text/html")
    public void createUser(String userNameAndPassword) throws FileNotFoundException, IOException, ClassNotFoundException 
    {
        ArrayList<User> users = getUserList();

        String[] parts = userNameAndPassword.split(" ");
        String part1 = parts[0]; // user
        String part2 = parts[1]; // password
        System.out.println(part1 + " " + part2);

        User user = new User(part1, part2);
        users.add(user);

        File f = new File("users.ser");
        System.out.println(f.getAbsolutePath());

        FileOutputStream fout = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        
        for(int i = 0; i < users.size(); i++) {
            oos.writeObject(users.get(i));
        }

        fout.close();
        oos.close();

    }

    @POST
    @Path("updateUser")
    @Consumes("text/html")
    public void updateUser(String userNamePasswordAndNewPassword) throws IOException, FileNotFoundException, ClassNotFoundException {
        ArrayList<User> users = getUserList();
        
        String[] parts = userNamePasswordAndNewPassword.split(" ");
        String userName = parts[0];     // user
        String password = parts[1];     // password
        String newPassword = parts[2];  // new password
        System.out.println(userName + " " + password + " " + newPassword);
        
        for (User user : users) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                user.setPassword(newPassword);
                System.out.println(user.getUserName()+" "+user.getPassword());
            }
        }
        
        File f = new File("users.ser");
        System.out.println(f.getAbsolutePath());

        FileOutputStream fout = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        
        for(int i = 0; i < users.size(); i++) {
            oos.writeObject(users.get(i));
        }

        fout.close();
        oos.close();
    }
    
    private ArrayList<User> getUserList() throws FileNotFoundException, IOException, ClassNotFoundException
    {
        ArrayList<User> users = new ArrayList<User>();
        
        try {
            FileInputStream fis = new FileInputStream("users.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);

            try {
                while(true) {
                    User user = (User) ois.readObject();
                    users.add(user);
                }
            } catch (Exception e) {
                ois.close();
                return users;
            }
        } catch (FileNotFoundException fnfe) {
            File f = new File("users.ser");
            return users;
        }
    }  
    
    
}
