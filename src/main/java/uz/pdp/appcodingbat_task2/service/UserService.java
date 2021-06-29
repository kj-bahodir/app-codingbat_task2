package uz.pdp.appcodingbat_task2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat_task2.entity.User;
import uz.pdp.appcodingbat_task2.payload.ApiResponse;
import uz.pdp.appcodingbat_task2.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getUsers() {
        List<User> userList = userRepository.findAll();
        return userList;
    }


    public User getUser(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return (optionalUser.orElse(null));
    }


    public ApiResponse deleteUser(Integer id) {
        try {
            userRepository.deleteById(id);
            return new ApiResponse("Deleted", true);
        } catch (Exception e) {
            return new ApiResponse("User not found", false);
        }
    }


    public ApiResponse add(User user) {
        boolean byEmail = userRepository.findByEmail(user.getEmail());
        if (byEmail) {
            return new ApiResponse("User with this email exists", false);
        }
        User user1 = new User();
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        userRepository.save(user1);
        return new ApiResponse("Successfully added", true);
    }

    public ApiResponse edit(Integer id, User user) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            return new ApiResponse("User not found", false);
        }
        User user1 = optionalUser.get();
        user1.setPassword(user.getPassword());
        user1.setEmail(user.getEmail());
        userRepository.save(user1);
        return new ApiResponse("successfully edited", true);
    }
}
