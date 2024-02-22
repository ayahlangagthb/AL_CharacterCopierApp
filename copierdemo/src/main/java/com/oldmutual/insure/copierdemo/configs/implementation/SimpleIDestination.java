package com.oldmutual.insure.copierdemo.configs.implementation;

import com.oldmutual.insure.copierdemo.interfaces.IDestination;

public class SimpleIDestination implements IDestination {
	
	
	   private StringBuilder content = new StringBuilder();
	
	   
	   public SimpleIDestination() {}

		@Override
	    public void writeChar(char c) {
	        content.append(c);
	    }

	    @Override
	    public void writeChars(char[] values) {
	        content.append(values);
	    }

	    public String getContent() {
	        return content.toString();
	    }
}