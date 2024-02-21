package com.oldmutual.insure.copierdemo.functionality;

import com.oldmutual.insure.copierdemo.implementationfactory.MyDestination;
import com.oldmutual.insure.copierdemo.implementationfactory.MySource;
import com.oldmutual.insure.copierdemo.interfaces.IDestination;
import com.oldmutual.insure.copierdemo.interfaces.ISource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Copier {
    private static final Logger LOGGER = LoggerFactory.getLogger(Copier.class);

    private final MySource source;
    private final MyDestination destination;

   
    @Autowired
    public Copier(MySource source, MyDestination destination) {
        this.source = source;
        this.destination = destination;
    }

    public void copy() {
        char c;
        StringBuilder buffer = new StringBuilder();

        try {
            while ((c = source.readChar()) != '\0' && c != '\n') {
                buffer.append(c);
                destination.writeChar(c);
            }
        } catch (Exception e) {
            LOGGER.error("Error copying characters: {}", e.getMessage());
        }

        destination.writeChars(buffer.toString());
    }
}