import java.io.InputStream;
import java.util.*;

public class AutocorrectDemo {

    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter filename of text to read: ");
        String filename = scan.nextLine();
        System.out.print("Enter size of hash table: ");
        int size = Integer.parseInt(scan.nextLine());

        HashtableMap<String, Integer> wordFreqs = processFile(filename, size);
        System.out.println("Entries in table: " + wordFreqs.size());
        wordFreqs.printTable();

        // Uncomment this when you start writing the autocorrecter (part D):
        Autocorrector autocorrector = new Autocorrector(wordFreqs);
        testAutocorrect(autocorrector);

        // window code: uncomment when you're sure the autocorrector is working.
        //AutocorrectFrame frame = new AutocorrectFrame(autocorrector);
        //frame.setVisible(true);
    }

    public static void testAutocorrect(Autocorrector autocorrector)
    {
        Scanner scan = new Scanner(System.in);
        String word;
        while (true) {
            System.out.print("\nEnter a word, or 'stop' to end: ");
            word = scan.nextLine();
            if (word.equals("stop"))
                break;
            System.out.println("Autocomplete: " + autocorrector.getBestAutocomplete(word));
            System.out.println("Autocorrect (order might be different):");
            System.out.println("  " + autocorrector.getBestAutocorrect(word));
            System.out.println("Best suggestions (order should match):");
            System.out.println("  " + autocorrector.getBestSuggestions(word));
        }
    }

    /**
     * Read the file specified to add proper items to the word frequencies.
     */
    private static HashtableMap<String, Integer> processFile(String filename, int size)
    {
        HashtableMap<String, Integer> wordFreqs = new HashtableMap<>(size);
        InputStream is = AutocorrectDemo.class.getResourceAsStream(filename);
        if (is == null) {
            System.err.println("Bad filename: " + filename);
            System.exit(1);
        }
        Scanner scan = new Scanner(is);

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] words = line.split(" ");

            // Processing all the words in the array and keeping track of the number of times you see each word.
            for (int i = 0; i < words.length; i++) {
                // If true, the word is in the map already
                if (wordFreqs.containsKey(words[i])) {
                    // Increase the old frequency by 1
                    int oldFreq = wordFreqs.get(words[i]);
                    wordFreqs.put(words[i], oldFreq + 1);
               } else {
                    // Add the word to the mpa and set its frequency to 1
                    wordFreqs.put(words[i], 1);
                }
            }
        }
        scan.close();
        return wordFreqs;
    }
}
