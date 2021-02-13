package app.seeder;

import app.entities.User;
import app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class SeederDb {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public SeederDb(UserRepository userRepository,
                    PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public void SeedAllTabels() {
        SeedUser();
    }
    public void SeedUser() {
        if(userRepository.count()==0) {
            User user = new User();
            user.setName("Іван Петрович");
            user.setEmail("admin@gmail.com");
            user.setPassword(passwordEncoder.encode("123456"));

            userRepository.save(user);
        }
    }
}
