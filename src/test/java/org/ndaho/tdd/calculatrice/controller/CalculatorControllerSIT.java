package org.ndaho.tdd.calculatrice.controller;
import org.junit.jupiter.api.Test;
import org.ndaho.tdd.calculatrice.domain.Calculator;
import org.ndaho.tdd.calculatrice.service.CalculatorService;
import org.ndaho.tdd.calculatrice.service.SolutionFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = {CalculatorController.class, CalculatorService.class})
public class CalculatorControllerSIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SolutionFormatter solutionFormatter;

    @MockBean
    private Calculator calculator;


    @Test
    public void testGet_retuns_statusOk() throws Exception {
        mockMvc.perform(get("/calculator"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetWithRootContextPath_Should_redirectToCalculatorPath() throws Exception {
        mockMvc.perform(get("/")).andExpect(redirectedUrl("/calculator"));
    }

    @Test
    public void givenAUser_whenRequestIsMadeToAdd_thenASolutionSouldBeShown() throws Exception {
//        Given
        when(calculator.add(2,3)).thenReturn(5);
        //When
        mockMvc.perform(post("/calculator")
                        .param("leftArgument", "2")
                        .param("rightArgument", "3")
                        .param("calculationType", "ADDITION")
                ).andExpect(status().is2xxSuccessful()).
                andExpect(content().string(containsString("id=\"solution\""))).
                andExpect(content().string(containsString(">5</span>")));


        verify(calculator).add(2, 3);
        verify(solutionFormatter).format(5);
    }
}