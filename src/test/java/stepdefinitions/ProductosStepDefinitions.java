package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import questions.PreciosDeProductos;
import tasks.OrdenarProductos;

import java.math.BigDecimal;
import java.util.List;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductosStepDefinitions {

    @When("ordena los productos por precio de menor a mayor")
    public void ordenaPorPrecio() {
        theActorInTheSpotlight().attemptsTo(OrdenarProductos.porPrecioMenorAMayor());
    }

    @Then("los precios deberían mostrarse en orden ascendente")
    public void preciosAscendentes() {
        List<BigDecimal> precios = theActorInTheSpotlight().asksFor(PreciosDeProductos.mostrados());

        // isNotEmpty evita que una lista vacía pase como "ordenada"
        assertThat(precios).isNotEmpty().isSorted();
    }
}