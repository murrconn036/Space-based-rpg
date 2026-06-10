public class ObjectClass {
    private int objectIntegrity; 
    final private String objectName;  
    // Class constructor for multi-use objects // 
    public ObjectClass(int objectIntegrity, String objectName){
        this.objectIntegrity = objectIntegrity; 
        this.objectName = objectName; 
    } 
    // Class constructor for single-use objects 
    public ObjectClass(String objectName){
        this.objectName = objectName; 
    }
    
    public void damageItem(){
        this.objectIntegrity -= (int)(Math.random() * (100 - 1 + 1) + 1);
    } 

    public int getObjectIntegrity(){
        return this.objectIntegrity; 
    }

    public String getObjectName(){
        return this.objectName; 
    }
}
