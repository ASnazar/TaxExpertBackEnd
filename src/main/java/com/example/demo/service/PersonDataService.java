package com.example.demo.service;

import com.example.demo.controller.TelegramController;
import com.example.demo.entity.PersonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class PersonDataService {
    @Autowired
    private com.example.demo.repository.PersonDataRepository repo;
    @Autowired
    private EmailSenderService emailSender;

    @Autowired
    private TelegramController telegramController;

    public Iterable<PersonData> listAll(){
        return this.repo.findAll();
    }

    public void saveOrUpdate(PersonData personData){
        repo.save(personData);
    }

    public PersonData getPersonDataById(long id){
        return repo.findById(id).get();
    }

    public void update(PersonData personData, int id){
        repo.save(personData);
    }

    public void delete(long id){
        repo.deleteById(id);
    }

    private String getMessage(PersonData personData) {
        return "Клієнт : " + repo.findOne(Example.of(personData)).get().getId() + System.lineSeparator()
                + "Ім'я : " + personData.getName() + System.lineSeparator()
                + "Email : " + personData.getEmail() + System.lineSeparator()
                + "Номер телефону : " + personData.getPhoneNumber();
    }

    public void sendMessageInTelegram (PersonData personData) {
        telegramController.sendMessage(getMessage(personData));
    }

    public void sendMail(PersonData personData){
        String message = "Доброго дня, " + personData.getName() + "!" + System.lineSeparator()
                + "Ваші дані успішно передано " + System.lineSeparator()
                + "Дані для перевірки: " + System.lineSeparator() + getMessage(personData)
                + System.lineSeparator() + "При знаходженні помилки надішліть їх повторно!"
                + System.lineSeparator() + "З повагою, твій Податковий помічник.";
        emailSender.sendEmail(personData.getEmail(),"Tax Expert",message);
    }
}
