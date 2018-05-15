def LCLApplicationCreator(inputDeclarations,circuitDeclarations):

    appFile = open('LCLCompilable/app/LCLApplication.java','w+')    

    #Java required imports:
    appFile.write("package app;\n")
    appFile.write("import java.awt.event.ActionEvent;\n")
    appFile.write("import java.awt.event.ActionListener;\n")
    appFile.write("import java.awt.event.WindowEvent;\n")
    appFile.write("import javax.swing.JFrame;\n")
    appFile.write("import javax.swing.JScrollPane;\n")    
    appFile.write("import componentsImages.Gates;\n")
    appFile.write("import javax.swing.JToolBar;\n")
    appFile.write("import javax.swing.JButton;\n\n")

    
    #Java class construction:
    appFile.write("public class LCLApplication {\n\n\n")

    appFile.write("private JFrame frame;//Frame that will contain the circuit panel.\n")
    appFile.write("private static JFrame editFrame;//Frame that will contain the edit panel.\n")
    appFile.write("private static LCLCircuitPanel circuit;//Panel containing the circuit.\n")
    appFile.write("private JScrollPane scrollableCircuit;//Used to make the circuit scrollable.\n")
    appFile.write("private static CircuitBuilder circuitBuilder;//Used to construct circuits form code.\n")
    appFile.write("private Gates gates;//This will hold instantiate all the images for the gates.\n\n\n")
    appFile.write("private JToolBar toolBar;\n")
    appFile.write("private JButton btnEditCircuit;\n\n")
    appFile.write("private static String inputDec;\n")
    appFile.write("private static String circuitDec;\n\n")

    #Write the main entry point:
    appFile.write("public static void main(String[] args)\n{\n")
    appFile.write("\tLCLApplication app = new LCLApplication();\n")
    appFile.write("\tapp.setup();\n\tapp.run();\n}\n\n")


    #Write setup() method:
    appFile.write("private void setup()\n{\n")
    appFile.write('\tframe = new JFrame("LCL Circuit");\n')
    appFile.write("\tframe.setSize(1280,720);\n")
    appFile.write("\tframe.setLocation(50,50);\n")
    appFile.write("\tframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);\n")
    appFile.write("\tframe.setVisible(true);\n\n")
    appFile.write("\tgates = new Gates();//Initializes the Gate Images\n\n")
    appFile.write('\tinputDec = "%s";\n' %inputDeclarations)
    appFile.write('\tcircuitDec = "%s";\n' %circuitDeclarations)
    appFile.write("\t//Build circuits\n")
    appFile.write("\tcircuitBuilder = new CircuitBuilder(inputDec, circuitDec);\n")
    appFile.write("\tcircuitBuilder.buildCircuits();\n")
    appFile.write("\t//Build the LCLCircuitPanel\n")
    appFile.write("\tcircuit = new LCLCircuitPanel();\n")
    appFile.write("\tcircuit.setPreferredSize(circuitBuilder.getNumOfCircuits());\n")
    appFile.write("\tcircuit.setLocation(circuit.panelOffset(frame));\n")
    appFile.write("\tcircuit.setVisible(true);\n")
    appFile.write("\tcircuit.addCircuits(circuitBuilder.getCircuits());\n")
    appFile.write("\t//Create scrollable panel for the LCLCircuitPanel\n")
    appFile.write("\tscrollableCircuit = new JScrollPane(circuit);\n")
    appFile.write("\tframe.add(scrollableCircuit);\n\n")
    appFile.write("\ttoolBar = new JToolBar();\n")
    appFile.write("\tscrollableCircuit.setColumnHeaderView(toolBar);\n\n")
    appFile.write('\tbtnEditCircuit = new JButton("Edit Circuit");\n')
    appFile.write("\ttoolBar.add(btnEditCircuit);\n\n")
    appFile.write("\tbtnEditCircuit.addActionListener(new ActionListener()\n\t{\n")
    appFile.write("\t\t@Override\n")
    appFile.write("\t\tpublic void actionPerformed(ActionEvent e)\n\t\t{\n")
    appFile.write("\t\t\tsetupInput(inputDec,circuitDec);\n\t\t}\n\t});\n\n}")

    #Write setupInput(String,String) method
    appFile.write("private void setupInput(String inputDec, String circuitDec){\n")
    appFile.write('\teditFrame = new JFrame("LCL Circuit");\n')
    appFile.write("\teditFrame.setSize(500,325);\n")
    appFile.write("\teditFrame.setLocation(50,50);\n")
    appFile.write("\teditFrame.setVisible(true);\n")
    appFile.write("\tLCLInputPanel inputsPanel = new LCLInputPanel(inputDec,circuitDec);\n")
    appFile.write("\tinputsPanel.setVisible(true);\n")
    appFile.write("\teditFrame.getContentPane().add(inputsPanel);\n}\n\n")

    #Write editCirtuit(String,String) method
    appFile.write("//Method called after the user presses the run button when editing the circuit\n")
    appFile.write("public static void editCircuit(String inputs, String circuit) throws Exception{\n")
    appFile.write("\ttry {\n")
    appFile.write("\t\tcircuitBuilder.buildDynamicCircuits(inputs, circuit);\n")
    appFile.write("\t\tupdateCircuitPanel();\n")
    appFile.write("\t\t//Updates the inputs and circuit\n")
    appFile.write("\t\tcircuitDec = circuit;\n")
    appFile.write("\t\tinputDec = inputs;\n\n")
    appFile.write("\t\t//Close frame after successfully updated the circuit\n")
    appFile.write("\t\teditFrame.dispatchEvent(new WindowEvent(editFrame, WindowEvent.WINDOW_CLOSING));\n")
    appFile.write("\t} catch(Exception e) {\n")
    appFile.write("\t\tthrow new Exception(e.getMessage());\n\t}\n}")
 
    #Write updateCircuitPanel() method
    appFile.write("//Updates the circuit panel\n")
    appFile.write("public static void updateCircuitPanel(){\n")
    appFile.write("\tcircuit.addCircuits(circuitBuilder.getCircuits());\n}\n\n")

    #Write run() method
    appFile.write("private void run()\n{\n")
    appFile.write("\tframe.revalidate();\n")
    appFile.write("\twhile(true)\n{\n")
    appFile.write("\t\tframe.repaint();\n")
    appFile.write("\t\ttry\n\t\t{\n")
    appFile.write("\t\t\tThread.sleep(1000);\n")
    appFile.write("\t\t}catch(Exception e){\n")
    appFile.write('\t\t\tSystem.out.println("Error in run() method.");\n')
    appFile.write("\t\t}\n\t}\n\n}\n\n}")

    



    
