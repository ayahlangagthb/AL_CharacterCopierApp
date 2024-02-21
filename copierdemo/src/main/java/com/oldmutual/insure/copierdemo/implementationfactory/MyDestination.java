package com.oldmutual.insure.copierdemo.implementationfactory;

import com.oldmutual.insure.copierdemo.interfaces.IDestination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyDestination implements IDestination {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyDestination.class);

    
    private StringBuilder content = new StringBuilder();

    @Override
    public void writeChar(char c) {
    	 LOGGER.debug("Writing character: {}", c);
         content.append(c);
    }

    @Override
    public void writeChars(String value) {
    	 LOGGER.debug("Writing characters: {}", value);
         content.append(value);
    }

    public String getContent() {
        return content.toString();
    }

    public void clearContent() {
        LOGGER.debug("Clearing destination content");
        content.setLength(0);
    }
}