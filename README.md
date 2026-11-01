# Prueba técnica de automatización con Serenity BDD

Suite de automatización UI para validar el flujo de compra de SauceDemo con Java, Serenity BDD, Screenplay, Cucumber/Gherkin y Selenium WebDriver.

## Requisitos

- Java 21.
- Maven instalado y disponible en `PATH`.
- Google Chrome instalado.
- Acceso a internet para abrir `https://www.saucedemo.com/` y resolver dependencias Maven la primera vez.

## Dependencias

- Serenity BDD 4.1.3: `serenity-core`, `serenity-cucumber`, `serenity-screenplay`, `serenity-screenplay-webdriver` y `serenity-ensure`.
- Cucumber 7.15.0 sobre JUnit Platform (`cucumber-junit-platform-engine`, `junit-platform-suite`).
- Selenium WebDriver 4.x, incluido por Serenity.
- JUnit 4.13.2: lo requiere el reporter de Serenity para procesar los `Scenario Outline`.
- AssertJ 3.25.3 para aserciones y Logback 1.4.14 para logs.
- Plugins Maven: `maven-failsafe-plugin` 3.2.5 y `serenity-maven-plugin` 4.1.3.

## Ejecutar las pruebas

Abre una terminal en la raíz del proyecto y usa estos comandos:

```powershell
# Suite completa
mvn clean verify

# Suite de humo
mvn clean verify "-Dcucumber.filter.tags=@smoke"

# Suite de regresión
mvn clean verify "-Dcucumber.filter.tags=@regression"

# Un feature (carrito)
mvn clean verify "-Dcucumber.filter.tags=@carrito"

# Un escenario por nombre
mvn clean verify "-Dcucumber.filter.name=Agregar dos productos al carrito"
```

La suite completa genera el reporte Serenity en `target/site/serenity/index.html` y los resultados de Maven en `target/failsafe-reports/`. La copia del reporte que acompaña esta entrega está en `evidencias/serenity/`.

## Cobertura automatizada

1. Inicio de sesión exitoso: valida el inventario y el título `Products`.
2. Inicio de sesión fallido: valida el mensaje de error y que el inventario no aparezca.
3. Ordenamiento ascendente por precio: lee los precios visibles y verifica su orden con código.
4. Carrito: agrega Sauce Labs Backpack y Sauce Labs Bike Light; valida contador, productos y suma de precios.
5. Checkout exitoso: completa Ana Tester y el código postal 110111; valida productos, subtotal, impuesto, total y confirmación.
6. Checkout negativo: un `Scenario Outline` valida tres combinaciones con nombre, apellido o código postal vacío.

## Estructura

```text
src/test/java/
  questions/        Consultas y validaciones sobre la interfaz
  runners/          Suite de Cucumber sobre JUnit Platform
  stepdefinitions/  Implementación de los pasos Gherkin
  tasks/            Acciones Screenplay
  userinterfaces/   Selectores y elementos de las páginas
  utils/            Configuración y manejo de valores monetarios
src/test/resources/
  features/         Escenarios Gherkin
  serenity.conf     Navegador y datos de SauceDemo
evidencias/serenity/Reporte Serenity de la ejecución de entrega
```

Las tareas agrupan acciones del actor; las preguntas leen el estado de la interfaz para que las definiciones de pasos expresen las validaciones del negocio. Los elementos de las páginas están centralizados como `Target` de Screenplay.

### Sobre la carpeta `interactions/`

El enunciado la propone como parte de una estructura posible, pero no exige que contenga clases. En este proyecto, las acciones se componen dentro de `tasks/` con interacciones estándar de Screenplay, como `Click` y `Enter`. Por eso no se crearon interacciones propias reutilizables y la carpeta queda vacía.

## Configuración y decisiones

- URL, navegador y credenciales de la cuenta demo se configuran en `src/test/resources/serenity.conf` y se consultan mediante `utils.Config`.
- El navegador se ejecuta con Chrome en modo visible. Las opciones de Chrome aíslan la sesión en incógnito y desactivan el gestor de contraseñas para evitar que sus avisos interfieran con la automatización.
- Los precios se convierten a `BigDecimal` en `utils.Dinero`; no se usa `double` para evitar errores de precisión al sumar importes.
- Para el checkout se asume que SauceDemo calcula el impuesto como 8% del subtotal, redondeado a dos decimales con `HALF_UP`.
- Las credenciales incluidas son únicamente las públicas de la aplicación demo. No reemplazarlas por credenciales personales ni guardar secretos reales en el repositorio.

## Resultado de referencia

La última ejecución de `mvn clean verify` terminó con `BUILD SUCCESS`: 8 ejecuciones aprobadas, sin fallos ni errores. El reporte Serenity agrupa la cobertura en 6 escenarios e incluye las tres filas del checkout negativo.
