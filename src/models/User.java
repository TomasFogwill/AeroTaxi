
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
    
    
         
    
        
    
    
    
//BORRAR O MODIFICAR DESPUES, SOLO PARA PRUEBAS
    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + name + ", apellido=" + surname + ", dni=" + id + ", edad=" + age + ", totalVuelos=" + flightTot + ", mejorCateg=" + best + '}';
    }
    
   
          
    
    
    }
    

