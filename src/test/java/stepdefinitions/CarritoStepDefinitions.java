package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import questions.ContenidoDelCarrito;
import questions.PreciosDeProductos;
import tasks.ManejarCarrito;
import utils.Dinero;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.assertj.core.api.Assertions.assertThat;

// clase que conecta las frases de carrito.feature con el código
// cada método de abajo se ejecuta cuando Cucumber encuentra su frase en el escenario
public class CarritoStepDefinitions {

// precios de los productos agregados, para compararlos con los del carrito
    private final List<BigDecimal> preciosAgregados = new ArrayList<>();
// paso: agrega un producto; {string} recibe el nombre que viene entre comillas en el .feature
    @When("agrega al carrito el producto {string}")
    public void agregaProducto(String nombre) {
// primero se lee el precio, mientras el producto sigue visible en el inventario
        preciosAgregados.add(theActorInTheSpotlight().asksFor(PreciosDeProductos.elPrecioDe(nombre)));
// luego se agrega al carrito
        theActorInTheSpotlight().attemptsTo(ManejarCarrito.agregar(nombre));
    }
 // paso: abre el carrito
    @When("abre el carrito")
    public void abreCarrito() {
        theActorInTheSpotlight().attemptsTo(ManejarCarrito.abrir());
    }
// paso: valida el contador {int} recibe el número del .feature
    @Then("el contador del carrito debería indicar {int} artículos")
    public void validaContador(int esperado) {
        int actual = theActorInTheSpotlight().asksFor(ContenidoDelCarrito.contador());
        assertThat(actual).isEqualTo(esperado);
    }
// paso: valida que un producto esté en el carrito
    @Then("el carrito debería contener el producto {string}")
    public void validaProducto(String nombre) {
        assertThat(theActorInTheSpotlight().asksFor(ContenidoDelCarrito.productos())).contains(nombre);
    }
// paso: valida que la suma de precios del carrito sea la suma de lo que valían los productos en el inventario
    @Then("el total de los precios en el carrito debería ser la suma de los precios de los productos")
    public void validaSuma() {
        List<BigDecimal> preciosCarrito = theActorInTheSpotlight().asksFor(ContenidoDelCarrito.precios());

        // sin esto, 0 == 0 pasaría aunque no se hubiera leído nada
        assertThat(preciosAgregados).isNotEmpty();

        // isEqualByComparingTo compara el valor y no la escala: 39.98 y 39.980 son iguales
        assertThat(Dinero.sumar(preciosCarrito)).isEqualByComparingTo(Dinero.sumar(preciosAgregados));
    }
}