# Java-Polynomial-Solver
A Java implementation of a polynomial solver, capable of solving polynomials using Newton's method.

The code implements a custom system of representing polynomials through their coefficent and powers (and a number interface that allows for both double values and fractional representations).
Users are able to define custom polynomials with no limit, so long as their coefficient and subsequent powers are provided. Currently the program can solve for the roots of any polynomial, but this may be expanded to any terms that can be differentiated (since Newton's method works on those too, not just polynomials).

To find the roots, the code picks a suitable starting position (for beginning Newton's method), then applies Newton's method iteratively.

NOTE: this is an old project from December 2022

Below is a sample run of the program:



Enter the amount of terms in your polynomial: 

3

Enter the coefficient of your 1st term: 

1/3

Enter the power of your 1st term: 

3

Enter the coefficient of your 2nd term: 

1/2

Enter the power of your 2nd term: 

2

Enter the coefficient of your 3rd term: 

-1

Enter the power of your 3rd term: 

1

Your polynomial: 1/3x^3+1/2x^2-1x

Your polynomial's derivative: x^2+x-1


--Finding the roots--


Enter the beginning of the interval you would like to search for roots in: 

-4

Enter the ending of the interval you would like to search for roots in: 

4


Roots in the chosen interval: 

Root: -2.6374586088176875

Root: 0.0

Root: 1.1374586088176875

