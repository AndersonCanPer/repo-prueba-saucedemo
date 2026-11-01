package utils;

import java.math.BigDecimal;
import java.util.List;
// clase que agrupa los métodos para trabajar con precios, así no se repiten en cada question
public class Dinero {

// método que convierte un texto con precio en número: "$29.99" -> 29.99
// devuelve BigDecimal y no double porque double redondea mal y falla al sumar decimales
    public static BigDecimal aNumero(String texto) {
        return new BigDecimal(texto.substring(texto.indexOf('$') + 1).trim());
    }
// método que suma todos los números de una lista y devuelve el total
// empieza en cero y va acumulando, con una lista vacía devuelve 0
    public static BigDecimal sumar(List<BigDecimal> valores) {
        return valores.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Dinero() {
    }
}