
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

    public Aircraft(String id, float maxFuel, float costXkm, int maxPas, float vMax, Kind kindProp) {
        this.id = id;
        this.maxFuel = maxFuel;
        this.costXkm = costXkm;
        this.maxPas = maxPas;
        this.vMax = vMax;
        this.kindProp = kindProp;
    }

    public Aircraft() {
    }
    
    
   

   
    
    

    
    }
    
    
            

