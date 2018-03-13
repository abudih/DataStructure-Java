import java.util.*;

public class HuffmanFrequencyTable {
	
    ArrayList<TableItem> table; 
    String inputStr = "";

    public HuffmanFrequencyTable(String theStr){
    	
        if (theStr.length() < 2) {
            System.out.println("Error: the input string should have at least 2 characters");
            System.exit(0);
        }
        
        inputStr = theStr;
        table = new ArrayList<TableItem>();
        this.initTable();
        
    }

    private void initTable(){
    	
    		for(int i=0; i < inputStr.length(); i++){
    		
            char thisChar = inputStr.charAt(i);
            Boolean hasThisChar = false;
            
            for (TableItem element : this.table){
            	
                if (element.getInputChar() == thisChar){
                	
                	
                    element.setFreq(element.getFreq() + 1);
                    hasThisChar = true;
                    break;
                }
            }
            
            
            if (!hasThisChar){
            	
                table.add(new TableItem(thisChar, 1));
            }
        }
    }
    
    public int size(){
    	
    		return table.size();
    }

    public char getChar(String theCode){
    	
    		for (TableItem item : table){
    		
            if (item.getCode() == theCode){
            	
                return item.getInputChar();
            }
        }
        return '\0';
    }

    public String getCode(char inputChar){
    	
        for (TableItem item : table){
        	
            if (item.getInputChar() == inputChar){
            	
                return item.getCode();
            }
        }
        return "";
    }

    public TableItem getItem(int index){
    		return table.get(index);
    }


    public void setCode(char inputChar, String newCode){
    	
    		for (TableItem item : table){
            if (item.getInputChar() == inputChar){
                item.setCode(newCode);
            }
        }
        
    }
}