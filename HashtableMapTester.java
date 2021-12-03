public class HashtableMapTester {
    public static void main(String args[])
    {
        //testGetAndPut();
        //testContainsKey();
        //testSize();
        //testKeySet();
        //testIntIntMap();
        //testStringStringMap();
        //testLongIntMap();
    }

    public static void testGetAndPut() {
        System.out.println("Testing get() and put():");
        HashtableMap<Integer, Integer> map = new HashtableMap<Integer, Integer>(5);

        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
        map.put(7, 8);
        map.put(9, 10);

        System.out.println("Get 1: " + map.get(1)); // return 2
        System.out.println("Get 5: " + map.get(5)); // return 6
        System.out.println("Get 9: " + map.get(9)); // return 10

        map.put(1, 20);
        map.put(5, 60);
        map.put(9, 100);

        System.out.println("Testing overwriting a value:");
        System.out.println("Get 1: " + map.get(1)); // return 20
        System.out.println("Get 5: " + map.get(5)); // return 60
        System.out.println("Get 9: " + map.get(9)); // return 100

        System.out.println("Testing returning null:");
        System.out.println("Get 2: " + map.get(2)); // return null
        System.out.println("Get 6: " + map.get(6)); // return null
        System.out.println("Get 10: " + map.get(10)); // return null
    }

    public static void testContainsKey() {
        System.out.println("Testing containsKey() with Integers:");
        HashtableMap<Integer, Integer> map1 = new HashtableMap<Integer, Integer>(5);

        map1.put(1, 2);
        map1.put(3, 4);
        map1.put(5, 6);
        map1.put(7, 8);
        map1.put(9, 10);

        System.out.println("Contains 1:" + map1.containsKey(1)); // return true
        System.out.println("Contains 5:" + map1.containsKey(5)); // return true
        System.out.println("Contains 9:" + map1.containsKey(9)); // return true
        System.out.println("Contains 2:" + map1.containsKey(2)); // return false
        System.out.println("Contains 4:" + map1.containsKey(4)); // return false

        System.out.println("Testing containsKey() with Strings:");
        HashtableMap<String, Integer> map2 = new HashtableMap<String, Integer>(5);

        map2.put("ABC", 2);
        map2.put("DEF", 4);
        map2.put("GHI", 6);
        map2.put("JKL", 8);
        map2.put("MNO", 10);

        System.out.println("Contains ABC:" + map2.containsKey("ABC")); // return true
        System.out.println("Contains GHI:" + map2.containsKey("GHI")); // return true
        System.out.println("Contains MNO:" + map2.containsKey("MNO")); // return true
        System.out.println("Contains PQR:" + map2.containsKey("PQR")); // return false
        System.out.println("Contains STU:" + map2.containsKey("STU")); // return false
    }

    public static void testSize() {
        System.out.println("Testing size() of map1:");
        HashtableMap<Integer, Integer> map1 = new HashtableMap<Integer, Integer>(1);
        map1.put(1, 2);
        System.out.println("Size:" + map1.size()); // return 1

        System.out.println("Testing size() of map2:");
        HashtableMap<Integer, Integer> map2 = new HashtableMap<Integer, Integer>(2);
        map2.put(3, 4);
        map2.put(5, 6);
        System.out.println("Size:" + map2.size()); // return 2

        System.out.println("Testing size() of map3:");
        HashtableMap<Integer, Integer> map3 = new HashtableMap<Integer, Integer>(3);
        map3.put(7, 8);
        map3.put(9, 10);
        map3.put(11, 12);
        System.out.println("Size:" + map3.size()); // return 3
    }

    public static void testKeySet() {
        System.out.println("Testing keySet() of map1:");
        HashtableMap<Integer, Integer> map1 = new HashtableMap<Integer, Integer>(3);
        map1.put(1, 2);
        map1.put(3, 4);
        map1.put(5, 6);
        System.out.println("Set of keys:" + map1.keySet()); // return 1, 3, 5

        System.out.println("Testing keySet() of map2:");
        HashtableMap<Integer, Integer> map2 = new HashtableMap<Integer, Integer>(3);
        map2.put(7, 8);
        map2.put(9, 10);
        map2.put(11, 12);
        System.out.println("Set of keys:" + map2.keySet()); // return 7, 9, 11

        System.out.println("Testing keySet() of map3:");
        HashtableMap<Integer, Integer> map3 = new HashtableMap<Integer, Integer>(3);
        map3.put(13, 14);
        map3.put(15, 16);
        map3.put(17, 18);
        System.out.println("Set of keys:" + map3.keySet()); // return 13, 15, 17
    }

    public static void testIntIntMap()
    {
        System.out.println("int -> int map:");
        HashtableMap<Integer, Integer> map = new HashtableMap<Integer, Integer>(4);

        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
        map.put(7, 8);

        // Test get().
        System.out.println("Get 1: " + map.get(1));
        System.out.println("Get 3: " + map.get(3));
        System.out.println("Get 5: " + map.get(5));
        System.out.println("Get 7: " + map.get(7));
        System.out.println("Get 9: " + map.get(9));

        // Test size().
        System.out.println("Size = " + map.size());

        // Test keySet().
        System.out.println("All keys (order might vary): " + map.keySet());

        // Test printTable().
        map.printTable();
    }

    public static void testStringStringMap()
    {
        System.out.println("\nString -> String map:");
        HashtableMap<String, String> map2 = new HashtableMap<String, String>(7);

        map2.put("Elvis Presley", "elvis@graceland.com");
        map2.put("Albus Dumbledore", "dumbledore@hogwarts.edu");
        map2.put("Dorothy Gale", "dorothy@oz.org");
        map2.put("Grace Hopper", "hopper@navy.mil");  // look her up!
        System.out.println("Get: " + map2.get("Elvis Presley"));
        System.out.println("Get: " + map2.get("Albus Dumbledore"));
        System.out.println("Get: " + map2.get("Dorothy Gale"));
        System.out.println("Get: " + map2.get("Grace Hopper"));

        // Test size().
        System.out.println("Size = " + map2.size());

        // Test keySet().
        System.out.println("All keys (order might vary): " + map2.keySet());

        // Test printTable().
        map2.printTable();
    }

    public static void testLongIntMap()
    {
        System.out.println("\nlong -> int map:");
        HashtableMap<Long, Integer> map3 = new HashtableMap<Long, Integer>(11);

        // Insert some powers of 2 into the map.
        for (int x = 0; x < 60; x++)
        {
            map3.put((long)Math.pow(2, x), x);
        }

        // Test size().
        System.out.println("Size = " + map3.size());

        // Test keySet().
        System.out.println("All keys (order might vary): " + map3.keySet());

        // Test printTable().
        map3.printTable();
    }
}
