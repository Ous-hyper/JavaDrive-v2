package app;

import logica.GestorClientes;
import logica.GestorFlota;
import model.Cliente;
import model.Coche;
import model.TipoCoche;
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

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    void testMenuAltaCliente() {
        simularEntrada("123\nPepe\n666666666\n");
        assertDoesNotThrow(() -> Main.menuAltaCliente());
    }



    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    void testOpcionInvalidaEnMain() {
        // opción inválida primero, luego salir
        simularEntrada("99\n6\n");
        assertDoesNotThrow(() -> Main.main(new String[]{}));
    }

    // ─── HELPER ─────────────────────────────────────────────────

    private void simularEntrada(String texto) {
        ByteArrayInputStream in = new ByteArrayInputStream(texto.getBytes());
        System.setIn(in);
        Main.sc = new Scanner(System.in);
    }

    // ─── AÑADIR ESTOS AL FINAL DE TU MainTest.java ─────────────────

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    void testMainListarVehiculosYSalir() {
        simularEntrada("3\n6\n");
        assertDoesNotThrow(() -> Main.main(new String[]{}));
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    void testMainListarClientesYSalir() {
        simularEntrada("5\n6\n");
        assertDoesNotThrow(() -> Main.main(new String[]{}));
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    void testMenuAltaClienteExitoso() {
        simularEntrada("99999999Z\nTestNombre\n600000000\n");
        assertDoesNotThrow(() -> Main.menuAltaCliente());
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    void testMenuAltaClienteDuplicado() {
        GestorClientes.crearCliente(new Cliente("111", "Existente", "000"));
        simularEntrada("111\nOtroNombre\n111111111\n");
        assertDoesNotThrow(() -> Main.menuAltaCliente());
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    void testMenuAltaVehiculoCocheExitoso() {
        simularEntrada("C\nTEST001\nSeat\nIbiza\nPequeño\n5\n");
        assertDoesNotThrow(() -> Main.menuAltaVehiculo());
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    void testMenuAltaVehiculoCocheDuplicado() {
        GestorFlota.ejecutarAltaCoche(new Coche("DUP001", "Ford", "Focus", true, TipoCoche.Familiar, 5));
        simularEntrada("C\nDUP001\nAudi\nA3\nDeportivo\n4\n");
        assertDoesNotThrow(() -> Main.menuAltaVehiculo());
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    void testMenuAltaVehiculoFurgoneta() {
        simularEntrada("F\nFURG001\nFord\nTransit\ntrue\n1000\n");
        assertDoesNotThrow(() -> Main.menuAltaVehiculo());
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    void testMenuAltaVehiculoErrorDatos() {
        simularEntrada("C\nERR001\nMarca\nModelo\nTipoInvalido\n5\n");
        assertDoesNotThrow(() -> Main.menuAltaVehiculo());
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    void testMenuReservaClienteNoExiste() {
        simularEntrada("DNIINEXISTENTE\nMAT999\n");
        assertDoesNotThrow(() -> Main.menuReserva());
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    void testMenuReservaExitosa() {
        Cliente c = new Cliente("RES001", "ClienteTest", "600");
        Coche v = new Coche("RESMAT1", "Seat", "Ibiza", true, TipoCoche.Pequeño, 5);
        GestorClientes.clientes.add(c);
        GestorFlota.flota.add(v);
        simularEntrada("RES001\nRESMAT1\n3\n");
        assertDoesNotThrow(() -> Main.menuReserva());
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    void testMainOpcion1YSalir() {
        simularEntrada("1\nDNINUEVO\nNombreTest\n600111222\n6\n");
        assertDoesNotThrow(() -> Main.main(new String[]{}));
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    void testMainOpcion4YSalir() {
        simularEntrada("4\nDNIFALSO\nMATFALSA\n6\n");
        assertDoesNotThrow(() -> Main.main(new String[]{}));
    }


}