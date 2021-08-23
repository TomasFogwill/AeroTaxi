
package main;

import java.util.Scanner;
import models.Aircraft;


public abstract class Admin {
    
    public static void addAircraft(){
    Aircraft a;
    boolean aux=false;
    Scanner e=new Scanner(System.in);
    System.out.println("");
    System.out.println("A continuación se le muestran las categorías posibles de avión ");
    System.out.println("1.Bronze\n2.Silver\n3.Gold");
    do{System.out.print("Ingrese el número correspondiente: ");
    String var=e.nextLine();
    switch (var){
        case "1":
            aux=true;
            break;
        case "2":
            aux=true;
            break;
        case "3":
            aux=true;
            break;
        default: System.out.println("No ha ingresado una opción válida");
        break;
            }
    }
while(aux==false);
    }
    public static void flightList(){
        
    }
    
    public static void userList(){
    
    }

}
