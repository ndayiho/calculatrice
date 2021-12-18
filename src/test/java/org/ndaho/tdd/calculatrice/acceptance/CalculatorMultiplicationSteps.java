package org.ndaho.tdd.calculatrice.acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculatorMultiplicationSteps {

    @Inject
    MockMvc mockMvc;

    private Integer lastLeftArgument;
    private Integer lastRightArgument;
    private String calculationType;

    @Given("un élève utilise le Calculateur ")
    public void a_student_is_using_the_Calculator_for_multiplication() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/calculator"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @When("{int} et {int} sont multipliés")
    public void and_are_multiplied(Integer leftArgument, Integer rightArgument) throws Exception {
        lastLeftArgument = leftArgument;
        lastRightArgument = rightArgument;
        calculationType = "MULTIPLICATION";
    }

    @Then("on montre la multi {int} à l'élève")
    public void the_student_is_shown_the_multiResult(Integer expectedResult) throws Exception {
        final MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("/calculator").param("leftArgument", lastLeftArgument.toString())
                        .param("rightArgument", lastRightArgument.toString()).param("calculationType", calculationType))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();

        assertThat(result.getResponse().getContentAsString()).contains(">" + expectedResult + "<");
    }
}
