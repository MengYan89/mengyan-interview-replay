/**
 * Created by mengyan on 2020/7/28.
 */
public class Test05 {
    public static void main(String[] args) {
        String s = null;
        iv.switchTest(s);
    }

    private void switchTest(String str) {
        switch (str) {
            case "1":
                System.out.println("1");
                break;
            case "2":
                System.out.println("2");
                break;
            case "null":
                System.out.println("null");
                break;
            default:
                System.out.println("default");
                break;
        }
    }
}
