package backend.client;

/**
 *
 * @author Aidem
 */

import com.computerstore.backend.domain.components.CPU;
import com.computerstore.backend.services.components.CPUService;
import java.util.Arrays;
import java.util.Set;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.*;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.util.Assert;

//@WebAppConfiguration
public class CPUControllerTest {
    //private MockMvc mockMvc;
 
    //@Autowired
    //private CPUService cpuServiceMock;
    
    @Test
    public void readCPU() throws Exception {
        CPU first = new CPU.Builder()
                .description("intel")
                .price(2999)
                .stock(20)
                .build();

        CPU second = new CPU.Builder()
                .description("AMD")
                .price(1599)
                .stock(15)
                .build();
        
     Assert.hasText(second.getDescription(),"AMD");
//        when(cpuServiceMock.readAll()).thenReturn( (Set<CPU>) Arrays.asList(first, second));
//        
//        mockMvc.perform(get("/cpuAll/"));
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].description", is("intel")))
//                .andExpect(jsonPath("$[0].price", is(2999)))
//                .andExpect(jsonPath("$[0].stock", is(20)))
//                
//                .andExpect(jsonPath("$[1].description", is("intel")))
//                .andExpect(jsonPath("$[0].price", is(1599)))
//                .andExpect(jsonPath("$[0].stock", is(15)));
 
//        verify(cpuServiceMock, times(1)).readAll();
//        verifyNoMoreInteractions(cpuServiceMock);
    }
}
