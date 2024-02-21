package com.oldmutual.insure.copierdemo.implementationfactory;
import com.oldmutual.insure.copierdemo.implementationfactory.MySource;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class MySourceTest {

    private MySource mySource;

    @Before
    public void setUp() {
        mySource = new MySource("abcdef");
    }

    @Test
    public void testReadChar() {
        assertEquals('a', mySource.readChar());
        assertEquals('b', mySource.readChar());
        assertEquals('c', mySource.readChar());
        assertEquals('d', mySource.readChar());
        assertEquals('e', mySource.readChar());
        assertEquals('f', mySource.readChar());
        assertEquals('\0', mySource.readChar());  // End of string
    }

    @Test
    public void testReadChars() {
        assertEquals("abc", mySource.readChars(3));
        assertEquals("def", mySource.readChars(3));
        assertEquals("", mySource.readChars(3));  // Not enough characters remaining
    }
}