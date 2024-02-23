package com.oldmutual.insure.copierdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class CopierdemoApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(CopierdemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CopierdemoApplication.class, args);

        try (Scanner scanner = new Scanner(System.in)) {
            LOGGER.info("*******************************************************");
            LOGGER.info("Interactive Character Copier Demo by Mr. AL [For O.M.I]");
            LOGGER.info("*******************************************************");

            boolean exit = false;

            while (!exit) {
                LOGGER.info("Enter character to copy (type 'exit' to exit):");
                String input = scanner.nextLine();

                if ("exit".equalsIgnoreCase(input)) {
                    exit = true;
                    continue;
                }

                // Take only the first character
                char singleCharInput = input.charAt(0);

                ISource mockSource = createMockSource(String.valueOf(singleCharInput));
                StringBuilder destinationContent = new StringBuilder();
                IDestination mockDestination = createMockDestination(destinationContent);

                Copier copier = new Copier(mockSource, mockDestination);
                CharacterCopierController controller = new CharacterCopierController(mockSource, mockDestination);
                LOGGER.warn("Copier functionality in use, please don't interrupt" + copier);
                LOGGER.info("Copying characters...");
                controller.copyCharacters();
                LOGGER.info("Input: " + singleCharInput);
                LOGGER.info("Result in destination: " + destinationContent);

                resetDestination(destinationContent);

                // Test multiple chars
                String multipleInput = readMultipleCharacters(scanner);

                ISource mockSourceMultiple = createMockSource(multipleInput);

                controller = new CharacterCopierController(mockSourceMultiple, mockDestination);
                LOGGER.info("Copying multiple characters...");
                controller.copyMultipleCharacters();
                LOGGER.info("Input: " + multipleInput);
                LOGGER.info("Result in destination: " + destinationContent);

                resetDestination(destinationContent);
            }

            LOGGER.info("Exiting Interactive Character Copier Demo.");
        }
    }

    private static ISource createMockSource(String input) {
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

    private static IDestination createMockDestination(StringBuilder destinationContent) {
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

    private static String readMultipleCharacters(Scanner scanner) {
        LOGGER.info("Enter characters to copy multiple: ");
        return scanner.nextLine();
    }

    private static void resetDestination(StringBuilder destinationContent) {
        destinationContent.setLength(0);
        LOGGER.info("Destination content reset.");
    }
}
