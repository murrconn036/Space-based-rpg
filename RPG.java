import java.io.*;
import java.util.*; 


public class RPG {
    static File saveFile; 
    static File inventoryFile;
    public static void main(String[] args) throws IOException {
        try {
            startGame(false, 0);
        } catch (IOException e) {
            System.out.println("An error has occurred, please restart your game");
        }
    }

    public static void startGame(boolean isInputted1, int iterations) throws IOException{
        Scanner input = new Scanner(System.in);
        boolean isInputted2 = false; 
        String newSave = ""; 
        while (!isInputted1) {
            // Prompts the user to either create a new save file or load an existing file // 
            System.out.println("New save file or load a file (a/b)");
            newSave = input.next();
            isInputted1 = true; 
        } 
        // if the user selects a and hasn't inputted anything else, runs the code to create a new save file //
        if (newSave.equalsIgnoreCase("a") || iterations > 0) { 
            System.out.println("Enter your player name (No spaces): ");
            String playerName = input.next(); 
            System.out.println("Enter your save name (No spaces): ");
            String saveName = input.next(); 
            // Creates a new file object in the save_files folder with the inputted file name in CSV format // 
            saveFile = new File("save_files\\" + saveName + ".csv"); 
            inventoryFile = new File("save_files\\" + saveName + "inventory.csv"); 
            // If the file doesn't already exist, creates the new file and delays for 200 milliseconds //
            if (saveFile.createNewFile() && inventoryFile.createNewFile()) {
                System.out.println("File Created!");
            } 
            // if the file already exists, prompts the user with further instructions // 
            else {
                do {
                    // Asks the user if they want to overwrite the existing save file //
                    System.out.println("File already exists, do you want to overwrite? (y/n)"); 
                    String option = input.next(); 
                    // If yes, deletes the existing save file and creates a new one with the same name //
                    if (option.equalsIgnoreCase("y")) {
                        saveFile.delete(); 
                        saveFile.createNewFile(); 
                        inventoryFile.delete(); 
                        inventoryFile.createNewFile(); 
                        isInputted2 = true; 
                    }
                    // If no, calls the method again // 
                    else if (option.equalsIgnoreCase("n")) {
                        startGame(false, 0);  
                    } 
                    // Stops any misinputs // 
                    else {
                        System.out.println("Please enter a letter of either y or n");
                    }
                } while (!isInputted2);
            }
            PlayerClass player = new PlayerClass(playerName, 100, 0, 0);
            checkLevelNum(player);
        } 
        // If the user selects b and hasn't inputted anything else, runs the code to load an existing save file // 
        else if (newSave.equalsIgnoreCase("b") || iterations == 0){
            // Loads the save data for the player // 
            boolean isInputted = false; 
            System.out.println("File name: "); 
            String saveName = input.next(); 
            // Creates new file objects with the inputted file name //
            saveFile = new File("save_files\\" + saveName + ".csv");
            inventoryFile = new File("save_files\\" + saveName + "inventory.csv"); 
            String playerName;
            double playerHealth;
            int levelNum;  
            int lastUserSelection; 
            try {
                Scanner saveFileReader = new Scanner(saveFile); 
                saveFileReader.useDelimiter(",");
                playerName = "";
                playerHealth = 0.0;
                levelNum = 0; 
                lastUserSelection = 0; 
                while (saveFileReader.hasNext()){
                    playerName = saveFileReader.next(); 
                    if (saveFileReader.hasNextDouble()){
                        playerHealth = saveFileReader.nextDouble();
                    }
                    if (saveFileReader.hasNextInt() && saveFileReader.nextInt() >= 0){
                        levelNum = saveFileReader.nextInt();
                    } 
                    else {
                        lastUserSelection = saveFileReader.nextInt(); 
                    }
                }
                saveFileReader.close(); 
                PlayerClass player = new PlayerClass(playerName, playerHealth, levelNum, lastUserSelection); 
                // Loads the save data for the player's inventory // 
                Scanner inventoryFileReader = new Scanner(inventoryFile); 
                inventoryFileReader.useDelimiter(","); 
                String objectName = ""; 
                int objectHealth = 0; 
                while (inventoryFileReader.hasNext()) { 
                    // if the next file input isn't an integer, set the object name to the input // 
                    if (inventoryFileReader.hasNext() && !inventoryFileReader.hasNextInt()) {
                        objectName = inventoryFileReader.next(); 
                    } 
                    else {
                        // sets the object health if the input is an integer // 
                        objectHealth = inventoryFileReader.nextInt(); 
                    }
                    // Checks the object name, then creates an object of that type // 
                    switch (objectName) {
                        case "Hammer":
                            HammerObject hammer = new HammerObject(objectHealth, objectName); 
                            player.addItem(hammer);
                            break;
                    }
                }
                inventoryFileReader.close();
                isInputted = true; 
                checkLevelNum(player);
            } catch (FileNotFoundException e){
                System.out.println("File not found, please try again"); 
                startGame(true, 0);
            }
        } 
        // Covers any misinputs and re-loops the method // 
        else {
            System.out.println("Please enter a letter of y or n");
            startGame(true, 0);
        }

    }

    public static void Introduction(PlayerClass player){
        Scanner input = new Scanner(System.in);
        // Clears the text from the terminal // 
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println("Could not clear terminal");
        }
        // Prints the opening story and options for the user to select, also incrementing the level counter //
        player.setLevelNum(1);
        String print = "You awake in a strange room, alone and surrounded by flashing red lights. An automated voice repeats “SYSTEM FAILURE. LOSS OF CREW DETECTED.” Then, the lights go out, and the room turns black. You awake again, this time in a room with white, fluorescent lights, and a constant background hum. You slowly rise to your feet, a wave of dizziness striking your head. You have no memory of the past, and have no clue where you are. As you begin to explore your surroundings, you find the bodies of your crew laying on the floor. They have facial expressions of horror, but no scratches or marks on their bodies. You then find a window, and realize that you are not on Earth.\n"; 
        printText(print, player);
        String print2 = "Select an option:\n"; 
        printText(print2, player);
        String option1 = "1. Explore your surroundings\n";
        printText(option1, player);
        String option2 = "2. Open the airlock\n";
        printText(option2, player);
        String option3 = "3. Move to the next room\n";
        printText(option3, player);
        String option4 = "4. Save your game\n";
        printText(option4, player);
        // calls the next part of the story // 
        story1(player); 

        
    }

    public static void printText(String print, PlayerClass player){
        for (int i = 0; i < print.length(); i++) {
            System.out.print(print.charAt(i));
            // Delays the character printing by 50 milliseconds, and delays the end of sentences by 200 milliseconds // 
            try {
                Thread.sleep(50);
                if (print.charAt(i) == '.') {
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                    try {
                        saveGame(player, "b");
                    } catch (IOException a) {
                        System.out.println("An error has occurred. Your game could not be saved");
                    }
                System.out.println("An error has occured. Your game has been saved");
            }
        }
    }

    public static void story1(PlayerClass player){
        Scanner input = new Scanner(System.in);
        boolean isInputted = false;
        int userOption;  
        // Checks the user's last selected option from the player class, then sets the userOption to that choice // 
        switch (player.getLastUserSelection()) {
            case -1:
                userOption = 1; 
                break;
            case -2: 
                userOption = 2;
                break;  
            case -3: 
                userOption = 3; 
                break; 
            default:
                // If the user hasn't selected an option yet, this allows them to do so // 
                userOption = input.nextInt();
        }
        /*  Checks the user's input, then executes the story function  
            The while loop loops the switch if the user enters anything other than 1, 2, 3, or 4 */
        while (!isInputted) {  
            switch (userOption) {
                case 1:
                    String print1 = "Exploring your surroundings, you find things scattered all over the floor. One of these things is a hammer, so you pick it up.\n"; 
                    printText(print1, player);
                    // The if loop prevents the item from being duplicated in the player's inventory // 
                    if (player.getLastUserSelection() == 0) {
                        HammerObject hammer = new HammerObject(100, "Hammer"); 
                        player.addItem(hammer);
                        printText("Hammer added to your inventory\n", player); 
                        player.setLastUserSelection(-1);
                    } 
                    else {
                        printText("Hammer already in your inventory", player);
                    }
                    printText("Player HP: " + player.getHealthPoints() + "\n", player); 
                    System.out.format("%1s%25s", "Item", "Integrity\n");
                    for (int i = 0; i < player.getInventoryData().size(); i++) {
                        printText(player.getInventoryData().get(i).getObjectName() + "             " + player.getInventoryData().get(i).getObjectIntegrity() + "\n", player); 
                    }
                    player.setLevelNum(2);
                    checkLevelNum(player); 
                    isInputted = true;
                    break;
                case 2: 
                    String print2 = "You step into the airlock and close the door behind you. You depressurize the airlock and are immediately killed. You forgot to put on a space suit.\n"; 
                    printText(print2, player); 
                    endGame(player);
                    isInputted = true;
                    player.setLastUserSelection(-2); 
                    break; 
                case 3: 
                    player.setLastUserSelection(-3);
                    player.setLevelNum(2); 
                    checkLevelNum(player); 
                    isInputted = true; 
                    break; 
                case 4: 
                    try {
                        saveGame(player, "b"); 
                    } catch (IOException e) {
                        System.err.println("An error has occurred.");
                    }
                    isInputted = true;
                    break; 
                default:
                    System.out.println("Please select a number between 1-4");
                    break; 
            } 
        }
    }

    public static void story2(PlayerClass player){
        player.setLastUserSelection(0);
            printText("Moving to the next room, you find a hole in the wall. Air is leaking out from the room, so you have to think quick.  You have 3 options to fix it.\n", player);
            printText("1. Grab a piece of wood and nails to fix it (Hammer required)\n", player);
            printText("2. Patch the hole with fabric and glue\n", player);
            printText("3. Leave the hole alone and move to the next room\n", player);
            printText("4. Save your game\n", player);

        Scanner input = new Scanner(System.in);
        boolean isInputted = false;
        int userOption;  
        // Checks the user's last selected option from the player class, then sets the userOption to that choice // 
        switch (player.getLastUserSelection()) {
            case -1:
                userOption = 1; 
                break;
            case -2: 
                userOption = 2;
                break;  
            case -3: 
                userOption = 3; 
                break; 
            default:
                // If the user hasn't selected an option yet, this allows them to do so // 
                userOption = input.nextInt();
        }
        
        do {
            switch (userOption) {
                case 1:
                    if (player.getInventoryData().isEmpty()) {
                        printText("You do not have a hammer. Choose another option\n", player); 
                        userOption = input.nextInt();
                        break;
                    } 
                    else {
                        printText("The board holds, and the air stops escaping." + player.getPlayerName() + "'s hammer loses 20 HP\n", player);
                        player.getInventoryData().get(0).damageItem();
                        printText("Player HP: " + player.getHealthPoints() + "\n", player); 
                        System.out.format("%1s%25s", "Item", "Integrity\n");
                        for (int i = 0; i < player.getInventoryData().size(); i++) {
                            printText(player.getInventoryData().get(i).getObjectName() + "             " + player.getInventoryData().get(i).getObjectIntegrity() + "\n", player); 
                        }
                        isInputted = true; 
                    }
                    break;
                case 2: 
                    printText("The glue around the edge of the fabric doesn't hold, so air continues to leak out and you lose 50 HP\n", player);
                    player.setHealthPoints(50);
                    printText("Player HP: " + player.getHealthPoints() + "\n", player); 
                        System.out.format("%1s%25s", "Item", "Integrity\n");
                        for (int i = 0; i < player.getInventoryData().size(); i++) {
                            printText(player.getInventoryData().get(i).getObjectName() + "             " + player.getInventoryData().get(i).getObjectIntegrity(), player); 
                        }
                        isInputted = true; 
                    break; 
                case 3: 
                    printText("You move fast enough to the next room to avoid serious damage, but you still lose 25HP\n", player); 
                    player.setHealthPoints(player.getHealthPoints() - 25);
                    printText("Player HP: " + player.getHealthPoints() + "\n", player); 
                        System.out.format("%1s%25s", "Item", "Integrity\n");
                        for (int i = 0; i < player.getInventoryData().size(); i++) {
                            printText(player.getInventoryData().get(i).getObjectName() + "             " + player.getInventoryData().get(i).getObjectIntegrity(), player); 
                        }
                        isInputted = true; 
                    break; 
                case 4: 
                    try {
                        saveGame(player, "");
                    } catch (IOException e) {
                        System.out.println("An error has occurred");
                    }
                    isInputted = true;
                    break;
                default:
                    System.out.println("Please select a number between 1-4");
                    break; 
            }
        } while (!isInputted); 
    
    
    }

    public static void endGame(PlayerClass player){
        Scanner input = new Scanner(System.in); 
        String print = "Game over! Start a new game, load a save, or quit? (a/b/c)"; 
        printText(print, player);
        String choice = input.next(); 
        switch (choice) {
            case "a":
                try {
                    startGame(true, 1);
                } catch (IOException e) {
                    System.out.println("An error has occurred.");
                    break; 
                }
                break;
            case "b": 
                try {
                    startGame(true, 0);
                } catch (IOException e) {
                    System.out.println("An error has occurred.");
                    break; 
                }
                break;
            case "c": 
                System.out.println("Goodbye!");
                break; 
            default:
                System.out.println("Please enter a, b, or c (lowercase)");
                endGame(player); 
        }
    }

    public static void saveGame(PlayerClass player, String userInput) throws IOException{
        Scanner input = new Scanner(System.in);
        // Creates fileWriters to write all the save data to their respective files //
        FileWriter saveData = new FileWriter(saveFile); 
        FileWriter saveInventory = new FileWriter(inventoryFile); 
        LinkedHashMap<String, Integer> inventoryData = new LinkedHashMap<>(); 
        for (int i = 0; i < player.getInventoryData().size(); i++) {
            // This string of functions gets the name of the object // 
            String temp = player.getInventoryData().get(i).getObjectName();
            // This function adds the object name as a string and the object integrity as a int // 
            inventoryData.put(temp, player.getInventoryData().get(i).getObjectIntegrity());
            saveInventory.write(temp); 
            saveInventory.write(",");
            saveInventory.write(inventoryData.get(temp).toString());
        } 
        saveData.write(player.getSaveData());
        saveData.close(); 
        saveInventory.close();
        // If loop is used for autosave feature, may or may not be used in the final game // 
        if (userInput.isEmpty()){
            printText("Save Complete! Continue playing or quit? (a/b)", player);
            userInput = input.next(); 
            switch (userInput) {
                case "a":
                    checkLevelNum(player);
                    break; 
                case "b": 
                    printText("Goodbye!", player); 
                    break; 
            }
        }
    }

    public static void checkLevelNum(PlayerClass player){
        switch (player.getLevelNum()) {
            case 1:
                Introduction(player); 
                break;
            case 2: 
                story2(player); 
                break; 
        }
    }
}
