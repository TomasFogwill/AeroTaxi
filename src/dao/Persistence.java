
package dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import models.Aircraft;
import models.User;

public abstract class Persistence {
//    public Aircraft flight1=new BronzeAircraft("Bronze 1",25000, 2, 2500, 900, "A pistones");
//    public Aircraft flight2=new SilverAircraft("Silver 1",30000, 3, 2000, 950, "A reaccion");
//    public Aircraft flight3=new GoldAircraft(true,"Gold 1",45000, 4, 2000, 1000, "A hélice");
//    
   public static void saveNewUser(User user){
   File file=new File("src/dao/data/Users.json");
   ObjectMapper mapper=new ObjectMapper();
   ArrayList<User> users=Persistence.getUsers();
   users.add(user);
        try{
        mapper.writeValue(file, users);
        } catch (IOException ex) {
            System.out.println("Algo ha salido mal, contactese con el administrador");
        }        
    }        
    
    
    public static ArrayList<User> getUsers(){
    ObjectMapper mapper=new ObjectMapper(); 
    ArrayList<User> users = new ArrayList<User>();    
    File file=new File("src/dao/data/Users.json");
    if(file.exists()){
        try{
            users=mapper.readValue(file, new TypeReference<ArrayList<User>>(){});
        } catch (IOException ex) {
            System.out.println("Algo ha salido mal, contactese con administración");
        }     
       }else{
        try {
            file.createNewFile();
            mapper.writeValue(file,users);
        } catch (IOException ex) {
            System.out.println("Algo ha salido mal, contactese con administración");
        }
    }
       return users;
    }
    
     public static void saveNewAircraft(Aircraft aircraft){
   File file=new File("src/dao/data/Aircrafts.json");
   ObjectMapper mapper=new ObjectMapper();
   ArrayList<Aircraft> aircrafts=Persistence.getAircrafts();
   aircrafts.add(aircraft);
        try{
        mapper.writeValue(file, aircrafts);
        } catch (IOException ex) {
            System.out.println("Algo ha salido mal");
        }        
    } 
     
    public static ArrayList<Aircraft> getAircrafts(){
    ObjectMapper mapper=new ObjectMapper(); 
    ArrayList<Aircraft> aircrafts = new ArrayList<Aircraft>();    
    File file=new File("src/dao/data/Aircrafts.json");
    if(file.exists()){
        try{
            aircrafts=mapper.readValue(file, new TypeReference<ArrayList<Aircraft>>(){});
        } catch (IOException ex) {
            System.out.println("Algo ha salido mal, contactese con administración");
        }     
       }else{
        try {
            file.createNewFile();
            mapper.writeValue(file,aircrafts);
        } catch (IOException ex) {
            System.out.println("Algo ha salido mal");
        }
    }
       return aircrafts;
    }
    
   
}
