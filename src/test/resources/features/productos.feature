@productos
Feature: Ordenamiento de productos
    Valida que el catálogo se pueda ordenar por precio.

    # se necesita sesión iniciada para ver el inventario
    Background:
        Given que el usuario abre la página de login
        And inicia sesión con credenciales válidas

    # la validación se hace por código: se leen los precios de la página y se comprueba el orden
    @regression
    Scenario: Ordenar productos por precio de menor a mayor
        When ordena los productos por precio de menor a mayor
        Then los precios deberían mostrarse en orden ascendente