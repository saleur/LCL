package app;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JTextField;
import javax.swing.JTextPane;



import javax.swing.JButton;
import javax.swing.JFrame;

public class LCLInputPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	
	public LCLInputPanel(String inputs, String circuit) {
		super();
		setLayout(null);

		JLabel lblInputs = new JLabel("Inputs");
		lblInputs.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblInputs.setBounds(100, 42, 49, 16);
		add(lblInputs);
		
		JLabel lblCiruitLogic = new JLabel("Circuit Logic");
		lblCiruitLogic.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblCiruitLogic.setBounds(327, 42, 99, 16);
		add(lblCiruitLogic);
		
		JTextPane logicInputPane = new JTextPane();
		logicInputPane.setBounds(258, 70, 236, 192);
		add(logicInputPane);
		
		//Shows current circuit
		logicInputPane.setText(circuit);
		
		JButton btnRun = new JButton("Run");
		btnRun.setBounds(192, 265, 117, 29);
		add(btnRun);
		
		JTextPane inputsValuePane = new JTextPane();
		inputsValuePane.setBounds(6, 70, 236, 192);
		add(inputsValuePane);
		
		//Shows current inputs
		inputsValuePane.setText(inputs);
		

		btnRun.addActionListener( new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		        
		        try {
					// Shows Success Message Dialog after successfully updating the circuit
					LCLApplication.editCircuit(inputsValuePane.getText(),logicInputPane.getText());			
					JOptionPane.showMessageDialog(null, "Circuit Updated Succesfully", "", JOptionPane.INFORMATION_MESSAGE);

					
				} catch (Exception e1) {
					// Shows Error Message Dialog
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
					System.out.println(e1.getMessage());
				}   
		    }
		});

	}
	
	

	public Point panelOffset(JFrame frame)
	{
		String osName = System.getProperty("os.name").toLowerCase();
		
		int panelOffX,panelOffY;
		
		if(osName.contains("linux"))
		{
			panelOffX = 50;
			panelOffY = 50;
		}
		else if(osName.contains("mac os"))
		{
			panelOffX = 0;
			panelOffY = 50;
		}
		else
		{
			panelOffX = 0;
			panelOffY = 0;
		}
		
		return new Point(frame.getX()-panelOffX,frame.getY()-panelOffY);
	}
}
