import ply.lex as lex
import ply.yacc as yacc


# -----------------------------------------------------------------------------
# calc.py
#
# A simple calculator with variables.
# -----------------------------------------------------------------------------

tokens = (
    'NAME','VALUE',
    'AND','OR','XOR','NAND','NOR','XNOR','EQUALS',
    'LPAREN','RPAREN'
    )

# Tokens
t_EQUALS  = r'='
t_LPAREN  = r'\('
t_RPAREN  = r'\)'


def t_NAME(t):
    r'[A-Z_]'
    if len(t.value)==1:
        return t
    else: 
        return ValueError
    
    
def t_AND(t):
    r'and'
    t.value = 'and'
    return t

def t_OR(t):
    r'or'
    t.value = 'or'
    return t 

def t_XOR(t):
    r'xor'
    t.value = 'xor'
    return t 

def t_NAND(t):
    r'nand'
    t.value = 'nand'
    return t

def t_NOR(t):
    r'nor'
    t.value = 'nor'
    return t  

def t_XNOR(t):
    r'xnor'
    t.value = 'xnor'
    return t   
   

def t_VALUE(t):
    r'1|0'
    if t.value == '1':
        t.value = True
    elif t.value == '0':
        t.value = False
    return t 

t_ignore = " \t\n"


def t_error(t):
    print("Illegal character '%s'" % t.value[0])
    t.lexer.skip(1)

# Build the lexer
lexer = lex.lex()

try:
  LCLSourceFile = open("tCode.txt", 'r')
except IOError:
   print("Error opening file")
   exit()

sourceFile = LCLSourceFile.read()
lexer.input(sourceFile)
# Tokenize
while True:
     tok = lexer.token()
     if not tok:
        break      # No more input
     print(tok)


