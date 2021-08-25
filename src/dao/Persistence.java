
package dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import models.Aircraft;
import models.User;

public abstract class Persistence {

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
   Aircraft[] aircrafts=Persistence.getAircrafts();
   Aircraft[] aircrafts1=new Aircraft[aircrafts.length+1];
   for(int i=0;i<aircrafts.length;i++){
   aircrafts1[i]=aircrafts[i];
   }
   aircrafts1[aircrafts.length]=aircraft;
        try{
        mapper.writeValue(file, aircrafts1);
        } catch (IOException ex) {
            System.out.println("Algo ha salido mal");
        }        
    } 
     
    public static Aircraft[] getAircrafts(){
    ObjectMapper mapper=new ObjectMapper(); 
    Aircraft[] aircrafts ={};
    File file=new File("src/dao/data/Aircrafts.json");
    if(file.exists()){
        try{
            aircrafts=mapper.readValue(file,Aircraft[].class);
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
