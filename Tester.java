/**
 * 
 * @author Andrew Budihardja
 *
 */

public class Tester {
	
    public static void main(String[] args){
    	
    		args = new String[] {"My Test works fine!"};
	
        if (args.length <1 ){
            System.out.println("Error: no arguments");
            System.exit(0);
        }
        
        Encoder enc = new Encoder(args[0]);
        HuffmanFrequencyTable freqTable = enc.getFreqTable();
        
        
        System.out.println("======================================");
        System.out.println(" char       frequency       code");
        System.out.println("--------------------------------------");
	

        for(int i = 0; i < freqTable.size(); i++){
		
        		System.out.println("  "+ freqTable.getItem(i).getInputChar()+"           "+
        			freqTable.getItem(i).getFreq()+"             "+freqTable.getItem(i).getCode());
	    
	}
	
	System.out.println("======================================");
	System.out.println("Encoded bit stream");
	System.out.println(enc.encodeString());
	System.out.println("Total number of bits without Huffman coding: " + args[0].length()*16);
	System.out.println("Total number of bits with Huffman coding: " + enc.encodeString().length()); 
	
	Decoder dec = new Decoder(enc.getHuffmanTree(), enc.encodeString());
	System.out.println("\nDecoded String: " + dec.decode());
	
    }
}

