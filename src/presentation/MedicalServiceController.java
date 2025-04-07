package presentation;

import business.MedicalServService;
import model.MedicalService;
import model.User;

import java.util.List;

public class MedicalServiceController {
    private static final MedicalServService service = new MedicalServService();

    public static boolean handleCreateMedicalService(String name, float price, int duration) {
        try {
            service.addMedicalService(name, price, duration);
            return true;
        } catch (Exception e) {
            System.out.println("Error creating service: " + e.getMessage());
            return false;
        }
    }

    public static List<MedicalService> handleGetAllMedicalServices() {
        return service.getAllServices();
    }

    public static boolean handleDeleteMedicalServiceById(int id) {
        return service.deleteMedicalServiceById(id);
    }
}
