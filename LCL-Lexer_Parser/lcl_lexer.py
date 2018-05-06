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
    'LPAREN','RPAREN',
    )

# Tokens
t_EQUALS  = r'='
t_LPAREN  = r'\('
t_RPAREN  = r'\)'
t_NAME    = r'[a-zA-Z_]'

#def t_NUMBER(t):
#    r'\d+'
#    t.value = int(t.value)
#    return t

# Ignored characters
t_ignore = " \t"

def t_newline(t):
    r'\n+'
    t.lexer.lineno += t.value.count("\n")

def t_error(t):
    print("Illegal character '%s'" % t.value[0])
    t.lexer.skip(1)

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
        t.value = 'true'
    elif t.value == '0':
        t.value = 'false'
    return t   

# Build the lexer
lexer = lex.lex()

data = '''A=1 B=2 C=AandB'''

lexer.input(data)

while True:
    tok = lexer.token()
    if not tok:
        break
    print tok



