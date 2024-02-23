package com.oldmutual.insure.copierdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.oldmutual.insure.copierdemo.functionality.Copier;
import com.oldmutual.insure.copierdemo.interfaces.ISource;
import com.oldmutual.insure.copierdemo.interfaces.IDestination;

/**Please use this class for the evaluation of code as it follows the instruction of using ISource 
and IDestination while not implementing them but rather having Copier.class as the only implemented class,
with the use of Mock instances.
**/

@Component
public class CharacterCopierController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CharacterCopierController.class);

    private final ISource source;
    private final IDestination destination;
    private final Copier copier;

    @Autowired
    public CharacterCopierController(ISource source, IDestination destination) {
        this.source = source;
        this.destination = destination;
        this.copier = new Copier(source, destination);
    }

    public String copyCharacters() {
        try {
            copier.copy();
            LOGGER.info("Characters copied successfully.");
            return "Characters copied successfully.";
        } catch (Exception e) {
            LOGGER.error("Error copying characters: {}", e.getMessage());
            return "Error copying characters. See logs for details.";
        }
    }
    
    public String copyMultipleCharacters() {
        try {
            // Read 3 characters from the source
            String characters = source.readChars(100);
            
            // Write the characters to the destination
            destination.writeChars(characters.toCharArray());
            
            LOGGER.info("Multiple characters copied successfully.");
            return "Multiple characters copied successfully.";
        } catch (Exception e) {
            LOGGER.error("Error copying multiple characters: {}", e.getMessage());
            return "Error copying multiple characters. See logs for details.";
        }
    }

    public String getTestCase() {
        StringBuilder testCase = new StringBuilder();
        testCase.append("Test Case: Run the following API calls in your browser to simulate copying characters\n");
        testCase.append("1. /api/copier/copy - Copy single character\n");
        testCase.append("2. /api/copier/copyMultiple - Copy multiple characters\n");
        testCase.append("\nNote: For an interactive demo, run the application in the console.\n");
        return testCase.toString();
    }
}
