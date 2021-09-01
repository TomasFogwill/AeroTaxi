package main;

import dao.Persistence;
import java.util.ArrayList;
import java.util.Scanner;
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
        User user = new User();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el código: ");
        String code = scanner.nextLine();
        if ("1234".equals(code)) {
            user = new User("scanner=new", "", "codigo numerico", 5000);
            return user;
        }
        return user;
    }

    public static void newAircraft() {
        Aircraft aircraft = null;
        Admin.setAircraftId(aircraft);
        Admin.setAircraftMaxFuel(aircraft);
        Admin.setAircraftCostXkm(aircraft);
        Admin.setAircraftMaxPas(aircraft);
        Admin.setAircraftMaxV(aircraft);
        Admin.setAircraftKindOfProp(aircraft);
        aircraft = Admin.setAircraftClass(aircraft);
        Admin.confirmAircraft(aircraft);
    }

    public static void setAircraftId(Aircraft aircraft) {
        boolean flag = false;
        String id = new String();
        do {
            System.out.print("Ingrese un código numérico de identificación: ");
            if (!scanner.hasNextInt()) {
                System.out.println("No ha ingresado un número válido");
                scanner.nextLine();
            } else {
                id = scanner.nextLine();
                if (isThisIdAvailable(id)) {
                    flag = true;
                } else {
                    System.out.println("El código ingresado ya existe");
                }
            }
        } while (flag == false);
        aircraft.setId(id);
    }

    public static boolean isThisIdAvailable(String id) {
        boolean available = true;
        Aircraft[] aircrafts = Persistence.getAircrafts();
        for (int i = 0; i < aircrafts.length; i++) {
            if (id == aircrafts[i].getId()) {
                available = false;
            }
        }
        return available;
    }

    public static void setAircraftMaxFuel(Aircraft aircraft) {
        float maxFuel = 0;
        boolean flag = false;
        do {
            System.out.print("Ingrese la capacidad de combustible del avión: ");
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
            System.out.println("Los posibles tipos de propuslsión son los sigientes\n1.Motor a reacción\n2.Motor a hélice\n3.Motor a pistones ");
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

    public static Aircraft setAircraftClass(Aircraft aircraft) {
        boolean flag1 = false;
        System.out.println("A continuación se le muestran las categorías posibles de avión ");
        do {
            System.out.println("1.Bronze\n2.Silver\n3.Gold");
            System.out.print("Ingrese el número correspondiente: ");
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    aircraft = new BronzeAircraft(aircraft.getId(), aircraft.getMaxFuel(), aircraft.getCostXkm(), aircraft.getMaxPas(), aircraft.getvMax(), aircraft.getKindProp());
                    flag1 = true;
                    break;
                case "2":
                    aircraft = new SilverAircraft(aircraft.getId(), aircraft.getMaxFuel(), aircraft.getCostXkm(), aircraft.getMaxPas(), aircraft.getvMax(), aircraft.getKindProp());
                    flag1 = true;
                    break;
                case "3":
                    boolean flag2 = false;
                    String u;
                    System.out.println("¿El avión posee conexión continua de Wifi?\n1.Si\n2.No");
                    do {
                        System.out.print("Ingrese la opción correcta: ");
                        u = scanner.nextLine();
                        switch (u) {
                            case "1":
                                aircraft = new GoldAircraft("Si", aircraft.getId(), aircraft.getMaxFuel(), aircraft.getCostXkm(), aircraft.getMaxPas(), aircraft.getvMax(), aircraft.getKindProp());
                                flag2 = true;
                                break;
                            case "2":
                                aircraft = new GoldAircraft("No", aircraft.getId(), aircraft.getMaxFuel(), aircraft.getCostXkm(), aircraft.getMaxPas(), aircraft.getvMax(), aircraft.getKindProp());
                                flag2 = true;
                                break;
                            default:
                                System.out.println("No ingreso una opción válida");
                                break;
                        }
                    } while (flag2 = false);
                    flag1 = true;
                    break;
                default:
                    System.out.println("No ingreso una opción válida");
                    break;
            }
        } while (flag1 == false);
        return aircraft;
    }

    public static void confirmAircraft(Aircraft aircraft) {
        boolean flag = false;
        String input;
        System.out.println(aircraft.toString());
        do {
            System.out.println("¿Confirmar y guardar?\n1.Si\n2.No");
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
        Flight[] flights = Persistence.getFlights();
        for (int i = 0; i < flights.length; i++) {
            System.out.println(flights[i].toString());
        }
        System.out.println("Esta es la lista de vuelos");
    }

    public static void userList() {
        ArrayList<User> users = Persistence.getUsers();
        for (User i : users) {
            System.out.println(i.toString());
        }
        System.out.println("Esta es la lista de usuarios");
    }

}
