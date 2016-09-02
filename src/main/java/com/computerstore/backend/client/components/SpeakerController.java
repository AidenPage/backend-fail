package com.computerstore.backend.client.components;

/**
 * Created by Aidem on 2016/04/17.
 */
import com.computerstore.backend.domain.components.Speaker;
import com.computerstore.backend.services.components.SpeakerService;
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
public class SpeakerController{
 // Inject Service
    @Autowired
    private SpeakerService speakerService;

    //-------------------Create a Speaker--------------------------------------------------------

    @RequestMapping(value = "/speaker/", method = RequestMethod.POST)
    public ResponseEntity<Void> createSpeaker(@RequestBody Speaker speaker, UriComponentsBuilder ucBuilder) {
        speakerService.create(speaker);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/speaker/{id}").buildAndExpand(speaker.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //-------------------Retrieve Single Speaker--------------------------------------------------------
    @RequestMapping(value = "/speaker/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Speaker> getSpeaker(@PathVariable("id") long id) {
        Speaker speaker = speakerService.readById(id);
        if (speaker == null) {
            return new ResponseEntity<Speaker>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Speaker>(speaker, HttpStatus.OK);
    }


    //-------------------Retrieve All Stories--------------------------------------------------------

    @RequestMapping(value = "/speakerAll/", method = RequestMethod.GET)
    public ResponseEntity<Set<Speaker>> getAllSpeaker() {
        Set<Speaker> speakerAll = speakerService.readAll();
        if(speakerAll.isEmpty()){
            return new ResponseEntity<Set<Speaker>>(HttpStatus.NO_CONTENT);// OR HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Set<Speaker>>(speakerAll, HttpStatus.OK);
    }

    //------------------- Update a Speaker --------------------------------------------------------

    @RequestMapping(value = "/speaker/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Speaker> updateSpeaker(@PathVariable("id") long id, @RequestBody Speaker speaker) {

        Speaker currentSpeaker = speakerService.readById(id);

        if (currentSpeaker==null) {
            return new ResponseEntity<Speaker>(HttpStatus.NOT_FOUND);
        }
        Speaker updatedSpeaker = new Speaker.Builder().Speaker(currentSpeaker)
                .description(speaker.getDescription())
                .price(speaker.getPrice())
                .stock(speaker.getStock())
                .build();
        speakerService.update(updatedSpeaker);
        return new ResponseEntity<Speaker>(updatedSpeaker, HttpStatus.OK);
    }

    //------------------- Delete a Speaker --------------------------------------------------------

    @RequestMapping(value = "/speaker/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Speaker> deleteSpeaker(@PathVariable("id") long id) {
        Speaker speaker = speakerService.readById(id);
        if (speaker == null) {
            return new ResponseEntity<Speaker>(HttpStatus.NOT_FOUND);
        }
        speakerService.delete(speaker);
        return new ResponseEntity<Speaker>(HttpStatus.NO_CONTENT);
    }
}
