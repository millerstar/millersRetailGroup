package sql_practice;

import com.sun.org.glassfish.gmbal.Description;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DataSourceTest {

    private DataSource ds = new DataSource();

    @Test
    @Description("Add employee to chain")
    public void TestAddEmployeeToChain() throws SQLException {
        Long beforeEmployeeCount = ds.getEmployeeCount();
        String empName = "Idan";
        ds.addEmployeeToChain(empName,"Ha Lahit",1,"Hanegev","4433",1,1,"1980-03-17");
        Long afterEmployeeCount = ds.getEmployeeCount();
        ds.deleteRowFromEmployee(empName);
        assertTrue(afterEmployeeCount - beforeEmployeeCount == 1,"The new employee was not add as expected");
        ds.closeConnection();
    }

    @Test
    @Description("Add chain")
    void addChain() throws SQLException {
        Long currentChainCount = ds.getChainsCount();
        String chainName = "ZARA";
        ds.addNewChain(chainName, "Fashion store");
        Long newChainCount = ds.getChainsCount();
        ds.deleteRowFromChain(chainName);
        assertTrue(newChainCount - currentChainCount == 1,"The new chain was not add as expected");
    }
}