package system;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import gates.*;
import gates.AbstractLogicalGate.GateType;

public class CircuitBuilder {
	
	private File codeFile;//Eliminate this later.
	private int numOfCircuits;
	private ArrayList<Circuit> circuits;//Holds all the circuits constructed.
	private HashMap<Character,Boolean> inputs;//Holds the values of the input declarations.
	private HashMap<Character,Boolean> outputs;//Holds the values of the ouputs of the constructed circuits.
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
		outputs = new HashMap<>();
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
	
	
	public void buildDynamicCircuits(String inputDec,String circuitDec) throws Exception
	{
		//mapOperators();
		HashMap<Character,Boolean> tempInputs = new HashMap<>();
		HashMap<Character,Boolean> tempOutputs = new HashMap<>();
		ArrayList<Circuit> tempCircuits = new ArrayList<>();
		Scanner inputScanner=null, circuitScanner = null;
		circuitYPos = 40;
		if(inputDec.length()<3||circuitDec.length()<6)			
			throw new Exception("Not enough characters in input or circuit declaration.");
		
		try
		{
			inputScanner = new Scanner(inputDec);
			while(inputScanner.hasNext())
			{
				String declaration = inputScanner.next();
				try {					
					contructDynamicInput(declaration,tempInputs);										
				} catch (Exception e) {
					inputScanner.close();
					throw new Exception("Verify input declaration: " + declaration);					
				}				
			}
			System.out.println("Finished constructing inputs.");				
			inputScanner.close();

			circuitScanner = new Scanner(circuitDec);
			while(circuitScanner.hasNext())
			{
				String declaration = circuitScanner.next();
				try {
					constructDynamicCircuit(declaration,tempCircuits,tempInputs, tempOutputs);					
				} catch (Exception e) {
					circuitScanner.close();
					throw new Exception("Verify circuit declaration: " + declaration);
				}
			}
			System.out.println("Finished constructing circuits.");
			circuitScanner.close();

		}catch(Exception e)
		{
			throw new Exception("Verify input and circuit declaration");
		};

		//If no error is found then all class atributes will be changed with the temporary or 'dynamic' ones.
		inputs = tempInputs;
		outputs = tempOutputs;
		circuits = tempCircuits;
		numOfCircuits = circuits.size();
	}

	private void contructDynamicInput(String inputDeclaration,HashMap<Character,Boolean> dynamicInputs) throws Exception
	{
		if(inputDeclaration.length()!=3)
			throw new Exception("Input Declarations need to have a length of 3 characters");
		if(!Character.isLetter(inputDeclaration.charAt(0))&&inputDeclaration.charAt(1)!='=')
			throw new Exception("Error in input declaration");
		if(inputDeclaration.charAt(2)!='1'&&inputDeclaration.charAt(2)!='0')
			throw new Exception("The only acceptable value for an input are either '1' or '0'.");
		
		char inputName = inputDeclaration.charAt(0);
		boolean inputValue = (inputDeclaration.charAt(2)=='1')? true : false;

		dynamicInputs.put(inputName, inputValue);
	}

	private void constructDynamicCircuit(String circuitDeclaration, ArrayList<Circuit> dynamicCircuits,HashMap<Character,Boolean> dynamicInputs,HashMap<Character,Boolean> dynamicOutputs) throws Exception
	{
		if(!Character.isLetter(circuitDeclaration.charAt(0))&&circuitDeclaration.charAt(1)!='=')
			throw new Exception();
		
		
		Circuit newCircuit = new Circuit(circuitXPos, circuitYPos);
		char circuitName = circuitDeclaration.charAt(0);
		String operations  = circuitDeclaration.substring(3);
		char[] componentInputNames = new char[2];
		boolean[] componentInputValues = new boolean[2];
		
		componentInputNames[0] = circuitDeclaration.charAt(2);
		componentInputValues[0]= getCorrectDynamicInputValue(circuitDeclaration.charAt(2),dynamicInputs,dynamicOutputs);
			
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
	
		componentInputNames[1] = operations.charAt(i);
		
		componentInputValues[1] = getCorrectInputValue(operations.charAt(i));
			
		newCircuit.addFirstComponent(operators.get(operator),componentInputNames, componentInputValues);
			
		
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
				tempInputValue = getCorrectInputValue(c);
				newCircuit.addComponent(operators.get(operator),c,tempInputValue);
				operator = "";
			}
				
			i++;
		}

		boolean circuitOutput = newCircuit.getCircuitOutput();
		dynamicOutputs.put(circuitName,circuitOutput);
			
		dynamicCircuits.add(newCircuit);	
		
		circuitYPos +=200;			
		
	}
	
	private Boolean getCorrectDynamicInputValue(char inputName,HashMap<Character,Boolean> dynamicInputs, HashMap<Character,Boolean> dynamicOutputs)
	{
		Boolean inputsValue = dynamicInputs.get(inputName);
		
		Boolean outputsValue = null;

		if(outputs.size()>0)
			outputsValue = dynamicOutputs.get(inputName);

		if(inputsValue!=null)
			return inputsValue;
			
		else if(outputsValue!=null)
			return outputsValue; 

	    return null;
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
		char circuitName = circuitDeclaration.charAt(0);
		String operations  = circuitDeclaration.substring(3);
		char[] componentInputNames = new char[2];
		boolean[] componentInputValues = new boolean[2];
		
		componentInputNames[0] = circuitDeclaration.charAt(2);
		componentInputValues[0]= getCorrectInputValue(circuitDeclaration.charAt(2));
		
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

		componentInputNames[1] = operations.charAt(i);
	
		componentInputValues[1] = getCorrectInputValue(operations.charAt(i));
		
		newCircuit.addFirstComponent(operators.get(operator),componentInputNames, componentInputValues);
		
	
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
				tempInputValue = getCorrectInputValue(c);
				newCircuit.addComponent(operators.get(operator),c,tempInputValue);
				operator = "";
			}
			
			i++;
		}

		boolean circuitOutput = newCircuit.getCircuitOutput();
		outputs.put(circuitName,circuitOutput);
		
		circuits.add(newCircuit);		
		
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

	private Boolean getCorrectInputValue(char inputName)
	{
		Boolean inputsValue = inputs.get(inputName);
		
		Boolean outputsValue = null;

		if(outputs.size()>0)
			outputsValue = outputs.get(inputName);

		if(inputsValue!=null)
			return inputsValue;
			
		else if(outputsValue!=null)
			return outputsValue; 

	    return null;
	}
	

}