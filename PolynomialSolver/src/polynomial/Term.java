package polynomial;

public class Term {

	// number obj to represent the power the term is raised to and its coefficient
	private Number pow;
	private Number coeff;

	public Term(Number coefficient, Number power) {
		pow = power;
		coeff = coefficient;
	}

	public Number eval(Number input) {
		Number res = null;
		if(pow.getNumer() != 0) {
			res = Number.multiply(coeff, Number.pow(input, pow));
		}else {
			res = Number.multiply(coeff, new Number(1));
		}
		return res;
	}

	public Term derive() {
		Number.simplify(pow);
		if (pow.getNumer() == 0 || pow.getDenom() == 0)
			return new Term(new Number(0), new Number(0));
		if (pow.getNumer() == 1)
			return new Term(Number.multiply(coeff, new Number(1)), new Number(0));
		Term d = new Term(Number.multiply(pow, coeff), Number.subtract(pow, new Number(1)));

		return d;
	}

	public void setPow(Number pow) {
		this.pow = pow;
	}

	public void setCoeff(Number coeff) {
		this.coeff = coeff;
	}

	public Number getPow() {
		return pow;
	}

	public Number getCoeff() {
		return coeff;
	}

}
