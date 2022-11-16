package nl.novi.techiteasycontroller.controller;

import nl.novi.techiteasycontroller.exeptions.RecordNotFoundException;
import nl.novi.techiteasycontroller.model.Televisie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class TelevisionsController {

    private ArrayList<Televisie> televisies;

    public TelevisionsController(){
        televisies = new ArrayList<>();

        Televisie televisie = new Televisie();
        televisie.setId(0);
        televisie.setName("Samsung");
        televisie.setType("g11");
        televisies.add(televisie);
    }

    @GetMapping("/televisies")
    public ResponseEntity<Object> getTelevisies(){
        return new ResponseEntity<>(televisies, HttpStatus.OK);
    }

    @GetMapping("televisies/{id}")
    public ResponseEntity<Object> getOneTelevisie(@PathVariable int id){
        for (int i = 0; i < televisies.size(); i++){
            if (i == id){
                return new ResponseEntity<>(televisies.get(i),HttpStatus.OK);
            }

        }
        throw new RecordNotFoundException("televisie no found");
    }

    @PostMapping("/televisies")
    public ResponseEntity<Televisie> createTelevisie(@RequestBody Televisie televisie){
        televisies.add(televisie);
        return new ResponseEntity<>(televisie, HttpStatus.CREATED);
    }

    @PutMapping("/televisies/{id}")
    public ResponseEntity<Object> updateTelevisie(@PathVariable int id, @RequestBody Televisie televisie){
        if (id >= 0 && id < televisies.size()){
            televisies.set(id, televisie);
            return new ResponseEntity<>(televisie, HttpStatus.OK);
        } else {
            throw new RecordNotFoundException("invalid id");
        }
    }

    @DeleteMapping("/televisies")
    public ResponseEntity<Object> deleteTelevisie(@RequestBody String type){
        for (int i = 0; i < televisies.size(); i++){
            if (televisies.get(i).getType().equals(type)){
                televisies.remove(i);
                return new ResponseEntity<>("televisie deleted", HttpStatus.NO_CONTENT);
            }
        }
        throw new RecordNotFoundException("televisie no found");
    }

}
