
package main;

import models.User;
import java.util.Scanner;

public abstract class Menu {
    
    public static void first(){
    System.out.println("///////////////////AEROTAXI////////////////////");
    System.out.println("//Bienvenido al servicio de gestión de vuelos//\n///////////////////////////////////////////////");
    User user=Executive.initial();
    if("admin".equals(user.getName())&&"0000".equals(user.getId())){
    Menu.menuAdmin();
        System.out.println("Se ejecuta el menu de admin");
    }else{
    Menu.menu(user);
    }
    }
    
   public static void menu(User user){
       Scanner entradaMenu=new Scanner(System.in);
       boolean verif=false;
       System.out.println("\n//Bienvenido,"+user.getName()+"//");
       System.out.println("1.Contratar un vuelo\n2.Consultar vuelo\n3.Cancelar vuelo\n4.Administrador\n5.Registrar usuario nuevo\n6.Salir");
       do{System.out.print("Ingrese la opción deseada: ");
       String opcion=entradaMenu.nextLine();
       switch(opcion){
           case "1":Executive.contract(user);
               verif=true;
               break;
           case "2":
               verif=true;
               break;
           case "3":
               verif=true;
               break;
           case "4":
               verif=true;
               break;
           case "5":
               verif=true;
               break;
           case "6":
               verif=true;
               break;
           default: System.out.println("La opción ingresada no es válida");
               break;}}
       while(verif==false);
    }             
       
       
       
   public static void menuAdmin(){
       System.out.println("///////////////////AEROTAXI////////////////////");
    System.out.println("//Bienvenido al menú de administrador//\n///////////////////////////////////////////////");
       System.out.println("Seleccione la opción deseada");
//agregar avión al sistema
   //consultar todas los vuelos en una fecha dada
   //listar todos los clientes indicando todos sus datos personales
   //,mejor categoría usada y el total gastado de los vuelos
   }
   
   
   }
    

