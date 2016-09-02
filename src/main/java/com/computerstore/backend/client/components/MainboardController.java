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
public class MainboardController{
 // Inject Service
    @Autowired
    private CPUService mainboardService;

    //-------------------Create a CPU--------------------------------------------------------

    @RequestMapping(value = "/mainboard/", method = RequestMethod.POST)
    public ResponseEntity<Void> createCPU(@RequestBody CPU mainboard, UriComponentsBuilder ucBuilder) {
        mainboardService.create(mainboard);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/mainboard/{id}").buildAndExpand(mainboard.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //-------------------Retrieve Single CPU--------------------------------------------------------
    @RequestMapping(value = "/mainboard/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CPU> getCPU(@PathVariable("id") long id) {
        CPU mainboard = mainboardService.readById(id);
        if (mainboard == null) {
            return new ResponseEntity<CPU>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<CPU>(mainboard, HttpStatus.OK);
    }


    //-------------------Retrieve All Stories--------------------------------------------------------

    @RequestMapping(value = "/mainboardAll/", method = RequestMethod.GET)
    public ResponseEntity<Set<CPU>> getAllMainboard() {
        Set<CPU> mainboardAll = mainboardService.readAll();
        if(mainboardAll.isEmpty()){
            return new ResponseEntity<Set<CPU>>(HttpStatus.NO_CONTENT);// OR HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Set<CPU>>(mainboardAll, HttpStatus.OK);
    }

    //------------------- Update a CPU --------------------------------------------------------

    @RequestMapping(value = "/mainboard/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CPU> updateCPU(@PathVariable("id") long id, @RequestBody CPU mainboard) {

        CPU currentCPU = mainboardService.readById(id);

        if (currentCPU==null) {
            return new ResponseEntity<CPU>(HttpStatus.NOT_FOUND);
        }
        CPU updatedCPU = new CPU.Builder().CPU(currentCPU)
                .description(mainboard.getDescription())
                .price(mainboard.getPrice())
                .stock(mainboard.getStock())
                .build();
        mainboardService.update(updatedCPU);
        return new ResponseEntity<CPU>(updatedCPU, HttpStatus.OK);
    }

    //------------------- Delete a CPU --------------------------------------------------------

    @RequestMapping(value = "/mainboard/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<CPU> deleteCPU(@PathVariable("id") long id) {
        CPU mainboard = mainboardService.readById(id);
        if (mainboard == null) {
            return new ResponseEntity<CPU>(HttpStatus.NOT_FOUND);
        }
        mainboardService.delete(mainboard);
        return new ResponseEntity<CPU>(HttpStatus.NO_CONTENT);
    }
}
