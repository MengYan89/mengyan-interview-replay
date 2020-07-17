public class OverloadingTest {
    public int test(int i) {
        return i;
    }

    public int test(int a, int b) {
        return a+b;
    }

    public long test(int a, int b, int c) {
        return a+b+c;
    }
}
