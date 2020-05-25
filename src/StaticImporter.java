
/*
    Little test for how static imports behave
 */

//import FirstPackage.*;
//import FirstPackage.StaticImportTest; // already a static import context???
// versus, import static FirstPackage.StaticImportTest.*;
import static FirstPackage.StaticImportTest.*;

import static FirstPackage.StaticImportTest.*;

/*
    Learning notes about static imports:
        1.) static import's context is specific towards a class's static members (variables, and methods)
        2.) it allows the class that is using static imports for the src class (the one we importing from)
            to use the src classes' PUBLIC static variables and methods without the explictly prefixing with
            the class's name. Example: Math.pow(), with static imports allows us to say pow()
        3.) It does not import anything else from the class that is not PUBLIC static.
        4.) Should use it sparingly or not at all since multiple static imports can conflict and make code
        difficult to read. If there is conflict, we must be explict with what member fields we are using, otherwise
        the compiler can't figure out what we are referring to.
            Example: two static imports, with class names Apple & Banana, with the same method called "foo()"
            If we write foo() in our code, then the compiler can't tell what foo() we want to use.
            We will be forced to say, Apple.foo(), or Banana.foo() if we still want to do static import.

 */

public class StaticImporter {

    public static void main(String[] args)
    {
        printTestOne();
        System.out.println(staticString);

    }

}
