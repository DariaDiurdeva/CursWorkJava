package hospital.web;

import hospital.entity.User;
import hospital.repository.UserRepository;
import hospital.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/hospital/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping(value = "/signin", consumes = "application/json")
    public ResponseEntity singIn(@RequestBody AuthRequest authRequest){
        try{
            String name = authRequest.getUserName();
            Optional<User> user = userRepository.findUserByUserName(name);
            boolean passwordMatch = false;
            if (user.isPresent()) {
                passwordMatch = passwordEncoder.matches(authRequest.getPassword(),user.get().getPassword());
            } else {
                throw new UsernameNotFoundException("Invalid user ot password");
            }

            if (!passwordMatch){
                throw new BadCredentialsException("Invalid password");
            }
            String token = jwtTokenProvider.createToken(name,user.get().getRoles());
            Map<Object, Object> model = new HashMap<>();
            model.put("userName",name);
            model.put("token",token);

            return  ResponseEntity.ok(model);
        } catch (AuthenticationException e ){
            throw  new BadCredentialsException("Invalid username or password");
        }
    }
}
