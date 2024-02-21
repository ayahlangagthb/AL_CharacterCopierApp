package com.oldmutual.insure.copierdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.oldmutual.insure.copierdemo.functionality.Copier;
import com.oldmutual.insure.copierdemo.implementationfactory.MyDestination;
import com.oldmutual.insure.copierdemo.implementationfactory.MySource;

@RestController
@RequestMapping("/api/copier")
public class CharacterCopierController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CharacterCopierController.class);

    private final MySource source;
    private final MyDestination destination;
    private final Copier copier;

    @Autowired
    public CharacterCopierController(MySource source, MyDestination destination) {
        this.source = source;
        this.destination = destination;
        this.copier = new Copier(source, destination);
    }

    @GetMapping("/copy")
    @ResponseBody
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

    @GetMapping("/copyMultiple")
    @ResponseBody
    public String copyMultipleCharacters() {
    	try {
            String characters = source.readChars(3);
            destination.writeChars(characters);
            LOGGER.info("Multiple characters copied successfully.");
            return "Multiple characters copied successfully.";
        } catch (Exception e) {
            LOGGER.error("Error copying multiple characters: {}", e.getMessage());
            return "Error copying multiple characters. See logs for details.";
        }
    }
}