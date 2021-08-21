
package dao;

import models.Aircraft;
import models.BronzeAircraft;
import models.GoldAircraft;
import models.SilverAircraft;
import models.User;

public class Persistence {
    public Aircraft flight1=new BronzeAircraft(25000, 2, 2500, 900, "A pistones");
    public Aircraft flight2=new SilverAircraft(30000, 3, 2000, 950, "A reaccion");
    public Aircraft flight3=new GoldAircraft(45000, 4, 2000, 1000, "A hélice");
    public User user1=new User("Tomas","Fogwill","40129369",24);
    public User user2=new User("admin","","0000",24);
    public User user3=new User("Pepe","Martinez","38999999",21);
    
    
}
