package com.company.challenge2.controller;

import com.company.challenge2.models.MathSolution;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class MathSolutionController {

    private static List<MathSolution> solution = new ArrayList<>(Arrays.asList(
            new MathSolution(10, 20, "add", 30),
            new MathSolution(40, 30, "subtract", 10),
            new MathSolution(12, 5, "multiply", 60),
            new MathSolution(100, 10, "divide", 10)

    ));


    @RequestMapping(value = "/solution/add", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public MathSolution addNumbers(@RequestBody @Valid MathSolution operand) {

        operand.getOperand1();
        operand.getOperand2();
        operand.getOperation();
        operand.setAnswer(operand.getOperand1() + operand.getOperand2());
        operand.getAnswer();

        return operand;
    }

    @RequestMapping(value = "/solution/subtract", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public MathSolution subtractNumbers(@RequestBody @Valid MathSolution operand) {

        operand.getOperand1();
        operand.getOperand2();
        operand.getOperation();
        operand.setAnswer(operand.getOperand1() - operand.getOperand2());
        operand.getAnswer();

        return operand;
    }

    @RequestMapping(value = "/solution/multiply", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public MathSolution multiplyNumbers(@RequestBody @Valid MathSolution operand) {

        operand.getOperand1();
        operand.getOperand2();
        operand.getOperation();
        operand.setAnswer(operand.getOperand1() * operand.getOperand2());
        operand.getAnswer();

        return operand;

    }

    @RequestMapping(value = "/solution/divide", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public MathSolution divideNumbers(@RequestBody @Valid MathSolution operand) {

        operand.getOperand1();
        operand.getOperand2();
        operand.getOperation();
        operand.setAnswer(operand.getOperand1() / operand.getOperand2());
        operand.getAnswer();

        return operand;

    }

}
