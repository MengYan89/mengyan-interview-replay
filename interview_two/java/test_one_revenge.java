
public class test_one_revenge {
    /**
     * 函数接口,传入要 求导的函数
     */
    interface Function {
        double accept(double value);
    }
    /**
     *  求导类
     */
    static class Derivatives{
        // 求导的函数
        private Function function;
        // 默认Δx无限趋近于0
        private static final double DELTA_X = 0.000001d;
        // 设置Δx
        private Double lim_x;
        public Derivatives(Function function) {
            this.function = function;
        }
        public Derivatives(Function function, Double lim_x) {
            this.function = function;
            this.lim_x = lim_x;
        }
        // 调用function中储存的函数accept进行求导，如果lim_x为null使用默认的无限趋近于0.
        public double derivation(double x) {
            if (lim_x == null) {
                return (function.accept(x + DELTA_X) - function.accept(x))/DELTA_X;
            } else {
                return (function.accept(x + lim_x) - function.accept(x))/lim_x;
            }
        }
    }
    public static void main(String[] args) {
        // 用我们需要的求导的函数创建一个Derivatives
        Derivatives derivatives = new Derivatives((x)->(1.2 * pow(x,3)) + (6 * pow( x,2)) + (3.2 * x));
        // 调用derivation求函数在x=7.5 时的导数
        System.out.println(derivatives.derivation(7.5d));

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
