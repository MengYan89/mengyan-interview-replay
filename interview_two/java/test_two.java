import java.text.DecimalFormat;

public class test_two {
    public static void main (String[] args) {
        // 格式化
        DecimalFormat df = new DecimalFormat("#.############");
        System.out.println(df.format(test()));
    }

    public static double test() {
        return (1+recursion(1,1.0d))*2;
    }

    // 递归计算
    public static double recursion(int number, double n) {
        n =n* (number*1.0/(1.0+(number*2)));
        if (n <= 1e-12) {
            return n;
        }
        number++;
        return n + recursion(number,n);
    }
}
