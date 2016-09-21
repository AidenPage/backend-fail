package backend.client;

/**
 *
 * @author Aidem
 */

import com.computerstore.backend.App;
import com.computerstore.backend.domain.components.CPU;
import com.computerstore.backend.factories.components.CPUFactory;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@IntegrationTest({"server.port=8181"})
public class CPUControllerTest {
    final String BASE_URL = "http://localhost:8181/";
    private RestTemplate template;
    
     
    @Test
    public void readCPU() throws Exception {

         RestTemplate restTemplate = new RestTemplate();
        CPU cpu = CPUFactory.getCPU("intel",2999.99,15);
        URI uri = restTemplate.postForLocation(BASE_URL +"/cpu/",cpu,CPU.class);

    }
    
    @Test
    public void test() throws Exception {
    try
        {
            Class.forName("com.mysql.jdbc.Driver");
        } 
        catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found !!");
            return;
        }
        System.out.println("MySQL JDBC Driver Registered!");
        Connection connection = null;
        try {
            connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/computerstoredb", "root", "");
            System.out.println("SQL Connection to database established!");
 
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            return;
        } finally {
            try
            {
                if(connection != null)
                    connection.close();
                System.out.println("Connection closed !!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}
    
@Before
 public void setUp() throws Exception {
  template = new TestRestTemplate();

 }
}

 
 
