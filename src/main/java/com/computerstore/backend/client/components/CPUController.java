package com.computerstore.backend.client.components;

/**
 * Created by Aidem on 2016/04/17.
 */
import com.computerstore.backend.domain.components.CPU;
import com.computerstore.backend.services.components.CPUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.bind.annotation.RestController;
import java.util.Set;

@RestController
public class CPUController{
  // Inject Service
    @Autowired
    private CPUService cpuService;

    //-------------------Create a CPU--------------------------------------------------------

    @RequestMapping(value = "/cpu/", method = RequestMethod.POST)
    public ResponseEntity<Void> createCPU(@RequestBody CPU cpu, UriComponentsBuilder ucBuilder) {
        cpuService.create(cpu);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/cpu/{id}").buildAndExpand(cpu.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //-------------------Retrieve Single CPU--------------------------------------------------------
    @RequestMapping(value = "/cpu/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CPU> getCPU(@PathVariable("id") long id) {
        CPU cpu = cpuService.readById(id);
        if (cpu == null) {
            return new ResponseEntity<CPU>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<CPU>(cpu, HttpStatus.OK);
    }


    //-------------------Retrieve All Stories--------------------------------------------------------

    @RequestMapping(value = "/cpuAll/", method = RequestMethod.GET)
    public ResponseEntity<Set<CPU>> getAllCPU() {
        Set<CPU> cpuAll = cpuService.readAll();
        if(cpuAll.isEmpty()){
            return new ResponseEntity<Set<CPU>>(HttpStatus.NO_CONTENT);// OR HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Set<CPU>>(cpuAll, HttpStatus.OK);
    }

    //------------------- Update a CPU --------------------------------------------------------

    @RequestMapping(value = "/cpu/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CPU> updateCPU(@PathVariable("id") long id, @RequestBody CPU cpu) {

        CPU currentCPU = cpuService.readById(id);

        if (currentCPU==null) {
            return new ResponseEntity<CPU>(HttpStatus.NOT_FOUND);
        }
        CPU updatedCPU = new CPU.Builder().CPU(currentCPU)
                .description(cpu.getDescription())
                .price(cpu.getPrice())
                .stock(cpu.getStock())
                .build();
        cpuService.update(updatedCPU);
        return new ResponseEntity<CPU>(updatedCPU, HttpStatus.OK);
    }

    //------------------- Delete a CPU --------------------------------------------------------

    @RequestMapping(value = "/cpu/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<CPU> deleteCPU(@PathVariable("id") long id) {
        CPU cpu = cpuService.readById(id);
        if (cpu == null) {
            return new ResponseEntity<CPU>(HttpStatus.NOT_FOUND);
        }
        cpuService.delete(cpu);
        return new ResponseEntity<CPU>(HttpStatus.NO_CONTENT);
    }
}
