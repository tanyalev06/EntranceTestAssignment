import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Gehtsoft Technical Assessment");
        System.out.println("Please choose an option: ");
        System.out.println("1. Caesar Cipher Encryption");
        System.out.println("2. Caesar Cipher Decryption");
        System.out.println("3. Arithmetic Expression Evaluation");

        int menuoption = scanner.nextInt();
        scanner.nextLine();

        String message = "";
        StringBuilder fileContent = new StringBuilder();
        int shift = 0;

        if (menuoption == 1) {
            System.out.println("Chose the option, please: ");
            System.out.println("1 - Encript entered message: ");
            System.out.println("2 - Encript message from file: ");

            int action = scanner.nextInt();
            scanner.nextLine();

            if (action == 1) {
                System.out.println("Enter your message, please: ");
                message = scanner.nextLine();
            } else if (action == 2) {
                System.out.println("Enter the file path, please : ");
                String fileway = scanner.nextLine();
                File file = new File(fileway);
                Scanner filescanner = new Scanner(file);
                while (filescanner.hasNextLine()) {
                    fileContent.append(filescanner.nextLine()).append("\n");
                }
                filescanner.close();
                message = fileContent.toString();
            } else {
                System.out.println("Choose the right option, please: ");
                scanner.close();
                return;
            }

            System.out.println("Enter a shift value (integer), please: ");
            shift = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Encrypted message:");
            System.out.println(CaesarCipherImplementation.caesarCipher(message, shift));
        } else if (menuoption == 2) {
            System.out.println("Chose the option, please: ");
            System.out.println("3 - Decrypt message if the shift value is known: ");
            System.out.println("4 - Decrypt message if the shift value is NOT known: ");

            int action = scanner.nextInt();
            scanner.nextLine();

            if (action == 3) {
                System.out.println("Enter the file path, please: ");
                String fileway = scanner.nextLine();
                File file = new File(fileway);
                Scanner filescanner = new Scanner(file);
                while (filescanner.hasNextLine()) {
                    fileContent.append(filescanner.nextLine()).append("\n");
                }
                filescanner.close();
                message = fileContent.toString();

                System.out.println("Enter a shift value (integer), please: ");
                shift = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Decrypted message: ");
                System.out.println(CaesarCipherImplementation.caesarCipher(message, shift));

            } else if (action == 4) {
                System.out.println("Enter your message, please: ");
                message = scanner.nextLine();

                System.out.println("Options of decryption depend on shift: ");
                for (int i = -32; i <= 32; i++) {
                    System.out.println("Shift " + i + ":");
                    System.out.println(CaesarCipherImplementation.caesarCipher(message, i));
                    System.out.println();
                }
            } else {
                System.out.println("Choose the right option, please");
                scanner.close();
                return;
            }

        } else if (menuoption == 3) {
            ArithmeticExpressionEvaluator evaluator = new ArithmeticExpressionEvaluator();

            System.out.print("Enter your expression, please: ");
            String input = scanner.nextLine();

            try {
                double result = evaluator.evaluate(input);
                System.out.println("Result: " + result);
            } catch (ArithmeticException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error: wrong expression");
            }
        } else {
            System.out.println("Choose the right option, please");
        }

        scanner.close();
    }
}