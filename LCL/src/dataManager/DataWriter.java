package dataManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;



public class DataWriter {
	private static File logicFout = null;
	private static File inputFout = null;
	private static BufferedWriter bw = null;
	
	//Constructor
	public DataWriter(){
		logicFout = new File("LogicInput.txt"); 
		inputFout = new File("InputValues.txt"); 
	}
	
	//Method to write the Logic Input file
	public void writeToLogicFile(String text, boolean append) {

		try {
			
			FileWriter fw = new FileWriter(logicFout, append);
			bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw);
			out.println(text);
			out.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.print(e);
			
	}
}	
	
	//Method to write the Input Values file
		public void writeToInputValuesFile(String text, boolean append) {

			try {
				
				FileWriter fw = new FileWriter(inputFout, append);
				bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw);
				out.println(text);
				out.close();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.print(e);
				
		}
	}
}
