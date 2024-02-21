package com.oldmutual.insure.copierdemo.implementationfactory;

import com.oldmutual.insure.copierdemo.interfaces.ISource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySource implements ISource {
    private static final Logger LOGGER = LoggerFactory.getLogger(MySource.class);

    private String data;
    private int currentIndex = 0;

    public MySource(String data) {
        this.data = data;
    }

    @Override
    public char readChar() {
    	LOGGER.debug("Reading a character");
        return currentIndex < data.length() ? data.charAt(currentIndex++) : '\0';
    }

    @Override
    public String readChars(int count) {
    	  LOGGER.debug("Reading {} characters", count);
          
          int endIndex = currentIndex + count;
          endIndex = Math.min(endIndex, data.length());
          String result = data.substring(currentIndex, endIndex);
          currentIndex = endIndex;
          return result;
    }
}