package View;
/**
 * Main class.
 * 
 *@author Andrew Budihardja
 */
public final class GameMain {

    /** 
     * Constructor. 
     */
    private GameMain() {
    }
    
    /**
     * Main method.
     * 
     * @param theArgs the command line arguments
     */
    public static void main(final String[] theArgs) {
        new TetrisGUI();
    }

}
