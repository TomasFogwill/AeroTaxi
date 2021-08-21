package models;


public class GoldAircraft extends Aircraft{
    boolean conContWifi;//true si la conexion es cont, false si no

    public GoldAircraft(float maxFuel, float costXkm, int maxPas, float vMax, String kindProp) {
        super(maxFuel, costXkm, maxPas, vMax, kindProp);
    }

}
