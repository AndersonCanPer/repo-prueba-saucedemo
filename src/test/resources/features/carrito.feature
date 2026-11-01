@carrito
Feature: Carrito de compras
    Valida que los productos se agreguen al carrito y que los precios coincidan.

    # se necesita sesión iniciada para ver el inventario
    Background:
        Given que el usuario abre la página de login
        And inicia sesión con credenciales válidas

    @regression
    Scenario: Agregar dos productos al carrito
        When agrega al carrito el producto "Sauce Labs Backpack"
        And agrega al carrito el producto "Sauce Labs Bike Light"
        And abre el carrito
        Then el contador del carrito debería indicar 2 artículos
        And el carrito debería contener el producto "Sauce Labs Backpack"
        And el carrito debería contener el producto "Sauce Labs Bike Light"
        And el total de los precios en el carrito debería ser la suma de los precios de los productos