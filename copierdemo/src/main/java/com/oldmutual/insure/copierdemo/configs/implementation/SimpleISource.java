package com.oldmutual.insure.copierdemo.configs.implementation;

import com.oldmutual.insure.copierdemo.interfaces.ISource;

public class SimpleISource implements ISource {
	
	
	   public SimpleISource(String data, int currentIndex) {
		super();
		this.data = data;
		this.currentIndex = currentIndex;
	}

	private String data;
	    private int currentIndex = 0;
	    
    @Override
    public char readChar() {
    	return currentIndex < data.length() ? data.charAt(currentIndex++) : '\0';
    }

    @Override
    public String readChars(int count) {
    	  int endIndex = currentIndex + count;
          endIndex = Math.min(endIndex, data.length());
          String result = data.substring(currentIndex, endIndex);
          currentIndex = endIndex;
          return result;
    }
}
