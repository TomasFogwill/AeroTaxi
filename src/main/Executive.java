package main;

import dao.Persistence;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
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

    public static User initial() {
        boolean flag = false;
        User user = null;
        do {
            System.out.println("\n1.Iniciar sesión\n2.Registrarse\n3.Salir");
            System.out.print("Seleccione la opcion correspondiente: ");
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    user = Executive.logIn();
                    if (user != null) {
                        flag = true;
                    } else {
                        System.out.println("\nEl DNI ingresado no está registrado");
                    }
                    break;
                case "2":
                    user = Executive.register();
                    if (user != null) {
                        flag = true;
                    }
                    break;
                case "3":
                    flag = true;
                    break;
                case "administration":
                    user = Admin.user();
                    if (user != null) {
                        flag = true;
                    } else {
                        System.out.println("Opcion inválida");
                    }
                    break;
                default:
                    System.out.println("\nLa opcion ingresada no es válida\n");
                    break;
            }
        } while (flag == false);
        return user;
    }

    public static User logIn() {
        ArrayList<User> users = Persistence.getUsers();
        User user = new User();
        System.out.print("Ingrese su DNI para iniciar sesión: ");
        String id = scanner.nextLine();
        user = Executive.getUserById(id);
        return user;
    }

    public static User getUserById(String id) {
        ArrayList<User> users = Persistence.getUsers();
        User user =null;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                user = users.get(i);
            }
        }
        return user;
    }

    public static User register() {
        System.out.println("Vamos a registrar su usuario");
        User user = new User();
        user.setBest("Ninguna");
        user.setFlightTot(0);
        Executive.setUserId(user);
        if (user.getId()!=null) {
            Executive.setUserName(user);
            Executive.setUserSurname(user);
            Executive.setUserAge(user);
            Persistence.saveNewUser(user);
            scanner.nextLine();
        } else {
            user = null;
        }
        return user;
    }

    public static void setUserId(User user) {
        boolean flag = false;
        String id = new String();
        do {
            System.out.print("Ingrese su DNI: ");
            if (!scanner.hasNextInt()) {
                System.out.println("No a ingresado un número válido");
                scanner.nextLine();
            } else {
                id = scanner.nextLine();
                boolean available=Executive.isUserIdAvailable(id);
                if (!available) {
                    boolean ask = Executive.askUserNewId();
                    if (!ask) {
                        user.setId(null);
                        flag = true;
                    }}else{
                    flag=true;
                    user.setId(id);
                    }                            
        }} while (flag == false);
    }

    public static boolean isUserIdAvailable(String id) {
        boolean available = true;
        ArrayList<User> users = Persistence.getUsers();
        for (User i : users) {
            if (id.equals(i.getId())) {
                available = false;
            }
        }
        if (available == false) {
            System.out.println("El DNI ya está registrado con otro usuario");
        }
        return available;
    }

    public static boolean askUserNewId() {
        boolean answer = false;
        boolean flag = false;
        String input = new String();
        do {
            System.out.println("1.Volver atras\n2.Ingresar otro DNI");
            input = scanner.nextLine();
            switch (input) {
                case "1":
                    flag = true;
                    break;
                case "2":
                    flag = true;
                    answer = true;
                    break;
                default:
                    break;
            }
        } while (flag == false);
        return answer;
    }

    public static void setUserName(User user) {
        System.out.print("Ingrese su nombre: ");
        String name = scanner.nextLine();
        user.setName(name);
    }

    public static void setUserSurname(User user) {
        System.out.print("Ingrese su apellido: ");
        String surname = scanner.nextLine();
        user.setSurname(surname);
    }

    public static void setUserAge(User user) {
        boolean flag = false;
        int age = 0;
        do {
            System.out.print("Ingrese su edad: ");
            if (!scanner.hasNextInt()) {
                System.out.println("No a ingresado un número válido");
                scanner.nextLine();
            } else {
                age = scanner.nextInt();
                flag = true;
            }
        } while (flag == false);
        user.setAge(age);
    }

    public static void newFlightByMenu(User user) {
        boolean flag = false;
        Flight flight = new Flight();
        flight.setUser(user);
        System.out.println("\nBienvenido a la gestión de contratación de vuelos");
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
        LocalDate today = LocalDate.now();
        LocalDate flightDate = LocalDate.now();
        do {
            System.out.print("\nIngrese la fecha a viajar de la siguiente forma dd/mm/yyyy: ");
            String inputDate = scanner.nextLine();
            try {
                flightDate = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (java.time.format.DateTimeParseException ex) {
                System.out.println("La fecha no fue ingresada correctamente");
            }
          if(today.until(flightDate,ChronoUnit.DAYS) < 0){
              System.out.println("No puede gestionar un vuelo en el pasado...");
          }else{
              if(!flightDate.equals(today)){
              flag=true;}
          }  
        } while (flag == false);
        flight.setDate(flightDate);

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
        System.out.println("\nLos vuelos disponibles conectan las siguientes ciudades:\n1.Córdoba\n2.Buenos Aires\n3.Montevideo\n4.Santiago de Chile");
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
            System.out.print("Ingrese el número que corresponda a la ciudad de destino: ");
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
            System.out.print("\nIndique la cantidad de pasajeros que lo acompañaran: ");
            if (!scanner.hasNextInt()) {
                System.out.println("No ha ingresado un número válido");
                scanner.nextLine();
            } else {
                flight.setnPassengers(scanner.nextInt() + 1);
                flag = true;
            }
        } while (flag == false);
    scanner.nextLine();
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
        if (cantPassengers > a+1) {
            System.out.println("No tenemos aviones disponibles para esa cantidad de pasajeros");
        } else {
            isEmpty = false;
        }
        return isEmpty;
    }

    public static void setFlightAircraftByMenu(Flight flight) {
        ArrayList<Aircraft> available = Persistence.getAvailableAircraft(flight.getDate());
        System.out.println("\nA continuación se le presentan las fichas de los aviones disponibles:\n");
        for (Aircraft i : available) {
            if (i.getMaxPas() > flight.getnPassengers()) {
                System.out.println(i.userString() + "\n");
            }
        }
        boolean flag = false;
        do {
            System.out.println("Ingrese el ID deseado o ingrese Enter para salir");
            String anames = scanner.nextLine();
            switch (anames) {
                case "":
                    flag = true;
                    break;
                default:
                    for (Aircraft i : available) {
                        if (anames.equals(i.getId().toString())) {
                            flight.setAircraft(i);                           
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
        }else{
        flight.setId(UUID.randomUUID());
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
        System.out.println("\nEl ticket de vuelo:\n" + flight.toString());
        do {
            System.out.println("\n¿Confirmar?\n1.Si\n2.No");
            String v = scanner.nextLine();
            switch (v) {
                case "1":
                    Executive.setUserNewData(flight);
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

    public static void setUserNewData(Flight flight) {
        ArrayList<User> users = Persistence.getUsers();
        ArrayList<User> newUsers=new ArrayList<User>();
        String category = flight.getAircraft().getCategory();
        float cost = flight.getCost();
        User user = flight.getUser();
        for(User i: users){
        if(!user.getId().equals(i.getId()))
        newUsers.add(i);
        }
        Executive.setUserBestCategory(user, category);
        Executive.setUserTotalFlight(user, cost);
        newUsers.add(user);
        Persistence.saveNewUserList(newUsers);
    }

    public static void setUserBestCategory(User user, String flightCategory) {
        String actualCategory = user.getBest();
        if ("Bronce".equals(flightCategory)) {
            if ("Ninguna".equals(actualCategory)) {
                user.setBest(flightCategory);
            }
        }
        if ("Silver".equals(flightCategory)) {
            if ("Ninguna".equals(actualCategory) || "Bronce".equals(actualCategory)) {
                user.setBest(flightCategory);
            }
        }
        if ("Gold".equals(flightCategory)) {
            user.setBest(flightCategory);
        }
    }

    public static void setUserTotalFlight(User user, float cost) {
        float totalFlight = user.getFlightTot() + cost;
        user.setFlightTot(totalFlight);
    }

    public static void consultFlight(User user) {
        boolean flag = false;
        String answer;
        boolean areFlights=Executive.getUserFlightListByMenu(user);
        if(areFlights==true){
        do {
            System.out.println("¿Desea cancelar alguno?\n1.Si\n2.No");
            answer = scanner.nextLine();
            switch (answer) {
                case "1":
                    Executive.deleteFlightByMenu(user);
                    flag = true;
                    break;
                case "2":
                    System.out.println("Nada será cancelado");
                    flag = true;
                    break;
                default:
                    System.out.println("No ha ingresado una opcion válida");
                    break;
            }
        } while (flag == false);}
    }

    public static boolean getUserFlightListByMenu(User user) {
        boolean areFlights=false;
        ArrayList<Flight> userFlights = Persistence.getFlightByUser(user);
        if(userFlights.isEmpty()){
            System.out.println("\nNo hay vuelos para mostrar");
        }else{
        for (Flight i : userFlights) {
            System.out.println("\n"+i);
        }
        System.out.println("\nEstos son los vuelos confirmados");
        areFlights=true;
        }
    return areFlights;    
    }

    public static void deleteFlightByMenu(User user) {
        ArrayList<Flight> userFlights = Persistence.getFlightByUser(user);
        Flight flight = Executive.getFlightByMenu(userFlights);
        if (flight != null) {
            boolean flag = Executive.canDeleteFlight(flight);
            if (flag == true) {
                Executive.confirmDeleteFlight(flight);
            }
        }
    }

    public static Flight getFlightByMenu(ArrayList<Flight> flights) {
        Flight flight = null;
        boolean flag = false;
        do {
            System.out.println("Ingrese el ID del vuelo a cancerlar, o Enter para volver atras");
            String id;
            id = scanner.nextLine();
            switch (id) {
                case "":
                    System.out.println("Se ha cancelado la operacion");
                    flag = true;
                    break;
                default:
                    for (Flight i : flights) {
                        if (id.equals(i.getId().toString())) {
                            flight = i;
                            flag = true;
                        }
                    }
                    break;
            }
            if (flag == false) {
                System.out.println("La opcion ingresada no es válida");
            }
        } while (flag == false);
        return flight;
    }

    public static boolean canDeleteFlight(Flight flight) {
        boolean can = false;
        LocalDate now = LocalDate.now();
        LocalDate flightDate = flight.getDate();
        if (now.until(flightDate, ChronoUnit.DAYS) < 2) {
            System.out.println("\nNo puede cancelarce un vuelo con menos de 24 horas de anticipación\n");
        } else {
            System.out.println("\nEl vuelo puede cancelarce\n");
            can = true;
        }
        return can;
    }

    public static void confirmDeleteFlight(Flight flight) {
        boolean flag = false;
        String input;
        System.out.println("El vuelo seleccionado es el siguiente:\n" + flight.toString());
        System.out.println("¿Desea cancelarlo?\n1.Si\n2.No");
        do {
            input = scanner.nextLine();
            switch (input) {
                case "1":
                    Persistence.deleteFlight(flight);
                    Executive.deleteFlightDataFromUser(flight);                    
                    System.out.println("El vuelo ha sido cancelado");
                    flag = true;
                    break;
                case "2":
                    System.out.println("El vuelo no se cancelo");
                    flag = true;
                    break;
                default:
                    System.out.println("No ha ingresado una opción válida");
                    break;
            }
        } while (flag == false);
    }

public static void deleteFlightDataFromUser(Flight flight){
ArrayList<User> users = Persistence.getUsers();
        ArrayList<User> newUsers=new ArrayList<User>();
        User user = flight.getUser();
        for(User i: users){
        if(!user.getId().equals(i.getId()))
        newUsers.add(i);
        }
        user.setBest(Executive.getOldCategory(user)); 
        float flightTotal=user.getFlightTot();
        user.setFlightTot(flightTotal-flight.getCost());
        System.out.println("La cuenta da: "+user.getFlightTot());
        newUsers.add(user);
        Persistence.saveNewUserList(newUsers);    
}

    public static String getOldCategory(User user) {
        String oldCategory = "Ninguna";
        ArrayList<Flight> flights = Persistence.getFlightByUser(user);
        ArrayList<String> categories = new ArrayList<String>();
        for (Flight i : flights) {
            categories.add(i.getAircraft().getCategory());
        }
        if (!categories.isEmpty()) {
            for (String i : categories) {
                if (i.equals("Bronce")) {
                    oldCategory = "Bronce";
                }
            }
            for (String i : categories) {
                if (i.equals("Silver")) {
                    oldCategory = "Silver";
                }
            }
            for (String i : categories) {
                if (i.equals("Gold")) {
                    oldCategory = "Gold";
                }
            }
        }
        return oldCategory;
    }
}
