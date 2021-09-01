package main;

import dao.Persistence;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;
import models.Aircraft;
import models.BronzeAircraft;
import models.City;
import models.Flight;
import models.GoldAircraft;
import models.Route;
import models.SilverAircraft;
import models.User;

public class Executive {

    public static Scanner scanner = new Scanner(System.in);

    public static User logIn() {
        ArrayList<User> users = Persistence.getUsers();
        User user = new User();
        System.out.print("Ingrese su DNI para iniciar sesión: ");
        String id = scanner.nextLine();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                user = users.get(i);
            }
        }      
        return user;
    }

    public static User register() {
        String id = null;
        int age = 0;
        System.out.println("Vamos a registrar su usuario");
        boolean verifR = false;
        do {
            System.out.print("Ingrese su DNI: ");
            if (!scanner.hasNextInt()) {
                System.out.println("No a ingresado un número válido");
                scanner.nextLine();
            } else {
                id = scanner.nextLine();
                ArrayList<User> users = Persistence.getUsers();
                int a = 0;
                for (int i = 0; i < users.size(); i++) {
                    if (id.equals(users.get(i).getId())) {
                        a++;
                    }
                }
                if (a == 0) {
                    verifR = true;
                } else {
                    System.out.println("Ya se encuentra un usuario registrado con ese DNI,vuelva a intentarlo");
                    return null;
                }
            }
        } while (verifR == false);
        System.out.print("Ingrese su nombre: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese su apellido: ");
        String surname = scanner.nextLine();
        boolean verifR2 = false;
        do {
            System.out.print("Ingrese su edad: ");
            if (!scanner.hasNextInt()) {
                System.out.println("No a ingresado un número válido");
                scanner.nextLine();
            } else {
                age = scanner.nextInt();
                verifR2 = true;
            }
        } while (verifR2 == false);
        User user = new User(name, surname, id, age);
        Persistence.saveNewUser(user);
        return user;
    }

    public static User initial() {
        boolean verif = false;
        do {
            System.out.println("\n1.Iniciar sesión\n2.Registrarse\n3.Salir");
            System.out.print("Seleccione la opcion correspondiente: ");
            String var = scanner.nextLine();
            switch (var) {
                case "1":
                    User user1 = Executive.logIn();
                    if (user1 == null) {
                        System.out.println("No se ha podido iniciar sesión");
                        break;
                    } else {
                        return user1;
                    }
                case "2":
                    User user2 = Executive.register();
                    if (user2 == null) {
                        System.out.println("No se ha podido registrar al usuario");
                        break;
                    } else {
                        return user2;
                    }
                case "3":
                    return null;
                case "administration":
                    User user3 = Admin.user();
                    return user3;
                default:
                    System.out.println("\nLa opcion ingresada no es válida\n");
                    break;
            }
        } while (verif == false);
        return null;
    }

    public static void newFlightByMenu(User user) {
        boolean flag = false;
        Flight flight = new Flight();
        flight.setUser(user);
        System.out.println("Bienvenido a la gestión de contratación de vuelos");
        Executive.setFlightDateByMenu(flight);
        flag = Executive.isAvailableAircraftEmpty(flight.getDate());
        if (flag == true) {
            System.out.println("Se cancela la operación");
        } else {
            Executive.setFlightRoute(flight);
            Executive.setFlightPassengers(flight);
            flag = Executive.isAvailableAircraftEmpty(flight.getDate(), flight.getnPassengers());
            if (flag == true) {
                System.out.println("Se cancela la operación");
            } else {
                Executive.setFlightAircraftByMenu(flight);
                flag = Executive.isAircraftSetted(flight);
                if (flag == false) {
                    System.out.println("Se cancela la operacion");
                } else {
                    Executive.setFlightCostByMenu(flight);
                    Executive.confirmFlight(flight);
                }
            }
        }
    }

    public static void setFlightDateByMenu(Flight flight) {
        boolean flag = false;
        LocalDate d = LocalDate.now();
        do {
            System.out.print("Ingrese la fecha a viajar de la siguiente forma dd/mm/yyyy: ");
            String dates = scanner.nextLine();
            try {
                d = LocalDate.parse(dates, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                flag = true;
            } catch (java.time.format.DateTimeParseException ex) {
                System.out.println("La fecha no fue ingresada correctamente");
            }
        } while (flag == false);
        flight.setDate(d);

    }

    public static boolean isAvailableAircraftEmpty(LocalDate flightDate) {
        boolean isEmpty = true;
        ArrayList<Aircraft> available = Persistence.getAvailableAircraft(flightDate);
        if (available.isEmpty()) {
            System.out.println("No hay aviones disponibles para la fecha deseada");
        } else {
            isEmpty = false;
        }
        return isEmpty;
    }

    public static void setFlightRoute(Flight flight) {
        int dist;
        City c1 = null;
        City c2 = null;
        System.out.println("Los vuelos disponibles conectan las siguientes ciudades:\n1.Córdoba\n2.Buenos Aires\n3.Montevideo\n4.Santiago de Chile");
        do {
            System.out.print("Ingrese el número que corresponda a la ciudad de origen: ");
            String ciudad1 = scanner.nextLine();
            switch (ciudad1) {
                case "1":
                    c1 = City.CORDOBA;
                    break;
                case "2":
                    c1 = City.BUENOS_AIRES;
                    break;
                case "3":
                    c1 = City.MONTEVIDEO;
                    break;
                case "4":
                    c1 = City.SANTIAGO_DE_CHILE;
                    break;
                default:
                    System.out.println("No ingresó un caracter correspondiente\n");
                    break;
            }
        } while (c1 == null);
        System.out.println("Usted eligió de origen: " + c1);
        do {
            System.out.print("\nIngrese el número que corresponda a la ciudad de destino: ");
            String ciudad2 = scanner.nextLine();
            switch (ciudad2) {
                case "1":
                    c2 = City.CORDOBA;
                    break;
                case "2":
                    c2 = City.BUENOS_AIRES;
                    break;
                case "3":
                    c2 = City.MONTEVIDEO;
                    break;
                case "4":
                    c2 = City.SANTIAGO_DE_CHILE;
                    break;
                default:
                    System.out.println("No ingresó un caracter correspondiente\n");
                    break;
            }
        } while (c2 == null);
        System.out.println("Usted eligió de destino: " + c2);
        if (c1 == c2) {
            System.out.println("Error de validación: destino y origen son idénticos. Se anula la operación");
        } else {
            Route route = new Route(c1, c2);
            dist = route.calcDist();
            route.setDistance(dist);
            flight.setRoute(route);
        }
    }

    public static void setFlightPassengers(Flight flight) {
        boolean flag = false;
        do {
            System.out.print("Indique la cantidad de pasajeros que lo acompañaran: ");
            if (!scanner.hasNextInt()) {
                System.out.println("No ha ingresado un número válido");
                scanner.nextLine();
            } else {
                flight.setnPassengers(scanner.nextInt() + 1);
                flag = true;
            }
        } while (flag == false);
    }

    public static boolean isAvailableAircraftEmpty(LocalDate flightDate, int cantPassengers) {
        boolean isEmpty = true;
        ArrayList<Aircraft> available = Persistence.getAvailableAircraft(flightDate);
        int a = 0;
        for (Aircraft i : available) {
            if (i.getMaxPas() > a) {
                a = i.getMaxPas();
            }
        }
        if (cantPassengers > a) {
            System.out.println("No tenemos aviones disponibles para esa cantidad de pasajeros");
        } else {
            isEmpty = false;
        }
        return isEmpty;
    }

    public static void setFlightAircraftByMenu(Flight flight) {
        ArrayList<Aircraft> available = Persistence.getAvailableAircraft(flight.getDate());
        System.out.println("A continuación se le presentan las fichas de los aviones disponibles:\n");
        for (Aircraft i : available) {
            if (i.getMaxPas() > flight.getnPassengers()) {
                System.out.println(i.userString() + "\n");
            }
        }
        boolean flag = false;
        do {
            System.out.println("Ingrese el ID deseado o ingrese 0 para salir");
            String anames = scanner.nextLine();
            switch (anames) {
                case "0":
                    System.out.println("Se cancela la operacion");
                    flag = true;
                    break;
                default:
                    for (Aircraft i : available) {
                        if (anames.equals(i.getId())) {
                            flight.setAircraft(i);
                            System.out.println("Usted seleccionó el avión con ID " + flight.getAircraft().getId() + "de categoría " + flight.getAircraft().getClass());
                            flag = true;
                        }
                    }
                    if (flag == false) {
                        System.out.println("No ha ingresado una opcion correcta");
                    }
            }
        } while (flag == false);
    }

    public static boolean isAircraftSetted(Flight flight) {
        boolean confirm = true;
        if (flight.getAircraft() == null) {
            confirm = false;
        }
        return confirm;
    }

    public static void setFlightCostByMenu(Flight flight) {
        float c = (flight.getRoute().getDistance() * flight.getAircraft().getCostXkm()) + (flight.getnPassengers() * 3500);
        if (flight.getAircraft().getClass() == BronzeAircraft.class) {
            c += 3000;
        }
        if (flight.getAircraft().getClass() == SilverAircraft.class) {
            c += 4000;
        }
        if (flight.getAircraft().getClass() == GoldAircraft.class) {
            c += 6000;
        }
        flight.setCost(c);
    }

    public static void confirmFlight(Flight flight) {
        boolean flag = false;
        System.out.println("El ticket de vuelo:\n" + flight.toString());
        do {
            System.out.println("¿Confirmar?\n1.Si\n2.No");
            String v = scanner.nextLine();
            switch (v) {
                case "1":
                    flight.setIdByMenu();
                    Persistence.saveNewFlight(flight);
                    System.out.println("El vuelo se a confirmado");
                    flag = true;
                    break;
                case "2":
                    System.out.println("El vuelo no se ha confirmado");
                    flag = true;
                    break;
                default:
                    System.out.println("No ha ingresado una opcion válida");
                    break;
            }
        } while (flag == false);
    }


    
    public static void consultFlight(User user) {
        boolean flag = false;
        String answer;
        Executive.getUserFlightListByMenu(user);
        do {
            System.out.println("¿Desea cancelar alguno?\n1.Si\n2.No");
            answer = scanner.nextLine();
            switch (answer) {
                case "1":
                    Executive.deleteFlightByMenu(user);
                    flag = true;
                    break;
                case "2": System.out.println("Nada será cancelado");
                    flag = true;
                    break;
                default:
                    System.out.println("No ha ingresado una opcion válida");
                    break;
            }
        } while (flag == false);
    }

public static void getUserFlightListByMenu(User user){
ArrayList<Flight> userFlights=Persistence.getFlightByUser(user);
for(Flight i:userFlights){
    System.out.println(i);
}
    System.out.println("Estos son los vuelos confirmados");
}

public static void deleteFlightByMenu(User user){
    ArrayList<Flight> userFlights=Persistence.getFlightByUser(user);
    Flight flight=Executive.getFlightByMenu(userFlights);
    if(!(flight==new Flight())){
    boolean flag=Executive.canDeleteFlight(flight);
    if(flag==true){
    Executive.confirmDeleteFlight(flight);
    }    
    }    
}

public static Flight getFlightByMenu(ArrayList<Flight> flights){
    Flight flight=new Flight();
    boolean flag=false;
    do{System.out.println("Ingrese el ID del vuelo a cancerlar, o Enter para volver atras");
    String id;
    id=scanner.nextLine();
    switch(id){
        case "":
            System.out.println("Se ha cancelado la operacion");
            flag=true;
            break;
        default:
    for(Flight i: flights){
    if(id.equals(i.getId())){
    flight=i;
    flag=true;
    }}    
break;}
if(flag==false){
    System.out.println("La opcion ingresada no es válida");
}    
}while(flag==false);
return flight;
}

public static boolean canDeleteFlight(Flight flight){
boolean can=false;
LocalDate now=LocalDate.now();
LocalDate flightDate=flight.getDate();
if(now.until(flightDate,ChronoUnit.DAYS)<1){
    System.out.println("No puede cancelarce un vuelo con menos de 24 horas de anticipación");
}else{
    System.out.println("El vuelo puede cancelarce");    
can=true;
}
return can;
}

public static void confirmDeleteFlight(Flight flight){
    boolean flag=false;
    String input;
    System.out.println("El vuelo seleccionado es el siguiente:\n"+flight.toString());
    System.out.println("¿Desea cancelarlo?\n1.Si\n2.No");
    do{input=scanner.nextLine();
    switch(input){
        case "1":
            Persistence.deleteFlight(flight);
            System.out.println("El vuelo ha sido cancelado");
            flag=true;
            break;
        case "2":
            System.out.println("El vuelo no se cancelo");
            flag=true;
            break;
        default:
            System.out.println("No ha ingresado una opción válida");
            break;
    }
    }while(flag==false);
}
}
