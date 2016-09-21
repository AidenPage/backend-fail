package com.computerstore.backend.client.components;

/**
 * Created by Aidem on 2016/04/17.
 */
import com.computerstore.backend.domain.components.OpticalDevices;
import com.computerstore.backend.services.components.OpticalDevicesService;
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
public class OpticalDevicesController{
 // Inject Service
    @Autowired
    private OpticalDevicesService opticalDevicesService;

    //-------------------Create a OpticalDevices--------------------------------------------------------

    @RequestMapping(value = "/opticalDevices/",consumes = MediaType.APPLICATION_JSON_VALUE , method = RequestMethod.POST)
    public ResponseEntity<Void> createOpticalDevices(@RequestBody OpticalDevices opticalDevices, UriComponentsBuilder ucBuilder) {
        opticalDevicesService.create(opticalDevices);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/opticalDevices/{id}").buildAndExpand(opticalDevices.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //-------------------Retrieve Single OpticalDevices--------------------------------------------------------
    @RequestMapping(value = "/opticalDevices/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OpticalDevices> getOpticalDevices(@PathVariable("id") long id) {
        OpticalDevices opticalDevices = opticalDevicesService.readById(id);
        if (opticalDevices == null) {
            return new ResponseEntity<OpticalDevices>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<OpticalDevices>(opticalDevices, HttpStatus.OK);
    }


    //-------------------Retrieve All Stories--------------------------------------------------------

    @RequestMapping(value = "/opticalDevicesAll/", method = RequestMethod.GET)
    public ResponseEntity<Set<OpticalDevices>> getAllOpticalDevices() {
        Set<OpticalDevices> opticalDevicesAll = opticalDevicesService.readAll();
        if(opticalDevicesAll.isEmpty()){
            return new ResponseEntity<Set<OpticalDevices>>(HttpStatus.NO_CONTENT);// OR HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Set<OpticalDevices>>(opticalDevicesAll, HttpStatus.OK);
    }

    //------------------- Update a OpticalDevices --------------------------------------------------------

    @RequestMapping(value = "/opticalDevices/{id}", method = RequestMethod.PUT)
    public ResponseEntity<OpticalDevices> updateOpticalDevices(@PathVariable("id") long id, @RequestBody OpticalDevices opticalDevices) {

        OpticalDevices currentOpticalDevices = opticalDevicesService.readById(id);

        if (currentOpticalDevices==null) {
            return new ResponseEntity<OpticalDevices>(HttpStatus.NOT_FOUND);
        }
        OpticalDevices updatedOpticalDevices = new OpticalDevices.Builder().OpticalDevices(currentOpticalDevices)
                .description(opticalDevices.getDescription())
                .price(opticalDevices.getPrice())
                .stock(opticalDevices.getStock())
                .build();
        opticalDevicesService.update(updatedOpticalDevices);
        return new ResponseEntity<OpticalDevices>(updatedOpticalDevices, HttpStatus.OK);
    }

    //------------------- Delete a OpticalDevices --------------------------------------------------------

    @RequestMapping(value = "/opticalDevices/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<OpticalDevices> deleteOpticalDevices(@PathVariable("id") long id) {
        OpticalDevices opticalDevices = opticalDevicesService.readById(id);
        if (opticalDevices == null) {
            return new ResponseEntity<OpticalDevices>(HttpStatus.NOT_FOUND);
        }
        opticalDevicesService.delete(opticalDevices);
        return new ResponseEntity<OpticalDevices>(HttpStatus.NO_CONTENT);
    }
}
