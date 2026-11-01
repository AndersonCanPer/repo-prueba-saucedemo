package questions;

import net.serenitybdd.screenplay.Question;
import userinterfaces.InventoryPage;

public class TituloDePagina {

    // devuelve el texto del encabezado (debería ser "Products")
    public static Question<String> mostrado() {
        return Question.about("el encabezado de la página")
                .answeredBy(actor -> InventoryPage.TITLE.resolveFor(actor).getText());
    }

    private TituloDePagina() {
    }
}