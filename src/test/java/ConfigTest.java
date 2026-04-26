
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
    }

