import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class FirstSwingApplication {
	public static void main(String[]args){
		JFrame frame = new JFrame("First swing application");
		frame.setSize(500,400);
		//terminate the application when closing the window
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		//frame.getContentPane().setBackground(Color.BLUE);
		Mypanel panel = new Mypanel();
		frame.add(panel);
		//add a label at the top of the frame
		JPanel northPanel = new JPanel();
		northPanel.setBackground(Color.white);
		//a Jpanel uses a Flowlayout
		JLabel label = new JLabel("This is a label");
		label.setFont(new Font("Courier", Font.ITALIC|Font.BOLD,18));
		northPanel.add(label);
		frame.add(label,BorderLayout.NORTH);
		frame.setVisible(true);
		
		//add a button at the bottom of the frame
		
		JButton button = new JButton("Repaint");
		JPanel southPanel = new JPanel();
		southPanel.setBackground(Color.WHITE);
		southPanel.add(button);
		frame.add(southPanel, BorderLayout.SOUTH);
		
		//Connect the button to a listener
		ActionListener listener = new ButtonListener(panel);
		button.addActionListener(listener);
	}
	
	

}
