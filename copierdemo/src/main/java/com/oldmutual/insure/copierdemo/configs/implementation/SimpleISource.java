package com.oldmutual.insure.copierdemo.configs.implementation;

import com.oldmutual.insure.copierdemo.interfaces.ISource;

/* i know the instruction was to not have a direct implements for the Interfaces, 
 * this was done to make a console test runnable.
*/
public class SimpleISource implements ISource {
	
	private String data;
	private int currentIndex = 0;

	public SimpleISource(String data, int currentIndex) {
		super();
		this.data = data;
		this.currentIndex = currentIndex;
	}

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
