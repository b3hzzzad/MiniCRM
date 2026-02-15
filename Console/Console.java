package Console;

import Exceptions.InvalidInputException;

import java.util.Scanner;

public class Console {

    private final Scanner scanner = new Scanner(System.in);

    public int getInt(String msg) throws InvalidInputException {
        System.out.println(msg);

        if (!scanner.hasNextInt()) {
            scanner.next();
            throw new InvalidInputException("Input must be a valid integer.");
        }

        int value = scanner.nextInt();
        scanner.nextLine();

        if (value < 0) {
            throw new InvalidInputException("Integer cannot be negative.");
        }

        return value;
    }

    public String getString(String msg) throws InvalidInputException {
        System.out.println(msg);

        String value = scanner.nextLine().trim();

        if (value.isEmpty()) {
            throw new InvalidInputException("String cannot be empty.");
        }

        return value;
    }

    public double getDouble(String msg) throws InvalidInputException {
        System.out.println(msg);

        if (!scanner.hasNextDouble()) {
            scanner.next();
            throw new InvalidInputException("Input must be a valid decimal number.");
        }

        double value = scanner.nextDouble();
        scanner.nextLine();

        if (value < 0) {
            throw new InvalidInputException("Double value cannot be negative.");
        }

        return value;
    }
}
