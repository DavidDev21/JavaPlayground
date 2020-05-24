
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class ConwayLife {

    // Constants
    public static final int ROW_LENGTH = 10;
    public static final int COL_LENGTH = 10;
    public static final String LIVE_CELL = "*";
    public static final String DEAD_CELL = "-";

    public static void main(String[] args)
    {
        runLife(10);
    }

    public static void print_world(String[][] world)
    {
        System.out.println("========================");
        for(int row = 1; row < world.length-1; row++)
        {
            for(int col = 1; col < world[row].length-1; col++)
            {
                System.out.print(world[row][col]);
            }

            System.out.println("||");
        }
        System.out.println("========================");
    }

    public static void reset_world(String[][] world)
    {
        /*
            Initalize an empty world
         */
        for(int r = 0; r < world.length; r++)
        {
            for(int c = 0; c < world[r].length; c++)
            {
                world[r][c] = DEAD_CELL;
            }
        }
    }
    public static int init_world(String[][] world, String inputFile)
    {
        /*
            Init the world with given input file
            Any input line that are greater than the world grid are truncated
         */
        if(world == null)
        {
            System.err.println("No World is given");
            return -1;
        }
        // empty the world
        reset_world(world);
        Scanner fileReader = null;
        try{
            fileReader = new Scanner(new File(inputFile));

            int row = 1;

            while(fileReader.hasNextLine() && row < ROW_LENGTH-1)
            {

                char[] lineChars = fileReader.nextLine().toCharArray();

                int linePos = 0;
                // fill from input
                for(int col = 1; col < world[row].length-1; col++)
                {
                    if(linePos < lineChars.length)
                    {
                        String lineChar = String.valueOf(lineChars[linePos]);

                        if(lineChar.equals(LIVE_CELL))
                            world[row][col] = LIVE_CELL;
                        else
                            world[row][col] = DEAD_CELL;
                        linePos++;
                    }
                    else
                    {
                        world[row][col] = DEAD_CELL;
                    }
                }

                // move to next row
                ++row;
            }

            fileReader.close();

        } catch (FileNotFoundException e) {
            System.err.printf("%s not found%n", inputFile);
            if(fileReader != null)
            {
                fileReader.close();
            }
            return -1;
        }
        return 0;
    }

    public static int numNeighbors(String[][] world, int row, int col)
    {
        /*
            Counts the number of neighboring cells for given row, col cell
            World must have buffer rows and cols
         */
        if(world == null || row <= 0 || col <= 0 || row >= world.length-1 || col >= world[0].length)
            return -1;

        int count = 0;

        for(int r = row-1; r < row + 2; r++)
        {
            for(int c = col-1; c < col + 2; c++)
            {
                if((row != r || col != c) && world[r][c].equals(LIVE_CELL))
                {
                    count++;
                }
            }
        }

        return count;
    }

    public static String nextCellState(String[][] world, int row, int col)
    {
        /*
            Function to determine of the new state of the cell at row, col
            Rules based on conway
         */
        int numLive = numNeighbors(world, row, col);

        if (world[row][col].equals(DEAD_CELL) && numLive == 3)
        {
            return LIVE_CELL;
        }
        else if(world[row][col].equals(LIVE_CELL))
        {
            if(numLive > 3 || numLive < 2)
            {
                return DEAD_CELL;
            }
            else
            {
                return LIVE_CELL;
            }
        }
        else
        {
            return DEAD_CELL;
        }
    }

    public static void runLife(int genToRun)
    {
        String[][] world = new String[ROW_LENGTH+2][COL_LENGTH+2];
        String[][] nextGen = new String[ROW_LENGTH+2][COL_LENGTH+2];
        int genNum = 0;

        reset_world(world);
        reset_world(nextGen);

        init_world(world, "src/life.txt");

        do{
            System.out.printf("GEN %d ========%n", genNum);
            print_world(world);

            // process the next world (prints on each iteration)
            for(int row = 1; row < world.length; row++)
            {
                for(int col = 1; col < world[row].length-1; col++)
                {
                    nextGen[row][col] = nextCellState(world, row, col);
                }
            }

            for(int row = 1; row < world.length; row++)
            {
                for(int col = 1; col < world[row].length-1; col++)
                {
                    world[row][col] = nextGen[row][col];
                }
            }

            genNum++;
        } while(genNum <= genToRun);
    }
}
