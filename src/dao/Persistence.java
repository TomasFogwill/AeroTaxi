
package dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Aircraft;
import models.BronzeAircraft;
import models.GoldAircraft;
import models.SilverAircraft;
import models.User;

public abstract class Persistence {
//    public static void saveUsers() throws IOException{
//    User user1=new User("Tomas","Fogwill","40129369",24);
//    User user2=new User("admin","","0000",24);
//    User user3=new User("Pepe","Martinez","38999999",21);
//    ArrayList <User> users=new ArrayList<User>();
//    users.add(user1);
//    users.add(user2);
//    users.add(user3);
//    ObjectMapper mapper=new ObjectMapper();
//    mapper.writeValue(new File("s"),users);   
//    }
//    
//    public Aircraft flight1=new BronzeAircraft("Bronze 1",25000, 2, 2500, 900, "A pistones");
//    public Aircraft flight2=new SilverAircraft("Silver 1",30000, 3, 2000, 950, "A reaccion");
//    public Aircraft flight3=new GoldAircraft(true,"Gold 1",45000, 4, 2000, 1000, "A hélice");
//    
    public static void saveNewUser(User user){
        File file = null;
   ObjectMapper mapper=new ObjectMapper();
   ArrayList<User> users=Persistence.loadUsers();
   users.add(user);
        try {
        mapper.writeValue(file, users);
        } catch (IOException ex) {
            Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }        
    
    
    public static ArrayList<User> loadUsers(){
    ObjectMapper mapper=new ObjectMapper(); 
    ArrayList<User> users = null;
    File file=new File("Users");
    if(file.exists()){
        try {
            users=mapper.readValue(file, new TypeReference<ArrayList<User>>(){});
        } catch (IOException ex) {
            Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
        }
     
       }
       return users;
    }
    
    public static void noFile(){
        System.out.println("");
    }
   
}
