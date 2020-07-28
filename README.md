# a427

# This page is a work in progess
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

1. Write a program (or programs) to integrate up to four sets of coupled differential equations (so it can handle the problems below) using
the Euler method, fourth order Runge-Kutta, and Leapfrog. (Note:
Leapfrog only applies to special cases.) For Runge-Kutta, you may use
a packaged routine such as available in scipy or Numerical Recipes. If
you do used a packaged routine, be sure to use one with a fixed
timestep and order so that testing the convergence can be
easily performed.
2. Use your program to solve the differential equation for x(t):
d
2x
dt2
+ x = 0;
with the initial conditions x(0) = 1, x0
(0) = 0. Note that this has the
analytical solution: x = cos(t)
(a) Integrate the equation for 0 ≤ t ≤ 30 using each of the methods,
and step sizes of 1, .3, .1, .03, and .01. Comment on the behavior
of the solutions.
(b) Plot log(|xnumerical(30)−xexact(30)|) as a function of log(stepsize)
and check for the expected convergence of the error term.
3. Now try the two dimensional orbit described by the potential:
Φ = −
1
√
1 + x
2 + y
2
.
The orbits are given by the coupled differential equations:
d
2x
dt2
= −
x
(1 + x
2 + y
2
)
3/2
,
d
2
y
dt2
= −
y
(1 + x
2 + y
2
)
3/2
.
(a) Integrate this for 0 ≤ t ≤ 100 for the initial conditions x = 1, y =
0, x0 = 0, y0 = .3. Try this with either Leapfrog or Runge-Kutta
and step sizes from .01 to 1. Plot x vs. y for these integrations.
(b) Plot the energy E = (x
02 + y
02
)/2 + Φ(x, y) as a function of time
for your integrations.


[HW 3](https://github.com/maria8ch/a427/tree/master/hw3)


1. Write a program to find the roots of one-dimensional equations using

a) Bisection method and b) Newton-Raphson method.

Test your implementations on the following equations:
(a) A simple test case: x<sup>2<sup> = a (choose an a, say 2.0, and solve for x).

(b) Kepler’s equation, used for determining Solar System orbits: M = E − e sin(E).

Here M is the “mean anomoly”, an angle that increases linearly in time; E is the “eccentric anomoly”, the position of the body from the center (not the focus) of the ellipse, and e is the eccentricity of the ellipse. Choose M = 1.5 and e = 0.5 and solve for E in the interval 0 ≤ E < 2π. Try it again for M = 1.5 and e = 0.9, but be careful where you start the Newton-Raphson. For each attempt, demonstrate how quickly the method converges by plotting the error (i.e., the difference between the current guess and the true answer) as a function of iteration number. I suggest using a logarithmic axis for the error.

2. It is frequently required that the Kepler problem be solved to the maximum precision available (i.e. to round-off) as quickly as possible. Attempt to do this with your Kepler solver. Which method, Bisection or Newton-Raphson, is better? Use your solver to plot sin(E) vs. cos(E) for 20 equally spaced values of M between 0 and 2π for an e = 0.9 orbit.

[HW 4](https://github.com/maria8ch/a427/tree/master/hw4)

1. Write a program to find the minimum of a function of one variable
using the Golden Search method.
Write your code in a flexible manner so that you can use it later for
other problems, and choose your stopping criteria carefully, considering
round-off issues.
2. Test your implementation on the following problem:
The rotation curves (that is, rotation velocity vs. distance from the
center) for galaxies are observed to rise linearly close to the center, and
to be constant far from the center. A possible function which can be
fit to such a rotation curve is;
vmodel(r) = vinf (1 − e
−r/r0
),
where vinf is the asymptotic velocity and r0 is a characteristic radius.
Using the Golden Search method, and assuming that vinf is 100 km/s,
find the r0 that gives the best fit of the above formula to the following
“data”:
robs (kiloparsecs) vobs(km/s)
1.0 12.09
2.0 47.53
3.0 51.80
4.0 63.28
5.0 90.33
6.0 84.32
7.0 92.23
8.0 94.84
9.0 99.37
10.0 94.42
This data is available at
http://faculty.washington.edu/trq/astr427/rot.csv. As a criteria for goodness of fit, use the standard least squares formula:
E =
N
Xdata
i=1
(vobs − vmodel(robs))2
.
That is, the observational error is the same for each data point.
3. Now relax the constraint on vinf and use a package (I recommend
scipy.optimize.minimize with the “Powell” method) to fit the above
data for both r0 and vinf .
Note that the above data were generated from a model with r0 = 3,
vinf = 100 and a Gaussian error with standard deviation of 10 km/s.
Comment on the differences between your results and the model values.

[HW 5](https://github.com/maria8ch/a427/tree/master/hw5)

1. Write a (non-GPU) program to calculate π by integrating the area of a
circle of radius 1 with the Monte-Carlo method. You may use a systemprovided random number generator. The Python random module says
it uses the Mersenne Twister which I mentioned in class as a quality
generator. Plot how the error in the estimated value of π changes with
the number of points used to evaluate the integral; does this follow the
expected error for a Monte Carlo method?
2. Implement the same Monte-Carlo method using CUDA on a CUDA
capable machine, such as the hyak gpu nodes. For those familiar with
Python, this may be easier using PyCUDA. This can be installed on
the hyak machines with pip install pycuda --user, or I have it installed in /usr/lusers/trq/.local/lib/python3.6/site-packages,
which requires that you module load contrib/python. You can interactively grab a GPU node on hyak with
srun -p stf-gpu -A stf --nodes=1 --mem=120G --time=1:00:00
--gres=gpu:P100:1 --pty /bin/bash
There is also an stf-int-gpu queue for interactive use, and a build-gpu
queue for building and installing GPU code. Verify that you get an accurate estimate of π, and compare the time taken with the CUDA
implementation with the time taken by the non-CUDA implementation.
