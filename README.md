# a427

This repo is homework for Astr427 taken at UW in Winter 2019. Each homework is in its own folder:

# [HW 1](https://github.com/maria8ch/a427/tree/master/hw1/hw1j)

## Homework 1: Machine numbers; Interpolation

### 1. Floating point representation
For the float data type, write a program to empirically (i.e., by performing tests on addition and subtraction operations within your program) determine the following “Machine constants” for your computer:

(a) The smallest &epsilon; such that 1.0 − &epsilon; &ne; 1.0

(b) The smallest &epsilon; such that 1.0 + &epsilon; &ne; 1.0

(c) The maximum representable number

(d) The minimum representable positive number

Comment on why the numbers you get are expected based on the IEEE 754 representation.


### 2. Roundoff error
Numerically evaluate the expression (1−cos(x))/x<sup>2</sup> in double precision for values of x around 10<sup>-7</sup> and smaller. Explain the difference between the numerical results and the analytic limit as x → 0.


### 3. Interpolation
(a) Write a program to read in a two column table from a file and perform linear interpolation at an arbitrary point. You may assume that the data is evenly spaced in the independent variable (this makes it easier to determine which points to use for interpolation).
(b) Use the program on the following input data: (available on the web site as hw1.dat)

x | y
------------ | -------------
-1.0 | 0.03846154
-0.5 | 0.13793103
0.0 | 1.0
0.5 | 0.13793103
1.0 | 0.03846154


and provide a linear estimate of y at x = 0.75.
(c) Write a program using Neville’s algorithm to fit a 4th order polynomial to the above data and provide an estimate of y at x = 0.75.
(d) The actual function tabulated above is y = 1/(1+25x<sup>2</sup>). Compare the actual value at x = 0.75 with the linear interpolation and the 4th order polynomial interpolation, and comment on why one is more accurate than the other.


[HW 2](https://github.com/maria8ch/a427/tree/master/hw2)

[HW 3](https://github.com/maria8ch/a427/tree/master/hw3)

[HW 4](https://github.com/maria8ch/a427/tree/master/hw4)

[HW 5](https://github.com/maria8ch/a427/tree/master/hw5)
