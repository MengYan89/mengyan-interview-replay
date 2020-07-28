/**
 * Created by mengyan on 2020/7/28.
 */
public class Test04 {
    public static void main(String[] args) {
        Test04 vi = new Test04();
        vi.overloadTest02(6);
    }

    private void overloadTest02(double d) {
        System.out.println("double="+d);
    }

    private void overloadTest02(Integer i) {
        System.out.println("Integer="+i);
    }
}
