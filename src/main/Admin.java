package main;

import dao.Persistence;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import models.Aircraft;
import models.BronzeAircraft;
import models.Flight;
import models.GoldAircraft;
import models.Kind;
import models.SilverAircraft;
import models.User;

public abstract class Admin {

    public static Scanner scanner = new Scanner(System.in);

    public static User user() {
        User user = null;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el código: ");
        String code = scanner.nextLine();
        if ("1234".equals(code)) {
            user = new User("1234","1234","1234",1234);
        }
        return user;
    }

    public static void newAircraft() {
        Aircraft aircraft = Admin.setAircraftClass();     
        Admin.setAircraftId(aircraft);
        Admin.setAircraftMaxFuel(aircraft);
        Admin.setAircraftCostXkm(aircraft);
        Admin.setAircraftMaxPas(aircraft);
        Admin.setAircraftMaxV(aircraft);
        Admin.setAircraftKindOfProp(aircraft);        
        Admin.confirmAircraft(aircraft);
    }

    public static void setAircraftId(Aircraft aircraft) {
        UUID id=UUID.randomUUID();;
        aircraft.setId(id);
    }

    public static void setAircraftMaxFuel(Aircraft aircraft) {
        float maxFuel = 0;
        boolean flag = false;
        do {
            System.out.print("\nIngrese la capacidad de combustible del avión: ");
            if (!scanner.hasNextFloat()) {
                System.out.println("No a ingresado un número válido");
                scanner.nextLine();
            } else {
                maxFuel = scanner.nextFloat();
                flag = true;
            }
        } while (flag == false);
        aircraft.setMaxFuel(maxFuel);
        scanner.nextLine();
    }

    public static void setAircraftCostXkm(Aircraft aircraft) {
        float costXkm = 0;
        boolean flag = false;
        do {
            System.out.print("Ingrese el costo por km de vuelo: ");
            if (!scanner.hasNextFloat()) {
                System.out.println("No a ingresado un número válido");
                scanner.nextLine();
            } else {
                costXkm = scanner.nextFloat();
                flag = true;
            }
        } while (flag == false);
        aircraft.setCostXkm(costXkm);
    }

    public static void setAircraftMaxPas(Aircraft aircraft) {
        int maxPas = 0;
        boolean flag = false;
        do {
            System.out.print("Ingrese la capacidad máxima de pasajeros: ");
            if (!scanner.hasNextInt()) {
                System.out.println("No a ingresado un número válido");
                scanner.nextLine();
            } else {
                maxPas = scanner.nextInt();
                if (maxPas < 1) {
                    System.out.println("No ha ingresado un número válido");
                } else {
                    flag = true;
                }
            }
        } while (flag == false);
        aircraft.setMaxPas(maxPas);
    }

    public static void setAircraftMaxV(Aircraft aircraft) {
        float vMax = 0;
        boolean flag = false;
        do {
            System.out.print("Ingrese la velocidad máxima de vuelo: ");
            if (!scanner.hasNextFloat()) {
                System.out.println("No a ingresado un número válido");
                scanner.nextLine();
            } else {
                vMax = scanner.nextFloat();
                flag = true;
            }
        } while (flag == false);
        scanner.nextLine();
        aircraft.setvMax(vMax);
    }

    public static void setAircraftKindOfProp(Aircraft aircraft) {
        Kind kindProp = null;
        boolean flag = false;
        do {
            System.out.println("\nLos posibles tipos de propuslsión son los sigientes\n1.Motor a reacción\n2.Motor a hélice\n3.Motor a pistones ");
            System.out.print("Ingrese el número correspondiente: ");
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    kindProp = Kind.MOTOR_A_REACCION;
                    flag = true;
                    break;
                case "2":
                    kindProp = Kind.MOTOR_A_HELICE;
                    flag = true;
                    break;
                case "3":
                    kindProp = Kind.MOTOR_A_PISTONES;
                    flag = true;
                    break;
                default:
                    System.out.println("La opcion ingresada no es válida");
                    break;
            }
        } while (flag == false);
        aircraft.setKindProp(kindProp);
    }

    public static Aircraft setAircraftClass() {
        Aircraft aircraft = null;
        boolean flag1 = false;
        System.out.println("\nA continuación se le muestran las categorías posibles de avión ");
        do {
            System.out.println("1.Bronze\n2.Silver\n3.Gold");
            System.out.print("Ingrese el número correspondiente: ");
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    aircraft = new BronzeAircraft();
                    flag1 = true;
                    break;
                case "2":
                    aircraft = new SilverAircraft();
                    flag1 = true;
                    break;
                case "3":
                    aircraft = new GoldAircraft();
                    Admin.setGoldAircraftWiFi((GoldAircraft) aircraft);
                    flag1 = true;
                    break;
                default:
                    System.out.println("No ingreso una opción válida");
                    break;
            }
        } while (flag1 == false);
        return aircraft;
    }

    public static void setGoldAircraftWiFi(GoldAircraft aircraft) {
        boolean flag = false;
        String input;
        System.out.println("¿El avión posee conexión continua de Wifi?\n1.Si\n2.No");
        do {
            System.out.print("Ingrese la opción correcta: ");
            input = scanner.nextLine();
            switch (input) {
                case "1":
                    aircraft.setConContWifi("Si");
                    flag = true;
                    break;
                case "2":
                    aircraft.setConContWifi("No");
                    flag = true;
                    break;
                default:
                    System.out.println("No ingreso una opción válida");
                    break;
            }
        } while (flag = false);
    }

    public static void confirmAircraft(Aircraft aircraft) {
        boolean flag = false;
        String input;
        System.out.println("\n" + aircraft.toString());
        do {
            System.out.println("\n¿Confirmar y guardar?\n1.Si\n2.No");
            input = scanner.nextLine();
            switch (input) {
                case "1":
                    Persistence.saveNewAircraft(aircraft);
                    System.out.println("El avión ha sido guardado correctamente");
                    flag = true;
                    break;
                case "2":
                    System.out.println("No se han guardado los cambios");
                    flag = true;
                    break;
                default:
                    System.out.println("No ha ingresado una opción válida");
                    break;
            }
        } while (flag = false);
    }

    public static void flightList() {
        boolean flag = false;
        LocalDate date = LocalDate.now();
        do {
            System.out.print("Ingrese la fecha a consultar de la siguiente forma dd/mm/yyyy: ");
            String inputDate = scanner.nextLine();
            try {
                date = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                flag = true;
            } catch (java.time.format.DateTimeParseException ex) {
                System.out.println("La fecha no fue ingresada correctamente");
            }
        } while (flag == false);
        ArrayList<Flight> flights = Persistence.getFlightByDate(date);
        if (flights.isEmpty()) {
            System.out.println("\nNo hay vuelos para mostrar");
        } else {
            for (Flight i : flights) {
                System.out.println("\n"+i.toString());
            }
            System.out.println("\nEsta es la lista de vuelos");
        }

    }

    public static void userList() {
        ArrayList<User> users = Persistence.getUsers();
        for (User i : users) {
            System.out.println("\n"+i.toString());
        }
        System.out.println("\nEsta es la lista de usuarios");
    }

}
