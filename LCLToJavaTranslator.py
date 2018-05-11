def LCLApplicationCreator(inputDeclarations,circuitDeclarations):

    appFile = open('LCLCompilableCode/LCLApplication.java','w+')    

    #Java required imports:
    appFile.write("import java.awt.Graphics;\n")
    appFile.write("import java.awt.Color;\n")
    appFile.write("import javax.swing.JFrame;\n")
    appFile.write("import javax.swing.JPanel;\n")
    appFile.write("import javax.swing.JScrollPane;\n")
    appFile.write("import compoentsImages.Gates;\n")

    
    #Java class construction:
    appFile.write("public class LCLApplication {\n\n\n")

    appFile.write("private JFrame frame;//Frame that will contain the circuit panel.\n")
    appFile.write("private LCLCircuitPanel circuit;//Panel containing the circuit.\n")
    appFile.write("private JScrollPane scrollableCircuit;//Used to make the circuit scrollable.\n")
    appFile.write("private CircuitBuilder circuitBuilder;//Used to construct circuits form code.\n")
    appFile.write("private Gates gates;//This will hold instantiate all the images for the gates.\n\n\n")

    #Write the main entry point:
    appFile.write("public static void main(String[] args)\n{\n")
    appFile.write("\tLCLSystem sys = new LCLSystem();\n")
    appFile.write("\tsys.setup();\n\tsys.run();\n}\n\n")


    #Write setup() method:
    appFile.write("private void setup()\n{\n")
    appFile.write('\tframe = new JFrame("LCL Circuit");\n')
    appFile.write("\tframe.setSize(1280,720);\n")
    appFile.write("\tframe.setLocation(50,50);\n")
    appFile.write("\tframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);\n")
    appFile.write("\tframe.setVisible(true);\n\n")
    appFile.write("\tgates = new Gates();//Initializes the Gate Images\n\n")
    appFile.write('\tString inputDec = "%s";\n' %inputDeclarations)
    appFile.write('\tString circuitDec = "%s";\n' %circuitDeclarations)
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
    appFile.write("\tframe.add(scrollableCircuit);\n\n}\n\n")

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

    



    
