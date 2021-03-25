package mk.ukim.finki.restaurantreviewapp.service.impl;

import mk.ukim.finki.restaurantreviewapp.Repository.UserRepository;
import mk.ukim.finki.restaurantreviewapp.model.User;
import mk.ukim.finki.restaurantreviewapp.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.restaurantreviewapp.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.restaurantreviewapp.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(InvalidUserCredentialsException::new);
    }
}
