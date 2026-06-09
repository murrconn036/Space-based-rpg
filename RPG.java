import java.io.*;
import java.util.*; 


public class RPG {
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
            System.out.println("Enter your player name: ");
            String playerName = input.next(); 
            System.out.println("Enter your save name: ");
            String saveName = input.next(); 
            // Creates a new file object in the save_files folder with the inputted file name in CSV format // 
            File saveFile = new File("save_files\\" + saveName + ".csv");
            // If the file doesn't already exist, creates the new file and delays for 200 milliseconds //
            if (saveFile.createNewFile()) {
                System.out.println("File Created!");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                }
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
            PlayerClass player = new PlayerClass(playerName, 100);
            Introduction(player);
        } 
        // If the user selects b and hasn't inputted anything else, runs the code to load an existing save file // 
        else if (newSave.equalsIgnoreCase("b") || iterations == 0){
            // call the load existing save method // 
            boolean isInputted = false; 
            System.out.println("File name: "); 
            String saveName = input.next(); 
            File saveFile = new File("save_files\\" + saveName + ".csv");
            String playerName;
            int playerHealth;
            try (Scanner fileReader = new Scanner(saveFile)) {
                fileReader.useDelimiter(",");
                playerName = "";
                playerHealth = 0;
                while (fileReader.hasNext()){
                    playerName = fileReader.next(); 
                    if (fileReader.hasNextInt()){
                        playerHealth = fileReader.nextInt();
                    }
                }
                PlayerClass player = new PlayerClass(playerName, playerHealth); 
                isInputted = true; 
                Introduction(player);
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
        // Prints the opening story and options for the user to select //
        String print = "You awake in a strange room, alone and surrounded by flashing red lights. An automated voice repeats “SYSTEM FAILURE. LOSS OF CREW DETECTED.” Then, the lights go out, and the room turns black. You awake again, this time in a room with white, fluorescent lights, and a constant background hum. You slowly rise to your feet, a wave of dizziness striking your head. You have no memory of the past, and have no clue where you are. As you begin to explore your surroundings, you find the bodies of your crew laying on the floor. They have facial expressions of horror, but no scratches or marks on their bodies. You then find a window, and realize that you are not on Earth.\n"; 
        printText(print);
        String print2 = "Select an option:\n"; 
        printText(print2);
        String option1 = "1. Explore your surroundings\n";
        printText(option1);
        String option2 = "2. Open the airlock\n";
        printText(option2);
        String option3 = "3. Move to the next room\n";
        printText(option3);
        String option4 = "4. Save your game\n";
        printText(option4);
        story1(player); 

        
    }

    public static void printText(String print){
        for (int i = 0; i < print.length(); i++) {
            System.out.print(print.charAt(i));
            // Delays the character printing by 50 milliseconds, and delays the end of sentences by 200 milliseconds // 
            try {
                Thread.sleep(50);
                if (print.charAt(i) == '.') {
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                System.out.println("An error has occured. Your game has been saved");
            }
        }
    }

    public static void story1(PlayerClass player){
        Scanner input = new Scanner(System.in);
        boolean isInputted = false;
        while (!isInputted) { 
            int userOption = input.nextInt(); 
            switch (userOption) {
                case 1:
                    String print1 = "Exploring your surroundings, you find things scattered all over the floor. One of these things is a hammer, so you pick it up.\n"; 
                    // adds item to inventory // 
                    printText(print1);
                    isInputted = true;
                    break;
                case 2: 
                    String print2 = "You step into the airlock and close the door behind you. You depressurize the airlock and are immediately killed. You forgot to put on a space suit.\n"; 
                    printText(print2); 
                    endGame();
                    isInputted = true; 
                    break; 
                case 3: 
                    isInputted = true; 
                    break; 
                case 4: 
                    isInputted = true;
                    break; 
                default:
                    System.out.println("Please select a number between 1-4");
                    break; 
            } 
        }
    }

    public static void endGame(){
        Scanner input = new Scanner(System.in); 
        String print = "Game over! Start a new game, load a save, or quit? (a/b/c)"; 
        printText(print);
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
                endGame(); 
        }
    }

    public static void saveGame(){

    }
}
