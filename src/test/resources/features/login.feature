@login
Feature: Inicio de sesión
  Valida el acceso a la aplicación con credenciales válidas e inválidas.
  Las credenciales válidas se toman del serenity.conf.

  # todos los escenarios parten de la página de login
  Background:
    Given que el usuario abre la página de login

  # smoke: es lo mínimo que debe funcionar para que valga la pena seguir probando
  @smoke @regression
  Scenario: Inicio de sesión exitoso
    When inicia sesión con credenciales válidas
    Then debería ver el inventario de productos
    And el encabezado de la página debería ser "Products"
  # el mensaje debe coincidir exactamente con el de la aplicación
  @regression
  Scenario: Inicio de sesión fallido
    When inicia sesión con credenciales inválidas
    Then debería ver el mensaje de error "Epic sadface: Username and password do not match any user in this service"
    And no debería acceder al inventario