package com.oldmutual.insure.copierdemo.implementationfactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import com.oldmutual.insure.copierdemo.implementationfactory.MyDestination;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class MyDestinationTest {

	@Mock
    private MyDestination myDestination;

    @Before
    public void setUp() {
        myDestination = new MyDestination();
    }

    @Test
    public void testWriteChar() {
        myDestination.writeChar('a');
        myDestination.writeChar('b');
        myDestination.writeChar('c');

        assertEquals("abc", myDestination.getContent());
    }

    @Test
    public void testWriteChars() {
        myDestination.writeChars("def");

        assertEquals("def", myDestination.getContent());
    }
}