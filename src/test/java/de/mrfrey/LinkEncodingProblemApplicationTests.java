package de.mrfrey;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.endsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LinkEncodingProblemApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() throws Exception {
        mockMvc.perform(get("/")
                .param("date1", "2017-03-21T20:20:20.000+0100")
        ).andDo(print())
                .andExpect(jsonPath("$._links.template.href").value(endsWith("?date1=2017-03-21T19:20:20.000%2B0000{&date2}")))
                .andExpect(jsonPath("$._links.expanded.href").value(endsWith("?date1=2017-03-21T19:20:20.000%2B0000")))
        ;

    }

}
