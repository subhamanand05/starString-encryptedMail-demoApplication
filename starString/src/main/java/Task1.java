import java.util.InputMismatchException;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter any non-negative integer: ");
        try {
            int inputInt = in.nextInt();
            in.close();
            if (inputInt < 0) {
                throw new IllegalArgumentException("Negative integer is not allowed in input");
            }
            System.out.println(starString(inputInt));

        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Input is invalid. Please enter a valid non-negative integer");
        }

    }

    public static String starString(int number) {
        if(number == 0) {
            return "*";
        } else {
            return starString(number-1) + starString(number-1);
        }
    }
}
