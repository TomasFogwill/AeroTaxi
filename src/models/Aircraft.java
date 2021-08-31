
package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
    @JsonSubTypes.Type(value = BronzeAircraft.class, name = "BronzeAircraft"),

    @JsonSubTypes.Type(value = SilverAircraft.class, name = "SilverAircraft"),
            
    @JsonSubTypes.Type(value = GoldAircraft.class, name = "GoldAircraft")}
)

public abstract class Aircraft {
    protected String id;//id para reconocer el avión
    protected float maxFuel;//capacidad de combustible
    protected float costXkm;// costo por kilómetro de vuelo
    protected int maxPas;// capacidad de pasajeros
    protected float vMax;// velocidad máx en km/h
    protected Kind kindProp;// tipo de motor de propulsión
    protected String category;
    

    public Aircraft() {
    }

    public Aircraft(String id, float maxFuel, float costXkm, int maxPas, float vMax, Kind kindProp, String category) {
        this.id = id;
        this.maxFuel = maxFuel;
        this.costXkm = costXkm;
        this.maxPas = maxPas;
        this.vMax = vMax;
        this.kindProp = kindProp;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

   
    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public abstract String userString();
    }
    
    
            

