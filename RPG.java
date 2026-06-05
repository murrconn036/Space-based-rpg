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
        String print = "You awake in a strange room, alone and surrounded by flashing red lights. An automated voice repeats “SYSTEM FAILURE. LOSS OF CREW DETECTED.” Then, the lights go out, and the room turns black. You awake again, this time in a room with white, fluorescent lights, and a constant background hum. You slowly rise to your feet, a wave of dizziness striking your head. You have no memory of the past, and have no clue where you are. As you begin to explore your surroundings, you find the bodies of your crew laying on the floor. They have facial expressions of horror, but no scratches or marks on their bodies. You then find a window, and realize that you are not on Earth."; 
        printText(print);
        System.out.println("");
        String print2 = "Select an option:"; 
        printText(print2);
        System.out.println("");
        String option1 = "1. Explore your surroundings";
        printText(option1);
        System.out.println("");
        String option2 = "2. Open the airlock";
        printText(option2);
        System.out.println("");
        String option3 = "3. Move to the next room";
        printText(option3);
        System.out.println("");
        String option4 = "4. Save and quit";
        printText(option4);
        System.out.println("");

        int choice = input.nextInt(); 
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
}
