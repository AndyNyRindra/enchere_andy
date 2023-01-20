package com.enchere.controller.loginUser;

import com.enchere.exception.LoginException;
import com.enchere.model.login.User;
import com.enchere.model.login.UserToken;
import com.enchere.service.login.UserService;
import com.enchere.service.login.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.enchere.util.ControllerUtil.returnError;
import static com.enchere.util.ControllerUtil.returnSuccess;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserTokenService userTokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User entity){
        try {
            User user = userService.login(entity.getEmail(),entity.getMdp());
            System.out.println("\n\n" + user.getNom() + " \n\n" + user.getId());
            userTokenService.disableTokenByUser(user);
            UserToken token = new UserToken(user);
            userTokenService.saveUserToken(token);
            return returnSuccess(token, HttpStatus.ACCEPTED);
        }catch (Exception e){
            return returnError(e,HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping("/token")
    public ResponseEntity<?> checkToken(@RequestHeader(value = "user_token") String value){
        try {
            return returnSuccess(userTokenService.getByToken(value), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return returnError(e,HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/token")
    public ResponseEntity<?> disableTokenByToken(@RequestHeader(value = "user_token") String value) {
        try {
            userTokenService.disableTokenByToken(value);
            return returnSuccess("Disconnect successfully", HttpStatus.ACCEPTED);
        }catch (Exception e){
            return returnError(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody User entity){
        try {
            return returnSuccess(userService.saveUser(entity), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return returnError(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
