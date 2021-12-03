import java.util.*;

public class Autocorrector {

    // Holds frequencies of every possible word.
    private final HashtableMap<String, Integer> wordFreqs;

    /**
     * Create a new Autocorrector, based on the supplied map of word frequencies.
     */
    public Autocorrector(HashtableMap<String, Integer> wordFrequencies)
    {
        wordFreqs = wordFrequencies;
    }

    /**
     * Returns the most frequent word in the wordFreqs map that has inputString as a prefix.
     * If no string in the wordFreq map starts with this string, return null.
     */
    public String getBestAutocomplete(String inputString)
    {
        // Instantiating variables to store the largest nunFreqs and predictedWord so far
        int numFreqs = 0;
        String predictedWord = null;

        // Looping through the keys in the map of word frequencies
        for (String keys : wordFreqs.keySet()) {
            // True if the key's prefix matches the input string
            if (keys.startsWith(inputString)) {
                // If the current word's frequency is larger than numFreq,
                // then set it to the largest numFreqs and predicted word so far
                if (numFreqs < wordFreqs.get(keys)) {
                    numFreqs = wordFreqs.get(keys);
                    predictedWord = keys;
                }
            }
        }
        // Return the predicted word
        return predictedWord;
    }

    /**
     * Return the set of possible words that are *both* an edit distance of 1 away from the inputString,
     * *and* are contained in our dictionary (wordFreq).
     */
    public Set<String> getBestAutocorrect(String inputString)
    {
        Set<String> edits = editDistance1(inputString);
        edits.retainAll(wordFreqs.keySet());
        return edits;
    }

    /**
     * Return the "best suggestions" for an inputString, based on both the most likely autocompletion,
     * and the set of possible autocorrections.  The suggestions are in decreasing sorted order of frequency.
     */
    public List<String> getBestSuggestions(String inputString)
    {
        // Calling getBestAutocomplete and getBestAutocorrect and storing the results
        String bestAutocomplete = getBestAutocomplete(inputString);
        Set<String> bestAutocorrect = getBestAutocorrect(inputString);

        // Creating a new ArrayList of strings to hold the final result
        ArrayList<String> finalResult = new ArrayList<>();

        // Adding all the strings that came back from getBestAutocorrect to the ArrayList
        for (String word : bestAutocorrect) {
            finalResult.add(word);
        }

        // If bestAutocomplete is not empty, adding the string to the ArrayList
        if ((bestAutocomplete != null) && !(finalResult.contains(bestAutocomplete))) {
                finalResult.add(bestAutocomplete);
        }

        // Sorting the Arraylist
        WordByFrequencyComparator comp = new WordByFrequencyComparator();
        Quicksort.quicksort(finalResult, comp);

        return finalResult;
    }

    /**
     * Returns the set of possible strings that have an edit distance of 1 from string s.
     * (No need to modify this function.)
     */
    private static Set<String> editDistance1(String s)
    {
        HashSet<String[]> splits = new HashSet<>();
        for (int x = 0; x < s.length() + 1; x++)
        {
            splits.add(new String[] { s.substring(0, x), s.substring(x) });
        }

        HashSet<String> edits = new HashSet<>();

        // deletions
        for (String[] splitString : splits)
        {
            String L = splitString[0], R = splitString[1];

            // deletion
            if (!R.equals(""))
                edits.add(L + R.substring(1));

            // transposition
            if (R.length() > 1)
                edits.add(L + R.charAt(1) + R.charAt(0)+ R.substring(2));

            String alphabet = "abcdefghijklmnopqrstuvwxyz";

            // replace
            if (!R.equals(""))
            {
                for (char ch : alphabet.toCharArray())
                    edits.add(L + ch + R.substring(1));
            }

            // insert
            for (char ch : alphabet.toCharArray())
                edits.add(L + ch + R);
        }

        return edits;
    }

    /**
     * This comparator compares two strings according to their frequency in the wordFreq map.
     * The string that appears more frequently should be "less than" the other string.
     * This will sort the more common strings earlier in the list.
     * If two words have the same frequency, compare them alphabetically.
     */
    class WordByFrequencyComparator implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            // Instantiating variables to hold the frequency of s1 and s2
            int s1WordFreq = 0;
            int s2WordFreq = 0;

            // Looping through the keys in the map and if the key equal s1 or s2, then storing the frequencies respectively
            for (String key : wordFreqs.keySet()) {
                if (key.equals(s1)) {
                    s1WordFreq = wordFreqs.get(key);
                }
                if (key.equals(s2)) {
                    s2WordFreq = wordFreqs.get(key);
                }
            }

            // If s1 frequency is bigger than s2 frequency, return -1
            if (s1WordFreq - s2WordFreq > 0) {
                return -1;
            }
            // If s1 frequency is smaller than s2 frequency, return 1
            else if (s1WordFreq - s2WordFreq < 0) {
                return 1;
            }
            // If the frequencies are equal, then compare the string alphabetically
            else {
                return (s1.compareTo(s2));
            }
        }
    }
}
