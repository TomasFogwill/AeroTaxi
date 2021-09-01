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

    public static File usersFile = new File("src/dao/data/Users.json");
    public static File aircraftsFile = new File("src/dao/data/Aircrafts.json");
    public static File flightsFile = new File("src/dao/data/Flights.json");
    public static ObjectMapper mapper = new ObjectMapper();

    public static void saveNewUser(User user) {
        ArrayList<User> users=Persistence.getUsers();
        users.add(user);
        try {
            mapper.writeValue(usersFile, users);
        } catch (IOException ex) {
            System.out.println("Algo ha salido mal, contactese con el administrador");
        }
    }

    public static ArrayList<User> getUsers() {
       ArrayList<User> users=new ArrayList<>(); 
        if (usersFile.exists()) {
            try {
                users = mapper.readValue(usersFile, new TypeReference<ArrayList<User>>() {
                });
            } catch (IOException ex) {
                System.out.println("Algo ha salido mal, contactese con administración");
            }
        } else {
            try {
                usersFile.createNewFile();
                mapper.writeValue(usersFile, users);
            } catch (IOException ex) {
                System.out.println("Algo ha salido mal, contactese con administración");
            }
        }
        return users;
    }

    public static void saveNewAircraft(Aircraft aircraft) {
        Aircraft[] aircrafts=Persistence.getAircrafts();
        Aircraft[] aircrafts1=new Aircraft[aircrafts.length+1];
        for (int i = 0; i < aircrafts.length; i++) {
            aircrafts1[i] = aircrafts[i];
        }
        aircrafts1[aircrafts.length] = aircraft;
        try {
            mapper.writeValue(aircraftsFile, aircrafts1);
        } catch (IOException ex) {
            System.out.println("Algo ha salido mal");
        }
    }

    public static Aircraft[] getAircrafts() {
        Aircraft[] aircrafts = new Aircraft[0];
        if (aircraftsFile.exists()) {
            try {
                aircrafts = mapper.readValue(aircraftsFile, Aircraft[].class);
            } catch (IOException ex) {
                System.out.println("Algo ha salido mal, contactese con administración");
            }
        } else {
            try {
                aircraftsFile.createNewFile();
                mapper.writeValue(aircraftsFile, aircrafts);
            } catch (IOException ex) {
                System.out.println("Algo ha salido mal");
            }
        }

        return aircrafts;
    }

    public static void saveNewFlight(Flight flight) {
        Flight[] flights=Persistence.getFlights();
        Flight[] flights1=new Flight[flights.length+1];         
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);;
        for (int i = 0; i < flights.length; i++) {
            flights1[i] = flights[i];
        }
        flights1[flights.length] = flight;
        try {
            mapper.writeValue(flightsFile, flights1);
        } catch (IOException ex) {
            System.out.println("Algo ha salido mal");
        }
    }

    public static Flight[] getFlights() {
        Flight[] flights = new Flight[0];
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        if (flightsFile.exists()) {
            try {
                flights = mapper.readValue(flightsFile, Flight[].class);
            } catch (IOException ex) {
                System.out.println("Algo ha salido mal, contactese con administración");
            }
        } else {
            try {
                flightsFile.createNewFile();
                mapper.writeValue(flightsFile, flights);
            } catch (IOException ex) {
                System.out.println("Algo ha salido mal");
            }
        }

        return flights;
    }

    public static ArrayList<Flight> getFlightByDate(LocalDate date) {
        ArrayList<Flight> flightsList = new ArrayList<Flight>();
                Flight[] flights = Persistence.getFlights();
        for (int i = 0; i < flights.length; i++) {
            if (flights[i].getDate() == date) {
                flightsList.add(flights[i]);
            }
        }
        return flightsList;
    }

    public static ArrayList<Aircraft> getAvailableAircraft(LocalDate date) {
        ArrayList<Flight> flights = Persistence.getFlightByDate(date);
        ArrayList<Aircraft> notAv = new ArrayList<Aircraft>();
        ArrayList<Aircraft> available = new ArrayList<Aircraft>();
        Aircraft[] aircrafts = Persistence.getAircrafts();
        for (Flight i : flights) {
            notAv.add(i.getAircraft());
        }
        for (int i = 0; i < aircrafts.length; i++) {
            if (!(notAv.contains(aircrafts[i]))) {
                available.add(aircrafts[i]);
            }
        }
        return available;
    }

    public static ArrayList<Flight> getFlightByUser(User user) {
        Flight[] flights = Persistence.getFlights();
        ArrayList<Flight> userFlights = new ArrayList<>();
        for (int i = 0; i < flights.length; i++) {
            if (flights[i].getUser() == user) {
                userFlights.add(flights[i]);
            }
        }
        return userFlights;
    }

    public static void deleteFlight(Flight flight) {
        Flight[] flights = Persistence.getFlights();
        boolean flag = false;
        int place = 0;
        for (int i = 0; i < flights.length; i++) {
            if (flight == flights[i]) {
                flag = true;
                place = i;
            }
        }
        if (flag = false) {
            System.out.println("Error: no se ha podido eliminar el vuelo");
        } else {
            Flight[] flights1 = new Flight[flights.length - 1];
            for (int i = 0; i < place; i++) {
                flights1[i] = flights[i];
            }
            for (int i = place + 1; i < flights.length; i++) {
                flights1[i - 1] = flights[i];
            }
            Persistence.saveNewFlightList(flights1);
        }
    }

    public static void saveNewFlightList(Flight[] flights) {
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            mapper.writeValue(flightsFile, flights);
        } catch (IOException ex) {
            System.out.println("Algo ha salido mal");
        }
    }

    public static void saveNewUserList(ArrayList<User> users) {
        try {
            mapper.writeValue(usersFile, users);
        } catch (IOException ex) {
            System.out.println("Algo ha salido mal");
        }
    }

}
