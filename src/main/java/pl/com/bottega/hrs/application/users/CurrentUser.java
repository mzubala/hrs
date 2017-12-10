package pl.com.bottega.hrs.application.users;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Optional;

@Component
@SessionScope
public class CurrentUser {

    private Integer userId;
    private UserRepository userRepository;

    public CurrentUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void login(User user) {
        userId = user.getId();
    }

    public Optional<UserDto> getUserInfo() {
        if(userId == null)
            return Optional.empty();
        User user = userRepository.get(userId);
        return Optional.of(new UserDto(user));
    }

    public void logout() {
        userId = null;
    }

    public boolean isAuthenticated() {
        return userId != null;
    }
}
