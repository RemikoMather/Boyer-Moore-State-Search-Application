import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Boyer-Moore State Search Application
 * Implements the bad character rule of Boyer-Moore algorithm to search US state names
 */
public class StateSearchApp {
    
    // Array containing all 50 US state names
    private static final String[] US_STATES = {
        "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut",
        "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa",
        "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan",
        "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire",
        "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio",
        "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota",
        "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia",
        "Wisconsin", "Wyoming"
    };
    
    // Combined text of all states separated by spaces
    private static String statesText;
    
    // Scanner for user input
    private static Scanner scanner = new Scanner(System.in);
    
    /**
     * Main method - entry point of the application
     */
    public static void main(String[] args) {
        // Initialize the states text
        initializeStatesText();
        
        System.out.println("=== Boyer-Moore State Search Application ===");
        System.out.println("This application searches US state names using the Boyer-Moore algorithm (bad character rule)");
        System.out.println();
        
        // Main menu loop
        while (true) {
            displayMenu();
            int choice = getUserChoice();
            
            switch (choice) {
                case 1:
                    displayText();
                    break;
                case 2:
                    searchPattern();
                    break;
                case 3:
                    exitProgram();
                    return;
                default:
                    System.out.println("Invalid option. Please select 1, 2, or 3.");
            }
            System.out.println();
        }
    }
    
    /**
     * Initialize the states text by combining all state names
     */
    private static void initializeStatesText() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < US_STATES.length; i++) {
            sb.append(US_STATES[i]);
            if (i < US_STATES.length - 1) {
                sb.append(" ");
            }
        }
        statesText = sb.toString();
    }
    
    /**
     * Display the main menu options
     */
    private static void displayMenu() {
        System.out.println("Please select an option:");
        System.out.println("1) Display the text");
        System.out.println("2) Search");
        System.out.println("3) Exit program");
        System.out.print("Enter your choice (1-3): ");
    }
    
    /**
     * Get user's menu choice
     */
    private static int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1; // Invalid input
        }
    }
    
    /**
     * Display the complete text (all US state names)
     */
    private static void displayText() {
        System.out.println("\n=== US State Names ===");
        System.out.println(statesText);
        System.out.println("\nTotal characters: " + statesText.length());
    }
    
    /**
     * Search for a pattern using Boyer-Moore bad character rule
     */
    private static void searchPattern() {
        System.out.print("\nEnter a pattern to search for: ");
        String pattern = scanner.nextLine().trim();
        
        if (pattern.isEmpty()) {
            System.out.println("Pattern cannot be empty.");
            return;
        }
        
        System.out.println("\nSearching for pattern: \"" + pattern + "\"");
        System.out.println("Using Boyer-Moore algorithm (bad character rule)...");
        
        List<Integer> matches = boyerMooreSearch(statesText, pattern);
        
        if (matches.isEmpty()) {
            System.out.println("No matches found.");
        } else {
            System.out.println("Found " + matches.size() + " match(es) at indices:");
            for (int index : matches) {
                System.out.println("  Index " + index + ": \"" + 
                    statesText.substring(index, Math.min(index + pattern.length() + 10, statesText.length())) + "...\"");
            }
        }
    }
    
    /**
     * Boyer-Moore search algorithm using bad character rule
     * @param text The text to search in
     * @param pattern The pattern to search for
     * @return List of indices where pattern is found
     */
    private static List<Integer> boyerMooreSearch(String text, String pattern) {
        List<Integer> matches = new ArrayList<>();
        
        if (pattern.length() > text.length()) {
            return matches;
        }
        
        // Build bad character table
        int[] badCharTable = buildBadCharacterTable(pattern);
        
        int textLength = text.length();
        int patternLength = pattern.length();
        int skip = 0;
        
        while (skip <= textLength - patternLength) {
            int j = patternLength - 1;
            
            // Keep reducing index j of pattern while characters match
            while (j >= 0 && pattern.charAt(j) == text.charAt(skip + j)) {
                j--;
            }
            
            // If pattern is found (j becomes -1)
            if (j < 0) {
                matches.add(skip);
                
                // Shift pattern so that next character in text aligns with last occurrence in pattern
                skip += (skip + patternLength < textLength) ? 
                    patternLength - badCharTable[text.charAt(skip + patternLength)] : 1;
            } else {
                // Shift pattern so that bad character in text aligns with last occurrence in pattern
                skip += Math.max(1, j - badCharTable[text.charAt(skip + j)]);
            }
        }
        
        return matches;
    }
    
    /**
     * Build bad character table for Boyer-Moore algorithm
     * @param pattern The pattern to build table for
     * @return Bad character table
     */
    private static int[] buildBadCharacterTable(String pattern) {
        final int ALPHABET_SIZE = 256; // ASCII characters
        int[] badCharTable = new int[ALPHABET_SIZE];
        
        // Initialize all occurrences as -1
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            badCharTable[i] = -1;
        }
        
        // Fill the actual value of last occurrence of a character
        for (int i = 0; i < pattern.length(); i++) {
            badCharTable[(int) pattern.charAt(i)] = i;
        }
        
        return badCharTable;
    }
    
    /**
     * Exit the program
     */
    private static void exitProgram() {
        System.out.println("\nThank you for using the Boyer-Moore State Search Application!");
        System.out.println("Goodbye!");
        scanner.close();
    }
}
