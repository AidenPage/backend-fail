package com.computerstore.backend.client.components;

/**
 * Created by Aidem on 2016/04/17.
 */
import com.computerstore.backend.domain.components.PCU;
import com.computerstore.backend.services.components.PCUService;
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
public class PCUController{
 // Inject Service
    @Autowired
    private PCUService pcuService;

    //-------------------Create a PCU--------------------------------------------------------

    @RequestMapping(value = "/pcu/",consumes = MediaType.APPLICATION_JSON_VALUE , method = RequestMethod.POST)
    public ResponseEntity<Void> createPCU(@RequestBody PCU pcu, UriComponentsBuilder ucBuilder) {
        pcuService.create(pcu);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/pcu/{id}").buildAndExpand(pcu.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //-------------------Retrieve Single PCU--------------------------------------------------------
    @RequestMapping(value = "/pcu/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PCU> getPCU(@PathVariable("id") long id) {
        PCU pcu = pcuService.readById(id);
        if (pcu == null) {
            return new ResponseEntity<PCU>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<PCU>(pcu, HttpStatus.OK);
    }


    //-------------------Retrieve All Stories--------------------------------------------------------

    @RequestMapping(value = "/pcuAll/", method = RequestMethod.GET)
    public ResponseEntity<Set<PCU>> getAllPCU() {
        Set<PCU> pcuAll = pcuService.readAll();
        if(pcuAll.isEmpty()){
            return new ResponseEntity<Set<PCU>>(HttpStatus.NO_CONTENT);// OR HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Set<PCU>>(pcuAll, HttpStatus.OK);
    }

    //------------------- Update a PCU --------------------------------------------------------

    @RequestMapping(value = "/pcu/{id}", method = RequestMethod.PUT)
    public ResponseEntity<PCU> updatePCU(@PathVariable("id") long id, @RequestBody PCU pcu) {

        PCU currentPCU = pcuService.readById(id);

        if (currentPCU==null) {
            return new ResponseEntity<PCU>(HttpStatus.NOT_FOUND);
        }
        PCU updatedPCU = new PCU.Builder().PCU(currentPCU)
                .description(pcu.getDescription())
                .price(pcu.getPrice())
                .stock(pcu.getStock())
                .build();
        pcuService.update(updatedPCU);
        return new ResponseEntity<PCU>(updatedPCU, HttpStatus.OK);
    }

    //------------------- Delete a PCU --------------------------------------------------------

    @RequestMapping(value = "/pcu/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<PCU> deletePCU(@PathVariable("id") long id) {
        PCU pcu = pcuService.readById(id);
        if (pcu == null) {
            return new ResponseEntity<PCU>(HttpStatus.NOT_FOUND);
        }
        pcuService.delete(pcu);
        return new ResponseEntity<PCU>(HttpStatus.NO_CONTENT);
    }
}
