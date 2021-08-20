
package aerotaxi;

import java.util.Scanner;

public class Recorrido {
    Ciudad ciudadOrigen;//ciudad de origen
    Ciudad ciudadDestino;//ciudad de destino
    int distancia; //distancia en km
    
    enum Ciudad{
    CORDOBA,
    BUENOS_AIRES,
    SANTIAGO_DE_CHILE,
    MONTEVIDEO    
    }
    
public Recorrido(){
    Ciudad c1=null,c2=null;
    Scanner entrada=new Scanner(System.in);
    System.out.println("Los vuelos disponibles conectan las siguientes ciudades:\n1.Córdoba\n2.Buenos Aires\n3.Montevideo\n4.Santiago de Chile");
    do{System.out.print("Ingrese el número que corresponda a la ciudad de origen: ");
    String ciudad1=entrada.nextLine();
    switch(ciudad1){
        case "1": c1=Ciudad.CORDOBA;
        break;
        case "2": c1=Ciudad.BUENOS_AIRES;
        break;
        case "3": c1=Ciudad.MONTEVIDEO;
        break;
        case "4": c1=Ciudad.SANTIAGO_DE_CHILE;
        break;
        default:System.out.println("No ingresó un caracter correspondiente\n");
        break;}}
    while(c1==null);
    System.out.println("Usted eligió de origen: "+c1);
    do{System.out.print("\nIngrese el número que corresponda a la ciudad de destino: ");
    String ciudad2=entrada.nextLine();
    switch(ciudad2){
        case "1": c2=Ciudad.CORDOBA;
        break;
        case "2": c2=Ciudad.BUENOS_AIRES;
        break;
        case "3": c2=Ciudad.MONTEVIDEO;
        break;
        case "4": c2=Ciudad.SANTIAGO_DE_CHILE;
        break;
        default:System.out.println("No ingresó un caracter correspondiente\n");
        break;}}
    while(c2==null);
    System.out.println("Usted eligió de destino: "+c2);
    if(c1==c2){
        System.out.println("Error de validación: destino y origen son idénticos. Se anula la operación");
    }else{
    ciudadOrigen=c1;
    ciudadDestino=c2;
    
    }
    
        
    }   
}

    

        
        
 
        
    

