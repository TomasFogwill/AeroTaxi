
package main;

import dao.Persistence;
import java.util.Scanner;
import models.Aircraft;
import models.BronzeAircraft;
import models.GoldAircraft;
import models.Kind;
import models.SilverAircraft;
import models.User;


public abstract class Admin {
    
    public static User user(){
        User user=null;
        Scanner scanner=new Scanner(System.in);
        System.out.print("Ingrese el código: ");
        String code=scanner.nextLine();
        if("1234".equals(code)){
        user=new User("scanner=new","","codigo numerico",5000);
        return user;
        }
      return user;
    }
    
    public static void newAircraft(){
    Aircraft a = null;
    Scanner scanner=new Scanner(System.in);
    boolean verif0=false;
    String id = null;
    do{System.out.print("Ingrese un código numérico de identificación: ");
      if(!scanner.hasNextInt()){
          System.out.println("No ha ingresado un número válido");
          scanner.nextLine();
      }else{
      id=scanner.nextLine();
      Aircraft[] b=Persistence.getAircrafts();
      int t=0;
      for(int i=0;i<b.length;i++){
      if(id==b[i].getId()){
      t++;    
      }    
      }
      if(t==0){
      verif0=true;
      }else{
          System.out.println("El código ingresado ya existe, vuelva a intentarlo");    
      }  
    }}while(verif0==false);
        float maxFuel = 0;
        boolean verif1=false;
        do{System.out.print("Ingrese la capacidad de combustible del avión: ");
          if(!scanner.hasNextFloat()){
          System.out.println("No a ingresado un número válido");
          scanner.nextLine();
          }else{
            maxFuel=scanner.nextFloat();
              verif1=true;              
          }}
        while(verif1==false);
        scanner.nextLine();
        float costXkm = 0;
        boolean verif2=false;
        do{System.out.print("Ingrese el costo por km de vuelo: ");
          if(!scanner.hasNextFloat()){
          System.out.println("No a ingresado un número válido");
          scanner.nextLine();
          }else{
            costXkm=scanner.nextFloat();
              verif2=true;              
          }}
        while(verif2==false);
        int maxPas = 0;
        boolean verif3=false;
        do{System.out.print("Ingrese la capacidad máxima de pasajeros: ");
          if(!scanner.hasNextInt()){
          System.out.println("No a ingresado un número válido");
          scanner.nextLine();
          }else{
            maxPas=scanner.nextInt();
            if(maxPas<1){
                System.out.println("No ha ingresado un número válido");
            }else{
              verif3=true;              
          }}}
        while(verif3==false);
        float vMax = 0;
        boolean verif4=false;
        do{System.out.print("Ingrese la velocidad máxima de vuelo: ");
          if(!scanner.hasNextFloat()){
          System.out.println("No a ingresado un número válido");
          scanner.nextLine();
          }else{
            vMax=scanner.nextFloat();
              verif4=true;              
          }}
        while(verif4==false);
        scanner.nextLine();
            Kind kindProp = null;
            boolean verif5=false;
            do{System.out.println("Los posibles tipos de propuslsión son los sigientes\n1.Motor a reacción\n2.Motor a hélice\n3.Motor a pistones ");
            System.out.print("Ingrese el número correspondiente: ");
            String v=scanner.nextLine();
            switch(v){
                case "1":kindProp=Kind.MOTOR_A_REACCION;
                verif5=true;
                    break;
                case "2":kindProp=Kind.MOTOR_A_HELICE;
                verif5=true;
                    break;
                case "3":kindProp=Kind.MOTOR_A_PISTONES;
                verif5=true;
                    break;
                default:System.out.println("La opcion ingresada no es válida");
                    break;                    
            }}while(verif5==false);
            boolean verif6=false;
    System.out.println("A continuación se le muestran las categorías posibles de avión ");
    do{System.out.println("1.Bronze\n2.Silver\n3.Gold");
    System.out.print("Ingrese el número correspondiente: ");
    String var=scanner.nextLine();
    switch(var){
        case "1":a=new BronzeAircraft(id,maxFuel,costXkm,maxPas,vMax,kindProp);
          verif6=true;  
        break;
        case "2":a=new SilverAircraft(id,maxFuel,costXkm,maxPas,vMax,kindProp);
        verif6=true;    
        break;
        case "3":boolean verif7=false;
        String u;
            System.out.println("¿El avión posee conexión continua de Wifi?\n1.Si\n2.No");
         do{System.out.print("Ingrese la opción correcta: ");
         u=scanner.nextLine();
             switch(u){
                 case "1":a=new GoldAircraft("Si",id,maxFuel,costXkm,maxPas,vMax,kindProp);
                 verif7=true;    
                 break;
                 case "2":a=new GoldAircraft("No",id,maxFuel,costXkm,maxPas,vMax,kindProp);
                     verif7=true;
                 break;
                 default:System.out.println("No ingreso una opción válida");
                 break;
                          }
         }while(verif7==false);
         verif6=true;
        break;
        default:System.out.println("No ingreso una opción válida");
            break;
    }
    }while(verif6==false);
    boolean verif8=false;
    
    String f;
        System.out.println(a.toString());
        do{System.out.println("¿Confirmar y guardar?\n1.Si\n2.No");
        f=scanner.nextLine();
        switch(f){
            case "1":Persistence.saveNewAircraft(a);
                System.out.println("El avión ha sido guardado correctamente");
                verif8=true;
                break;
            case "2":System.out.println("No se han guardado los cambios");
            verif8=true;    
            break;
            default:System.out.println("No ha ingresado una opción válida");
                break;
        
        }
        }while(verif8=false);
        }
    
 
    public static void flightList(){
        
    }
    
    public static void userList(){
    
    }

}
