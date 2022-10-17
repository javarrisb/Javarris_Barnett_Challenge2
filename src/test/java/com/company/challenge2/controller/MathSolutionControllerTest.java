package com.company.challenge2.controller;

import com.company.challenge2.models.MathSolution;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MathSolutionController.class)
public class MathSolutionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {

    }

    @Test
    public void shouldReturnSolutionToAdditionMethodForPostRequest() throws Exception {
        MathSolution inputAddition = new MathSolution();
        inputAddition.setOperand1(2);
        inputAddition.setOperand2(4);
        inputAddition.setOperation("add");

        String inputAddJson = mapper.writeValueAsString(inputAddition);

        MathSolution outputAddition = new MathSolution();
        outputAddition.setOperand1(2);
        outputAddition.setOperand2(4);
        outputAddition.setOperation("add");
        outputAddition.setAnswer(6);

        String outputAddJson = mapper.writeValueAsString(outputAddition);

        mockMvc.perform(post("/solution/add")
                .content(inputAddJson)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputAddJson));
    }

    @Test
    public void shouldReturnSolutionForSubtractEndpointOnPostRequest()throws Exception {
        MathSolution inputSubtraction = new MathSolution();
       inputSubtraction.setOperand1(100);
       inputSubtraction.setOperand2(55);
       inputSubtraction.setOperation("subtract");

        String inputSubtractJson = mapper.writeValueAsString(inputSubtraction);

        MathSolution outputSubtraction = new MathSolution();
        outputSubtraction.setOperand1(100);
        outputSubtraction.setOperand2(55);
        outputSubtraction.setOperation("subtract");
        outputSubtraction.setAnswer(45);

        String outputSubtractJson = mapper.writeValueAsString(outputSubtraction);

        mockMvc.perform(post("/solution/subtract")
                        .content(inputSubtractJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputSubtractJson));
    }

    @Test
    public void shouldReturnSolutionOfMultiplyMethodOnPostRequest() throws Exception {
        MathSolution inputMultiplication = new MathSolution();
        inputMultiplication.setOperand1(10);
        inputMultiplication.setOperand2(5);
        inputMultiplication.setOperation("multiply");

        String inputMultiplyJson = mapper.writeValueAsString(inputMultiplication);

        MathSolution outputMultiplication = new MathSolution();
        outputMultiplication.setOperand1(10);
        outputMultiplication.setOperand2(5);
        outputMultiplication.setOperation("multiply");
        outputMultiplication.setAnswer(50);

        String outputMultiplyJson = mapper.writeValueAsString(outputMultiplication);

        mockMvc.perform(post("/solution/multiply")
                        .content(inputMultiplyJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputMultiplyJson));
    }

    @Test
    public void shouldReturnSolutionOfDivideMethodOnPostRequest() throws Exception {
        MathSolution inputDivision = new MathSolution();
        inputDivision.setOperand1(200);
        inputDivision.setOperand2(100);
        inputDivision.setOperation("divide");

        String inputDivideJson = mapper.writeValueAsString(inputDivision);

        MathSolution outputDivision = new MathSolution();
        outputDivision.setOperand1(200);
        outputDivision.setOperand2(100);
        outputDivision.setOperation("divide");
        outputDivision.setAnswer(2);

        String outputDivideJson = mapper.writeValueAsString(outputDivision);

        mockMvc.perform(post("/solution/divide")
                        .content(inputDivideJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputDivideJson));
    }

    @Test
    public void shouldRespondWith422IfMissingOperandInAddEndpoint() throws Exception {
        MathSolution inputAddition = new MathSolution();
        inputAddition.setOperand1(7);
        inputAddition.setOperation("add");

        String inputAddJson = mapper.writeValueAsString(inputAddition);

        mockMvc.perform(post("/solution/add")
                        .content(inputAddJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

    }

    @Test
    public void shouldReturnStatus422IfBothOperandsAreNotNumbers() throws Exception {
        MathSolution inputAddition = new MathSolution();
        inputAddition.setOperand2(0);
        inputAddition.setOperation("add");

        String inputAddJson = mapper.writeValueAsString(inputAddition);


        mockMvc.perform(post("/solution/add")
                        .content(inputAddJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    public void shouldReturnStatus422IfMissingOperandOfSubtractMethod() throws Exception {
        MathSolution inputSubtraction = new MathSolution();
        inputSubtraction.setOperand2(10);
        inputSubtraction.setOperation("subtract");

        String inputSubtractJson = mapper.writeValueAsString(inputSubtraction);


        mockMvc.perform(post("/solution/subtract")
                        .content(inputSubtractJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

    }
}