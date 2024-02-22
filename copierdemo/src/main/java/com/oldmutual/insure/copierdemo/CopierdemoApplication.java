package com.oldmutual.insure.copierdemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import com.oldmutual.insure.copierdemo.controller.CharacterCopierController;
import com.oldmutual.insure.copierdemo.functionality.Copier;
import com.oldmutual.insure.copierdemo.interfaces.IDestination;
import com.oldmutual.insure.copierdemo.interfaces.ISource;

import java.util.Scanner;

@SpringBootApplication
@ComponentScan("com.oldmutual.insure.copierdemo")
public class CopierdemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CopierdemoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Interactive Character Copier Demo");
            System.out.println("=================================");

            boolean exit = false;

            while (!exit) {
                System.out.print("\nEnter characters to copy (Ctrl+D to finish, type 'exit' to exit): ");
                String input = scanner.nextLine();

                if ("exit".equalsIgnoreCase(input)) {
                    exit = true;
                    continue;
                }

                ISource mockSource = createMockSource(input);
                StringBuilder destinationContent = new StringBuilder();
                IDestination mockDestination = createMockDestination(destinationContent);

                Copier copier = new Copier(mockSource, mockDestination);
                CharacterCopierController controller = new CharacterCopierController(mockSource, mockDestination);

                System.out.println("\nCopying characters...");
                controller.copyCharacters();
                System.out.println("Input: " + input);
                System.out.println("Result in destination: " + destinationContent);

                resetDestination(destinationContent);

                // Test multiple chars
                String multipleInput = readMultipleCharacters(scanner);

                ISource mockSourceMultiple = createMockSource(multipleInput);

                controller = new CharacterCopierController(mockSourceMultiple, mockDestination);
                System.out.println("\nCopying multiple characters...");
                controller.copyMultipleCharacters();
                System.out.println("Input: " + multipleInput);
                System.out.println("Result in destination: " + destinationContent);

                resetDestination(destinationContent);
            }

            System.out.println("\nExiting Interactive Character Copier Demo.");
        }
    }

    private ISource createMockSource(String input) {
        return new ISource() {
            private int currentIndex = 0;

            @Override
            public char readChar() {
                if (currentIndex < input.length()) {
                    return input.charAt(currentIndex++);
                } else {
                    return '\0'; // Indicating end of input
                }
            }

            @Override
            public String readChars(int count) {
                int endIndex = Math.min(currentIndex + count, input.length());
                String result = input.substring(currentIndex, endIndex);
                currentIndex = endIndex;
                return result;
            }
        };
    }

    private IDestination createMockDestination(StringBuilder destinationContent) {
        return new IDestination() {
            @Override
            public void writeChar(char c) {
                destinationContent.append(c);
            }

            @Override
            public void writeChars(char[] values) {
                destinationContent.append(values);
            }

            public String getContent() {
                return destinationContent.toString();
            }
        };
    }

    private String readMultipleCharacters(Scanner scanner) {
        System.out.print("\nEnter characters to copy multiple (Ctrl+D to finish): ");
        return scanner.nextLine();
    }

    private void resetDestination(StringBuilder destinationContent) {
        destinationContent.setLength(0);
        System.out.println("\nDestination content reset.");
    }
}
