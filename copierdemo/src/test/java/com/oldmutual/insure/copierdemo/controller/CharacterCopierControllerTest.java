package com.oldmutual.insure.copierdemo.controller;

import com.oldmutual.insure.copierdemo.interfaces.ISource;
import com.oldmutual.insure.copierdemo.interfaces.IDestination;
import com.oldmutual.insure.copierdemo.functionality.Copier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*use this CharacterCopierControllerTest for evaluation
*/
public class CharacterCopierControllerTest {

    private ISource mockSource;
    private MockDestination mockDestination;
    private Copier copier;
    private CharacterCopierController controller;

    @BeforeEach
    public void setUp() {
        mockSource = new MockSource();
        mockDestination = new MockDestination();
        copier = new Copier(mockSource, mockDestination);
        controller = new CharacterCopierController(mockSource, mockDestination);
    }

    @Test
    public void testCopyCharacters() {
        controller.copyCharacters();
        assertEquals("abc", mockDestination.getContent());
    }

    @Test
    public void testCopyMultipleCharacters() {
        controller.copyMultipleCharacters();
        assertEquals("abc", mockDestination.getContent());
    }

    private static class MockSource implements ISource {
        private int index = 0;
        private final String content = "abc";

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
