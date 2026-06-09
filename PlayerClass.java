import java.util.*;

public class PlayerClass {
    private String playerName;  
    public int healthPoints; 
    private ArrayList<ObjectClass> inventory; 
    public PlayerClass(String playerName, int healthPoints){
        this.playerName = playerName;  
        this.healthPoints = healthPoints; 
        
    }

    public int getHealthPoints(){
        return this.healthPoints; 
    } 

    public void setHealthPoints(int amount){
        this.healthPoints = amount; 
    }
}
