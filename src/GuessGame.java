
import java.util.Scanner;
import java.math.*;

public class GuessGame {

    private static final int MAX_RANGE = 10;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int randomNum = (int) (Math.random() * (MAX_RANGE+1));
        int userGuess = -1;

        do {
            System.out.printf("Please guess a number between (0-%d): ", MAX_RANGE);
            if(input.hasNextInt())
            {
                userGuess = input.nextInt();

                if (userGuess != randomNum)
                {
                    System.out.println("Wrong Guess, Try again");
                }
            }
            else
            {
                System.out.println("The guess has to be a number");
            }
        } while(userGuess != randomNum);

        System.out.println("CONGRATS YOU GOT IT RIGHT!");

        input.close();
    }

}
