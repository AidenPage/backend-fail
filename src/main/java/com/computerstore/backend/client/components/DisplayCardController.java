package com.computerstore.backend.client.components;

/**
 * Created by Aidem on 2016/04/17.
 */
import com.computerstore.backend.domain.components.DisplayCard;
import com.computerstore.backend.services.components.DisplayCardService;
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
public class DisplayCardController{
  // Inject Service
    @Autowired
    private DisplayCardService displayCardService;

    //-------------------Create a DisplayCard--------------------------------------------------------

    @RequestMapping(value = "/displayCard/",consumes = MediaType.APPLICATION_JSON_VALUE , method = RequestMethod.POST)
    public ResponseEntity<Void> createDisplayCard(@RequestBody DisplayCard displayCard, UriComponentsBuilder ucBuilder) {
        displayCardService.create(displayCard);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/displayCard/{id}").buildAndExpand(displayCard.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //-------------------Retrieve Single DisplayCard--------------------------------------------------------
    @RequestMapping(value = "/displayCard/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DisplayCard> getDisplayCard(@PathVariable("id") long id) {
        DisplayCard displayCard = displayCardService.readById(id);
        if (displayCard == null) {
            return new ResponseEntity<DisplayCard>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<DisplayCard>(displayCard, HttpStatus.OK);
    }


    //-------------------Retrieve All Stories--------------------------------------------------------

    @RequestMapping(value = "/displayCardAll/", method = RequestMethod.GET)
    public ResponseEntity<Set<DisplayCard>> getAllDisplay() {
        Set<DisplayCard> displayCardAll = displayCardService.readAll();
        if(displayCardAll.isEmpty()){
            return new ResponseEntity<Set<DisplayCard>>(HttpStatus.NO_CONTENT);// OR HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Set<DisplayCard>>(displayCardAll, HttpStatus.OK);
    }

    //------------------- Update a DisplayCard --------------------------------------------------------

    @RequestMapping(value = "/displayCard/{id}", method = RequestMethod.PUT)
    public ResponseEntity<DisplayCard> updateDisplayCard(@PathVariable("id") long id, @RequestBody DisplayCard displayCard) {

        DisplayCard currentDisplayCard = displayCardService.readById(id);

        if (currentDisplayCard==null) {
            return new ResponseEntity<DisplayCard>(HttpStatus.NOT_FOUND);
        }
        DisplayCard updatedDisplayCard = new DisplayCard.Builder().DisplayCard(currentDisplayCard)
                .description(displayCard.getDescription())
                .price(displayCard.getPrice())
                .stock(displayCard.getStock())
                .build();
        displayCardService.update(updatedDisplayCard);
        return new ResponseEntity<DisplayCard>(updatedDisplayCard, HttpStatus.OK);
    }

    //------------------- Delete a DisplayCard --------------------------------------------------------

    @RequestMapping(value = "/displayCard/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<DisplayCard> deleteDisplayCard(@PathVariable("id") long id) {
        DisplayCard displayCard = displayCardService.readById(id);
        if (displayCard == null) {
            return new ResponseEntity<DisplayCard>(HttpStatus.NOT_FOUND);
        }
        displayCardService.delete(displayCard);
        return new ResponseEntity<DisplayCard>(HttpStatus.NO_CONTENT);
    }
}
