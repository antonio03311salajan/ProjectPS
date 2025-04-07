package business;

import model.RoleEnum;
import model.SpecialtyEnum;
import model.User;
import at.favre.lib.crypto.bcrypt.BCrypt;
import dataAccess.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class UserService {
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private static final UserRepository userRepository = new UserRepository();


    public static Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public static boolean registerUser(String username, String email,
                                       RoleEnum role, String firstName,
                                       String lastName, String cnp, String phoneNumber, SpecialtyEnum specialty,
                                       int shift) {
        if (username.isEmpty() || email.isEmpty()) {
            System.out.println("Error: Username, email, and password cannot be empty!");
            return false;
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            System.out.println("Error: Invalid email format!");
            return false;
        }
        if (userRepository.findByEmail(email).isPresent()) {
            System.out.println("Error: Email already in use!");
            return false;
        }

        User user = new User( 0,username, email, null, role, firstName, lastName, cnp, phoneNumber, specialty, shift);
        return userRepository.saveUser(user);
    }

    public static User loginUser(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            System.out.println("Error: Email and password cannot be empty!");
            return null;
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            System.out.println("Error: Invalid email format!");
            return null;
        }

        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if(user.getPassword() != null) {
                BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                if (result.verified) {
                    System.out.println("Login successful!");
                    return user;
                } else {
                    System.out.println("Error: Incorrect password!");
                }
            }else{
                System.out.println("User password is null and will be updated.");
                boolean passwordUpdated = userRepository.updatePassword(user.getEmail(), password);
                System.out.println("Password updated successfully: " + passwordUpdated);
                return passwordUpdated ? user : null;
            }
        } else {
            System.out.println("Error: User not found!");
        }
        return null;
    }

    public boolean updateUserPassword(String email, String newPassword) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            System.out.println("Error: User not found.");
            return false;
        }

        User user = userOptional.get();

        if(user.getPassword() == null) {
            System.out.println("User password is null and will be updated.");
            return userRepository.updatePassword(email, newPassword);
        }
        return false;
    }

    public boolean deleteUser(int userId) {
        return userRepository.deleteUser(userId);
    }

    public List<User> fetchAllUsers(int id) {
        return userRepository.getAllUsers(id);
    }

    public boolean updateUserInfo(int userId, String username, String email, String firstName, String lastName, String phoneNumber) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUsername(username);
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPhoneNumber(phoneNumber);

            return userRepository.updateUser(user);
        } else {
            System.out.println("Error: User not found." + userId);
            return false;
        }
    }

    public int getUserCountBySpecialty(String specialty) {
        if (specialty == null || specialty.trim().isEmpty()) {
            System.out.println("Error: Specialty cannot be empty!");
            return 0;
        }
        return userRepository.countUsersBySpecialty(specialty);
    }

    public static List<User> getAllByRole(RoleEnum role) {
        return userRepository.findUsersByRole(role);
    }


    public static Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }
}
