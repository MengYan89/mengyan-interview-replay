class InteviewTest01Parent {
    public InteviewTest01Parent() {
        System.out.println("Test01 Parent");
    }
    {
        System.out.println("Parent class");
    }
    static {
        System.out.println("static Parent");
    }
}
public class Test08 extends InteviewTest01Parent{
    public Test08() {
        System.out.println("Test01");
    }
    {
        System.out.println("Test01 class");
    }
    static {
        System.out.println("static Test01");
    }
    public static void main(String[] args) {
        new Test08();
    }
}
