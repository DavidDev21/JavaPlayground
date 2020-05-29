package InterfacePlayground;

public class InterfaceTest implements PlayInterface {

    @Override
    public void interMethodOne() {
        System.out.println("WOOO");
    }

    @Override
    public int interMethodTwo(int x, int y) {
        return 0;
    }

    public static void main(String[] args)
    {
        InterfaceTest testObj = new InterfaceTest();

        testObj.interMethodOne();

        OuterClass testObj2 = new OuterClass();

        testObj2.OuterMethodOne();

        testObj2.OuterMethodTwo(2);
    }
}


