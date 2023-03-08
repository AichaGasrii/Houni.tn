package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.*;
import com.esprit.achat.repositories.UserRepository;
import com.esprit.achat.services.Implementation.EmailServiceImpl;
import com.esprit.achat.services.Implementation.UserService;
import com.esprit.achat.services.Implementation.VerificationTokenService;
import com.esprit.achat.util.UserCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userDao;
    @Autowired
    private EmailServiceImpl emailServ;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private VerificationTokenService verificationTokenService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@Valid @RequestBody User user) {

        User savedUser = userService.registerNewUser(user);
        VerificationToken verificationToken = verificationTokenService.createVerificationToken(user); // création du jeton de vérification
        verificationTokenService.saveVerificationToken(verificationToken);
        return savedUser;
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin() {
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/users"})
    @PreAuthorize("hasRole('Admin')")
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping({"/user/{userName}"})
    public User findOne(@PathVariable String userName) {
        return userService.findOne(userName);
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser() {
        return "This URL is only accessible to the user";
    }

    @DeleteMapping({"/delete/{userName}"})
    @PreAuthorize("hasRole('Admin')")
    public void delete(@PathVariable String userName) {
        userService.delete(userName);
    }

    @PutMapping({"/update"})
    @PreAuthorize("hasRole('User')")
    public void update(@Valid @RequestBody User user) {
        userService.update(user);
    }

    @GetMapping("/count")
    @PreAuthorize("hasRole('Admin')")
    public long count() {
        return userService.count();
    }

    @GetMapping("/countoperateur")
    @PreAuthorize("hasRole('Admin')")
    public long countoperateur() {
        return userService.countoperateur();
    }

    @GetMapping("/countadmin")
    @PreAuthorize("hasRole('Admin')")
    public long countadmin() {
        return userService.countadmin();
    }

    @GetMapping("/countusers")
    @PreAuthorize("hasRole('Admin')")
    public long countusers() {
        return userService.countusers();
    }

    @GetMapping({"/sms/{userName}"})
    public void SMS(@PathVariable String userName) {
        userService.sms(userName);
    }

    @PutMapping({"/addRole/{roleName}/{userName}"})
    @PreAuthorize("hasRole('Admin')")
    public void addRoleToUser(@PathVariable String roleName, @PathVariable String userName) {
        userService.addRoleToUser(roleName, userName);


    }
    //activate compte
    @PutMapping("/activate/{verificationToken}")
    public ResponseEntity activateAccount(@PathVariable String verificationToken) {
        User user = userService.activateUser(verificationToken);
        if (user != null) {
            String to = user.getUserEmail();
            String subject = "Account Created";
            String text = "Your account has been created successfully.";
            emailServ.sendEmail(to, subject, text);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }






    // mail


    // http://localhost:8080/checkEmail
    @PostMapping("/checkEmail")
    public UserAccountResponse resetPasswordEmail(@RequestBody UserResetPassword resetPassword) {
        User user = this.userService.findByUserEmail(resetPassword.getEmail());
        UserAccountResponse accountResponse = new UserAccountResponse();
        if (user != null) {
            String code = UserCode.getCode();
            System.out.println("le code est" + code);
            UserMail mail = new UserMail(resetPassword.getEmail(), code);
            System.out.println("le mail est" + resetPassword.getEmail());
            System.out.println("la variable mail est" + mail);
            emailServ.sendCodeByMail(mail);
            System.out.println("la variable User est" + user);
            user.setUserCode(code);
            userDao.save(user);
            accountResponse.setResult(1);
        } else {
            accountResponse.setResult(0);
        }
        return accountResponse;
    }

    // http://localhost:8080/resetPassword
    @PostMapping("/resetPassword")
    public UserAccountResponse resetPassword(@RequestBody UserNewPassword newPassword) {
        User user = this.userService.findByUserEmail(newPassword.getEmail());
        UserAccountResponse accountResponse = new UserAccountResponse();
        if (user != null) {
            if (user.getUserCode().equals(newPassword.getCode())) {
                user.setUserPassword(passwordEncoder.encode(newPassword.getPassword()));
                userDao.save(user);
                accountResponse.setResult(1);
            } else {
                accountResponse.setResult(0);
            }
        } else {
            accountResponse.setResult(0);
        }
        return accountResponse;
    }
    @ControllerAdvice
    public class CommandeControllerAdvice {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ResponseBody
        public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
            Map<String, String> errors = new HashMap<>();
            ex.getBindingResult().getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            return errors;
        }
    }
}