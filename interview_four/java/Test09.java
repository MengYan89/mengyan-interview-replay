public class Test09 extends InteviewTest03Parent {
    private String name = "fkh";

    public Test09() {
        sayHi();
        sayGoodbye();
    }
    public void sayHi() {
        System.out.println("fkh say hi:" + name);
    }

    public void sayGoodbye() {
        System.out.println("fkh say goodbye:" + name);
    }

    public static void main(String[] args) {
        new Test09();
    }

}
class InteviewTest03Parent {
    private String name = "parent";

    public InteviewTest03Parent() {
        sayHi();
        sayGoodbye();
    }
    public void sayHi() {
        System.out.println("Parent say hi:" + name);
    }
    public void sayGoodbye() {
        System.out.println("Parent say goodbye:"+name);
    }
}