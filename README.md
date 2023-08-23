# equationparser
String based equation calculation code written in Java
It is written in a single file (program.java), so it is easy to port, and convert to other languages by using a very small number of libraries (ArrayList, HashMap).

## features
It consists of an equation class that stores formulas and a program class that executes formulas.
Formulas written in strings can be stored in the equation class, and the equation class can be executed through the program class whenever necessary.
The program class preserves the variables created during execution, and the equation class can be executed multiple times.
The program class can be duplicated, and in case of duplication, the variables created during the execution process are duplicated.
There is no separate process to create a variable, and when a new variable name is identified during equation execution, a space for the variable is automatically created and initialized to 0.
A variable name can be anything that starts with an alphabetic character, and is case sensitive. However, duplicate function names or pi,e,NaN,i,true,false cannot be used as variable names.
Several mathematical functions are provided for simple mathematical operations, but functions cannot be added.
Complex numbers and exponential format numbers are supported, and the multiplication sign between the number and i in complex number notation and the sign between e and the number in exponential format can be omitted. (Example: 1+2i,1e10)
When calling a function, the order of argument calculation in parentheses is left->right, and the entire formula is always calculated even when logic or conditional calculations are performed (even parts that are not executed are calculated).
All numbers are treated as real numbers, and all comparison operations allow a small margin in determining true or false to account for floating-point precision issues.
The result of running equation is returned as a string. However, "" is returned if there is no execution result.

### operator list
():Priority change or function call
++,--:Pre and post increment
+,-:sign operation
~,!,|,||,&,&&,^,^^:Logic and bitwise operations, ^^ is a bitwise xor
+,-,*,/,**,%:arithmetic operations, ** for powers, % for remainder operations
<<,>>:shift operations
<,>,==,<=,>=:compare operations
? and : pair:if-else operations
=,+=,-=,*=,/=,<<=,>>=,&=,|=,^=,**=:assign operations
,:comma
;:semicolon, Used when putting multiple expressions into one equation

### function list

pow:2 arguments, power
exp:1 argument, power of e
log:1 or 2 arguments, The first argument is base of log, the second argument is value, and the base is 10 when the first argument is omitted.
sqrt,ln,cbrt,abs,arg:1 argument
conj:1 argument, conjugate value of complex
min,max:no limit on the number of arguments, min/max value calculation
floor,ceil:1 argument
round:1 argument, round to the nearest whole number
sin,cos,tan,sec,csc,cot,asin,acos,asec,acsc,acot,sinh,cosh,tanh,sech,csch,coth:1 argument
atan,atan2:1 or 2 arguments, both are treated as the same function, calculate arc tangent when inputting 1 argument, and atan2 (https://en.wikipedia.org/wiki/Atan2) when inputting 2 arguments
fact:1 argument, calculate complex factorial value
rand:1 argument, generate random value range between 0 and 1
isnan:1 argument, Checks if a variable is NaN and returns true or false
real,imag:1 argument
