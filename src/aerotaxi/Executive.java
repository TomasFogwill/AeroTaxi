package aerotaxi;

import java.util.Scanner;
import models.User;

public abstract class Executive {
    
    public static User logIn(){
    User user=new User();
    return user;
    }
    
    
    public static User register(){
        String id=null;
        int age=0;
        Scanner entradaReg=new Scanner(System.in);
        System.out.print("Vamos a registrar su usuario\nIngrese su nombre: ");
        String name=entradaReg.nextLine();
        System.out.print("Ingrese su apellido: ");
        String surname=entradaReg.nextLine();
        boolean verifR=false;
        do{System.out.print("Ingrese su DNI: ");
          if(!entradaReg.hasNextInt()){
          System.out.println("No a ingresado un número válido");
          entradaReg.nextLine();
          }else{
              id=entradaReg.nextLine();
              verifR=true;              
          }}
        while(verifR==false);
        boolean verifR2=false;
        do{System.out.print("Ingrese su edad: ");
          if(!entradaReg.hasNextInt()){
          System.out.println("No a ingresado un número válido");
          entradaReg.nextLine();
          }else{
              age=entradaReg.nextInt();
              verifR2=true;}}
        while(verifR2==false);
        User user=new User(name,surname,id,age);
        return user;
        }                
    
    
    public static User initial(){
       boolean verif=false;
       Scanner entradaIngrUs=new Scanner(System.in);
       System.out.println("\n1.Iniciar sesión\n2.Registrarse\n3.Salir");
       do{System.out.print("Seleccione la opcion correspondiente: ");
       String var=entradaIngrUs.nextLine();
       switch(var){
           case "1":
               User user1=Executive.logIn();
               return user1;
           case "2":
               User user2=Executive.register();
               return user2;
           case "3":
           return null;
           default:
               System.out.println("\nLa opcion ingresada no es válida\n");
               break;}}
       while(verif==false);
       return null;
       }
    
   public static void contract(){
       System.out.println("\nBienvenido a la gestión de contratación de vuelos");
       System.out.print("");
//Inicialmente indicar la fecha deseada para realizar un vuelo.
       //Seleccionar el origen de su vuelo y posteriormente el destino.
       //El usuario debe indicar la cantidad de acompañantes que tendrá 
         //en el vuelo.
       //  Ahora el usuario debe seleccionar un avión. El sistema se encargará
//demostrar los aviones disponibles para esa fecha y el usuario elige el
//deseado.
//Por último, el sistema debe mostrar el costo total del vuelo y el 
//usuario deberá confirmar para generar el vuelo.
   }
    
}
