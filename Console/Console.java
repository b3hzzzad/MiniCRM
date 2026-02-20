package Console;

import java.util.Scanner;

public class Console {

    private final Scanner scanner = new Scanner(System.in);

    public int getInt(String msg) {
        while (true) {
            System.out.print(msg);

            try {
                int value = Integer.parseInt(scanner.nextLine().trim());

                if (value < 0) {
                    System.out.println("Integer cannot be negative.");
                    continue;
                }

                return value;

            } catch (NumberFormatException e) {
                System.out.println("Input must be a valid integer.");
            }
        }
    }

    public double getDouble(String msg) {
        while (true) {
            System.out.print(msg);

            try {
                double value = Double.parseDouble(scanner.nextLine().trim());

                if (value < 0) {
                    System.out.println("Double value cannot be negative.");
                    continue;
                }

                return value;

            } catch (NumberFormatException e) {
                System.out.println("Input must be a valid decimal number.");
            }
        }
    }

    public String getString(String msg) {
        while (true) {
            System.out.print(msg);
            String value = scanner.nextLine().trim();

            if (value.isEmpty()) {
                System.out.println("String cannot be empty.");
            } else {
                return value;
            }
        }
    }
}