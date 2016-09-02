package com.computerstore.backend.client.components;

/**
 * Created by Aidem on 2016/04/17.
 */
import com.computerstore.backend.domain.components.Printer;
import com.computerstore.backend.services.components.PrinterService;
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
public class PrinterController{
 // Inject Service
    @Autowired
    private PrinterService printerService;

    //-------------------Create a Printer--------------------------------------------------------

    @RequestMapping(value = "/printer/", method = RequestMethod.POST)
    public ResponseEntity<Void> createPrinter(@RequestBody Printer printer, UriComponentsBuilder ucBuilder) {
        printerService.create(printer);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/printer/{id}").buildAndExpand(printer.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //-------------------Retrieve Single Printer--------------------------------------------------------
    @RequestMapping(value = "/printer/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Printer> getPrinter(@PathVariable("id") long id) {
        Printer printer = printerService.readById(id);
        if (printer == null) {
            return new ResponseEntity<Printer>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Printer>(printer, HttpStatus.OK);
    }


    //-------------------Retrieve All Stories--------------------------------------------------------

    @RequestMapping(value = "/printerAll/", method = RequestMethod.GET)
    public ResponseEntity<Set<Printer>> getAllPrinter() {
        Set<Printer> printerAll = printerService.readAll();
        if(printerAll.isEmpty()){
            return new ResponseEntity<Set<Printer>>(HttpStatus.NO_CONTENT);// OR HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Set<Printer>>(printerAll, HttpStatus.OK);
    }

    //------------------- Update a Printer --------------------------------------------------------

    @RequestMapping(value = "/printer/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Printer> updatePrinter(@PathVariable("id") long id, @RequestBody Printer printer) {

        Printer currentPrinter = printerService.readById(id);

        if (currentPrinter==null) {
            return new ResponseEntity<Printer>(HttpStatus.NOT_FOUND);
        }
        Printer updatedPrinter = new Printer.Builder().Printer(currentPrinter)
                .description(printer.getDescription())
                .price(printer.getPrice())
                .stock(printer.getStock())
                .build();
        printerService.update(updatedPrinter);
        return new ResponseEntity<Printer>(updatedPrinter, HttpStatus.OK);
    }

    //------------------- Delete a Printer --------------------------------------------------------

    @RequestMapping(value = "/printer/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Printer> deletePrinter(@PathVariable("id") long id) {
        Printer printer = printerService.readById(id);
        if (printer == null) {
            return new ResponseEntity<Printer>(HttpStatus.NOT_FOUND);
        }
        printerService.delete(printer);
        return new ResponseEntity<Printer>(HttpStatus.NO_CONTENT);
    }
}
