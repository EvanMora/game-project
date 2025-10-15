package tests;

public class TestUtils {
    private static int passed = 0;
    private static int failed = 0;

    /*
     * Shows a message that indicate if the test were success or not
     * 
     * @param name Indicates the name of the test
     * 
     * @param success The condition to indicates if the test is success
     */
    public static void check(String name, boolean success) {
        if (success) {
            System.out.println(ConsoleColors.GREEN + "(Success) " + name + ConsoleColors.RESET);
            passed++;
        } else {
            System.out.println(ConsoleColors.RED + "(Failed) " + name + ConsoleColors.RESET);
            failed++;
        }
    }

    /*
     * Shows how many test failed and how many success
     */
    public static void summary() {
        System.out.println("\n==== Results ====");
        System.out.println(ConsoleColors.GREEN + "Successful: " + passed + ConsoleColors.RESET);
        System.out.println(ConsoleColors.RED + "Failed: " + failed + ConsoleColors.RESET);
    }

}

/*
 * Class with some static constants for put colors on the terminal
 */
class ConsoleColors {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
}