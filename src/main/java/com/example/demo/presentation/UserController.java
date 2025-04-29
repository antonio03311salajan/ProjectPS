package com.example.demo.presentation;


import com.example.demo.business.UserService;
import com.example.demo.model.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static utils.UserFunctions.getTimeFromShift;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final UserService userService = new UserService();

    private static final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @CrossOrigin(
            origins = "http://localhost:3000",
            allowCredentials = "true",
            allowedHeaders = "*",
            methods = { RequestMethod.POST }
    )
    @PostMapping("/login")
    public LoginResponse handleLogin(@RequestBody LoginRequest loginRequest) {
        User user = UserService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
        System.out.println(user);

        if (user != null) {
            String jwt = Jwts.builder()
                    .setSubject(user.getEmail())
                    .claim("role", user.getRole())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                    .signWith(secretKey)
                    .compact();

            return new LoginResponse(jwt, user);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }
    }

    @CrossOrigin(
            origins = "http://localhost:3000",
            allowCredentials = "true",
            allowedHeaders = "*",
            methods = { RequestMethod.POST }
    )
    @PostMapping("/register")
    public static boolean handleRegister(@RequestBody UpdateUserDTO updateUserDTO) {
        System.out.println(updateUserDTO);
        boolean success = UserService.registerUser(
                updateUserDTO.getUsername(),
                updateUserDTO.getEmail(),
                updateUserDTO.getRole(),
                updateUserDTO.getFirstName(),
                updateUserDTO.getLastName(),
                updateUserDTO.getCnp(),
                updateUserDTO.getPhoneNumber(),
                updateUserDTO.getSpecialty(),
                updateUserDTO.getShift()
        );

        if (success) {
        } else {
            System.out.println("Registration failed.");
        }
        return success;
    }

    public void handleUpdatePassword(String email, String oldPassword, String newPassword) {
        boolean isUpdated = userService.updateUserPassword(email, newPassword);

        if (isUpdated) {
            System.out.println("Password updated successfully!");
        } else {
            System.out.println("Password update failed.");
        }
    }

    @CrossOrigin(
            origins = "http://localhost:3000",
            allowCredentials = "true",
            allowedHeaders = "*",
            methods = { RequestMethod.DELETE }
    )
    @DeleteMapping("/deleteUser")
    public boolean handleDeleteUser(@RequestParam Integer id) {
        return userService.deleteUser(id);
    }

    @CrossOrigin(
            origins = "http://localhost:3000",
            allowCredentials = "true",
            allowedHeaders = "*",
            methods = { RequestMethod.GET }
    )
    @GetMapping("/getUsers")
    public List<User> refreshUserTable(@RequestParam Integer id) {
        return userService.fetchAllUsers(id);
//        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
//        tableModel.setRowCount(0);
//
//        for (User user : users) {
//            System.out.println(user);
//            tableModel.addRow(new Object[]{
//                    user.getId(),
//                    user.getEmail(),
//                    user.getFirstName(),
//                    user.getLastName(),
//                    user.getRole().name()
//            });
//        }
    }

    @CrossOrigin(
            origins = "http://localhost:3000",
            allowCredentials = "true",
            allowedHeaders = "*",
            methods = { RequestMethod.GET }
    )
    @GetMapping("/findByEmail")
    public static ResponseEntity<User> handleFindUserByEmail(@RequestParam String email) {
        Optional<User> userOptional = UserService.findUserByEmail(email);

        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get());
        } else {
            System.out.println("User not found with email: " + email);
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(
            origins = "http://localhost:3000",
            allowCredentials = "true",
            allowedHeaders = "*",
            methods = { RequestMethod.PATCH }
    )
    @PatchMapping("/updateUser")
    public static boolean handleUpdateUser(@RequestParam int id,@RequestBody UpdateUserDTONew updateUserDTONew) {
        return userService.updateUserInfo(
                id,
                updateUserDTONew.getUsername(),
                updateUserDTONew.getEmail(),
                updateUserDTONew.getFirstName(),
                updateUserDTONew.getLastName(),
                updateUserDTONew.getPhoneNumber()
        );
    }

    @CrossOrigin(
            origins = "http://localhost:3000",
            allowCredentials = "true",
            allowedHeaders = "*",
            methods = { RequestMethod.GET }
    )
    @GetMapping("/countBySpecialty")
    public static int handleGetUserCountBySpecialty(@RequestParam String specialty) {
        return userService.getUserCountBySpecialty(specialty);
    }

    @CrossOrigin(
            origins = "http://localhost:3000",
            allowCredentials = "true",
            allowedHeaders = "*",
            methods = { RequestMethod.GET }
    )
    @GetMapping("/getAllDoctors")
    public static List<User> handleGetAllDoctors() {
        return UserService.getAllByRole(RoleEnum.DOCTOR);
//        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
//        tableModel.setRowCount(0);
//
//        if(!receptionistView) {
//            for (User user : users) {
//                tableModel.addRow(new Object[]{
//                        user.getFirstName(),
//                        user.getLastName(),
//                        user.getEmail(),
//                        user.getRole(),
//                        user.getSpecialty(),
//                        getTimeFromShift(user.getShift()),
//                        AppointmentController.handleGetAppointmentCountForDoctor(user.getId())
//                });
//            }
//        }
//        else{
//            for (User user : users) {
//                tableModel.addRow(new Object[]{
//                        user.getId(),
//                        user.getFirstName(),
//                        user.getLastName(),
//                        user.getEmail(),
//                        user.getRole(),
//                        user.getSpecialty(),
//                        user.getShift()
//                });
//            }
//        }
    }

    @CrossOrigin(
            origins = "http://localhost:3000",
            allowCredentials = "true",
            allowedHeaders = "*",
            methods = { RequestMethod.GET }
    )
    @GetMapping("/getAllPatients")
    public static List<User> handleGetAllPatients() {
        return UserService.getAllByRole(RoleEnum.USER);
//        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
//        tableModel.setRowCount(0);
//
//        for (User user : users) {
//            tableModel.addRow(new Object[]{
//                    user.getId(),
//                    user.getFirstName(),
//                    user.getLastName(),
//                    user.getEmail()
//            });
//        }
    }

    @CrossOrigin(
            origins = "http://localhost:3000",
            allowCredentials = "true",
            allowedHeaders = "*",
            methods = { RequestMethod.GET }
    )
    @GetMapping("/findById")
    public static User handleGetUserById(@RequestParam int id) {
        return UserService.getUserById(id)
                .orElse(null);
    }
}
