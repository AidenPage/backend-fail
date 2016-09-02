package com.computerstore.backend.client.components;

/**
 * Created by Aidem on 2016/04/17.
 */
import com.computerstore.backend.domain.components.Monitor;
import com.computerstore.backend.services.components.MonitorService;
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
public class MonitorController{
  // Inject Service
    @Autowired
    private MonitorService monitorService;

    //-------------------Create a Monitor--------------------------------------------------------

    @RequestMapping(value = "/monitor/", method = RequestMethod.POST)
    public ResponseEntity<Void> createMonitor(@RequestBody Monitor monitor, UriComponentsBuilder ucBuilder) {
        monitorService.create(monitor);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/monitor/{id}").buildAndExpand(monitor.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //-------------------Retrieve Single Monitor--------------------------------------------------------
    @RequestMapping(value = "/monitor/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Monitor> getMonitor(@PathVariable("id") long id) {
        Monitor monitor = monitorService.readById(id);
        if (monitor == null) {
            return new ResponseEntity<Monitor>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Monitor>(monitor, HttpStatus.OK);
    }


    //-------------------Retrieve All Stories--------------------------------------------------------

    @RequestMapping(value = "/monitorAll/", method = RequestMethod.GET)
    public ResponseEntity<Set<Monitor>> getAllMonitor() {
        Set<Monitor> monitorAll = monitorService.readAll();
        if(monitorAll.isEmpty()){
            return new ResponseEntity<Set<Monitor>>(HttpStatus.NO_CONTENT);// OR HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Set<Monitor>>(monitorAll, HttpStatus.OK);
    }

    //------------------- Update a Monitor --------------------------------------------------------

    @RequestMapping(value = "/monitor/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Monitor> updateMonitor(@PathVariable("id") long id, @RequestBody Monitor monitor) {

        Monitor currentMonitor = monitorService.readById(id);

        if (currentMonitor==null) {
            return new ResponseEntity<Monitor>(HttpStatus.NOT_FOUND);
        }
        Monitor updatedMonitor = new Monitor.Builder().Monitor(currentMonitor)
                .description(monitor.getDescription())
                .price(monitor.getPrice())
                .stock(monitor.getStock())
                .build();
        monitorService.update(updatedMonitor);
        return new ResponseEntity<Monitor>(updatedMonitor, HttpStatus.OK);
    }

    //------------------- Delete a Monitor --------------------------------------------------------

    @RequestMapping(value = "/monitor/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Monitor> deleteMonitor(@PathVariable("id") long id) {
        Monitor monitor = monitorService.readById(id);
        if (monitor == null) {
            return new ResponseEntity<Monitor>(HttpStatus.NOT_FOUND);
        }
        monitorService.delete(monitor);
        return new ResponseEntity<Monitor>(HttpStatus.NO_CONTENT);
    }
}
