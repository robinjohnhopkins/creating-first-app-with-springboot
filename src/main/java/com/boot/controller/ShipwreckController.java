package com.boot.controller;

import com.boot.model.Shipwreck;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/")
@RestController
public class ShipwreckController {

//    @Autowired
//    private ShipwreckRepository shipwreckRepository;

    @RequestMapping(value = "shipwrecks", method = RequestMethod.GET)
    public List<Shipwreck> list(){
        return ShipwreckStub.list();
    }

//    @RequestMapping(value = "shipwrecks", method = RequestMethod.GET)
//    public List<Shipwreck> list() {
//        return shipwreckRepository.findAll();
//    }

    @RequestMapping(value = "shipwrecks", method = RequestMethod.POST)
    public Shipwreck create(@RequestBody Shipwreck shipwreck) {
        return ShipwreckStub.create(shipwreck);
//        return shipwreckRepository.saveAndFlush(shipwreck);
    }

    @RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.GET)
    public Shipwreck get(@PathVariable Long id) {
        return ShipwreckStub.get(id);
//        return shipwreckRepository.findOne(id);
    }

    @RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.PUT)
    public Shipwreck update(@PathVariable Long id, @RequestBody Shipwreck shipwreck) {
        return ShipwreckStub.update(id, shipwreck);

//        Shipwreck existingShipwreck = shipwreckRepository.findOne(id);
//        BeanUtils.copyProperties(shipwreck, existingShipwreck);
//        return shipwreckRepository.saveAndFlush(existingShipwreck);
    }

    @RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.DELETE)
    public Shipwreck delete(@PathVariable Long id) {
        return ShipwreckStub.delete(id);

//        Shipwreck existingShipwreck = shipwreckRepository.findOne(id);
//        shipwreckRepository.delete(existingShipwreck);
//        return existingShipwreck;
    }

}
