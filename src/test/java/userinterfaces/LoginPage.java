package userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

// aquí solo se guardan los elementos de la pantalla de login, no hay acciones
public class LoginPage {

    // Target es el nombre que sale en el reporte de Serenity
    // Para los elementos que tienen id se consulta por medi odel selector por id
    public static final Target USERNAME = Target.the("campo usuario").located(By.id("user-name"));
    public static final Target PASSWORD = Target.the("campo contraseña").located(By.id("password"));
    public static final Target LOGIN_BUTTON = Target.the("botón login").located(By.id("login-button"));

    // este elemento no tiene id, así que se usa su atributo data-test con selector css
    public static final Target ERROR_MESSAGE = Target.the("mensaje de error").located(By.cssSelector("[data-test='error']"));

    // constructor privado ya que la clase no se instancia, se usa directo como LoginPage.USERNAME
    private LoginPage() {
    }
}