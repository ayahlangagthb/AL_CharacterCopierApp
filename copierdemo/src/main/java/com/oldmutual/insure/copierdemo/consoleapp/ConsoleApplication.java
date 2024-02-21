package com.oldmutual.insure.copierdemo.consoleapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.oldmutual.insure.copierdemo.controller.CharacterCopierController;
import com.oldmutual.insure.copierdemo.controller.config.MyDestinationConfig;
import com.oldmutual.insure.copierdemo.controller.config.MySourceConfig;
import com.oldmutual.insure.copierdemo.functionality.Copier;
import com.oldmutual.insure.copierdemo.implementationfactory.MyDestination;
import com.oldmutual.insure.copierdemo.implementationfactory.MySource;

import java.util.Scanner;

@SpringBootApplication
@Import({MySourceConfig.class, MyDestinationConfig.class})
public class ConsoleApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ConsoleApplication.class, args);
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

    	                MySource mySource = new MySource(input);
    	                MyDestination myDestination = new MyDestination();
    	                Copier copier = new Copier(mySource, myDestination);
    	                CharacterCopierController controller = new CharacterCopierController(mySource, myDestination);

    	                System.out.println("\nCopying characters...");
    	                controller.copyCharacters();
    	                System.out.println("Result in destination: " + myDestination.getContent());

    	                resetDestination(myDestination);

    	                System.out.print("\nEnter characters to copy multiple (Ctrl+D to finish): ");
    	                String multipleInput = scanner.nextLine();
    	                mySource = new MySource(multipleInput);

    	                controller = new CharacterCopierController(mySource, myDestination);
    	                System.out.println("\nCopying multiple characters...");
    	                controller.copyMultipleCharacters();
    	                System.out.println("Result in destination: " + myDestination.getContent());

    	                resetDestination(myDestination);
    	            }

    	            System.out.println("\nExiting Interactive Character Copier Demo.");
    	        }

      
    }

    	 private void resetDestination(MyDestination destination) {
    	        destination.clearContent();
    	        System.out.println("\nDestination content reset.");
    	    }
}
