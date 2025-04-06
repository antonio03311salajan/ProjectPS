package business;

import model.MedicalService;
import dataAccess.MedicalServiceRepository;

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
}
