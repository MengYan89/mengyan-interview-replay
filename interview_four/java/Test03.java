/**
 * Created by mengyan on 2020/7/28.
 */
public class Test03 {
    public static void main(String[] args) {
        Test03 iv = new Test03();
        iv.overloadTest01(null);
    }

    private void overloadTest01(String s) {
        System.out.println("String="+s);
    }

    private void overloadTest01(Integer i) {
        System.out.println("Integer"+i);
    }
}
