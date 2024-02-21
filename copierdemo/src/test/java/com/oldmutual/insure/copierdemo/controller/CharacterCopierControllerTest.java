package com.oldmutual.insure.copierdemo.controller;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.oldmutual.insure.copierdemo.implementationfactory.MyDestination;
import com.oldmutual.insure.copierdemo.implementationfactory.MySource;

@SpringBootTest
public class CharacterCopierControllerTest {

    @Autowired
    private MySource mySource;

    @Autowired
    private MyDestination myDestination;

    private CharacterCopierController controller;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        controller = new CharacterCopierController(mySource, myDestination);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testCopyCharacters() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/copier/copy"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Characters copied successfully."));
    }

    @Test
    public void testCopyMultipleCharacters() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/copier/copyMultiple"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Multiple characters copied successfully."));
    }
}
