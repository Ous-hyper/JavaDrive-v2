
import logica.GestorClientes;
import logica.GestorFlota;
import model.Cliente;
import model.Coche;
import model.TipoCoche;
import org.junit.Test;
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
        public void testGestorClientes() {
            GestorClientes gc = new GestorClientes();
            assertNotNull(gc);
        }

        @Test
        public void testGestorFlota() {
            GestorFlota gf = new GestorFlota();
            assertNotNull(gf);
        }
    }

