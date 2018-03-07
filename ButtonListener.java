/**
 * @author Andrew Budihardja
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;


public class ButtonListener implements ActionListener{
	//the panel to repaint
	private JPanel panel;
	public ButtonListener(JPanel P){
		panel= P;
	}
	/**
	 * Repaints the panel whenever the button is clicked.
	 */
	
	public void actionPerformed(ActionEvent e){
		if(panel != null && e.getActionCommand().equals("Repaint")){
			//For a button,getActionCommand return the string on the button
			panel.repaint();
		}
	}
	
}
