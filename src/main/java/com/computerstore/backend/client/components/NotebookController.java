package com.computerstore.backend.client.components;

/**
 * Created by Aidem on 2016/04/17.
 */
import com.computerstore.backend.domain.components.Notebook;
import com.computerstore.backend.services.components.NotebookService;
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
public class NotebookController{
 // Inject Service
    @Autowired
    private NotebookService notebookService;

    //-------------------Create a Notebook--------------------------------------------------------

    @RequestMapping(value = "/notebook/", method = RequestMethod.POST)
    public ResponseEntity<Void> createNotebook(@RequestBody Notebook notebook, UriComponentsBuilder ucBuilder) {
        notebookService.create(notebook);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/notebook/{id}").buildAndExpand(notebook.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //-------------------Retrieve Single Notebook--------------------------------------------------------
    @RequestMapping(value = "/notebook/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notebook> getNotebook(@PathVariable("id") long id) {
        Notebook notebook = notebookService.readById(id);
        if (notebook == null) {
            return new ResponseEntity<Notebook>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Notebook>(notebook, HttpStatus.OK);
    }


    //-------------------Retrieve All Stories--------------------------------------------------------

    @RequestMapping(value = "/notebookAll/", method = RequestMethod.GET)
    public ResponseEntity<Set<Notebook>> getAllNotebook() {
        Set<Notebook> notebookAll = notebookService.readAll();
        if(notebookAll.isEmpty()){
            return new ResponseEntity<Set<Notebook>>(HttpStatus.NO_CONTENT);// OR HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Set<Notebook>>(notebookAll, HttpStatus.OK);
    }

    //------------------- Update a Notebook --------------------------------------------------------

    @RequestMapping(value = "/notebook/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Notebook> updateNotebook(@PathVariable("id") long id, @RequestBody Notebook notebook) {

        Notebook currentNotebook = notebookService.readById(id);

        if (currentNotebook==null) {
            return new ResponseEntity<Notebook>(HttpStatus.NOT_FOUND);
        }
        Notebook updatedNotebook = new Notebook.Builder().Notebook(currentNotebook)
                .description(notebook.getDescription())
                .price(notebook.getPrice())
                .stock(notebook.getStock())
                .build();
        notebookService.update(updatedNotebook);
        return new ResponseEntity<Notebook>(updatedNotebook, HttpStatus.OK);
    }

    //------------------- Delete a Notebook --------------------------------------------------------

    @RequestMapping(value = "/notebook/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Notebook> deleteNotebook(@PathVariable("id") long id) {
        Notebook notebook = notebookService.readById(id);
        if (notebook == null) {
            return new ResponseEntity<Notebook>(HttpStatus.NOT_FOUND);
        }
        notebookService.delete(notebook);
        return new ResponseEntity<Notebook>(HttpStatus.NO_CONTENT);
    }
}
