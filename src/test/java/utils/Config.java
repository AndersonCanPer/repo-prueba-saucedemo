package utils;

import net.thucydides.model.environment.SystemEnvironmentVariables;

// lee valores del serenity.conf, con esto se evita que las credenciales se escriban en el código
public class Config {

    // ejemplo: Config.get("saucedemo.url") devuelve la url del conf
    public static String get(String clave) {
        return SystemEnvironmentVariables.currentEnvironmentVariables().getProperty(clave);
    }

    private Config() {
    }
}