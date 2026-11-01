package questions;

import net.serenitybdd.screenplay.Question;
import userinterfaces.InventoryPage;
import utils.Dinero;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

// clase que agrupa las preguntas sobre los precios del inventario
public class PreciosDeProductos {

// método que devuelve la question "¿qué precios se ven en pantalla?"
// los devuelve como números y en el mismo orden en que aparecen
    public static Question<List<BigDecimal>> mostrados() {
        return Question.about("los precios de los productos")
                .answeredBy(actor -> InventoryPage.PRICES.resolveAllFor(actor).textContents()
                        .stream()
                        .map(Dinero::aNumero)
                        .collect(Collectors.toList()));
    }

// método que devuelve la question "¿cuánto cuesta este producto?"
// recibe el nombre del producto y devuelve su precio como número
    public static Question<BigDecimal> elPrecioDe(String producto) {
// el bloque se ejecuta cuando el actor responde la pregunta
        return Question.about("el precio de " + producto).answeredBy(actor -> {
            List<String> nombres = InventoryPage.ITEM_NAMES.resolveAllFor(actor).textContents();
            List<String> precios = InventoryPage.PRICES.resolveAllFor(actor).textContents();
// nombres y precios salen en el mismo orden, así que la posición del nombre sirve para el precio
// si el nombre no existe, indexOf devuelve -1, se lanza un error claro 
            int posicion = nombres.indexOf(producto);
            if (posicion < 0) {
                throw new IllegalArgumentException("No existe el producto: " + producto);
            }
            return Dinero.aNumero(precios.get(posicion));
        });
    }

    private PreciosDeProductos() {
    }
}