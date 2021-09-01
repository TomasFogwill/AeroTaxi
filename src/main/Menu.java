
package main;

import java.util.Objects;
import models.User;
import java.util.Scanner;

public abstract class Menu {
    
    public static void first(){
    System.out.println("///////////////////AEROTAXI////////////////////");
    System.out.println("//Bienvenido al servicio de gestión de vuelos//\n///////////////////////////////////////////////");
    User user=Executive.initial();
    if(user!=null){
    if(user.getId().equals("1234")){
    Menu.menuAdmin();
    }else {    
    Menu.menu(user);
    }
    }
    }
    
   public static void menu(User user){
       Scanner entradaMenu=new Scanner(System.in);
       boolean verif=false;
       System.out.println("\n//Bienvenido,"+user.getName()+"//");
       do{System.out.println("\n1.Contratar un vuelo\n2.Consultar o cancelar vuelo\n3.Salir");
       System.out.print("Ingrese la opción deseada: ");
       String opcion=entradaMenu.nextLine();
       switch(opcion){
           case "1":Executive.newFlightByMenu(user);            
               break;
           case "2":Executive.consultFlight(user);
               break;
           case "3":
               verif=true;
               break;
           default: System.out.println("La opción ingresada no es válida");
               break;}}
       while(verif==false);
    }             
       
       
       
   public static void menuAdmin(){
       Scanner scanner=new Scanner(System.in);
       boolean verif=false;
       do{
       System.out.println("\n//Bienvenido al menú de administrador//");
       System.out.println("1.Agregar un nuevo avión al sistema\n2.Consultar vuelos por fecha\n3.Información clientes\n4.Salir");
       System.out.print("Ingrese la opción deseada: ");
       String v=scanner.nextLine();
       switch(v){
           case "1":Admin.newAircraft();              
               break;
           case "2":Admin.flightList();
               break;
           case "3":Admin.userList();
               break;
           case "4": verif=true;
           break;
           default:
               break;     
       }
       }while(verif==false);
   }
   }
    

