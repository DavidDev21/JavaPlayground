
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;

public class CollectionsTest {

    public static void printCollection(String message, Collection col)
    {
        System.out.println(message);
        System.out.println(col);
        System.out.println();
    }

    // returns a new string that is stripped of punctuations
    public static String stripPunc(String message)
    {
        // Strip leading punctuations
        // "match at the beginning of the line, one or more non alpha letters"
        message = message.replaceFirst("^[^a-zA-Z]+", "");
        // Strip trailing puncations
        message = message.replaceAll("[^a-zA-Z]+$", "");
        return message;
    }
    public static void main(String[] args)
    {
        Scanner in = null;

        try {
            in = new Scanner(new File("src/words.txt"));

            // Store the words in arraylist
            ArrayList<String> wordStorage = new ArrayList<>();

            while(in.hasNext())
            {
                wordStorage.add(stripPunc(in.next()));
            }

            in.close();
            printCollection("FROM ARRAYLIST: ", wordStorage);

            // Use set (hashset) for unique words
            // In this case we init hs with the words from wordStorage (another collection)
            HashSet<String> hs = new HashSet<>(wordStorage);
            HashSet<String> hs2 = new HashSet<>();

            printCollection("FROM HS: ", hs);

            // Adding things to hs2
            printCollection("FROM HS2: ", hs2);

            // iterate using enhanced for loops, can use list.iterator() to get the iterator directly
            for(String word : wordStorage)
            {
                hs2.add(word);
            }

            printCollection("FROM HS2: ", hs2);

            // check if hs2 has same contents as hs;
            if(hs.equals(hs2))
            {
                System.out.println("EQUALS!!!");
            }
            else
            {
                System.out.println("NOT EQUALS");
            }
            // Trying out treeset instead and see how its sorted
            TreeSet<String> ts = new TreeSet<>(wordStorage);

            printCollection("TS: ", ts);
            if(ts.contains("As"))
                System.out.println("HAS ITEM");

            // Count frequency of words
            HashMap<String, Integer> wordFrequency = new HashMap<>();

            Iterator<String> iter = wordStorage.iterator();

            while(iter.hasNext())
            {
                String key = iter.next();
                // Get the value for given word, return 0 if no key exist (meaning new word)
                Integer count = wordFrequency.getOrDefault(key, 0);
                // Integer is a wrapper class of int, but you can still use it like a normal int due to autoboxing
                // feature given by the compiler. Giving the illusion that it is a normal integer.
                ++count;
                wordFrequency.put(key, count);
            }

            // Note: Map interfaces is not under Collections
            System.out.println("WORD FREQ: \n" + wordFrequency);

            // Write out the stuff to a file?
            // So with PrintWriter, the act of "print" is like writing to the stream
            // Note, it is buffered and by default it is not line buffered so you would have to flush it out
            // or just close the stream when you done so tha it auto flushes
            PrintWriter outFile = new PrintWriter("src/output.txt"); // auto creates file if doesnt exist
            outFile.println(wordFrequency); // prints just like as if you printing to console
            outFile.println(hs);
            outFile.close();

            // Attempt to open with append?
            // So by default PrintWriter only truncates when it opens the file, so we have to give it
            // a stream object that was opened for appending
            outFile = new PrintWriter(new FileOutputStream("src/output.txt", true));
            outFile.println("THIS SHOULD APPEND");
            outFile.flush();
            outFile.println("THIS SHOULD WRITE AS WELL");
            outFile.close();

        } catch (Exception e)
        {
            if(in != null)
            {
                in.close();
            }
        }
    }
}
