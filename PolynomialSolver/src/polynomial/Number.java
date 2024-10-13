package polynomial;

public class Number {

	private boolean isFrac;
	private int numer;
	private int denom;
	private double val;

	public Number(boolean isFraction, int numerator, int denominator, double value) {
		if (isFraction) {
			numer = numerator;
			denom = denominator;
			if (denom != 0) {
				val = (double) ((double) numer) / ((double) denom);
			}
		} else {
			val = value;
			numer = 0;
			denom = 0;
		}
	}

	public Number(int numerator, int denominator) {
		isFrac = true;
		numer = numerator;
		denom = denominator;
		if (denom != 0) {
			val = (double) ((double) numer) / ((double) denom);
		}
	}

	public Number(double value) {
		val = value;
		isFrac = false;
		numer = 0;
		denom = 0;
	}

	public Number(int value) {
		isFrac = true;
		numer = value;
		denom = 1;
		val = (double) numer;
	}

	public int getNumer() {
		return numer;
	}

	public void setNumer(int numer) {
		this.numer = numer;
	}

	public int getDenom() {
		return denom;
	}

	public void setDenom(int denom) {
		this.denom = denom;
	}

	public double getVal() {
		return val;
	}

	public void setVal(double val) {
		this.val = val;
	}

	public boolean getIsFrac() {
		return isFrac;
	}

	public void reeval() {
		if (denom != 0) {
			val = (double) ((double) numer) / ((double) denom);
		}
	}

	public int compare(Number num) {
		int res = 0;
		if (val > num.val) {
			res = 1;
		} else if (val < num.val) {
			res = -1;
		} else if (val == num.val) {
			res = 0;
		}
		return res;
	}

	public static void commonDenom(Number n1, Number n2) {
		// makes each number have a common denominator
		if (n1.isFrac == false || n2.isFrac == false)
			return;
		int n1Denom = n1.denom;
		n1.numer *= n2.denom;
		n1.denom *= n2.denom;
		n2.numer *= n1Denom;
		n2.denom *= n1Denom;
	}

	public static void simplify(Number num) {
		// simplifies the fraction
		if (num.isFrac == false)
			return;
		int gcd = gcd(num.numer, num.denom);
		if (gcd != 0) {
			num.numer /= gcd;
			num.denom /= gcd;
		}
		// additionally, transfers the negative in the denom (if there is)
		// to the numer
		if (num.denom < 0) {
			num.denom *= -1;
			num.numer *= -1;
		}
	}

	public static Number add(Number n1, Number n2) {
		Number num = null;
		if (n1.isFrac && n2.isFrac) {
			// both numbers are fractions
			// and can be added like they are
			Number.commonDenom(n1, n2);
			// doesn't matter which denominator since both are same
			num = new Number((n1.numer + n2.numer), (n1.denom));
		} else {
			// either one or both numbers are decimal
			// means they simply have to be added
			num = new Number(n1.val + n2.val);
		}
		Number.simplify(num);
		return num;
	}

	public static Number subtract(Number n1, Number n2) {
		Number num = null;
		if (n1.isFrac && n2.isFrac) {
			// both numbers are fractions
			// and can be subtracted like they are
			Number.commonDenom(n1, n2);
			// doesn't matter which denominator since both are same
			num = new Number((n1.numer - n2.numer), (n1.denom));
		} else {
			// either one or both numbers are decimal
			// means they simply have to be subtracted
			num = new Number(n1.val - n2.val);
		}
		Number.simplify(num);
		return num;
	}

	public static Number multiply(Number n1, Number n2) {
		Number num = null;
		if (n1.isFrac && n2.isFrac) {
			// both numbers are fractions
			// and can be multiplied like they are
			num = new Number((n1.numer * n2.numer), (n1.denom * n2.denom));
		} else {
			// either one or both numbers are decimal
			// means they simply have to be multiplied
			num = new Number(n1.val * n2.val);
		}
		Number.simplify(num);
		return num;
	}

	public static Number divide(Number n1, Number n2) {
		Number num = null;
		if (n1.isFrac && n2.isFrac) {
			// both numbers are fractions
			// and can be divided like they are
			num = new Number((n1.numer * n2.denom), (n1.denom * n2.numer));
		} else {
			// either one or both numbers are decimal
			// means they simply have to be divided
			num = new Number(n1.val / n2.val);
		}
		Number.simplify(num);
		return num;
	}

	public static Number pow(Number base, Number power) {
		Number res = null;
		if (power.denom == 1) {
			// we can do repeated multiplication here
			res = Number.multiply(base, new Number(1));
			for (int i = 1; i < power.numer; i++)
				res = Number.multiply(res, base);
		} else {
			// we cannot do anything with repeated multiplication, must use math
			// pow method
			res = new Number(Math.pow(base.val, Math.abs(power.val)));
		}
		if (power.compare(new Number(0)) == -1) {
			res = Number.divide(new Number(1), res);
		}
		return res;
	}

	private static int gcd(int a, int b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}

	public int sign() {
		int sign = 0;
		if (isFrac) {
			if (numer > 0 && denom > 0)
				sign = 1;
			if (numer < 0 && denom > 0)
				sign = -1;
			if (numer < 0 && denom < 0)
				sign = 1;
			if (numer > 0 && denom < 0)
				sign = -1;
			if (numer == 0 && denom != 0)
				sign = 0;
		} else {
			sign = (val > 0) ? 1 : -1;
			if (val == 0.0)
				sign = 0;
		}
		return sign;
	}

	public static Number valueCopy(Number n) {
		return n;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Number other = (Number) obj;
		if (denom != other.denom)
			return false;
		if (numer != other.numer)
			return false;
		if (Double.doubleToLongBits(val) != Double.doubleToLongBits(other.val))
			return false;
		return true;
	}

}
