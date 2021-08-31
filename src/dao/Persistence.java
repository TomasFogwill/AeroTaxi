
package dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import main.Admin;
import models.Aircraft;
import models.BronzeAircraft;
import models.Flight;
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
        File file = new File("src/dao/data/Aircrafts.json");
        ObjectMapper mapper = new ObjectMapper();
        Aircraft[] aircrafts = Persistence.getAircrafts();
        Aircraft[] aircrafts1 = new Aircraft[aircrafts.length + 1];
        for (int i = 0; i < aircrafts.length; i++) {
            aircrafts1[i] = aircrafts[i];
        }
        aircrafts1[aircrafts.length] = aircraft;
        try {
            mapper.writeValue(file, aircrafts1);
        } catch (IOException ex) {
            System.out.println("Algo ha salido mal");
        }
    }
     
    public static Aircraft[] getAircrafts(){
    ObjectMapper mapper=new ObjectMapper(); 
    Aircraft[] aircrafts =new Aircraft[0];
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
    
   public static void saveNewFlight(Flight flight){
   File file=new File("src/dao/data/Flights.json");
   ObjectMapper mapper=new ObjectMapper();
   mapper.registerModule(new JavaTimeModule());
   mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
   Flight[] flights=Persistence.getFlights(); 
   Flight[] flights1=new Flight[flights.length+1];
   for(int i=0;i<flights.length;i++){
   flights1[i]=flights[i];
   }
   flights1[flights.length]=flight;
        try{
        mapper.writeValue(file, flights1);
        } catch (IOException ex) {
            System.out.println("Algo ha salido mal");
        }        
    } 
   
   

   public static Flight[] getFlights(){
    ObjectMapper mapper=new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    Flight[] flights =new Flight[0];
    File file=new File("src/dao/data/Flights.json");
    if(file.exists()){
        try{
            flights=mapper.readValue(file,Flight[].class);
        } catch (IOException ex) {
            System.out.println("Algo ha salido mal, contactese con administración");
        }     
       }else{
        try {
            file.createNewFile();
            mapper.writeValue(file,flights);
        } catch (IOException ex) {
            System.out.println("Algo ha salido mal");
        }
    }
    
       return flights;
    }


    public static ArrayList<Flight> getFlightByDate(LocalDate date) {
        Flight[] flights = Persistence.getFlights();
        ArrayList<Flight> flights1 = new ArrayList<Flight>();
        for (int i = 0; i < flights.length; i++) {
            if (flights[i].getDate() == date) {
                flights1.add(flights[i]);
            }
        }
        return flights1;
    }
   
   public static ArrayList<Aircraft> getAvailableAircraft(LocalDate date){
   ArrayList<Flight> flights=Persistence.getFlightByDate(date);//[]  
   ArrayList<Aircraft> notAv=new ArrayList<Aircraft>();//[]
   ArrayList<Aircraft> available=new ArrayList<Aircraft>();//[]
   Aircraft[]aircrafts=Persistence.getAircrafts();
   for(Flight i:flights){
       notAv.add(i.getAircraft());
   }
   for(int i=0;i<aircrafts.length;i++){
   if(!(notAv.contains(aircrafts[i])))
   available.add(aircrafts[i]);
   }
   return available;
   }
   
    public static ArrayList<Flight> getFlightByUser(User user) {
        Flight[] flights = Persistence.getFlights();
        ArrayList<Flight> userFlights = new ArrayList<Flight>();
        for (int i = 0; i < flights.length; i++) {
            if (flights[i].getUser() == user) {
                userFlights.add(flights[i]);
            }
        }
        return userFlights;
    }
}
