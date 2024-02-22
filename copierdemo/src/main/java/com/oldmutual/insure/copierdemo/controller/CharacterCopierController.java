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

@Component
@RestController
@RequestMapping("/api/copier")
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
            destination.writeChars(characters.toCharArray());
            LOGGER.info("Multiple characters copied successfully.");
            return "Multiple characters copied successfully.";
        } catch (Exception e) {
            LOGGER.error("Error copying multiple characters: {}", e.getMessage());
            return "Error copying multiple characters. See logs for details.";
        }
    }
}
