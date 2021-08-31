
package main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import dao.Persistence;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import models.Aircraft;
import models.BronzeAircraft;
import models.City;
import models.Flight;
import models.Kind;
import models.Route;
import models.User;

public abstract class Main {
   
public static void main(String[] args) throws JsonProcessingException, IOException  {
User user=new User("Tomas","Fogwill","40129369",24);
   // Executive.newFlight(user); 
//
LocalDate date=LocalDate.parse("14/01/1997", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
//
//Route route=new Route(City.BUENOS_AIRES,City.SANTIAGO_DE_CHILE);
Aircraft aircraft=new BronzeAircraft("747",32423,23423,34343,423423,Kind.MOTOR_A_HELICE);     
//Flight flight=new Flight(aircraft,route,user,5,date,50000);   
//Persistence.saveNewFlight(flight);
//Persistence.getFlights();
//ObjectMapper mapper=new ObjectMapper();
//mapper.registerModule(new JavaTimeModule());
//mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
////mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//String a= mapper.writeValueAsString(date);
//    System.out.println(a);
//    LocalDate d=mapper.readValue(a,LocalDate.class);
//    System.out.println("des= "+d);
//System.out.println(Persistence.getAvailableAircraft(date));
//Executive.newFlight(user);
//ArrayList<Aircraft> p=new ArrayList<Aircraft>();
//    System.out.println(p.size());
Aircraft[] aircraft1=new Aircraft[0];
Aircraft[] aircraft2=new Aircraft[aircraft1.length+1];
aircraft2[aircraft1.length]=aircraft;

}

}
