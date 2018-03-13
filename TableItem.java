/**
 * 
 * @author Andrew Budihardja
 *
 */
public class TableItem{
	
    private char inputChar;
    private int freq;
    private String code;
    
    
    public TableItem(char theChar, int theFreq){
    	
    	
    		inputChar = theChar;
    		freq = theFreq;
	
    }
    
    
    public char getInputChar(){
    	
    		return inputChar;
    }
    
    public int getFreq(){
    		return freq;
    }

    public String getCode(){
    		return code;
    }
    
    public void setFreq(int theFreq){
    		freq = theFreq;
    }
    
    public void setCode(String theCode){
    		code = theCode;
    }
}