package FirstPackage;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("HELLO WORLD");

        Scanner sc = new Scanner(System.in);

        int input = 0;
        String input2 = "";

        while(true) {

            if(sc.hasNextInt())
            {
                input = sc.nextInt();
            } else {
                input2 = sc.nextLine();
            }

            System.out.println(input);
            System.out.println(input2);
        }
    }
}
