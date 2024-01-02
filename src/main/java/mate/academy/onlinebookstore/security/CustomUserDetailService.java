package mate.academy.onlinebookstore.security;

import static mate.academy.onlinebookstore.util.ErrorMessagesConstants.USER_NOT_FOUND_WITH_EMAIL;

import lombok.RequiredArgsConstructor;
import mate.academy.onlinebookstore.repository.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_WITH_EMAIL));
    }
}
