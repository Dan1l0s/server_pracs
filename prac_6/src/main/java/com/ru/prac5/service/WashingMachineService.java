package com.ru.prac5.service;

import com.google.gson.Gson;
import com.ru.prac5.model.Book;
import com.ru.prac5.model.WashingMachine;
import com.ru.prac5.repository.WashingMachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WashingMachineService {
    @Autowired
    private WashingMachineRepository washingMachineRepository;

    public String createWashingMachine(WashingMachine washingMachine) {
        Optional<WashingMachine> existingWashingMachine = washingMachineRepository.findById(washingMachine.getId());
        if (existingWashingMachine.isPresent()) {
            return "error";
        }
        washingMachineRepository.save(washingMachine);
        return "created";
    }

    public List<WashingMachine> getAllWashingMachines() {
        return washingMachineRepository.findAllByOrderByIdAsc();
    }

    public WashingMachine getWashingMachineById(int id) {
        Optional<WashingMachine> washingMachine = washingMachineRepository.findById(id);
        if (washingMachine.isPresent()) {
            return washingMachine.get();
        }
        return null;
    }
    
    public String getWashingMachineByIdJson(int id) {
        Optional<WashingMachine> washingMachine = washingMachineRepository.findById(id);
        if (washingMachine.isPresent()) {
            Gson gson = new Gson();
            String json = gson.toJson(washingMachine.get());
            return json;
        }
        return "error";
    }

    public String updateWashingMachine(int id, WashingMachine washingMachineDetail) {
        Optional<WashingMachine> washingMachine = washingMachineRepository.findById(id);
        if (washingMachine.isPresent()) {
            WashingMachine existingWashingMachine = washingMachine.get();
            existingWashingMachine.replace(washingMachineDetail);
            washingMachineRepository.save(existingWashingMachine);
            return "updated";
        }
        return "error";
    }

    public String deleteWashingMachine(int id) {
        Optional<WashingMachine> existingWashingMachine = washingMachineRepository.findById(id);
        if (existingWashingMachine.isPresent()) {
            washingMachineRepository.deleteById(id);
            return "deleted";
        }
        return "error";
    }

    public int getAmount(int id) {
        Optional<WashingMachine> washingMachine = washingMachineRepository.findById(id);
        if (washingMachine.isPresent()) {
            return washingMachine.get().getAmount();
        }
        return -1;
    }

    public void updateAmount(int id, int amount) {
        Optional<WashingMachine> washingMachine = washingMachineRepository.findById(id);
        if (washingMachine.isPresent()) {
            washingMachine.get().setAmount(amount);
            washingMachineRepository.save(washingMachine.get());
        }
    }
}
