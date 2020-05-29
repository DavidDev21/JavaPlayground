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

        localTester.localInnerOne();
        localTester.localInnerTwo();

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
    }

}
