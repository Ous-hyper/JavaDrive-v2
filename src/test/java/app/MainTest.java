package app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    // Guardamos el System.in original para restaurarlo después
    private final InputStream originalIn = System.in;

    @AfterEach
    void restaurarSistema() {
        System.setIn(originalIn);
        Main.sc = new Scanner(System.in);
    }

    @Test
    void testMenusPrincipales() {
        // ✅ Ahora SÍ redirigimos System.in correctamente
        String entradaSimulada = "123\nPepe\n666\n";
        ByteArrayInputStream in = new ByteArrayInputStream(entradaSimulada.getBytes());
        System.setIn(in);                    // ← esto faltaba
        Main.sc = new Scanner(System.in);    // ← ahora lee del fake input

        assertDoesNotThrow(() -> Main.menuAltaCliente());
    }

    @Test
    void testMetodosEstaticosMain() {
        assertDoesNotThrow(() -> Main.cargarDatos());
        assertDoesNotThrow(() -> Main.guardarTodoAlSalir());
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS) // ✅ Evita bucle infinito
    void testSalirDelPrograma() {
        // Pon aquí el número real de la opción "Salir" de tu menú
        String entradaSimulada = "6\n";
        ByteArrayInputStream in = new ByteArrayInputStream(entradaSimulada.getBytes());
        System.setIn(in);
        Main.sc = new Scanner(System.in);

        assertDoesNotThrow(() -> Main.main(new String[]{}));
    }
}