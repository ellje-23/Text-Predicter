import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * A Map from keys to values that is implemented with a hash table.  Chaining is used to resolve collisions.
 * The user may set the starting size of the hash table, which never changes.
 */
public class HashtableMap<K, V> {

    // The ArrayList that stores the table itself.  Because we are using chaining, each entry in the ArrayList
    // is a linked list, containing key-value pairs.
    private final ArrayList<LinkedList<KVPair<K, V>>> table;

    /**
     * Create a new hash table of size initialSize.
     */
    HashtableMap(int initialSize) {
        // Initialize table to have a pre-defined size.
        table = new ArrayList<LinkedList<KVPair<K, V>>>(initialSize);

        // Go through table and add an empty linked list to each slot.
        for (int x = 0; x < initialSize; x++)
        {
            table.add(new LinkedList<KVPair<K, V>>());
        }
    }

    /**
     * Add a new key-value pair into the hash table.  If there is already an entry in
     * the table for this key, then overwrite it with the new value.
     *
     * Since we are using chaining, add items to the front of the linked
     * list (using addFirst), once you have found the correct index in the
     * table in which to insert.
     */
    public void put(K newKey, V newValue) {
        // Calculating the index you should use to insert the key-value pair
        int hashIndex = Math.abs(newKey.hashCode() % table.size());

        // Monitoring if the key was in the table and the old value was overwritten
        boolean overwrite = false;

        // Running through the linked list at index hashIndex in the table
        for (KVPair<K, V> element : table.get(hashIndex)) {
            // Already an entry in the table for this key, so overwrite the old value with the new value
            if (element.key.equals(newKey)) {
                element.value = newValue;
                overwrite = true;
            }
        }

        // The key is not in the table, so adding the pair to the front of the linked list
        if (!overwrite) {
            KVPair<K, V> newPair = new KVPair<K, V>();
            newPair.key = newKey;
            newPair.value = newValue;
            table.get(hashIndex).addFirst(newPair);
        }
    }

    /**
     * Get a value from this hash table, based on its key.  If the key doesn't already exist in the table,
     * this method returns null.
     */
    public V get(K searchKey) {
        // Calculating the index the searched key-value pair should be
        int hashIndex = Math.abs(searchKey.hashCode() % table.size());

        // Running through the linked list at index hashIndex in the table
        for (KVPair<K, V> element : table.get(hashIndex)) {
            // The key was found in the table, so return its value
            if (element.key.equals(searchKey)) {
                return element.value;
            }
        }
        // The key is not in the table, so return null
        return null;
    }

    /**
     * Test if this key exists in the hash table, and return true if it does, and false if it doesn't.
     */
    public boolean containsKey(K searchKey) {
        // Calculating the index the searched key-value pair should be
        int hashIndex = Math.abs(searchKey.hashCode() % table.size());

        // Running through the linked list at index hashIndex in the table
        for (KVPair<K, V> element : table.get(hashIndex)) {
            // The key was found in the table, so return true
            if (element.key.equals(searchKey)) {
                return true;
            }
        }
        // The key was not found in the table, so return false
        return false;
    }

    /**
     * Print the hash table.  If the table has more than 100 slots, only print the top 100 (indices 0-99).
     * Print the contents of each index in the table on a single line.  Include the index number, the
     * number of entries at that index (hint--use .size() on the linked list), and each individual entry
     * in the format "key: value" or something similar.
     *
     * Hint: Try overriding toString() in the KVPair class below.
     *
     * Example:
     * Index 0 (2): key1: value1, key2: value2
     * Index 1 (3): key3: value3, etc, etc
     */
    public void printTable() {
        // True, if the table has more than 100 slots
        if (table.size() > 100) {
            // Looping through the first 100 slots
            for (int i = 0; i < 100; i++) {
                // True, if the current slot (index) is empty and printing it's contents as empty
                if (table.get(i).size() == 0) {
                    System.out.println("Index " + i + " (0): " + table.get(i));
                }
                else {
                    // Running through the linked list at index hashIndex in the table and printing it's contents
                    boolean isFirst = true;
                    System.out.print("Index " + i + " (" + table.get(i).size() + ")" + ": [ ");
                    for (KVPair<K, V> element : table.get(i)) {
                        if (isFirst) {
                            System.out.print("{ " + element.toString() + " } ");
                            isFirst = false;
                        }
                        else {
                            System.out.print(", { " + element.toString() + " } ");
                        }
                    }
                    System.out.println("] ");
                }
            }
        }
        else {
            // Looping through the table slots
            for (int i = 0; i < table.size(); i++) {
                // True, if the current slot (index) is empty and printing it's contents as empty
                if (table.get(i).size() == 0) {
                    System.out.println("Index " + i + " (0): " + table.get(i));
                }
                else {
                    // Running through the linked list at index hashIndex in the table and printing it's contents
                    boolean isFirst = true;
                    System.out.print("Index " + i + " (" + table.get(i).size() + ")" + ": [ ");
                    for (KVPair<K, V> element : table.get(i)) {
                        if (isFirst) {
                            System.out.print("{ " + element.toString() + " } ");
                            isFirst = false;
                        }
                        else {
                            System.out.print(", { " + element.toString() + " } ");
                        }
                    }
                    System.out.println("] ");
                }
            }
        }
    }

    /**
     * Return the total number of key-value pairs stored in the hash table.
     * Note, this is not the same thing as the number of slots in the table.
     */
    public int size() {
        // Instantiating a variable to track the number of elements visited
        int sizeCount = 0;

        // Running through each element in the list
        for (int i = 0; i < table.size(); i++) {
            // Running through the linked list at index i in the table and adding 1 to sizeCount
            for (KVPair<K, V> element : table.get(i)) {
                sizeCount += 1;
            }
        }
        // Returning the total number of elements in the table
        return sizeCount;
    }

    /**
     * Turn the hash table into a Set of all the keys in the table (values are ignored).
     * Do this by creating an empty HashSet<K> (built-in Java class),
     * looping through the hash table, looping over each linked list in each slot of the table,
     * and add()ing each key into the hash set.
     */
    Set<K> keySet() {
        // Creating a new, empty HashSet<K>
        HashSet<K> setOfKeys = new HashSet<>();

        // Running through each element in the list
        for (int i = 0; i < table.size(); i++) {
            // Running through the linked list at index i in the table
            for (KVPair<K, V> element : table.get(i)) {
                // Adding the element's key to the new HashSet
                setOfKeys.add(element.key);
            }
        }
        // Returning a set of all the keys in the table
        return setOfKeys;
    }

    /**
     * It is very common to have private classes nested inside other classes.  This is most commonly used when
     * the nested class has no meaning apart from being a helper class or utility class for the outside class.
     * In this case, this KVPair class has no meaning outside of this Hashtable class, so we nest it inside here
     * so as to not prevent another class from declaring a KVPair class as well.
     */
    private static class KVPair<K, V> {
        public K key = null;
        public V value = null;

        // Writing a toString() method here will make it easy to print KVPairs using System.out.println().
        // Try returning a string like "key: value" (substituting actual key & value).
        @Override
        public String toString() {
            String keyValuePair = "Key = " + key + ", " + "Value = " + value;
            return keyValuePair;
        }
    }

}
