package com.computerstore.backend.client.components;

/**
 * Created by Aidem on 2016/04/17.
 */
import com.computerstore.backend.domain.components.Mainboard;
import com.computerstore.backend.services.components.MainboardService;
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
    private MainboardService mainboardService;

    //-------------------Create a Mainboard--------------------------------------------------------

    @RequestMapping(value = "/mainboard/",consumes = MediaType.APPLICATION_JSON_VALUE , method = RequestMethod.POST)
    public ResponseEntity<Void> createMainboard(@RequestBody Mainboard mainboard, UriComponentsBuilder ucBuilder) {
        mainboardService.create(mainboard);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/mainboard/{id}").buildAndExpand(mainboard.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //-------------------Retrieve Single Mainboard--------------------------------------------------------
    @RequestMapping(value = "/mainboard/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mainboard> getMainboard(@PathVariable("id") long id) {
        Mainboard mainboard = mainboardService.readById(id);
        if (mainboard == null) {
            return new ResponseEntity<Mainboard>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Mainboard>(mainboard, HttpStatus.OK);
    }


    //-------------------Retrieve All Stories--------------------------------------------------------

    @RequestMapping(value = "/mainboardAll/", method = RequestMethod.GET)
    public ResponseEntity<Set<Mainboard>> getAllMainboard() {
        Set<Mainboard> mainboardAll = mainboardService.readAll();
        if(mainboardAll.isEmpty()){
            return new ResponseEntity<Set<Mainboard>>(HttpStatus.NO_CONTENT);// OR HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Set<Mainboard>>(mainboardAll, HttpStatus.OK);
    }

    //------------------- Update a Mainboard --------------------------------------------------------

    @RequestMapping(value = "/mainboard/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Mainboard> updateMainboard(@PathVariable("id") long id, @RequestBody Mainboard mainboard) {

        Mainboard currentMainboard = mainboardService.readById(id);

        if (currentMainboard==null) {
            return new ResponseEntity<Mainboard>(HttpStatus.NOT_FOUND);
        }
        Mainboard updatedMainboard = new Mainboard.Builder().Mainboard(currentMainboard)
                .description(mainboard.getDescription())
                .price(mainboard.getPrice())
                .stock(mainboard.getStock())
                .build();
        mainboardService.update(updatedMainboard);
        return new ResponseEntity<Mainboard>(updatedMainboard, HttpStatus.OK);
    }

    //------------------- Delete a Mainboard --------------------------------------------------------

    @RequestMapping(value = "/mainboard/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Mainboard> deleteMainboard(@PathVariable("id") long id) {
        Mainboard mainboard = mainboardService.readById(id);
        if (mainboard == null) {
            return new ResponseEntity<Mainboard>(HttpStatus.NOT_FOUND);
        }
        mainboardService.delete(mainboard);
        return new ResponseEntity<Mainboard>(HttpStatus.NO_CONTENT);
    }
}
