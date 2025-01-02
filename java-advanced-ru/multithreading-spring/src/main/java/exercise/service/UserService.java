package exercise.service;

import exercise.model.User;
import exercise.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    // BEGIN
    public Mono<User> create(User user) {
        return userRepository.save(user);
    }

    public Mono<User> findById(long userId) {
        return userRepository.findById((long) userId);
    }

    public Mono<User> update(long userId, User user) {
        return userRepository.findById((long) userId)
                .flatMap(u -> {
                    u.setFirstName(user.getFirstName());
                    u.setLastName(user.getLastName());
                    u.setEmail(user.getEmail());
                    return userRepository.save(u);
                });
    }

    public Mono<Void> delete(long userId) {
        return userRepository.deleteById((long) userId);
    }
    // END
}
