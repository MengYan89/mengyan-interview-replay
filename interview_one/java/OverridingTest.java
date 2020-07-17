public class OverridingTest extends BasicsOverriding{
    /**
     * 覆盖
     * @return
     */
    @Override
    public int test() {
        return 2;
    }

    /**
     * 调用父类方法
     * @return
     */
    public int test2()  {
        return super.test();
    }
}
