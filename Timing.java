
/**
 * 
 * @author Andrew Budihardja
 *
 */
class Timing {
    public static final int NUM_TIMINGS = 5;

    
    private static long startTime=0;
    private static long endTime=0;
    private static long elapsedTime=0;
    
    public static void main(String[] args) {
        // it is best to do the timing a few times because when Java can appear
        // "slower when it starts", so if you see slower results for the first
        // couple of timing runs, it is reasonable to discard them
    	
    	
    	
    	
        for(int timing = 0; timing < NUM_TIMINGS; ++timing) {
        	
        	long[] theList = {10,100,1000,10000};
        	
        	
        	
        	for(int c=0;c<4;c++){
        		
        		System.out.println("n value = "+theList[c]);
        		
        		startTime = System.nanoTime();
        		for (int i = 0; i < theList[c]; i++) { 
        		}
            
            endTime = System.nanoTime();
            elapsedTime = endTime - startTime;
            // 1 second = 1000000000 (10^9) nanoseconds.
            System.out.println("first problem ="+elapsedTime + " nanoseconds or " + elapsedTime/(1000000000.0) + " seconds elapsed"
            );
            
        	
            
        	
        		
        		startTime = System.nanoTime();
        		for (int i = 0; i < theList[c]; i++) {
        			for (int j = 0; j < theList[c]; j++) {
        			}
        		}
        		
        		   endTime = System.nanoTime();
                   elapsedTime = endTime - startTime;
                   // 1 second = 1000000000 (10^9) nanoseconds.
                   System.out.println("second problem ="+elapsedTime + " nanoseconds or " + elapsedTime/(1000000000.0) + " seconds elapsed"
                		   );
        	
            
        	
        		
        		
        			startTime = System.nanoTime();
        			for (int i = 0; i < theList[c]; i++) {
        				for (int j = 0; j < theList[c]; j++) {
        				}
        			}
        			   endTime = System.nanoTime();
                       elapsedTime = endTime - startTime;
                       // 1 second = 1000000000 (10^9) nanoseconds.
                       System.out.println("third problem ="+elapsedTime + " nanoseconds or " + elapsedTime/(1000000000.0) + " seconds elapsed"
                    		    );
            	
            
        		
            		
        			startTime = System.nanoTime();
        			for (int i = 0; i < theList[c]; i++) {
        				for (int j = 0; j < i; j++) {
        				}
        			}
        			 endTime = System.nanoTime();
                     elapsedTime = endTime - startTime;
                     // 1 second = 1000000000 (10^9) nanoseconds.
                     System.out.println("fourth problem ="+ elapsedTime + " nanoseconds or " + elapsedTime/(1000000000.0) + " seconds elapsed"
                    		 );
          	
            
            
        		
            		
        			startTime = System.nanoTime();
        			for (int i = 0; i < theList[c]; i++) {
        				for (int j = 0; j < theList[c] * theList[c]; j++) {
        				}
        			}
        			endTime = System.nanoTime();
                    elapsedTime = endTime - startTime;
                    // 1 second = 1000000000 (10^9) nanoseconds.
                    System.out.println("fifth problem ="+ elapsedTime + " nanoseconds or " + elapsedTime/(1000000000.0) + " seconds elapsed"
                    		);
         	
            
        		
        		
            		
        			startTime = System.nanoTime();
        			for (int i = 0; i < theList[c]; i++) {
        				for (int j = 0; j < i; j++) {}
        				for (int k = 0; k < 8000; k++) { }
        			}
        			endTime = System.nanoTime();
                    elapsedTime = endTime - startTime;
                    // 1 second = 1000000000 (10^9) nanoseconds.
                    System.out.println("sixth problem ="+elapsedTime + " nanoseconds or " + elapsedTime/(1000000000.0) + " seconds elapsed"
                    		);
         	
            
        		
            		
        			startTime = System.nanoTime();
        			for (int i = 0; i < theList[c]; i++) {
        				for (int j = 0; j < i*i; j++) {
        					if (j % i == 0) {
        						for (int k = 0; k < j; k++) {
        						} 
        					}
        				}
        			}
        			endTime = System.nanoTime();
                    elapsedTime = endTime - startTime;
                    // 1 second = 1000000000 (10^9) nanoseconds.
                    System.out.println("seventh problem ="+elapsedTime + " nanoseconds or " + elapsedTime/(1000000000.0) + " seconds elapsed"
                    		);
         	
            
            
        		
            		
        			startTime = System.nanoTime();
        			for (int i = 0; i < theList[c]; i++) {
        				for (int j = 0; j < i*i; j++) {
        				}
        			}
        			endTime = System.nanoTime();
                    elapsedTime = endTime - startTime;
                    // 1 second = 1000000000 (10^9) nanoseconds.
                    System.out.println("eigth problem ="+elapsedTime + " nanoseconds or " + elapsedTime/(1000000000.0) + " seconds elapsed"
                    		);
        		}  
        }
    }
}
