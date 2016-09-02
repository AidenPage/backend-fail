package com.computerstore.backend.client.components;

/**
 * Created by Aidem on 2016/04/17.
 */
import com.computerstore.backend.domain.components.StorageDevice;
import com.computerstore.backend.services.components.StorageDeviceService;
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
public class StorageDeviceController{
  // Inject Service
    @Autowired
    private StorageDeviceService storageDeviceService;

    //-------------------Create a StorageDevice--------------------------------------------------------

    @RequestMapping(value = "/storageDevice/", method = RequestMethod.POST)
    public ResponseEntity<Void> createStorageDevice(@RequestBody StorageDevice storageDevice, UriComponentsBuilder ucBuilder) {
        storageDeviceService.create(storageDevice);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/storageDevice/{id}").buildAndExpand(storageDevice.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //-------------------Retrieve Single StorageDevice--------------------------------------------------------
    @RequestMapping(value = "/storageDevice/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StorageDevice> getStorageDevice(@PathVariable("id") long id) {
        StorageDevice storageDevice = storageDeviceService.readById(id);
        if (storageDevice == null) {
            return new ResponseEntity<StorageDevice>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<StorageDevice>(storageDevice, HttpStatus.OK);
    }


    //-------------------Retrieve All Stories--------------------------------------------------------

    @RequestMapping(value = "/storageDeviceAll/", method = RequestMethod.GET)
    public ResponseEntity<Set<StorageDevice>> getAllStorageDevice() {
        Set<StorageDevice> storageDeviceAll = storageDeviceService.readAll();
        if(storageDeviceAll.isEmpty()){
            return new ResponseEntity<Set<StorageDevice>>(HttpStatus.NO_CONTENT);// OR HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Set<StorageDevice>>(storageDeviceAll, HttpStatus.OK);
    }

    //------------------- Update a StorageDevice --------------------------------------------------------

    @RequestMapping(value = "/storageDevice/{id}", method = RequestMethod.PUT)
    public ResponseEntity<StorageDevice> updateStorageDevice(@PathVariable("id") long id, @RequestBody StorageDevice storageDevice) {

        StorageDevice currentStorageDevice = storageDeviceService.readById(id);

        if (currentStorageDevice==null) {
            return new ResponseEntity<StorageDevice>(HttpStatus.NOT_FOUND);
        }
        StorageDevice updatedStorageDevice = new StorageDevice.Builder().StorageDevice(currentStorageDevice)
                .description(storageDevice.getDescription())
                .price(storageDevice.getPrice())
                .stock(storageDevice.getStock())
                .build();
        storageDeviceService.update(updatedStorageDevice);
        return new ResponseEntity<StorageDevice>(updatedStorageDevice, HttpStatus.OK);
    }

    //------------------- Delete a StorageDevice --------------------------------------------------------

    @RequestMapping(value = "/storageDevice/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<StorageDevice> deleteStorageDevice(@PathVariable("id") long id) {
        StorageDevice storageDevice = storageDeviceService.readById(id);
        if (storageDevice == null) {
            return new ResponseEntity<StorageDevice>(HttpStatus.NOT_FOUND);
        }
        storageDeviceService.delete(storageDevice);
        return new ResponseEntity<StorageDevice>(HttpStatus.NO_CONTENT);
    }
}
