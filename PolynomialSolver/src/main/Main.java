package main;

import java.util.ArrayList;
import java.util.Scanner;

import polynomial.Number;
import polynomial.Polynomial;
import polynomial.Term;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the amount of terms in your polynomial: ");
		int length = input.nextInt();
		Polynomial p = new Polynomial();
		String postfix = "st";
		for (int i = 1; i <= length; i++) {
			if (i == 2)
				postfix = "nd";
			if (i == 3)
				postfix = "rd";
			if (i == 4)
				postfix = "th";
			System.out.println("Enter the coefficient of your " + i + postfix + " term: ");
			String str = input.next();
			System.out.println("Enter the power of your " + i + postfix + " term: ");
			String str2 = input.next();
			Number coeff = getNum(str);
			Number pow = getNum(str2);
			p.addTerm(new Term(coeff, pow));
		}
		System.out.println("Your polynomial: " + p.stringRep());
		System.out.println("Your polynomial's derivative: " + p.derive().stringRep());

		System.out.println("\n--Finding the roots--\n");
		System.out.println("Enter the beginning of the interval you would like to search for roots in: ");
		int x1 = input.nextInt();
		System.out.println("Enter the ending of the interval you would like to search for roots in: ");
		int x2 = input.nextInt();
		
		
		ArrayList<Number> roots = p.solve(x1, x2);
		if (roots.size() != 0) {
			System.out.println("\nRoots in the chosen interval: ");
			for (Number root : roots) {
				System.out.println("Root: " + root.getVal());
			}
		}else {
			System.out.println("No roots were found in the interval");
		}
		input.close();
	}

	public static Number getNum(String str) {
		Number res = null;
		if (str.contains("/")) {
			// fraction
			String[] strs = str.split("/");
			int coeff = Integer.valueOf(strs[0]);
			int denom = Integer.valueOf(strs[1]);
			res = new Number(coeff, denom);
		} else {
			// it is simply an integer
			res = new Number(Integer.valueOf(str), 1);
		}
		return res;
	}
}
