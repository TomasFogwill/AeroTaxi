
package models;



public class Route {
   public City origin;//ciudad de origen
   public City destiny;//ciudad de destino
   public int distance;

    public City getOrigin() {
        return origin;
    }

    public void setOrigin(City origin) {
        this.origin = origin;
    }

    public City getDestiny() {
        return destiny;
    }

    public void setDestiny(City destiny) {
        this.destiny = destiny;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
   
   
  
   public Route(){ 
        } 

   public Route(City origin, City destiny) {
        this.origin = origin;
        this.destiny = destiny;
            }
   
   public int calcDist(){
       int distance=0;
       City c1=origin;
       City c2=destiny;
       if((c1==City.BUENOS_AIRES&&c2==City.CORDOBA)||(c2==City.BUENOS_AIRES&&c1==City.CORDOBA)){
       distance=695;
       }if((c1==City.BUENOS_AIRES&&c2==City.SANTIAGO_DE_CHILE)||(c2==City.BUENOS_AIRES&&c1==City.SANTIAGO_DE_CHILE)){
       distance=1400;
       }if((c1==City.BUENOS_AIRES&&c2==City.MONTEVIDEO)||(c2==City.BUENOS_AIRES&&c1==City.MONTEVIDEO)){
       distance=950;
       }if((c1==City.CORDOBA&&c2==City.MONTEVIDEO)||(c2==City.CORDOBA&&c1==City.MONTEVIDEO)){
       distance=1190;
       }if((c1==City.CORDOBA&&c2==City.SANTIAGO_DE_CHILE)||(c2==City.CORDOBA&&c1==City.SANTIAGO_DE_CHILE)){
       distance=1050;
       }if((c1==City.MONTEVIDEO&&c2==City.SANTIAGO_DE_CHILE)||(c2==City.MONTEVIDEO&&c1==City.SANTIAGO_DE_CHILE)){
       distance=2100;
       }
       return distance;
       }


}
    

        
        
 
        
    

