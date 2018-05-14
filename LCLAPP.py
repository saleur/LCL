import lcl_parser as parser
import time
print "Tranlating LCL Code:"
time.sleep(5)

file = 'lclCode.txt'

try:
    parser.translateToIntermediateCode(file)
    print "Succesful Trasnlation of Correct Code.\nIntermdiate Code is ready for compilation."
except:
    print "Error in code"
