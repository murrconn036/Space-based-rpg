import java.io.IOException;
import java.util.concurrent.TimeUnit;
public class fontTest {
    // ANSI Escape Codes for formatting
    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";
    public static final String UNDERLINE = "\u001B[4m";
    
    // ANSI Escape Codes for coloring
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";

    public static final String YELLOW = "\u001B[33m";

    public static void main(String[] args) {
        System.out.println(BOLD + "This text is Bold!" + RESET);
        System.out.println(UNDERLINE + "This text is Underlined!" + RESET);
        System.out.println(RED + "This text is Red!" + RESET);
        System.out.println(GREEN + BOLD + "This text is Bold and Green!" + RESET);
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt(); 
            return; 
        }
        clearScreen();
    } 

public static void clearScreen() {
    try {
        if (System.getProperty("os.name").contains("Windows")) {
            // Executes the Windows command-line builtin 'cls'
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
            // Executes the Unix/Linux/macOS terminal command 'clear'
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        }
    } catch (IOException | InterruptedException ex) {
        System.err.println("Could not clear the terminal.");
    }
}
}
