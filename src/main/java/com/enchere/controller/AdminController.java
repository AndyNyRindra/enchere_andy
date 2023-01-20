package com.enchere.controller;

import com.enchere.exception.LoginException;
import com.enchere.model.login.Admin;
import com.enchere.model.login.AdminToken;
import com.enchere.service.AdminService;
import com.enchere.service.AdminTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.enchere.util.ControllerUtil.returnError;
import static com.enchere.util.ControllerUtil.returnSuccess;

@RestController
@CrossOrigin
@RequestMapping("/admins")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminTokenService adminTokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Admin entity){
        try {
            Admin admin = adminService.login(entity.getEmail(),entity.getMdp());
            adminTokenService.disableTokenByAdmin(admin);
            AdminToken token = new AdminToken(admin);
            adminTokenService.saveAdminToken(token);
            return returnSuccess(token, HttpStatus.ACCEPTED);
        }catch (Exception e){
            return returnError(e,HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping("/token")
    public ResponseEntity<?> checkToken(@RequestHeader(value = "admin_token") String value){
        try {
            return returnSuccess(adminTokenService.getByToken(value), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return returnError(e,HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/token")
    public ResponseEntity<?> disableTokenByToken(@RequestHeader(value = "admin_token") String value) {
        try {
            adminTokenService.disableTokenByToken(value);
            return returnSuccess("Disconnect successfully", HttpStatus.ACCEPTED);
        }catch (Exception e){
            return returnError(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
