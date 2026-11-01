package questions;

import net.serenitybdd.screenplay.Question;
import userinterfaces.InventoryPage;

public class InventarioVisible {

    // true si la lista de productos se ve en pantalla, false si no
    // isVisible espera hasta el timeout del serenity.conf antes de decir false
    public static Question<Boolean> enPantalla() {
        return Question.about("si el inventario está visible")
                .answeredBy(actor -> InventoryPage.INVENTORY_LIST.resolveFor(actor).isVisible());
    }

    private InventarioVisible() {
    }
}