import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author Andrew Budihardja
 *
 */
public class Huffman {

	
	public static Map<Character,Integer> charFreq= new HashMap<Character,Integer>();
	public static Map<Character,String> charEncoding = new HashMap<Character,String>();
	public double ratio;
	public static int lengthOfFile;
	public static int numOfChars;
	
	private static HuffmanCode codeClass;
	
	public Huffman(){
				
		codeClass = new HuffmanCode();
	}
	
	/**
	 * reads the file given in the path
	 * then adds them to maps.
	 * returns char frequencies as well as the chars.
	 * @param path
	 * @return
	 */
	public static Map<Character, Integer> getCharacterFrequencies(String path) {
		
		BufferedReader input=null;
		// Store the contents of the file in a string
        StringBuilder contents = new StringBuilder();
        try {
            FileReader fr = new FileReader(path);
            input = new BufferedReader(fr);

            String line = "";
            while (( line = input.readLine()) != null){
               contents.append(line);
               contents.append(System.getProperty("line.separator"));
            }
        } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
        }
        finally {
            try {
                  input.close();
                } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
        }
       
//       
        for(int i=0;i<contents.length();i++){
        	char temp=contents.charAt(i);
        	
        	if(charFreq.containsKey(temp)){
        		int value = charFreq.get(temp);
        		value=value+1;
        		charFreq.put(temp, value);
        		
        	}else{
        	charFreq.put(temp,1);
        	}
        }
        numOfChars=charFreq.size();
               
		return charFreq;	
	}
	
	/**
	 * returns the encoding bits for each char as well as the chars.
	 * Using the help of HuffmanCode.Java
	 * @param frequencies
	 * @return
	 */
	public static Map<Character, String> getEncoding(Map<Character, Integer> frequencies) {
		
		HuffmanCode codeClass = new HuffmanCode();
		
		String[] tempString = new String[]{};
		List<Character> tempChar = new ArrayList<Character>();
		
		codeClass.setCharandFreqList(frequencies);
		codeClass.sortCharandFreqList();
		codeClass.setTotalChars(frequencies.size());
		codeClass.run();
		
		tempChar=codeClass.getCharList();
		tempString=codeClass.getHuffmanEncoding();
		
		for(int i=0;i<tempChar.size();i++){
			charEncoding.put(tempChar.get(i), tempString[i]);
		}
		return charEncoding;
	}
	
	
	/**
	 * 
	 * returns the compressionratio
	 * by calculating the original bits(8) by the frequencies of each char.
	 * divided by the compressed bits according to the huffman encoding.
	 * 
	 * @param frequencies
	 * @param encoding
	 * @return
	 */
	public static double getCompressionRatio(Map<Character, Integer> frequencies, Map<Character, String> encoding) {
		
		double totalOriginalData=0;
		double totalCompressedData=0;;
		
		for(char temp: frequencies.keySet()){
			totalOriginalData=totalOriginalData+ (frequencies.get(temp)*8);
			totalCompressedData=totalCompressedData+ (frequencies.get(temp)*encoding.get(temp).length());
		}
		System.out.println("total original data="+totalOriginalData);
		
		System.out.println("total compressed data="+totalCompressedData);
		System.out.println("ratio data="+ (totalOriginalData/totalCompressedData));
		
		return totalOriginalData/totalCompressedData;	
	}
}
