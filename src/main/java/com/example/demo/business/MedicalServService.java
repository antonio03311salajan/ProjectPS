package com.example.demo.business;

import com.example.demo.dataAccess.MedicalServiceRepository;
import com.example.demo.model.MedicalService;

import java.util.List;

public class MedicalServService {
    private final MedicalServiceRepository repository = new MedicalServiceRepository();

    public void addMedicalService(String name, float price, int duration) {
        MedicalService service = new MedicalService(name, price, duration);
        repository.createMedicalService(service);
    }

    public List<MedicalService> getAllServices() {
        return repository.getAllMedicalServices();
    }

    public boolean deleteMedicalServiceById(int id) {
        return repository.deleteById(id);
    }

    public String getServiceNameById(int id) {
        return repository.getServiceNameById(id);
    }
}
