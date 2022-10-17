package com.company.challenge2.controller;

import com.company.challenge2.models.MonthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(MonthController.class)
public class MonthControllerTest {

        @Autowired
        private MockMvc mockMvc;

        private ObjectMapper mapper = new ObjectMapper();

        @Before
        public void setUp() {

        }

        @Test
        public void shouldReturnAllMonthsInList() throws Exception {
            mockMvc.perform(get("/months"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0]").isNotEmpty());
        }

        @Test
        public void shouldReturnMonthObjectWithMonthAndNumber() throws Exception {
                MonthService outputMonth = new MonthService(2, "February");

                String outputMonthJson = mapper.writeValueAsString(outputMonth);

                mockMvc.perform(get("/months/{monthNumber}", 2))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(content().json(outputMonthJson));
        }

        @Test
        public void shouldReturnMonthObjectWithRandomMonth() throws Exception {
              mockMvc.perform(get("/randomMonth"))
                      .andDo(print())
                      .andExpect(status().isOk())
                      .andExpect(jsonPath("$.number").isNotEmpty())
                      .andExpect(jsonPath("$.name").isNotEmpty());
        }

        @Test
        public void shouldRespondWith422WhenMonthInputIsOutOfRange() throws Exception {
                MonthService outputMonth = new MonthService(41, "AnotherMonth");

                String outputMonthJson = mapper.writeValueAsString(outputMonth);

                mockMvc.perform(get("/months/{monthNumber}", 41))
                        .andDo(print())
                        .andExpect(status().isUnprocessableEntity());
        }
}