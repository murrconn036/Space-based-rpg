import java.util.*;

public class PlayerClass {
    private final String playerName;  
    private double healthPoints;  
    private int levelNumber; 
    private int lastUserSelection; 
    private ArrayList<ObjectClass> inventory = new ArrayList<>();
    public PlayerClass(String playerName, double healthPoints, int levelNumber, int lastUserSelection){
        this.playerName = playerName;  
        this.healthPoints = healthPoints; 
        this.levelNumber = levelNumber; 
        this.lastUserSelection = lastUserSelection; 
    }

    public String getPlayerName(){
        return this.playerName; 
    }
    public double getHealthPoints(){
        return this.healthPoints; 
    } 

    public int getLevelNum(){
        return this.levelNumber; 
    } 

    public int getLastUserSelection(){
        return lastUserSelection; 
    }

    public void setLevelNum(int levelNum){
        this.levelNumber = levelNum; 
    }

    public void setLastUserSelection(int lastUserSelection){
        this.lastUserSelection = lastUserSelection; 
    }

    public void setHealthPoints(double amount){
        this.healthPoints = amount; 
    }

    public void addItem(ObjectClass item){
        inventory.add(item);
    }
    // Formats the save data in a string seperated by commas then returns that string //
    public String getSaveData(){
        String saveData = this.playerName + "," + this.healthPoints + "," + this.levelNumber + "," + this.lastUserSelection; 
        return saveData; 
    }
    
    public ArrayList<ObjectClass> getInventoryData(){
        return this.inventory; 
    }
}
