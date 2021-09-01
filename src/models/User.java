
package models;

import java.util.Scanner;

public class User {
    private String name;
    private String surname;
    private String id;
    private int age;
    private float flightTot;
    private String best;
    
    public User(String name,String surname,String id,int age){
    this.name=name;
    this.surname=surname;
    this.id=id;
    this.age=age;
    }
    
    public User(){
    }



    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public float getFlightTot() {
        return flightTot;
    }

    public String getBest() {
        return best;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setFlightTot(float flightTot) {
        this.flightTot = flightTot;
    }

    public void setBest(String best) {
        this.best = best;
    }
    
    
         
    
        
    
    
    
//BORRAR O MODIFICAR DESPUES, SOLO PARA PRUEBAS
    @Override
    public String toString() {
        return "Usuario{ Nombre = " + name + " Apellido = " + surname + " DNI = " + id + " Edad = " + age + " Gasto total de vuelos = " + flightTot + " Mejor categoría de vuelo = " + best + "}";
    }
    
   
          
    
    
    }
    

