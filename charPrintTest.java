import java.util.concurrent.TimeUnit;
public class charPrintTest {

    public static void main(String[] args) {
        String message = "Printing this text at a slower, cinematic speed!";
        
        // Print with a 50-millisecond delay between each character
        slowPrint(message, 50); 
    }

    public static void slowPrint(String text, int delayMillis) {
        for (int i = 0; i < text.length(); i++) {
            System.out.print(text.charAt(i));
            try {
                // Pause execution for the specified milliseconds
                TimeUnit.MILLISECONDS.sleep(delayMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        System.out.println(); // Move to the next line when finished
        
    }
}

