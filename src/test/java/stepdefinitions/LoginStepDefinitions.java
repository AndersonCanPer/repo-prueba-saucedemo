package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.ensure.Ensure;
import questions.InventarioVisible;
import questions.MensajeDeError;
import questions.TituloDePagina;
import tasks.AbrirPaginaLogin;
import tasks.IniciarSesion;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class LoginStepDefinitions {

//el texto del paso debe ser igual al del .feature
    @Given("que el usuario abre la página de login")
    public void abrePaginaLogin() {
//crea al actor; solo se hace en el primer paso
        theActorCalled("Ana").attemptsTo(AbrirPaginaLogin.enSaucedemo());
    }

    @When("inicia sesión con credenciales válidas")
    public void iniciaSesionValida() {
//en los demás pasos se reutiliza el mismo actor
        theActorInTheSpotlight().attemptsTo(IniciarSesion.conCredencialesValidas());
    }

    @When("inicia sesión con credenciales inválidas")
    public void iniciaSesionInvalida() {
        theActorInTheSpotlight().attemptsTo(IniciarSesion.conCredencialesInvalidas());
    }

    @Then("debería ver el inventario de productos")
    public void veInventario() {
        theActorInTheSpotlight().attemptsTo(Ensure.that(InventarioVisible.enPantalla()).isTrue());
    }

//{string} recibe el texto entre comillas del .feature
    @Then("el encabezado de la página debería ser {string}")
    public void validaEncabezado(String esperado) {
        theActorInTheSpotlight().attemptsTo(Ensure.that(TituloDePagina.mostrado()).isEqualTo(esperado));
    }

    @Then("debería ver el mensaje de error {string}")
    public void validaMensajeError(String esperado) {
        theActorInTheSpotlight().attemptsTo(Ensure.that(MensajeDeError.mostrado()).isEqualTo(esperado));
    }

    @Then("no debería acceder al inventario")
    public void noVeInventario() {
        theActorInTheSpotlight().attemptsTo(Ensure.that(InventarioVisible.enPantalla()).isFalse());
    }
}