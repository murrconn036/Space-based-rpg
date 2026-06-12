public class HammerObject extends ObjectClass {
    private int objectIntegrity; 
    public HammerObject(int objectIntegrity, String objectName){
        super(objectIntegrity, objectName); 
        this.objectIntegrity = objectIntegrity; 
    } 
    
    public void damageItem(int amount){
        this.objectIntegrity = this.objectIntegrity - amount; 
    }
}
