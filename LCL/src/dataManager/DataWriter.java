package dataManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;



public class DataWriter {
	private static File fout = null;
	private static BufferedWriter bw = null;
	
	//Constructor
	public DataWriter(){
		fout = new File("LogicInput.txt"); 
	}
	
	//Method to write the input file
	public void writeToFile(String text, boolean append) {

		try {
			
			FileWriter fw = new FileWriter(fout, append);
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
