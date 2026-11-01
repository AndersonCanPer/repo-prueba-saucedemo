package tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import userinterfaces.CheckoutPage;

// clase que agrupa las acciones del actor durante el checkout
public class RealizarCheckout {

    // task: da clic en Checkout desde el carrito
    public static Performable iniciar() {
        return Task.where("{0} inicia el checkout", Click.on(CheckoutPage.CHECKOUT_BUTTON));
    }

    // task: llena el formulario y continúa al resumen, recibe los tres datos para poder reutilizarla con otros valores
    public static Performable completarDatos(String nombre, String apellido, String codigoPostal) {
        return Task.where("{0} completa los datos de envío",
                Enter.theValue(nombre).into(CheckoutPage.FIRST_NAME),
                Enter.theValue(apellido).into(CheckoutPage.LAST_NAME),
                Enter.theValue(codigoPostal).into(CheckoutPage.POSTAL_CODE),
                Click.on(CheckoutPage.CONTINUE_BUTTON));
    }

    // task: confirma la compra desde el resumen
    public static Performable finalizar() {
        return Task.where("{0} finaliza la compra", Click.on(CheckoutPage.FINISH_BUTTON));
    }

    private RealizarCheckout() {
    }
}