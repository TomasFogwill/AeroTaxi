package models;


public class GoldAircraft extends Aircraft{
    boolean conContWifi;//true si la conexion es cont, false si no

    public GoldAircraft(boolean conContWifi, String id, float maxFuel, float costXkm, int maxPas, float vMax, String kindProp) {
        super(id, maxFuel, costXkm, maxPas, vMax, kindProp);
        this.conContWifi = conContWifi;
    }
    

}
