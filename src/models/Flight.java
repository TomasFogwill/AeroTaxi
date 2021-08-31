package models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.LocalDate;
import models.Aircraft;


public class Flight {
   private Aircraft aircraft;
   private Route route;
   private User user;
   private int nPassengers;   
   private LocalDate date;
   private float cost;

    public Flight() {
    }

    public Flight(Aircraft aircraft, Route route, User user, int nPassengers, LocalDate date, float cost) {
        this.aircraft = aircraft;
        this.route = route;
        this.user = user;
        this.nPassengers = nPassengers;
        this.date = date;
        this.cost = cost;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getnPassengers() {
        return nPassengers;
    }

    public void setnPassengers(int nPassengers) {
        this.nPassengers = nPassengers;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public float getCost() {       
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
    
    
    @Override
    public String toString() {
        return "Fecha de vuelo: "+date+"\nOrigen: "+route.origin+"\nDestino: "+route.destiny+"\nUsuario a cargo: "+user.getName()+" "+user.getSurname()+"\nCantidad de pasajeros: "+nPassengers+"\nAvion: "+aircraft.getCategory()+" "+aircraft.getId()+"\nCosto: $"+cost;
    }
    

}

