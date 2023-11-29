package com.ru.prac5.service;

import com.google.gson.Gson;
import com.ru.prac5.model.Book;
import com.ru.prac5.model.Telephone;
import com.ru.prac5.repository.TelephoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TelephoneService {
    @Autowired
    private TelephoneRepository telephoneRepository;

    public String createTelephone(Telephone telephone) {
        Optional<Telephone> existingTelephone = telephoneRepository.findById(telephone.getId());
        if (existingTelephone.isPresent()) {
            return "error";
        }
        telephoneRepository.save(telephone);
        return "created";
    }

    public List<Telephone> getAllTelephones() {
        return telephoneRepository.findAllByOrderByIdAsc();
    }

    public Telephone getTelephoneById(int id) {
        Optional<Telephone> telephone = telephoneRepository.findById(id);
        if (telephone.isPresent()) {
            return telephone.get();
        }
        return null;
    }
    
    public String getTelephoneByIdJson(int id) {
        Optional<Telephone> telephone = telephoneRepository.findById(id);
        if (telephone.isPresent()) {
            Gson gson = new Gson();
            String json = gson.toJson(telephone.get());
            return json;
        }
        return "error";
    }

    public String updateTelephone(int id, Telephone telephoneDetail) {
        Optional<Telephone> telephone = telephoneRepository.findById(id);
        if (telephone.isPresent()) {
            Telephone existingTelephone = telephone.get();
            existingTelephone.replace(telephoneDetail);
            telephoneRepository.save(existingTelephone);
            return "updated";
        }
        return "error";
    }

    public String deleteTelephone(int id) {
        Optional<Telephone> existingTelephone = telephoneRepository.findById(id);
        if (existingTelephone.isPresent()) {
            telephoneRepository.deleteById(id);
            return "deleted";
        }
        return "error";
    }

    public int getAmount(int id) {
        Optional<Telephone> telephone = telephoneRepository.findById(id);
        if (telephone.isPresent()) {
            return telephone.get().getAmount();
        }
        return -1;
    }

    public void updateAmount(int id, int amount) {
        Optional<Telephone> telephone = telephoneRepository.findById(id);
        if (telephone.isPresent()) {
            telephone.get().setAmount(amount);
            telephoneRepository.save(telephone.get());
        }
    }
}
