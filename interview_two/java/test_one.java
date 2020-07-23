public class test_one {
    public static void main(String[] args) {
        System.out.println(derivatives(7.5d, 0d));
    }

    public static double function(double x) {
        // 1.2* x^3 +6 * x^2 + 3.2 * x
        return (1.2 * pow(x,3)) + (6 * pow( x,2)) + (3.2 * x);
    }

    public static double derivatives(double x, double x0) {
        // 求导 3.6*x^2 + 3.6*Δx*x + Δx^2 + 12*x + 6*Δx + 3.2
        return (3.6 * pow( x,2)) + (3.6 * x * x0) + pow( x0 ,2) + (12 * x) + (6 * x0) +3.2;
    }

    // 求幂
    public static double pow(double x, int y) {
        double i = 1.0d;
        while (y > 0) {
            if ( (y & 1) != 0) {
                i *= x;
            }
            x *= x;
            y >>= 1;
        }
        return i;
    }
}
