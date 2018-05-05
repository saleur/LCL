package system;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JTextPane;

import dataManager.DataWriter;

import javax.swing.JButton;
import javax.swing.JFrame;

public class LCLInputPanel extends JPanel {
	
	private JTextField textField;
	private JTextField value_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField value_2;
	private JTextField value_3;
	private JTextField value_4;

	/**
	 * Create the panel.
	 */
	public LCLInputPanel() {
		super();
		setLayout(null);
		
		DataWriter writer = new DataWriter();
	
		
		JLabel lblInputs = new JLabel("Inputs");
		lblInputs.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblInputs.setBounds(100, 42, 49, 16);
		add(lblInputs);
		
//		textField = new JTextField();
//		textField.setBounds(16, 69, 70, 26);
//		add(textField);
//		textField.setColumns(10);
//		
//		value_1 = new JTextField();
//		value_1.setColumns(10);
//		value_1.setBounds(109, 69, 70, 26);
//		add(value_1);
//		
//		textField_2 = new JTextField();
//		textField_2.setColumns(10);
//		textField_2.setBounds(16, 108, 70, 26);
//		add(textField_2);
//		
//		textField_3 = new JTextField();
//		textField_3.setColumns(10);
//		textField_3.setBounds(16, 150, 70, 26);
//		add(textField_3);
//		
//		textField_4 = new JTextField();
//		textField_4.setColumns(10);
//		textField_4.setBounds(16, 188, 70, 26);
//		add(textField_4);
//		
//		value_2 = new JTextField();
//		value_2.setColumns(10);
//		value_2.setBounds(109, 108, 70, 26);
//		add(value_2);
//		
//		value_3 = new JTextField();
//		value_3.setColumns(10);
//		value_3.setBounds(109, 150, 70, 26);
//		add(value_3);
//		
//		value_4 = new JTextField();
//		value_4.setColumns(10);
//		value_4.setBounds(109, 188, 70, 26);
//		add(value_4);
		
		JLabel lblCiruitLogic = new JLabel("Circuit Logic");
		lblCiruitLogic.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblCiruitLogic.setBounds(327, 42, 99, 16);
		add(lblCiruitLogic);
		
		JTextPane logicInputPane = new JTextPane();
		logicInputPane.setBounds(258, 70, 236, 192);
		add(logicInputPane);
		
		//Example input
		logicInputPane.setText("A = (B || C)\nD = (A & C)");
		
		JButton btnRun = new JButton("Run");
		btnRun.setBounds(191, 417, 117, 29);
		add(btnRun);
		
		JTextPane inputsValuePane = new JTextPane();
		inputsValuePane.setBounds(6, 70, 236, 192);
		add(inputsValuePane);
		
		//Example input
		inputsValuePane.setText("B = 0\nC = 1");
		

		btnRun.addActionListener( new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		    	//We still have to check inputs fields are filled
		    	
		    	//Traduces &'s and ||'s to "and" & "or"
		    	String logic = logicInputPane.getText().replace("||", "or").replace("&", "and");
		        System.out.println(logic);
		        
		        //Writes the logic file
		        writer.writeToLogicFile(logic, false);
		        
		        //Writes the inputs Value file
		        writer.writeToInputValuesFile(inputsValuePane.getText(), false);
		        
		        
		        
		       
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
