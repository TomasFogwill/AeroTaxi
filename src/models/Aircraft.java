
package models;

public abstract class Aircraft {
    private float maxFuel;//capacidad de combustible
    private float costXkm;// costo por kilómetro de vuelo
    private int maxPas;// capacidad de pasajeros
    private float vMax;// velocidad máx en km/h
    private String kindProp;// tipo de motor de propulsión

    public Aircraft(float maxFuel, float costXkm, int maxPas, float vMax, String kindProp) {
        this.maxFuel = maxFuel;
        this.costXkm = costXkm;
        this.maxPas = maxPas;
        this.vMax = vMax;
        this.kindProp = kindProp;
    }
    
    

    
    }
    
    
            

