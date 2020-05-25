package FirstPackage;

public class StaticImportTest {

    public static String staticString = "HELLO STATIC TEST VAR";
    private static String privateStaticString = "STATIC TEST VAR 2";

    public static void printTestOne()
    {
        System.out.println("Static Method 1");
    }

    public void printTestTwo()
    {
        System.out.println("NONE STATIC METHOD 1");
    }
}
