package logica;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class TestsExtra {
    @BeforeEach
    void setUp() {
        GestorClientes.clientes.clear();
        GestorFlota.flota.clear();
    }

    // ─────────────────────────────────────────────
    // GestorClientes — branches no cubiertos
    // ─────────────────────────────────────────────

    /** crearCliente(null) → false */
    @Test
    void testCrearClienteNulo() {
        boolean resultado = GestorClientes.crearCliente(null);
        assertFalse(resultado, "Un cliente nulo no debe añadirse");
        assertEquals(0, GestorClientes.clientes.size());
    }

    /** crearCliente con DNI vacío → false */
    @Test
    void testCrearClienteDniVacio() {
        Cliente c = new Cliente("", "Sin DNI", "000");
        boolean resultado = GestorClientes.crearCliente(c);
        assertFalse(resultado, "Un cliente con DNI vacío no debe añadirse");
    }

    /** crearCliente con DNI null → false */
    @Test
    void testCrearClienteDniNull() {
        Cliente c = new Cliente(null, "Sin DNI", "000");
        boolean resultado = GestorClientes.crearCliente(c);
        assertFalse(resultado, "Un cliente con DNI null no debe añadirse");
    }

    // ─────────────────────────────────────────────
    // GestorFlota — branches no cubiertos
    // ─────────────────────────────────────────────

    /** ejecutarAltaCoche(null) → false */
    @Test
    void testAltaCocheNulo() {
        assertFalse(GestorFlota.ejecutarAltaCoche(null));
    }

    /** ejecutarAltaFurgoneta con matrícula duplicada → false */
    @Test
    void testAltaFurgonetaDuplicada() {
        Furgoneta f1 = new Furgoneta("5555XYZ", "Ford", "Transit", true, true, 7);
        Furgoneta f2 = new Furgoneta("5555XYZ", "VW",   "Crafter", true, false, 7);

        assertTrue(GestorFlota.ejecutarAltaFurgoneta(f1));
        assertFalse(GestorFlota.ejecutarAltaFurgoneta(f2),
                "No debe permitir matrículas duplicadas en furgonetas");
        assertEquals(1, GestorFlota.flota.size());
    }

    /** listarVehiculosDisponibles con todos no disponibles — cubre el branch del mensaje vacío */
    @Test
    void testListarDisponiblesTodosOcupados() {
        Coche noDisp = new Coche("X1", "A", "B", false, TipoCoche.Pequeño, 4);
        GestorFlota.flota.add(noDisp);
        // Solo comprobamos que no lanza excepción y ejecuta el branch "no encontrado"
        assertDoesNotThrow(() -> GestorFlota.listarVehiculosDisponibles());
    }

    // ─────────────────────────────────────────────
    // GestorReservas — branches no cubiertos
    // ─────────────────────────────────────────────

    /** realizarReserva con cliente null → null */
    @Test
    void testReservaClienteNulo() {
        Coche v = new Coche("M1", "X", "X", true, TipoCoche.Pequeño, 5);
        GestorFlota.flota.add(v);

        Reserva r = GestorReservas.realizarReserva(null, v, 3);
        assertNull(r, "Con cliente null no debe crearse reserva");
    }

    /** realizarReserva con vehiculo null → null */
    @Test
    void testReservaVehiculoNulo() {
        Cliente c = new Cliente("1", "A", "1");
        GestorClientes.clientes.add(c);

        Reserva r = GestorReservas.realizarReserva(c, null, 3);
        assertNull(r, "Con vehículo null no debe crearse reserva");
    }

    /** guardarDatos no lanza excepción */
    @Test
    void testGuardarDatos() {
        assertDoesNotThrow(() -> GestorReservas.guardarDatos());
    }

    /** exportarTicket a través de GestorReservas no lanza excepción */
    @Test
    void testExportarTicketViaReservas() {
        Cliente c = new Cliente("1", "A", "1");
        Coche v   = new Coche("M", "M", "M", true, TipoCoche.Pequeño, 5);
        Reserva r = new Reserva(c, v, LocalDate.now(), LocalDate.now().plusDays(1));

        assertDoesNotThrow(() -> GestorReservas.exportarTicket(r));
    }

    /** buscarCliente con lista vacía → null */
    @Test
    void testBuscarClienteListaVacia() {
        assertNull(GestorReservas.buscarCliente("000"),
                "Lista vacía debe devolver null");
    }

    /** buscarVehiculo con lista vacía → null */
    @Test
    void testBuscarVehiculoListaVacia() {
        assertNull(GestorReservas.buscarVehiculo("ZZZ"),
                "Lista vacía debe devolver null");
    }
}
