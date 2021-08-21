
package models;

import java.util.Scanner;

public class Route {
    City origin;//ciudad de origen
    City destiny;//ciudad de destino
    int distance; //distancia en km
    
    enum City{
    CORDOBA,
    BUENOS_AIRES,
    SANTIAGO_DE_CHILE,
    MONTEVIDEO    
    }
    
public Route(){
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
    origin=c1;
    destiny=c2;
    
    }
    
        
    }   
}

    

        
        
 
        
    

