package com.oldmutual.insure.copierdemo.interfaces;
import org.springframework.stereotype.Component;

@Component
public interface ISource {
    char readChar();
    String readChars(int count);
}