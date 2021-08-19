
package aerotaxi;

import java.util.Scanner;

public class Usuario {
    private String nombre;
    private String apellido;
    private String dni;
    private int edad;
    private float totalVuelos;
    private String mejorCateg;
    
    public Usuario(String nombre,String apellido, String dni,int edad){
    this.nombre=nombre;
    this.apellido=apellido;
    this.dni=dni;
    this.edad=edad;
    }
    
    public static void verificarUsuario(){
    }
    
    public static void iniciarSesion(){
    }
    
    public Usuario(){
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
        nombre=name;
        apellido=surname;
        dni=id;
        edad=age;                
    }
        
    
    
    public static Usuario ingresoUsuario(){
       Usuario user=new Usuario();
       boolean verif=false;
       Scanner entradaIngrUs=new Scanner(System.in);
       System.out.println("\n1.Iniciar sesión\n2.Registrarse\n3.Atras");
       do{System.out.print("Seleccione la opcion correspondiente: ");
       String var=entradaIngrUs.nextLine();
       switch(var){
           case "1":
               Usuario.iniciarSesion();
               verif=true;
               break;
           case "2": 
               
               verif=true;
               break;
           case "3":
               verif=true;
               break;
           default:
               System.out.println("\nLa opcion ingresada no es válida\n");
               break;}}
       while(verif==false);
       
       return user;
       }

    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", edad=" + edad + ", totalVuelos=" + totalVuelos + ", mejorCateg=" + mejorCateg + '}';
    }
    
    
    }
    

