package com.computerstore.backend.client.components;

/**
 * Created by Aidem on 2016/04/17.
 */
import com.computerstore.backend.domain.components.Memory;
import com.computerstore.backend.services.components.MemoryService;
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
public class MemoryController{
 // Inject Service
    @Autowired
    private MemoryService memoryService;

    //-------------------Create a Memory--------------------------------------------------------

    @RequestMapping(value = "/memory/", method = RequestMethod.POST)
    public ResponseEntity<Void> createMemory(@RequestBody Memory memory, UriComponentsBuilder ucBuilder) {
        memoryService.create(memory);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/memory/{id}").buildAndExpand(memory.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //-------------------Retrieve Single Memory--------------------------------------------------------
    @RequestMapping(value = "/memory/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Memory> getMemory(@PathVariable("id") long id) {
        Memory memory = memoryService.readById(id);
        if (memory == null) {
            return new ResponseEntity<Memory>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Memory>(memory, HttpStatus.OK);
    }


    //-------------------Retrieve All Stories--------------------------------------------------------

    @RequestMapping(value = "/memoryAll/", method = RequestMethod.GET)
    public ResponseEntity<Set<Memory>> getAllMemory() {
        Set<Memory> memoryAll = memoryService.readAll();
        if(memoryAll.isEmpty()){
            return new ResponseEntity<Set<Memory>>(HttpStatus.NO_CONTENT);// OR HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Set<Memory>>(memoryAll, HttpStatus.OK);
    }

    //------------------- Update a Memory --------------------------------------------------------

    @RequestMapping(value = "/memory/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Memory> updateMemory(@PathVariable("id") long id, @RequestBody Memory memory) {

        Memory currentMemory = memoryService.readById(id);

        if (currentMemory==null) {
            return new ResponseEntity<Memory>(HttpStatus.NOT_FOUND);
        }
        Memory updatedMemory = new Memory.Builder().Memory(currentMemory)
                .description(memory.getDescription())
                .price(memory.getPrice())
                .stock(memory.getStock())
                .build();
        memoryService.update(updatedMemory);
        return new ResponseEntity<Memory>(updatedMemory, HttpStatus.OK);
    }

    //------------------- Delete a Memory --------------------------------------------------------

    @RequestMapping(value = "/memory/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Memory> deleteMemory(@PathVariable("id") long id) {
        Memory memory = memoryService.readById(id);
        if (memory == null) {
            return new ResponseEntity<Memory>(HttpStatus.NOT_FOUND);
        }
        memoryService.delete(memory);
        return new ResponseEntity<Memory>(HttpStatus.NO_CONTENT);
    }
}
