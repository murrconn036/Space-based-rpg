import java.util.*;

public class PlayerClass {
    private String playerName;  
    private double healthPoints;  
    private int levelNumber;
    private ArrayList<ObjectClass> inventory = new ArrayList<>();
    public PlayerClass(String playerName, double healthPoints, int levelNumber){
        this.playerName = playerName;  
        this.healthPoints = healthPoints; 
        this.levelNumber = levelNumber; 
    }

    public double getHealthPoints(){
        return this.healthPoints; 
    } 

    public int getLevelNum(){
        return this.levelNumber; 
    }

    public void setLevelNum(int levelNum){
        this.levelNumber = levelNum; 
    }

    public void setHealthPoints(double amount){
        this.healthPoints = amount; 
    }

    public void addItem(ObjectClass item){
        inventory.add(item);
    }

    public String getSaveData(){
        String saveData = this.playerName + "," + this.healthPoints + "," + this.levelNumber; 
        return saveData; 
    }

    public ArrayList<ObjectClass> getInventoryData(){
        return this.inventory; 
    }
}
