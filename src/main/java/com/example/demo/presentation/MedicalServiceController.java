package com.example.demo.presentation;



import com.example.demo.business.MedicalServService;
import com.example.demo.model.CreateMedicalServiceDTO;
import com.example.demo.model.MedicalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medical-service")
public class MedicalServiceController {
    private static final MedicalServService service = new MedicalServService();

    @CrossOrigin(
            origins = "http://localhost:3000",
            allowCredentials = "true",
            allowedHeaders = "*",
            methods = { RequestMethod.POST }
    )
    @PostMapping("/create")
    public static boolean handleCreateMedicalService(@RequestBody CreateMedicalServiceDTO createMedicalServiceDTO) {
        try {
            service.addMedicalService(
                    createMedicalServiceDTO.getName(),
                    createMedicalServiceDTO.getPrice(),
                    createMedicalServiceDTO.getDuration()
            );
            return true;
        } catch (Exception e) {
            System.out.println("Error creating service: " + e.getMessage());
            return false;
        }
    }

    @CrossOrigin(
            origins = "http://localhost:3000",
            allowCredentials = "true",
            allowedHeaders = "*",
            methods = { RequestMethod.GET }
    )
    @GetMapping("/getAll")
    public static List<MedicalService> handleGetAllMedicalServices() {
        return service.getAllServices();
    }

    public static boolean handleDeleteMedicalServiceById(int id) {
        return service.deleteMedicalServiceById(id);
    }

    public static String fetchServiceNameById(int id) {
        return service.getServiceNameById(id);
    }
}
