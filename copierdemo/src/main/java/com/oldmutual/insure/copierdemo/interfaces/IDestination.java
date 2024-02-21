package com.oldmutual.insure.copierdemo.interfaces;
import org.springframework.stereotype.Component;

@Component
public interface IDestination {
    void writeChar(char c);
    void writeChars(String value);
}