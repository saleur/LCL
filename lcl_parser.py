

from lcl_lexer import tokens

names = { }

def p_circuit_assign(p):
    'circuit : NAME EQUALS input'
    names[p[1]] = p[3]

def p_circuit_input(p):
    'circuit : input'
    print(p[1])

def p_input_operation(p):
    '''input : input AND input
               | input OR input
               | input XOR input
               | input NAND input
               | input NOR  input
               | input XNOR input'''
    if p[2] == 'and'  : p[0] = p[1] and p[3]
    elif p[2] == 'or':  p[0] = p[1] or p[3]
    elif p[2] == 'xor': p[0] = (p[1] and (not p[3])) or ((not p[1]) and p[3]) 
    elif p[2] == 'nand': p[0] = not(p[1] and p[3])
    elif p[2] == 'nor': p[0] = not(p[1] or p[3])
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
        p[0] = 0

def p_error(p):
    print("Syntax error at '%s'" % p.value)

import ply.yacc as yacc
parser = yacc.yacc()



#Test for parser
try:
  LCLSourceFile = 'tCode.txt'
  with open(LCLSourceFile) as file_object:
      for line in file_object:
          cleanLine = line.strip()
          print(cleanLine)
          parser.parse(cleanLine)
  parser.parse('A')#This verifies that the parser registered the value correctly
                
except IOError:
   print("Error opening file")
   exit()







