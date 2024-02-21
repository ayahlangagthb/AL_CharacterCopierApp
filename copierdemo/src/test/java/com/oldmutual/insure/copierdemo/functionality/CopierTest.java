package com.oldmutual.insure.copierdemo.functionality;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.oldmutual.insure.copierdemo.implementationfactory.MyDestination;
import com.oldmutual.insure.copierdemo.implementationfactory.MySource;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CopierTest {

    private MySource mySource;
    private MyDestination myDestination;
    private Copier copier;

    @Before
    public void setUp() {
        mySource = mock(MySource.class);
        myDestination = new MyDestination();
        copier = new Copier(mySource, myDestination);
    }

    @Test
    public void testCopy() {
        when(mySource.readChar()).thenReturn('a', 'b', 'c', '\0');
        copier.copy();
        assertEquals("abc", myDestination.getContent());
    }
}
