package tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import userinterfaces.InventoryPage;

public class OrdenarProductos {

    //"lohi" es el value de la opción "Price (low to high)" se busca por el value y no por el texto
    public static Performable porPrecioMenorAMayor() {
        return Task.where("{0} ordena los productos por precio de menor a mayor",
                SelectFromOptions.byValue("lohi").from(InventoryPage.SORT_DROPDOWN));
    }

    private OrdenarProductos() {
    }
}