@checkout
Feature: Checkout
    Valida el proceso de compra desde el carrito hasta la confirmación.

    # el checkout parte con los dos productos ya en el carrito
    Background:
        Given que el usuario abre la página de login
        And inicia sesión con credenciales válidas
        And agrega al carrito el producto "Sauce Labs Backpack"
        And agrega al carrito el producto "Sauce Labs Bike Light"
        And abre el carrito

    # supuesto: el impuesto de saucedemo es 8% del subtotal, redondeado a 2 decimales
    @smoke @regression
    Scenario: Checkout exitoso
        When inicia el checkout
        And completa los datos con nombre "Ana", apellido "Tester" y código postal "110111"
        Then el resumen debería mostrar los productos "Sauce Labs Backpack" y "Sauce Labs Bike Light"
        And el subtotal debería ser la suma de los precios de los productos
        And el impuesto debería ser el 8% del subtotal
        And el total debería ser el subtotal más el impuesto
        When finaliza la compra
        Then debería ver el mensaje de confirmación "Thank you for your order!"

    # un mismo flujo con tres combinaciones inválidas, cada fila deja un campo obligatorio vacío
    @regression @negativo
    Scenario Outline: Checkout con datos obligatorios incompletos
        When inicia el checkout
        And completa los datos con nombre "<nombre>", apellido "<apellido>" y código postal "<codigoPostal>"
        Then debería ver el mensaje de error "<mensaje>"

        Examples:
            | nombre | apellido | codigoPostal | mensaje                        |
            |        | Tester   | 110111       | Error: First Name is required  |
            | Ana    |          | 110111       | Error: Last Name is required   |
            | Ana    | Tester   |              | Error: Postal Code is required |