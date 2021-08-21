
package dao;

import models.Aircraft;
import models.BronzeAircraft;
import models.GoldAircraft;
import models.SilverAircraft;
import models.User;

public class Persistence {
    Aircraft flight1=new BronzeAircraft(25000, 2, 2500, 900, "A pistones");
    Aircraft flight2=new SilverAircraft(30000, 3, 2000, 950, "A reaccion");
    Aircraft flight3=new GoldAircraft(45000, 4, 2000, 1000, "A hélice");
    User user1=new User("Tomas","Fogwill","40129369",24);
    User user2=new User("admin","","0000",24);
    User user3=new User("Pepe","Martinez","38999999",21);
    
    
}
