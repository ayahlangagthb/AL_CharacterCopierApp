package com.oldmutual.insure.copierdemo.functionality;

import com.oldmutual.insure.copierdemo.interfaces.IDestination;
import com.oldmutual.insure.copierdemo.interfaces.ISource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CopierTest {

    private MockSource mockSource;
    private MockDestination mockDestination;
    private Copier copier;

    @Before
    public void setUp() {
        mockSource = new MockSource();
        mockDestination = new MockDestination();
        copier = new Copier(mockSource, mockDestination);
    }

    @Test
    public void testCopy() {
        Copier copier = new Copier(mockSource, mockDestination);
        copier.copy();

        assertEquals("AYANDA", mockDestination.getContent());
    }

    private static class MockSource implements ISource {
        private int index = 0;
        private final String content = "AYANDA";

        @Override
        public char readChar() {
            if (index < content.length()) {
                return content.charAt(index++);
            } else {
                return '\0';
            }
        }

        @Override
        public String readChars(int count) {
            int endIndex = Math.min(index + count, content.length());
            String result = content.substring(index, endIndex);
            index = endIndex;
            return result;
        }
    }

    private static class MockDestination implements IDestination {
        private StringBuilder content = new StringBuilder();

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
}
