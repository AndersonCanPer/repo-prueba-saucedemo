package userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

// clase que guarda los elementos de la pantalla del carrito; no hace acciones, solo los define
public class CartPage {
// Target del ícono del carrito, el que se pulsa para abrirlo
    public static final Target CART_LINK = Target.the("ícono del carrito").located(By.cssSelector("[data-test='shopping-cart-link']"));
// Target del numero sobre el carrito, solo existe cuando hay productos, con el carrito vacío no aparece    
    public static final Target CART_BADGE = Target.the("contador del carrito").located(By.cssSelector("[data-test='shopping-cart-badge']"));

 // estos dos Targets apuntan a varios elementos a la vez (uno por producto)
// .cart_item los limita a los que están dentro del carrito, no a otros de la página
    public static final Target ITEM_NAMES = Target.the("nombres de productos en el carrito").located(By.cssSelector(".cart_item .inventory_item_name"));
    public static final Target ITEM_PRICES = Target.the("precios de productos en el carrito").located(By.cssSelector(".cart_item .inventory_item_price"));

    private CartPage() {
    }
}