package com.example.demo.controller;

import com.example.demo.entity.PersonData;
import com.example.demo.service.PersonDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class PersonDataController {

        @Autowired
        private PersonDataService service ;

        @GetMapping("/get-all")
        public Iterable<PersonData>getPersonData(){
            return service.listAll();
        }
        @PostMapping(value="/save")
        private long savePersonDate(@RequestBody PersonData personData){
                service.saveOrUpdate(personData);
                service.sendMessageInTelegram(personData);
                service.sendMail(personData);
                return personData.getId();
        }
        @RequestMapping("/person/{id}")
        private PersonData getPersonDataById(@PathVariable(name = "id") int id){
                return service.getPersonDataById(id);
        }

        @PutMapping("/edit/{id}")
        private PersonData updatePersonDate(@RequestBody PersonData personData, @PathVariable int id){
                personData.setId(id);
                service.saveOrUpdate(personData);
                return personData;
        }
        @DeleteMapping("/delete/{id}")
        private void deleteStudent(@PathVariable("id") int id){
                service.delete(id);
        }
}
