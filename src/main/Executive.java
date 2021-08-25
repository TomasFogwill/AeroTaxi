package main;

import dao.Persistence;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import models.City;
import models.Flight;
import models.Route;
import models.User;

public abstract class Executive {
    
    public static User logIn(){
    ArrayList<User> users=Persistence.getUsers();
    Scanner scanner=new Scanner(System.in);
    User user=new User();
    System.out.print("Ingrese su DNI para iniciar sesión: ");
    String id=scanner.nextLine();
    int t=0;
    for (int i=0; i < users.size(); i++){
        if(users.get(i).getId().equals(id)){
        user=users.get(i);
        t++;
        }}
    switch(t){
        case 0:System.out.println("Los datos no se corresponden");
        user=null;
        break;
        case 1:System.out.println("Sesión inciada");
        break;
        default:System.out.println("Se encontró un problema, contacte al administrador");
        user=null;
    }
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
        Persistence.saveNewUser(user);
        return user;
        }                
    
    
    public static User initial(){
       boolean verif=false;
       Scanner entradaIngrUs=new Scanner(System.in);
       do{System.out.println("\n1.Iniciar sesión\n2.Registrarse\n3.Salir");
       System.out.print("Seleccione la opcion correspondiente: ");
       String var=entradaIngrUs.nextLine();
       switch(var){
           case "1":
               User user1=Executive.logIn();
               if(user1==null){
                   System.out.println("No se ha podido iniciar sesión");
               break;
               }else{
               return user1;
               }          
           case "2":
               User user2=Executive.register();
               return user2;
           case "3":
           return null;
           case "administration":
               User user3=Admin.user();
               return user3;
           default:
               System.out.println("\nLa opcion ingresada no es válida\n");
               break;}}
       while(verif==false);
       return null;
       }
    
    public static Route flightRoute(){
        int dist;
        City c1 = null;
        City c2 = null;
    Scanner e=new Scanner(System.in);
    System.out.println("Los vuelos disponibles conectan las siguientes ciudades:\n1.Córdoba\n2.Buenos Aires\n3.Montevideo\n4.Santiago de Chile");
    do{System.out.print("Ingrese el número que corresponda a la ciudad de origen: ");
    String ciudad1=e.nextLine();
    switch(ciudad1){
        case "1": c1=City.CORDOBA;
        break;
        case "2": c1=City.BUENOS_AIRES;
        break;
        case "3": c1=City.MONTEVIDEO;
        break;
        case "4": c1=City.SANTIAGO_DE_CHILE;
        break;
        default:System.out.println("No ingresó un caracter correspondiente\n");
        break;}}
    while(c1==null);
    System.out.println("Usted eligió de origen: "+c1);
    do{System.out.print("\nIngrese el número que corresponda a la ciudad de destino: ");
    String ciudad2=e.nextLine();
    switch(ciudad2){
        case "1": c2=City.CORDOBA;
        break;
        case "2": c2=City.BUENOS_AIRES;
        break;
        case "3": c2=City.MONTEVIDEO;
        break;
        case "4": c2=City.SANTIAGO_DE_CHILE;
        break;
        default:System.out.println("No ingresó un caracter correspondiente\n");
        break;}}
    while(c2==null);
    System.out.println("Usted eligió de destino: "+c2);
    if(c1==c2){
        System.out.println("Error de validación: destino y origen son idénticos. Se anula la operación");
    }else{
    Route route=new Route(c1,c2);
    dist=route.calcDist();
    route.setDistance(dist);
    return route;}
    return null;
} 
    
   public static void newFlight(User user){
       Flight flight = null;
       flight.user=user;
       boolean verif1=false;
       LocalDate d=null;
       Scanner scanner=new Scanner(System.in);
       System.out.println("Bienvenido a la gestión de contratación de vuelos");
       do{
       System.out.print("Ingrese la fecha a viajar de la siguiente forma: d/mm/yyyy ");
       String dates=scanner.nextLine();      
       try{
       d=LocalDate.parse(dates, DateTimeFormatter.ofPattern("d/mm/yyyy"));
       verif1=true;
       }catch(java.time.format.DateTimeParseException ex){
       System.out.println("La fecha no fue ingresada correctamente");
       }
       }while(verif1==false);
       flight.date=d;
       flight.route=Executive.flightRoute();
       System.out.print("Indique la cantidad de pasajeros que lo acompañaran: ");
       int nPassengers=scanner.nextInt();
       //Falta completar con un switch y limpiar el scanner
       System.out.println("A continuación se le muestran las categorías de vuelo ");
       System.out.println("1.Bronze\n2.Silver\n3.Gold");
       System.out.print("Ingrese el número correspondiente: ");
       
//  Ahora el usuario debe seleccionar un avión. El sistema se encargará
//demostrar los aviones disponibles para esa fecha y el usuario elige el
//deseado.
//Por último, el sistema debe mostrar el costo total del vuelo y el 
//usuario deberá confirmar para generar el vuelo.
   }
   
   public static void deleteFlight(User user){
       
   }
   
   public static void getFlight(User user){
   
   }
    
}
