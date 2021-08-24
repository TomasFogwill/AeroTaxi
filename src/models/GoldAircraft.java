package models;


public class GoldAircraft extends Aircraft{
    String conContWifi;
    private final String categ="Gold";
    public GoldAircraft(String conContWifi, String id, float maxFuel, float costXkm, int maxPas, float vMax, Kind kindProp) {
        super(id, maxFuel, costXkm, maxPas, vMax, kindProp);
        this.conContWifi = conContWifi;
    }
    
@Override
    public String toString() {
        return "Los datos del avion son:\nCategoría: "+categ+"\nId:"+id+"\nCapacidad máxima de combustible: "+maxFuel+"\nCosto por km de vuelo: "+costXkm+"\nCapacidad máxima de pasajeros: "+maxPas+"\nVelocidad máxima: "+vMax+"\nTipo de propulsión: "+kindProp+"\nWiFi continuo: "+conContWifi;
    }
}
