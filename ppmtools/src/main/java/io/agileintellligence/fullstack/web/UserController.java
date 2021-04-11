package io.agileintellligence.fullstack.web;

import io.agileintellligence.fullstack.domain.User;
import io.agileintellligence.fullstack.payload.JWTLoginSucessResponse;
import io.agileintellligence.fullstack.payload.LoginRequest;
import io.agileintellligence.fullstack.security.JwtTokenProvider;
import io.agileintellligence.fullstack.services.MapValidationErrorService;
import io.agileintellligence.fullstack.services.UserService;
import io.agileintellligence.fullstack.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static io.agileintellligence.fullstack.security.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest,BindingResult result)
    {
   ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationErrorService(result);
   if(errorMap != null)
       return errorMap;
   Authentication authentication = authenticationManager.authenticate(
           new UsernamePasswordAuthenticationToken(
                   loginRequest.getUsername(),
                   loginRequest.getPassword()
           )
   );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = TOKEN_PREFIX + tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTLoginSucessResponse(true, jwt));
        
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result)
    {
        //Validate Passwords Match
        userValidator.validate(user,result);
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationErrorService(result);
        if (errorMap != null )
            return errorMap;
        User newUser = userService.saveUser(user);

        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }
}
