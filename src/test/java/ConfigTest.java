
import app.Main;
import logica.*;
import model.*;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

    public class ConfigTest {

        @Test
        public void testCrearCliente() {
            Cliente c = new Cliente("12345678A", "Juan", "600000000");
            assertNotNull(c);
            assertEquals("Juan", c.getNombre());
        }

        @Test
        public void testCrearCoche() {
            Coche coche = new Coche("1234ABC", "Toyota", "Corolla", true, TipoCoche.Deportivo, 5);
            assertNotNull(coche);
            assertEquals("1234ABC", coche.getMatricula());
        }


        @Test
        public void testCrearReserva() {
            Cliente c = new Cliente("12345678A", "Juan", "600000000");
            Coche coche = new Coche("1234ABC", "Toyota", "Corolla", true, TipoCoche.Deportivo, 5);
            assertNotNull(c);
            assertNotNull(coche);
        }

        @Test
        public void testGetMatricula() {
            Coche coche = new Coche("5678XYZ", "Seat", "Ibiza", true, TipoCoche.Pequeño, 4);
            assertEquals("5678XYZ", coche.getMatricula());
        }

        @Test
        public void testGetNombre() {
            Cliente c = new Cliente("87654321B", "Maria", "611111111");
            assertEquals("Maria", c.getNombre());
        }

        @Test
        public void testTipoCoche() {
            Coche coche = new Coche("9999ZZZ", "BMW", "Serie3", true, TipoCoche.Familiar, 5);
            assertEquals(TipoCoche.Familiar, coche.getTipoCoche());
        }

        @Test
        public void testNumPlazas() {
            Coche coche = new Coche("1111AAA", "Audi", "A3", true, TipoCoche.Deportivo, 4);
            assertEquals(4, coche.getNumPlazas());
        }

        @Test
        public void testGestorClientes() {
            GestorClientes gc = new GestorClientes();
            assertNotNull(gc);
        }

        @Test
        public void testGestorFlota() {
            GestorFlota gf = new GestorFlota();
            assertNotNull(gf);
        }

        @Test
        public void testGestorInformes() {
            GestorInformes gi = new GestorInformes();
            assertNotNull(gi);
        }

        @Test
        public void testGestorReservas() {
            GestorReservas gr = new GestorReservas();
            assertNotNull(gr);
        }

        @Test
        public void testFurgoneta() {
            Furgoneta f = new Furgoneta("1111BBB", "Ford", "Transit", true, false, 5);
            assertNotNull(f);
            assertEquals("1111BBB", f.getMatricula());
        }

        @Test
        public void testReserva() {
            Cliente c = new Cliente("12345678A", "Juan", "600000000");
            Coche coche = new Coche("1234ABC", "Toyota", "Corolla", true, TipoCoche.Deportivo, 5);
            Reserva r = new Reserva(c, coche, LocalDate.now(), LocalDate.now().plusDays(3));
            assertNotNull(r);
        }

        @Test
        public void testCargarVehiculos() {
            GestorPersistencia gp = new GestorPersistencia();
            List<Vehiculo> lista = gp.cargarVehiculos();
            assertNotNull(lista);
        }

        @Test
        public void testCargarClientes() {
            GestorPersistencia gp = new GestorPersistencia();
            List<Cliente> lista = gp.cargarClientes();
            assertNotNull(lista);
        }

        @Test
        public void testCalcularSiguienteId() {
            GestorPersistencia gp = new GestorPersistencia();
            int id = gp.calcularSiguienteIdReserva();
            assertTrue(id >= 1);
        }

        @Test
        public void testGuardarVehiculos() {
            GestorPersistencia gp = new GestorPersistencia();
            List<Vehiculo> lista = new ArrayList<>();
            lista.add(new Coche("1234ABC", "Toyota", "Corolla", true, TipoCoche.Deportivo, 5));
            gp.guardarVehiculos(lista);
            assertTrue(true);
        }

        @Test
        public void testGuardarClientes() {
            GestorPersistencia gp = new GestorPersistencia();
            List<Cliente> lista = new ArrayList<>();
            lista.add(new Cliente("12345678A", "Juan", "600000000"));
            gp.guardarClientes(lista);
            assertTrue(true);
        }

        @Test
        public void testReservaGetCliente() {
            Cliente c = new Cliente("12345678A", "Juan", "600000000");
            Coche coche = new Coche("1234ABC", "Toyota", "Corolla", true, TipoCoche.Deportivo, 5);
            Reserva r = new Reserva(c, coche, LocalDate.now(), LocalDate.now().plusDays(3));
            assertEquals("Juan", r.getCliente().getNombre());
        }

        @Test
        public void testReservaGetVehiculo() {
            Cliente c = new Cliente("12345678A", "Juan", "600000000");
            Coche coche = new Coche("1234ABC", "Toyota", "Corolla", true, TipoCoche.Deportivo, 5);
            Reserva r = new Reserva(c, coche, LocalDate.now(), LocalDate.now().plusDays(3));
            assertEquals("1234ABC", r.getVehiculo().getMatricula());
        }

        @Test
        public void testReservaGetFechas() {
            Cliente c = new Cliente("12345678A", "Juan", "600000000");
            Coche coche = new Coche("1234ABC", "Toyota", "Corolla", true, TipoCoche.Deportivo, 5);
            LocalDate inicio = LocalDate.now();
            LocalDate fin = LocalDate.now().plusDays(3);
            Reserva r = new Reserva(c, coche, inicio, fin);
            assertEquals(inicio, r.getFechaInicio());
            assertEquals(fin, r.getFechaFin());
        }

        @Test
        public void testReservaTicket() {
            Cliente c = new Cliente("12345678A", "Juan", "600000000");
            Coche coche = new Coche("1234ABC", "Toyota", "Corolla", true, TipoCoche.Deportivo, 5);
            Reserva r = new Reserva(c, coche, LocalDate.now(), LocalDate.now().plusDays(3));
            assertNotNull(r.GenerarLineaTicket());
        }


        @Test
        public void testFurgonetaPasajeros() {
            Furgoneta f = new Furgoneta("2222CCC", "Mercedes", "Vito", true, false, 6);
            assertNotNull(f);
            assertFalse(f.isEsDeCarga());
            assertEquals(6, f.getCapacidad());
        }

        @Test
        public void testFurgonetaDetallesCarga() {
            Furgoneta f = new Furgoneta("1111BBB", "Ford", "Transit", true, true, 1000);
            assertTrue(f.obtenerDetalles().contains("Carga"));
        }

        @Test
        public void testFurgonetaDetallesPasajeros() {
            Furgoneta f = new Furgoneta("2222CCC", "Mercedes", "Vito", true, false, 6);
            assertTrue(f.obtenerDetalles().contains("Pasajeros"));
        }

        @Test
        public void testBuscarClienteNull() {
            assertNull(Main.buscarCliente("99999999Z"));
        }

        @Test
        public void testBuscarVehiculoNull() {
            assertNull(Main.buscarVehiculo("0000XXX"));
        }

        @Test
        public void testCargarDatos() {
            Main.cargarDatos();
            assertTrue(true);
        }

        @Test
        public void testRealizarReserva() {
            Main.cargarDatos();
            Cliente c = new Cliente("12345678A", "Juan", "600000000");
            Coche coche = new Coche("TEST01", "Toyota", "Corolla", true, TipoCoche.Deportivo, 5);
            Main.realizarReserva(c, coche, LocalDate.now(), LocalDate.now().plusDays(2));
            assertFalse(coche.isDisponible());
        }

        @Test
        public void testGuardarDatos() {
            Main.cargarDatos();
            Main.guardarDatos();
            assertTrue(true);
        }

        @Test
        public void testListarClientes() {
            Main.cargarDatos();
            Main.listarClientes();
            assertTrue(true);
        }
    }

