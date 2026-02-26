package daw.pi.platinum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import daw.pi.platinum.dto.platinum.MessageResponse;
import daw.pi.platinum.dto.platinum.auth.LoginRequest;
import daw.pi.platinum.dto.platinum.auth.LoginResponse;
import daw.pi.platinum.dto.platinum.auth.RegisterRequest;
import daw.pi.platinum.exception.EmailAlreadyExists;
import daw.pi.platinum.exception.UsernameAlreadyExistsException;
import daw.pi.platinum.exception.UsernameNotFoundException;
import daw.pi.platinum.models.User;
import daw.pi.platinum.repository.UserRepository;
import daw.pi.platinum.security.JwtTokenUtil;

@Service
public class AuthService {

    // Dependency injection
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private SyncService syncService;

    // User Register
    public MessageResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UsernameAlreadyExistsException(request.getUsername());
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExists(request.getEmail());
        }

        User user = new User(request.getUsername(), passwordEncoder.encode((request.getPassword())),
                request.getEmail());
        userRepository.save(user);

        return new MessageResponse("User registered successfully");
    }

    // User Login
    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtTokenUtil.generateToken(userDetails);

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(request.getUsername()));

        return new LoginResponse(token, user.getUsername(), user.getEmail(), user.getRole(), user.isEnabled());
    }

    // Steam Account Linking
    public MessageResponse linkSteamAccount(String request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(username));

        user.setSteamId(request);

        userRepository.save(user);

        System.out.println("DEBUG: Running AuthService.linkSteamAccount() - Sync player summary, please!");
        syncService.syncPlayerSummary(user);

        System.out.println("DEBUG: Running AuthService.linkSteamAccount() - Sync player games, please!");
        syncService.syncPlayerGames(user);

        return new MessageResponse("Account linked successfully");
    }
}
