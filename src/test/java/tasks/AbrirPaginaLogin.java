package tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import utils.Config;

public class AbrirPaginaLogin {

    // el {0} se reemplaza por el nombre del actor en el reporte (por ejemplo: Ana abre la página de login)
    public static Performable enSaucedemo() {
        return Task.where("{0} abre la página de login",
                Open.url(Config.get("saucedemo.url")));
    }

    private AbrirPaginaLogin() {
    }
}