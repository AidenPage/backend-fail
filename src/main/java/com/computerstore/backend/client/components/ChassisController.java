package com.computerstore.backend.client.components;

/**
 * Created by Aidem on 2016/04/17.
 */
import com.computerstore.backend.domain.components.Chassis;
import com.computerstore.backend.services.components.ChassisService;
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
public class ChassisController{
 // Inject Service
    @Autowired
    private ChassisService chassisService;

    //-------------------Create a Chassis--------------------------------------------------------

    @RequestMapping(value = "/chassis/",consumes = MediaType.APPLICATION_JSON_VALUE , method = RequestMethod.POST)
    public ResponseEntity<Void> createChassis(@RequestBody Chassis chassis, UriComponentsBuilder ucBuilder) {
        chassisService.create(chassis);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/chassis/{id}").buildAndExpand(chassis.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //-------------------Retrieve Single Chassis--------------------------------------------------------
    @RequestMapping(value = "/chassis/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Chassis> getChassis(@PathVariable("id") long id) {
        Chassis chassis = chassisService.readById(id);
        if (chassis == null) {
            return new ResponseEntity<Chassis>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Chassis>(chassis, HttpStatus.OK);
    }


    //-------------------Retrieve All Stories--------------------------------------------------------

    @RequestMapping(value = "/chassisAll/", method = RequestMethod.GET)
    public ResponseEntity<Set<Chassis>> getAllChassis() {
        Set<Chassis> ChassisAll = chassisService.readAll();
        if(ChassisAll.isEmpty()){
            return new ResponseEntity<Set<Chassis>>(HttpStatus.NO_CONTENT);// OR HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Set<Chassis>>(ChassisAll, HttpStatus.OK);
    }

    //------------------- Update a Chassis --------------------------------------------------------

    @RequestMapping(value = "/chassis/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Chassis> updateChassis(@PathVariable("id") long id, @RequestBody Chassis chassis) {

        Chassis currentChassis = chassisService.readById(id);

        if (currentChassis==null) {
            return new ResponseEntity<Chassis>(HttpStatus.NOT_FOUND);
        }
        Chassis updatedChassis = new Chassis.Builder().Chassis(currentChassis)
                .description(chassis.getDescription())
                .price(chassis.getPrice())
                .stock(chassis.getStock())
                .build();
        chassisService.update(updatedChassis);
        return new ResponseEntity<Chassis>(updatedChassis, HttpStatus.OK);
    }

    //------------------- Delete a Chassis --------------------------------------------------------

    @RequestMapping(value = "/chassis/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Chassis> deleteChassis(@PathVariable("id") long id) {
        Chassis chassis = chassisService.readById(id);
        if (chassis == null) {
            return new ResponseEntity<Chassis>(HttpStatus.NOT_FOUND);
        }
        chassisService.delete(chassis);
        return new ResponseEntity<Chassis>(HttpStatus.NO_CONTENT);
    }
}
