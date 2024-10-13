package polynomial;

import java.util.ArrayList;

public class Polynomial {
	// polynomial is just a list of terms
	private ArrayList<Term> polynomial;

	public Polynomial() {
		polynomial = new ArrayList<Term>();
	}

	public void addTerm(Term t) {
		polynomial.add(t);
	}

	public Term getTerm(int index) {
		return polynomial.get(index);
	}

	public Number eval(Number input) {
		Number res = new Number(0);
		
		for (Term t : polynomial) {
			res = Number.add(res, t.eval(input));
		}
		return res;
	}

	public Polynomial derive() {
		Polynomial derivative = new Polynomial();
		for (Term t : polynomial) {
			derivative.addTerm(t.derive());
		}
		return derivative;
	}

	public String stringRep() {
		StringBuilder sb = new StringBuilder();
		for (Term t : polynomial) {
			Number coeff = t.getCoeff();
			Number pow = t.getPow();
			if (coeff.compare(new Number(0)) == 0 && pow.compare(new Number(0)) == 0)
				continue;
			if (sb.toString() != "") {
				// find sign of this term and using that ensure it is a
				// subtract or add
				if (coeff.sign() == 1) {
					sb.append("+");
				}
			}

			Number.simplify(coeff);
			Number.simplify(pow);
			int pow0 = pow.compare(new Number(0));
			if (coeff.getDenom() == 1) {
				if (coeff.getNumer() != 1 || pow0 == 0)
					sb.append(coeff.getNumer());
			} else {
				sb.append(coeff.getNumer());
				sb.append("/");
				sb.append(coeff.getDenom());
			}
			if (pow0 != 0) {
				sb.append("x");
				if (pow.compare(new Number(1)) != 0) {
					sb.append("^");
					if (pow.getDenom() == 1) {
						sb.append(pow.getNumer());
					} else {
						sb.append(pow.getNumer());
						sb.append("/");
						sb.append(pow.getDenom());
					}
				}
			}

		}
		return sb.toString();
	}

	public ArrayList<Number> solve(int x1, int x2) {
		ArrayList<Number> roots = new ArrayList<Number>();
		// in order to use Newton's method, we need a good first guess
		// the two parameters signify the interval on which we are searching for
		// roots
		// first we make sure x1 is smaller than x2
		if (x1 > x2) {
			int temp = x2;
			x2 = x1;
			x1 = temp;
		}
		// then we check every decimal in the interval (0.1 interval) for sign changes
		double xA = (double) x1;
		Polynomial der = derive();
		Number nB = null;
		Number nA = null;
		for (double i = x1 + 0.5; i <= (double) x2; i += 0.5) {
			nB = new Number(i);
			nA = new Number(xA);
			int signA = this.eval(nA).sign();
			int signB = this.eval(nB).sign();
			if (signA != signB && signB != 0) {
				// sign change, which means there is root in this interval
				Number estimate = new Number(0);
				if (der.eval(nA).compare(new Number(0)) != 0 && roots.contains(nA) == false) {
					estimate = Number.multiply(nA, new Number(1));
				} else if (der.eval(nB).compare(new Number(0)) != 0 && roots.contains(nB) == false) {
					estimate = Number.multiply(nB, new Number(1));
				} else {
					Number rand = new Number(0.0);
					do {
						rand.setVal(Math.random() * (nB.getVal() - nA.getVal() + 1) + nA.getVal());
					} while (der.eval(rand).compare(new Number(0)) == 0);
					estimate = Number.multiply(new Number(1), rand);
				}
				// now that we have a good first guess, we can now FINALLY
				// apply newton's method
				for(int j =0; j< 100; j++) estimate = Number.subtract(estimate, Number.divide(this.eval(estimate), der.eval(estimate)));
				// found reasonable root so we can now put that in the array
				if (roots.contains(estimate) == false)
					roots.add(estimate);
			} else if (signA == 0) {
				if (roots.contains(nA) == false) {
					roots.add(nA);
				}
			} else if (signB == 0) {
				if (roots.contains(nB) == false) {
					roots.add(nB);
				}
			}
			xA += 0.5;
		}

		return roots;
	}
}