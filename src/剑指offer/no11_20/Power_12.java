package 剑指offer.no11_20;

public class Power_12 {
    public double Power(double base, int exponent) {
        if (exponent == 0)
            return 1.0;
        if (base == 0 && exponent < 0) {
            throw new RuntimeException();
        }

        double result = 1.0;
        int absexp = exponent;
        if (exponent < 0) {
            absexp = -exponent;
        }

        result = PowerByAbsexp(base, absexp);

        if (exponent < 0) {
            result = 1.0 / result;
        }

        return result;
    }

    public double PowerByAbsexp(double base, int absexp) {
        double result = 1.0;

        for (int i = 0; i < absexp; i++) {
            result *= base;
        }
        return result;
    }
}
