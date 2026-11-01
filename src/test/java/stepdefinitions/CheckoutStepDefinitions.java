package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import questions.ContenidoDelCarrito;
import questions.ResumenDeCompra;
import tasks.RealizarCheckout;
import utils.Dinero;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.assertj.core.api.Assertions.assertThat;

// clase que conecta las frases de checkout.feature con el código
public class CheckoutStepDefinitions {

    // supuesto: saucedemo cobra 8% de impuesto; si cambiara, se ajusta solo aquí
    private static final BigDecimal TASA_IMPUESTO = new BigDecimal("0.08");

    @When("inicia el checkout")
    public void iniciaCheckout() {
        theActorInTheSpotlight().attemptsTo(RealizarCheckout.iniciar());
    }

    @When("completa los datos con nombre {string}, apellido {string} y código postal {string}")
    public void completaDatos(String nombre, String apellido, String codigoPostal) {
        theActorInTheSpotlight().attemptsTo(RealizarCheckout.completarDatos(nombre, apellido, codigoPostal));
    }

    // el resumen usa los mismos selectores que el carrito, por eso se reutiliza ContenidoDelCarrito
    @Then("el resumen debería mostrar los productos {string} y {string}")
    public void validaProductos(String primero, String segundo) {
        List<String> productos = theActorInTheSpotlight().asksFor(ContenidoDelCarrito.productos());
        // exactamente esos dos, sin otros de más
        assertThat(productos).containsExactlyInAnyOrder(primero, segundo);
    }

    @Then("el subtotal debería ser la suma de los precios de los productos")
    public void validaSubtotal() {
        List<BigDecimal> precios = theActorInTheSpotlight().asksFor(ContenidoDelCarrito.precios());
        BigDecimal subtotal = theActorInTheSpotlight().asksFor(ResumenDeCompra.subtotal());

        // sin esto, 0 == 0 pasaría aunque no se hubiera leído ningún precio
        assertThat(precios).isNotEmpty();
        assertThat(subtotal).isEqualByComparingTo(Dinero.sumar(precios));
    }

    @Then("el impuesto debería ser el 8% del subtotal")
    public void validaImpuesto() {
        BigDecimal subtotal = theActorInTheSpotlight().asksFor(ResumenDeCompra.subtotal());
        BigDecimal impuesto = theActorInTheSpotlight().asksFor(ResumenDeCompra.impuesto());

        // se redondea a 2 decimales, como los muestra la página
        BigDecimal esperado = subtotal.multiply(TASA_IMPUESTO).setScale(2, RoundingMode.HALF_UP);
        assertThat(impuesto).isEqualByComparingTo(esperado);
    }

    @Then("el total debería ser el subtotal más el impuesto")
    public void validaTotal() {
        BigDecimal subtotal = theActorInTheSpotlight().asksFor(ResumenDeCompra.subtotal());
        BigDecimal impuesto = theActorInTheSpotlight().asksFor(ResumenDeCompra.impuesto());
        BigDecimal total = theActorInTheSpotlight().asksFor(ResumenDeCompra.total());

        assertThat(total).isEqualByComparingTo(subtotal.add(impuesto));
    }

    @When("finaliza la compra")
    public void finalizaCompra() {
        theActorInTheSpotlight().attemptsTo(RealizarCheckout.finalizar());
    }

    @Then("debería ver el mensaje de confirmación {string}")
    public void validaConfirmacion(String esperado) {
        assertThat(theActorInTheSpotlight().asksFor(ResumenDeCompra.confirmacion())).isEqualTo(esperado);
    }
}