package system;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import gates.*;
import gates.AbstractLogicalGate.GateType;

public class CircuitBuilder {
	
	private File codeFile;
	private int numOfCircuits;//This will be determined at later time.
	private ArrayList<Circuit> circuits;//This will be constructed at a later time.
	private HashMap<Character,Boolean> inputs;//This will be constructed at a later time.
	
	private HashMap<String,GateType> operators;
	
	private int circuitXPos;
	private int circuitYPos;

	private String inputDeclarations;
	private String circuitDeclarations;
	
	public CircuitBuilder(String codeFileName)
	{
		try
		{
			 codeFile = new File(codeFileName);
			 
		}catch(Exception e)
		{
			System.out.println("CicuitBuilder: Error message: " + e.getMessage());
			System.out.println("CircuitBuilder: Error in Circuit Builder Constructor");
		};
		
		numOfCircuits = 0;
		circuits = new ArrayList<>();
		inputs = new HashMap<>();
		operators = new HashMap<>();
		
		circuitXPos = 20;
		circuitYPos = 40;
		
	}
	
	public CircuitBuilder(String inputDeclarations,String circuitDeclarations)
	{
		numOfCircuits = 0;
		circuits = new ArrayList<>();
		inputs = new HashMap<>();
		operators = new HashMap<>();
		this.inputDeclarations = inputDeclarations;
		this.circuitDeclarations = circuitDeclarations;
		circuitXPos = 20;
		circuitYPos = 40;
	}

	
	public ArrayList<Circuit> getCircuits()
	{
		return circuits;
	}

	public int getNumOfCircuits()
	{
		return numOfCircuits;
	}

	/**
	 * This method constructs the circuits and their respective inputs.
	 * NOTE: This method assumes that all declarations have been passed through 
	 * the LCL parser.
	 */
	public void buildCircuits()
	{
		mapOperators();
		Scanner inputStatements = new Scanner(inputDeclarations);
		while(inputStatements.hasNext())
		{
			String inputDec = inputStatements.next();
			constructInput(inputDec);
		}
		System.out.println("Finished building inputs");
		inputStatements.close();

		Scanner circuitStatements = new Scanner(circuitDeclarations);
		while(circuitStatements.hasNext())
		{
			String circuitDec = circuitStatements.next();
			constructCircuit(circuitDec);
		}
		System.out.println("Finished building circuits");
		circuitStatements.close();

	}
	
	
	public void buildCircuit()
	{
		mapOperators();
		try
		{
			Scanner input = new Scanner(codeFile);
		
			String cursor = input.next();
			if(cursor.equals("inputs:"))
			{
				cursor = input.next();
				while(!(cursor.equals("circuit:"))){
						
					constructInput(cursor);
					cursor = input.next();
				}
					
				System.out.println("Inputs constructed:");
				for(Character inputName : inputs.keySet())
					System.out.println(inputName.toString() + '=' +inputs.get(inputName).toString());
				
				if(cursor.equals("circuit:"))
				{
					
					while(input.hasNext())
					{
						cursor = input.next();
						constructCircuit(cursor);
					}
					
					System.out.println("Circuits Constructed:");
					System.out.println(circuits.size());
				}
				else
				{
					System.out.println("CircuitBuilder: Error in code file format");
					input.close();
					return;
				}
				
			}
			else
			{
				System.out.println("CircuitBuilder: Error in code file format");
				input.close();
				return;
			}
				
			
			input.close();
				
		}catch(Exception e)
		{
			System.out.println("CircuitBuilder: Error message: " + e.getMessage());
			System.out.println("CircuitBuilder: Error in contructCircuit() method.");
		};
	}
	
	
	private void constructInput(String inputDeclaration)
	{
		System.out.println("Input Declaration Received: " + inputDeclaration);
		if(inputDeclaration.length()!=3)
		{
			System.out.println("Error in input declaration, maximum size is 3 characters");
			return;
		}
		
		char inputName = inputDeclaration.charAt(0);		
		
		boolean inputValue = (inputDeclaration.charAt(2)=='1')? true : false;
		//Add try catch for inputValue assignment
		
		inputs.put(inputName, inputValue);		
		
	}
	
	private void constructCircuit(String circuitDeclaration)
	{
		System.out.println("Circuit to Construct: " + circuitDeclaration);
		Circuit newCircuit = new Circuit(circuitXPos, circuitYPos);
		
		String operations  = circuitDeclaration.substring(3);
		char[] componentInputNames = new char[2];
		boolean[] componentInputValues = new boolean[2];
		
		componentInputNames[0] = circuitDeclaration.charAt(2);
		componentInputValues[0]= inputs.get(circuitDeclaration.charAt(2));
		
		String operator = "";
		
		int i = 0;
		while(i < operations.length())
		{
			char c = operations.charAt(i);
			if(Character.isLowerCase(c))
				operator += c;
			else
				break;
			i++;
		}
		
		//System.out.println("Finished first while loop");
		componentInputNames[1] = operations.charAt(i);
		componentInputValues[1] = inputs.get(operations.charAt(i));
		//System.out.println("Good at line 147 ");
		//System.out.println("operator = "+ operator + " inputs = " + componentInputs[0] + " " + componentInputs[1]);
		newCircuit.addFirstComponent(operators.get(operator),componentInputNames, componentInputValues);
		
		//System.out.println("Good at line 149");
		operator = "";
		i++;
		
		boolean tempInputValue;
		while(i<operations.length())
		{
			char c = operations.charAt(i);
			if(Character.isLowerCase(c))
				operator += c;
			else
			{
				tempInputValue = inputs.get(c);
				newCircuit.addComponent(operators.get(operator),c,tempInputValue);
				operator = "";
			}
			
			i++;
		}
		
		circuits.add(newCircuit);
		
		//System.out.println("Good at line 170");
		
		
		numOfCircuits++;
		circuitYPos +=200;
	}
	
	private void mapOperators()
	{
		operators.put("and", GateType.AND);
		operators.put("or", GateType.OR);
		operators.put("xor", GateType.XOR);
		operators.put("nand", GateType.NAND);
		operators.put("nor", GateType.NOR);
		operators.put("xnor", GateType.XNOR);
	}
	

}
