


from lcl_lexer import tokens
import LCLToJavaTranslator 

names = { }#Used for verification purposes
operations = []#At the end it will hold all valid operations.

def p_circuit_assign(p):
    'circuit : NAME EQUALS input'
    names[p[1]] = p[3]

def p_input_operation(p):
    '''input : input AND input
               | input OR input
               | input XOR input
               | input NAND input
               | input NOR  input
               | input XNOR input'''
    if p[2] == 'and':
        p[0] = p[1] and p[3]      
    elif p[2] == 'or':
        p[0] = p[1] or p[3]
    elif p[2] == 'xor':
        p[0] = (p[1] and (not p[3])) or ((not p[1]) and p[3]) 
    elif p[2] == 'nand':
        p[0] = not(p[1] and p[3])
    elif p[2] == 'nor':
        p[0] = not(p[1] or p[3])
    elif p[2] == 'xnor': not((p[1] and not p[3]) or (not p[1] and p[3]) )


def p_input_factor(p):
    'input : LPAREN input RPAREN'
    p[0] = p[2]

def p_input_number(p):
    'input : VALUE' 
    p[0] = p[1]

def p_input_name(p):
    'input : NAME'
    try:
        p[0] = names[p[1]]     
    except LookupError:
        print("Undefined name '%s'" % p[1])
        del operations[-1]
        p[0] = 0

def p_error(p):
    print("Syntax error at '%s'" % p.value)
    print("Verify that all variable names")
    print("have only one letter.")
    del operations[-1]

import ply.yacc as yacc
parser = yacc.yacc()



#Test for parser
def translateToIntermediateCode(fname):
    try:
        LCLSourceFile = fname
        with open(LCLSourceFile) as file_object:
            print "Code given: "
            for line in file_object:
                cleanLine = line.strip().replace(' ','')         
                print(cleanLine)
                operations.append(cleanLine)
                parser.parse(cleanLine)
        #Prints out every verified line of code:
        #print('Verified operations')
        inputs = ''
        circuits = ''
    
        for op in operations:
            if len(op) == 3: 
                inputs += op + '\\n'
            else: 
                circuits+= op + '\\n'
        #print('inputs:\n' + inputs)
        #print('circuits:\n' + circuits) 

        if (len(inputs)>=1) and (len(circuits)>=1):
            LCLToJavaTranslator.LCLApplicationCreator(inputs,circuits)
        else:
            raise SyntaxError("Error: There must be at least one valid input declaration\nand one valid circuit declaration.")

    except IOError:
        print("Error opening file")
        exit()







