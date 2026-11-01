package questions;

import net.serenitybdd.screenplay.Question;
import userinterfaces.CartPage;
import utils.Dinero;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

// clase que agrupa las preguntas sobre el carrito 
public class ContenidoDelCarrito {

// método que devuelve la question "¿cuántos artículos indica el contador?"
// el contador trae texto como "2", y se convierte a número entero para poder compararlo
    public static Question<Integer> contador() {
        return Question.about("el contador del carrito")
                .answeredBy(actor -> Integer.parseInt(CartPage.CART_BADGE.resolveFor(actor).getText().trim()));
    }

// método que devuelve la question "¿qué productos hay en el carrito?" (lista de nombres)
    public static Question<List<String>> productos() {
        return Question.about("los productos en el carrito")
                .answeredBy(actor -> CartPage.ITEM_NAMES.resolveAllFor(actor).textContents());
    }

// método que devuelve la question "¿qué precios hay en el carrito?" (lista de números)
// lee los textos ("$29.99"), los convierte uno por uno con la util Dinero y arma la lista
    public static Question<List<BigDecimal>> precios() {
        return Question.about("los precios en el carrito")
                .answeredBy(actor -> CartPage.ITEM_PRICES.resolveAllFor(actor).textContents()
                        .stream()
                        .map(Dinero::aNumero)
                        .collect(Collectors.toList()));
    }

    private ContenidoDelCarrito() {
    }
}