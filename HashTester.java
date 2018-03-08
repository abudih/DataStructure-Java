/**
 *  DJB hash function is used with a small variation to address
 *  the negative hash values. The hashtable buckets are implemented using LinkedList
data structure in Java API. With the given words in words.txt, the size of the 
bucket variebuckets varies from 0 to 9 (0 : 3085, 1 : 4639, 2 : 3673, 3 : 2123, 
4 : 911, 5 : 361, 6 : 137, 7 : 55, 8 : 11, 9 : 5). The Olog2n for the given
dictionary is 15 (25143 words) and the worst case performance of our hashtable
is bounded by 9 (the size of biggest bucket). Hence, the given solution performs
better than Olog2n.
@author Andrew Budihardja
*/
import java.util.Arrays;
import java.util.LinkedList;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;

class Anagram {

    private String key, value;

    public Anagram(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}

//
class Dictionary {
	
    private final int ARRAYSIZE = 15000; // A reasonable space/ speed tradeoff
    private LinkedList list[];
    private int wordsPlaced, collisionsOccured, uniqueHashValues;

    public Dictionary() {
        wordsPlaced = 0;
        collisionsOccured = 0;
        uniqueHashValues = 0;
        BufferedReader inFile = null;
        String word;
        // Initialize the array to hold the hashtable
        list = new LinkedList[ARRAYSIZE];
        for (int i = 0; i < ARRAYSIZE; i++) {
            list[i] = new LinkedList();
        }
        // Load the dictionary into the hashtable
        try {
            inFile = new BufferedReader(new FileReader("words.txt"));
            while ((word = inFile.readLine()) != null) {
                addItem(word);
            }
            System.out.println(wordsPlaced + " words placed, " + collisionsOccured + " collision occured, " + uniqueHashValues + " distinct hashvalues");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to open 'words.txt'");
            return;
        } catch (IOException e) {
            System.out.println("Unable to read from 'words.txt'");
        } finally {
            try {
                inFile.close();
            } catch (Exception e) {/*ignore*/

            }
        }
    }

    private void addItem(String value) {
        String key = makeKey(value);
        int hash = (int) getDjbHash(key);
        // Collect statistics for hashtable performance evaluation
        wordsPlaced++;
        collisionsOccured += (list[hash].size() > 0 ? 1 : 0);
        uniqueHashValues += (list[hash].size() == 0 ? 1 : 0);
        
        list[hash].add(new Anagram(key, value));
    }

    public String search(String word) {
        String key = makeKey(word);
        int hash = getDjbHash(key);
        int count = 0;
        String output = "";
        for (int i = 0; i < list[hash].size(); i++) {
            Anagram a = (Anagram) list[hash].get(i);
            if (key.equals(a.getKey()) && !word.equals(a.getValue())) {
                count++;
                output = output + " " + a.getValue();
            }
        }
        return (word + " " + count + output);
    }

    private int getDjbHash(String key) {
        /* DJB hash with small variation to address negative hashcode*/
        long hash = 5381;
        for (int i = 0; i < key.length(); i++) {
            hash = Math.abs((hash << 5) + hash) + key.charAt(i);
        }
        return (int) (Math.abs(hash) % ARRAYSIZE);
    }

    private String makeKey(String w) {
        // Make a copy of the string received as argument
        String word = new String(w);
        // Convert the word to lower case
        word = word.toLowerCase();
        // Delete every character other than digits and letters in the string
        word = word.replaceAll("[^0-9a-z]", "");
        // Covert the string to a character array (for convenient sorting)
        char arr[] = word.toCharArray();
        Arrays.sort(arr);
        // Convert back the sorted array to the string and return the result as key
        return new String(arr);
    }
}

public class HashTester {
    public static void main(String args[]) {
//        if (args.length != 2){
//            System.out.println("Usage: java HashTester <input_file> <output_file>");
//            return;
//        }
        Dictionary d = new Dictionary();
        BufferedReader inFile = null;
        PrintWriter outFile = null;
        String input = "input.txt", output = "output.txt";
        try {
            inFile = new BufferedReader(new FileReader(input));
            outFile = new PrintWriter(output);
            String str;
            while ((str = inFile.readLine()) != null) {
                outFile.println(d.search(str));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Unable to open files for reading/ writing");
            return;
        } catch (IOException ex) {
            System.out.println("Can not read/ write the files");
        } finally {
            try {
                inFile.close();
                outFile.close();
            } catch (Exception e) {/*ignore*/

            }
        }
    }
}
