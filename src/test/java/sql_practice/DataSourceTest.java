package sql_practice;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DataSourceTest {

    private DataSource ds = new DataSource();


    @Test
    void name() {
    }

//Test if we are able to add a chain
    @Test
    public void testAddChain() {
        ds.addNewChain("Idan","Idan-description");
        boolean thrown = false;
        try {
            ds.selectEmployeesByChain("Idan");
        }
        catch (Exception e) {
            thrown = true;
        }
        assertTrue(thrown);
        ds.closeConnection();
    }

    @Test
    public void badTestAddChain() {
        ds.addNewChain("Idan","Idan-description");
        Random rand = new Random();
        boolean thrown = false;
        try { ds.selectEmployeesByChain("Idan"+ rand.nextInt()); }
        catch (Exception e) {  thrown = true;}
        assertFalse(thrown);
        ds.closeConnection();
    }

    @Test
    public void TestAddEmployeeToChain() {
        ds.addEmployeeToChain("Idan","Idan",1,"Hanegev","4433",1,1,"1980-03-17");
        boolean thrown = false;
        try { ds.selectEmployeesByChain("Idan"); }
        catch (Exception e) {  thrown = true;}
        assertTrue(thrown);
        ds.closeConnection();
    }
}