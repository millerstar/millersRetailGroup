package sql_practice;

import com.sun.org.glassfish.gmbal.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DataSourceTest {

    private DataSource ds = new DataSource();




//Test if we are able to add a chain
    @Test
    @Description("Select employee by chain")
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
    @Description("Add chain - negative")
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
    @Description("Add employee to chain")
    public void TestAddEmployeeToChain() {
        ds.addEmployeeToChain("Idan","Ha Lahit",1,"Hanegev","4433",1,1,"1980-03-17");
        boolean thrown = false;
        try { ds.selectEmployeesByChain("Idan"); }
        catch (Exception e) {  thrown = true;}
        assertTrue(thrown);
        ds.closeConnection();
    }

    @Test
    @Description("Add chain")
    void addChain() throws SQLException {
        Long currentChainCount = ds.getChainsCount();
        ds.addNewChain("ZARA", "Fashion store");
        Long newChainCount = ds.getChainsCount();
        assertTrue(newChainCount - currentChainCount == 1,"The new chain was not add as expected");
    }
}