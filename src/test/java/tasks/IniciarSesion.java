package tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import userinterfaces.LoginPage;
import utils.Config;

public class IniciarSesion {

    // login con los datos del serenity.conf
    public static Performable conCredencialesValidas() {
        return iniciarSesionCon(Config.get("saucedemo.user"), Config.get("saucedemo.password"));
    }

    // datos que no existen en la app; no son sensibles, por eso van aquí y no en el
    // .conf
    public static Performable conCredencialesInvalidas() {
        return iniciarSesionCon("usuario_invalido", "clave_invalida");
    }

    // la task base: escribe usuario, escribe clave y da clic en el botón
    private static Performable iniciarSesionCon(String usuario, String clave) {
        return Task.where("{0} inicia sesión",
                Enter.theValue(usuario).into(LoginPage.USERNAME),
                Enter.theValue(clave).into(LoginPage.PASSWORD),
                Click.on(LoginPage.LOGIN_BUTTON));
    }

    private IniciarSesion() {
    }
}