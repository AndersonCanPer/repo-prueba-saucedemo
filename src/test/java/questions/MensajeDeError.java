package questions;

import net.serenitybdd.screenplay.Question;
import userinterfaces.LoginPage;

public class MensajeDeError {

    // devuelve el texto del mensaje de error del login
    public static Question<String> mostrado() {
        return Question.about("el mensaje de error")
                .answeredBy(actor -> LoginPage.ERROR_MESSAGE.resolveFor(actor).getText());
    }

    private MensajeDeError() {
    }
}
