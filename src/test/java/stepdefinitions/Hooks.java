package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

//código que corre antes y después de cada escenario
public class Hooks {

//corre antes de cada escenario, los actores que se creen tendrán su propio navegador
    @Before
    public void prepararEscenario() {
        OnStage.setTheStage(new OnlineCast());
    }

//corre después de cada escenario; cierra el navegador
    @After
    public void cerrarEscenario() {
        OnStage.drawTheCurtain();
    }
}