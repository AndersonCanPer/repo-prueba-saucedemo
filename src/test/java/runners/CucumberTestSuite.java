package runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite  // esta clase agrupa pruebas
@IncludeEngines("cucumber") // las ejecuta con el motor de Cucumber
@SelectClasspathResource("features") // dónde están los .feature
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "stepdefinitions") // paquete con el código de cada paso
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "io.cucumber.core.plugin.SerenityReporterParallel,pretty")  // Serenity genera el reporte, pretty imprime los pasos en consola
public class CucumberTestSuite {
}