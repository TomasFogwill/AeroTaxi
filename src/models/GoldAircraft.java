package models;

import java.util.Locale;
import java.util.UUID;


public class GoldAircraft extends Aircraft{
    public final String category="Gold";
    String conContWifi;

    public GoldAircraft(String conContWifi, UUID id, float maxFuel, float costXkm, int maxPas, float vMax, Kind kindProp) {
        super(id, maxFuel, costXkm, maxPas, vMax, kindProp,"Gold");
        this.conContWifi = conContWifi;
    }

    

    public GoldAircraft() {
    }

    public String getCategory() {
        return category;
    }


    public String getConContWifi() {
        return conContWifi;
    }

    public void setConContWifi(String conContWifi) {
        this.conContWifi = conContWifi;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public float getMaxFuel() {
        return maxFuel;
    }

    public void setMaxFuel(float maxFuel) {
        this.maxFuel = maxFuel;
    }

    public float getCostXkm() {
        return costXkm;
    }

    public void setCostXkm(float costXkm) {
        this.costXkm = costXkm;
    }

    public int getMaxPas() {
        return maxPas;
    }

    public void setMaxPas(int maxPas) {
        this.maxPas = maxPas;
    }

    public float getvMax() {
        return vMax;
    }

    public void setvMax(float vMax) {
        this.vMax = vMax;
    }

    public Kind getKindProp() {
        return kindProp;
    }

    public void setKindProp(Kind kindProp) {
        this.kindProp = kindProp;
    }

    


    
    
@Override
    public String toString() {
        return "Categoría: "+category+"\nId:"+id+"\nCapacidad máxima de combustible: "+maxFuel+"\nCosto por km de vuelo: "+costXkm+"\nCapacidad máxima de pasajeros: "+maxPas+"\nVelocidad máxima: "+vMax+"\nTipo de propulsión: "+kindProp+"\nWiFi continuo: "+conContWifi;
    }

@Override
public String userString(){
    return "Categoría:"+category+" \nId:"+id+"\nCapacidad máxima de pasajeros: "+maxPas+"\nWiFi continuo: "+conContWifi;
    }

}
