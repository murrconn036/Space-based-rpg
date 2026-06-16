# Space-based RPG game  

You awake in a strange room, alone and surrounded by flashing red lights. An automated voice repeats “SYSTEM FAILURE. LOSS OF CREW DETECTED.” Then, the lights go out, and the room turns black. You awake again, this time in a room with white, fluorescent lights, and a constant background hum. You slowly rise to your feet, a wave of dizziness striking your head. You have no memory of the past, and have no clue where you are. As you begin to explore your surroundings, you find the bodies of your crew laying on the floor. They have facial expressions of horror, but no scratches or marks on their bodies. You then find a window, and realize that you are not on Earth.  

# Outline: 
This program will consist of multiple classes, with a Player class and an Object class, with potential for expansion. The data will be stored in ArrayLists before being saved to separate files for the character and objects. The program will also be capable of loading the save data from the file. The general gameplay will be to choose your own path, with the game deciding consequences based on the user’s decisions. 

## Sample Output: 

Select an option: 
1.  Open the airlock 
2. Explore your surroundings 
3. Move to the next room 
4. Save and quit

This project is basically a framework for a RPG game, written entirely in Java. The game is mostly functional, but I ran out of time to extend the actual story. 
It features 3 classes, a playerClass, a ObjectClass, and a HammerClass. The hammerClass is a child of the ObjectClass, so it inherits all of the ObjectClass methods 

## playerClass methods: 
|     Function      | Function Purpose | 
| ---------------   | ---------------- |
|   Constructor     | Accepts the name, number of health points, the current level the player is on, and the most recent decision the player made | 
| getPlayerName()   | returns the name of the player as a string | 
| getHealthPoints() | returns the health of the player as a double | 
| getLevelNum()     | returns the current level the player is on as an integer | 
| getLastUserSelection() | returns the last decision the player made as an integer | 
| setLevelNum(int levelNum) | sets the current level the user is on, accepts an integer as input | 
| setLastUserSelection(int lastUserSelection) | sets the last decision that the user made, accepts an integer | 
| setHealthPoints(int healthPoints) | sets the health of the player, accepts an integer | 
| addItem(ObjectClass object) | adds a object to the player's inventory, accepts a ObjectClass object |
| getSaveData() | formats all of the classes fields into a string, then returns that string | 
| getInventoryData() | returns the inventory of the player, as an arrayList | 

## objectClass methods: 
|     Function      | Function Purpose | 
| ---------------   | ---------------- | 
| objectClass(int objectIntegrity, String objectName) | Constructor for multi-use objects | 
| objectClass(String objectName) | Constructor for single-use objects |
| damageItem() | Randomly damages the object when it's used (for multi-use objects) | 
| getObjectIntegrity() | Returns the current integrity of the object (for multi-use objects)| 
| getObjectName() | Returns the name of the object | 

## HammerObject methods: 
|     Function      | Function Purpose | 
| ---------------   | ---------------- |
|   Constructor     | creates a hammer object, and inherits all of the objectClass methods | 
| damageItem(int amount) | was meant to be overridden the objectClass method, but polymorphisim is weird, so it subtracts the amount of health points submitted | 
