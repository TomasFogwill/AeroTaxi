
package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.Persistence;
import java.io.File;
import java.io.IOException;
import models.User;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.Scanner;
import models.City;
import models.Route;



public abstract class Main {

    
 
    
    public static void main(String[] args) throws IOException {
    User user1=new User("Tomas","Fogwill","40129369",24);
    User user2=new User("admin","","0000",24);
    User user3=new User("Pepe","Martinez","38999999",21); 
    Persistence.saveNewUser(user1);
    Persistence.saveNewUser(user2);
    
//File file=new File("Users");
//ObjectMapper mapper=new ObjectMapper();
//ArrayList<User> m=new ArrayList<User>();
//mapper.writeValue(file, m);


    }
    
}
