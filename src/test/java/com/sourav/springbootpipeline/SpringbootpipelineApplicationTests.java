package com.sourav.springbootpipeline;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SpringbootpipelineApplicationTests {

	@Autowired
    private ApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

	 @Test
    void contextLoads() {
        // Ensure that the application context is loaded
        assertThat(context).isNotNull();
    }

    @Test
    void helloEndpointReturnsExpectedResponse() throws Exception {
        // Test that GET / returns "Hello, Spring Boot!"
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Spring Boot!"));
    }
}
