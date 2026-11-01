package tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import userinterfaces.CartPage;
import userinterfaces.InventoryPage;

// clase que agrupa las acciones del actor sobre el carrito (cada acción es una task)
public class ManejarCarrito {
// método que devuelve la task "agregar un producto al carrito"
// recibe el nombre del producto y da clic en su botón de agregar
// el texto de Task.where es el que sale en el reporte {0} se reemplaza por el nombre del actor
    public static Performable agregar(String producto) {
        return Task.where("{0} agrega al carrito " + producto,
                Click.on(InventoryPage.ADD_TO_CART_BUTTON.of(slug(producto))));
    }
// método que devuelve la task "abrir el carrito": da clic en el ícono del carrito
    public static Performable abrir() {
        return Task.where("{0} abre el carrito", Click.on(CartPage.CART_LINK));
    }

// método auxiliar (privado, solo se usa dentro de esta clase) que arma el texto del botón
// el data-test del botón usa el nombre en minúscula y con guiones: "Sauce Labs Backpack" -> "sauce-labs-backpack"
    private static String slug(String producto) {
        return producto.toLowerCase().replace(" ", "-");
    }

    private ManejarCarrito() {
    }
}