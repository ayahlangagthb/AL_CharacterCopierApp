package com.oldmutual.insure.copierdemo.functionality;

import com.oldmutual.insure.copierdemo.interfaces.IDestination;
import com.oldmutual.insure.copierdemo.interfaces.ISource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Copier {
    private static final Logger LOGGER = LoggerFactory.getLogger(Copier.class);

    private final ISource source;
    private final IDestination destination;

   
    @Autowired
    public Copier(ISource source, IDestination destination) {
        this.source = source;
        this.destination = destination;
    }
    
    public void copy() {
    	try {
            char c;
            char[] buffer = new char[1]; // Buffer to store each character individually

            while ((c = source.readChar()) != '\0' && c != '\n') {
                buffer[0] = c;
                destination.writeChars(buffer);
            }

            LOGGER.info("Characters copied successfully.");
        } catch (Exception e) {
            LOGGER.error("Error copying characters: {}", e.getMessage());
        }
    }
}