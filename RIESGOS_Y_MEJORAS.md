# Riesgos, escenarios no automatizados y mejoras

## Riesgos identificados

- **Dependencia de SauceDemo:** la suite necesita que el sitio de demostración esté disponible y conserve sus textos, productos y selectores.
- **Regla de impuestos:** el checkout asume un impuesto de 8% y redondeo a dos decimales. Si la aplicación cambia esa regla, la expectativa debe actualizarse.
- **Compatibilidad del navegador:** la ejecución está configurada para Chrome visible. No se ha validado en Firefox, Edge ni en modo headless.
- **Avisos del navegador:** una alerta del gestor de contraseñas de Chrome interfería con el flujo. La configuración actual inicia Chrome en incógnito y desactiva el gestor para esa sesión; la última ejecución completa pasó, pero conviene volver a revisar esta configuración si cambia Chrome o el entorno.
- **Datos de demostración:** la cuenta y contraseña del proyecto son las públicas de SauceDemo. No deben sustituirse por credenciales personales ni por secretos reales guardados en el repositorio.
- **Driver del navegador:** Selenium Manager descarga el driver al ejecutar, por lo que se necesita internet. Un `chromedriver` instalado manualmente en el `PATH` con otra versión que Chrome hace fallar la apertura del navegador; si pasa, hay que quitarlo del `PATH`.

## Escenarios fuera de la cobertura actual

- Ordenar productos de mayor a menor o por nombre.
- Quitar productos del carrito, vaciarlo o verificar cambios en su contador después de quitar artículos.
- Completar el checkout con varios campos vacíos a la vez, cancelar una compra o volver desde el resumen.
- Validar otros usuarios y comportamientos de autenticación, como cuentas bloqueadas.
- Ejecutar la suite en distintos navegadores o resoluciones.

## Mejoras propuestas

1. Añadir esperas por condiciones concretas de visibilidad o disponibilidad en los pasos que dependen de cambios dinámicos de pantalla.
2. Incorporar pruebas de quitar artículos y de los flujos de checkout cancelado y con varios campos inválidos.
3. Hacer configurable la tasa de impuestos y centralizarla junto con los datos de prueba.
4. Ejecutar la suite en integración continua y publicar el reporte Serenity como artefacto de cada ejecución.
5. Añadir una matriz de ejecución para Chrome y Edge, y validar una ejecución headless antes de habilitarla en CI.
