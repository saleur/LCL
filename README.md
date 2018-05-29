# LCL Programming Language Developed by Luis F. Ruelas and Eduardo Nieves for the ICOM4036 Course Project

# Introduction
The Logical Circuits Language, or LCL for short, is a simple programming language that allows a user to simulate a logical circuit with a few lines of code which are parsed into a Java intermediate code. After the successful compilation and execution of LCL intermediate code the user will be presented with their circuit in a GUI that allows them to see the result of their circuit simulation as well as edit the current circuit in real time to simulate other logical circuits by following the LCL code syntax.

## Why use LCL?
Sometimes you want to simulate a simple logical circuit and you don’t want to learn how to use software tools or simulators to accomplish a simple simulation of your logical circuit. You also don’t want to have to learn to use programming languages like C, C++, C#, Python, etc., so that you can make your own simple simulation of a logical circuit. This is where LCL comes into play by writing simple grammatical expressions LCL provides a bridge between the simple and the complex. It provides a GUI, driven by Java, that is simple and allows you to edit your logical circuit in real-time by using its simple grammatical expressions.

## Language Features
* Translate LCL’s simple grammar into complex Java code that can be compiled and run.
* Create a GUI that presents your logical circuits and simulates them and allows for  real-time editing of those circuits.

## Aproach

![](https://github.com/saleur/LCL/blob/master/Project%20Images/Language%20Architecture.jpg)

### The LCL Architecture works in the following way:
1. First the user writes code in the lclcode.txt file following the LCL grammar.
2. Then the user runs the LCL translator which reads the LCL code in the lclcode.txt file and is sent to the PLY Lexer which tokenizes the code and which is later sent to the PLY Parser which parses it.
3. When the PLY Parser finishes the Java Intermediate is created.
4. The Java Intermediate Code creates the GUI and logical circuits.
5. After the code is ready, the user can compile it using the Java Compiler.
6. The LCL GUI Application can be executed to simulate the logical circuits.

# Learn LCL

## Video Tutorial

Click on the image below to see the Video Tutorial

[![Video Tutorial](https://img.youtube.com/vi/668EVaHnnJc/0.jpg)](https://www.youtube.com/watch?v=668EVaHnnJc)

## Language Tutorial

Click [here](https://github.com/saleur/LCL/wiki/Language-Tutorial) to go to the Language Tutorial.

## Reference Manual

Click [here](https://github.com/saleur/LCL/wiki/Language-Reference-Manual) to go to the Language Tutorial.

# Contributors
* [Luis F. Ruelas Lisboa](https://github.com/saleur)
* [Eduardo O. Nieves Colon](https://github.com/eduardonieves)


