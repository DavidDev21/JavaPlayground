package InterfacePlayground;

// Test class for inner class
public class OuterClass {

    private int firstNum;
    public int secondNum;
    public InnerClass innerObj;

    class InnerClass
    {
        public void innerMethodOne()
        {
            System.out.println("firstNum: " + firstNum);
        }
    }

    public OuterClass()
    {
        this.firstNum = 1;
        this.secondNum = 2;
        this.innerObj = new InnerClass();
    }

    public void OuterMethodOne()
    {
        innerObj.innerMethodOne();
    }

    public void OuterMethodThree() { System.out.println("OUTERMETHODTHREE()"); }

    public void OuterMethodTwo(int inputOne) {

        int localVar = 0;
        class LocalInner
        {
            public void localInnerOne()
            {
                System.out.println("HELLO FROM INNER LOCAL");
            }

            public void localInnerTwo()
            {
                // Not allowed, must be final
                // inputOne++;

                // This is allowed since this method does have access to it from the outer class?
                firstNum++;

                // Why is this allowed by compiler???? So we are fine as long we dont modify the local variable?
                secondNum += inputOne;
            }

            public void localInnerThree()
            {
                // Doesn't work either
                //localVar++;
            }

        }

        // So local inners can still modify and access outerclass fields directly without trouble.
        LocalInner localTester = new LocalInner();
        /*
            Local inner classes still behave like normal inner classes with the exception that local inner classes
            can't be access or seen by anyone outside of the method and that local inner methods that access
            to local variables as long they are final (or you dont touch them in your inner method code).
         */
        localTester.localInnerOne();
        localTester.localInnerTwo();
        // testing what if a local variable that inner method has access to changes from underneath it.
        // inputOne += 2; (compiler will complain right away)


        System.out.println("FIRST NUM: " + firstNum);
        System.out.println("SECOND NUM: " + secondNum);

        OuterMethodThree();

        // Attempt at anomoyous
        OuterClass innerObjTester = new OuterClass() {
            public void noNameMethodOne()
            {
                System.out.println("FROM NO NAME?");
            }

            @Override
            public void OuterMethodThree()
            {
                System.out.println("OVERRIDED METHODTHREE FROM NO NAME INNER");
            }
        }; // annymous inner that extends OuterClass()?
        //innerObjTester.OuterMethodTwo(); // this would become recursive
        innerObjTester.OuterMethodThree();
        //innerObjTester.noNameMethod(); Hmm, so we can't access the anonomyous inner's method
        // is it because it is still being casted as OuterClass? Thus through polymorphism
        // our base class is still OuterClass rather than our no name class. what if we overrided something.

        // Okay so overriding from within the anomoyous inner class does work
        innerObjTester.OuterMethodThree();
        /*
            So in reality, we really can't do a lot with anomyous inner classes since they dont have name to begin with
            They can be used for short implementation of interfaces like ActionListener or short easy initialization
         */
    }

}
