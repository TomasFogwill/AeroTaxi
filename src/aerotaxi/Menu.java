
package aerotaxi;

import java.util.Scanner;

public abstract class Menu {
    
   public static void menu(){
       Usuario.ingresoUsuario();
       Scanner entradaMenu=new Scanner(System.in);
       boolean verif=false;
       System.out.println("///////////////////AEROTAXI////////////////////");
       System.out.println("//Bienvenido al servicio de gestión de vuelos//\n///////////////////////////////////////////////");
       System.out.println("1.Contratar un vuelo\n2.Consultar vuelo\n3.Cancelar vuelo\n4.Administrador\n5.Registrar usuario nuevo\n6.Salir");
       do{System.out.print("Ingrese la opción deseada: ");
       String opcion=entradaMenu.nextLine();
       switch(opcion){
           case "1":Sistema.contratacion();
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
       
       
   
   
   
   }
    

