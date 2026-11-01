package userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

// clase que guarda los elementos de la pantalla de productos (el inventario)
public class InventoryPage {

// Target del título de la página, que debe decir "Products"
    public static final Target TITLE = Target.the("encabezado de la página").located(By.cssSelector(".title"));

// Target del contenedor de todos los productos, sirve para saber si el inventario cargó
    public static final Target INVENTORY_LIST = Target.the("lista de productos")
            .located(By.cssSelector(".inventory_list"));

// Target del desplegable para ordenar los productos
    public static final Target SORT_DROPDOWN = Target.the("selector de ordenamiento")
            .located(By.cssSelector("[data-test='product-sort-container']"));

// Targets que apuntan a todos los precios y a todos los nombres de la página
// se leen con resolveAllFor, que devuelve la lista completa
    public static final Target PRICES = Target.the("precios de los productos")
            .located(By.cssSelector(".inventory_item_price"));

    public static final Target ITEM_NAMES = Target.the("nombres de los productos")
            .located(By.cssSelector(".inventory_item_name"));
// Target con plantilla: {0} es un hueco que se llena al usarlo, con .of("sauce-labs-backpack")
// así un solo Target sirve para el botón de cualquier producto
    public static final Target ADD_TO_CART_BUTTON = Target.the("botón agregar {0}")
            .locatedBy("[data-test='add-to-cart-{0}']");

    private InventoryPage() {
    }
}