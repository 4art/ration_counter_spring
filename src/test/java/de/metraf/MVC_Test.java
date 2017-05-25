package de.metraf;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.assertj.core.api.Assertions.assertThat;
/**
 * Created by metraf on 08.05.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MVC_Test {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testReturnMessage() throws Exception{
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                /*.andExpect(content().string(containsString("Hello World")))*/;

    }
}
