package questions;

import net.serenitybdd.screenplay.Question;
import userinterfaces.CheckoutPage;
import utils.Dinero;

import java.math.BigDecimal;

// clase que agrupa las preguntas sobre el resumen y la confirmación de la compra
public class ResumenDeCompra {

    // pregunta: subtotal que muestra la página ("Item total: $39.98" -> 39.98)
    public static Question<BigDecimal> subtotal() {
        return Question.about("el subtotal")
                .answeredBy(actor -> Dinero.aNumero(CheckoutPage.SUBTOTAL.resolveFor(actor).getText()));
    }

    // pregunta: impuesto que muestra la página ("Tax: $3.20" -> 3.20)
    public static Question<BigDecimal> impuesto() {
        return Question.about("el impuesto")
                .answeredBy(actor -> Dinero.aNumero(CheckoutPage.TAX.resolveFor(actor).getText()));
    }

    // pregunta: total que muestra la página ("Total: $43.18" -> 43.18)
    public static Question<BigDecimal> total() {
        return Question.about("el total")
                .answeredBy(actor -> Dinero.aNumero(CheckoutPage.TOTAL.resolveFor(actor).getText()));
    }

    // pregunta: texto del mensaje al terminar la compra
    public static Question<String> confirmacion() {
        return Question.about("el mensaje de confirmación")
                .answeredBy(actor -> CheckoutPage.CONFIRMATION.resolveFor(actor).getText());
    }

    private ResumenDeCompra() {
    }
}