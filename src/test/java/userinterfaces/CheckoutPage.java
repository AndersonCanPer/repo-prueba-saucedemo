package userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

// clase que guarda los elementos de las pantallas del checkout (datos, resumen y confirmación)
public class CheckoutPage {

    // botón del carrito que inicia el checkout
    public static final Target CHECKOUT_BUTTON = Target.the("botón checkout").located(By.id("checkout"));

    // pantalla 1: formulario de datos
    public static final Target FIRST_NAME = Target.the("campo nombre").located(By.id("first-name"));
    public static final Target LAST_NAME = Target.the("campo apellido").located(By.id("last-name"));
    public static final Target POSTAL_CODE = Target.the("campo código postal").located(By.id("postal-code"));
    public static final Target CONTINUE_BUTTON = Target.the("botón continuar").located(By.id("continue"));

    // pantalla 2: resumen; cada etiqueta trae texto como "Item total: $39.98"
    public static final Target SUBTOTAL = Target.the("subtotal").located(By.cssSelector("[data-test='subtotal-label']"));
    public static final Target TAX = Target.the("impuesto").located(By.cssSelector("[data-test='tax-label']"));
    public static final Target TOTAL = Target.the("total").located(By.cssSelector("[data-test='total-label']"));
    public static final Target FINISH_BUTTON = Target.the("botón finalizar").located(By.id("finish"));

    // pantalla 3: confirmación
    public static final Target CONFIRMATION = Target.the("mensaje de confirmación").located(By.cssSelector("[data-test='complete-header']"));

    private CheckoutPage() {
    }
}