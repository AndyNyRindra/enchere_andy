package com.enchere.controller;

import com.enchere.exception.TokenNotFoundException;
import com.enchere.model.DemandeRechargement;
import com.enchere.model.login.AdminToken;
import com.enchere.model.login.User;
import com.enchere.model.login.UserToken;
import com.enchere.service.AdminService;
import com.enchere.service.AdminTokenService;
import com.enchere.service.login.UserTokenService;
import com.enchere.service.rechargement.RechargementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static com.enchere.util.ControllerUtil.returnSuccess;
import static com.enchere.util.ErrorDisplay.returnError;

@RestController
@CrossOrigin
@RequestMapping("/rechargement")
public class RechargementController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminTokenService adminTokenService;
    @Autowired
    private UserTokenService userTokenService;

    @Autowired
    private RechargementService rechargementService;

    @GetMapping
    public ResponseEntity<?> getDemandeEnCours(@RequestHeader(value = "admin_token") String value) {
        try {
            AdminToken token = adminTokenService.getByToken(value);
            List<DemandeRechargement> listeDemande = rechargementService.findAvailable();
            return returnSuccess(listeDemande, HttpStatus.ACCEPTED);
        } catch (TokenNotFoundException e) {
            return returnError(HttpStatus.UNAUTHORIZED, e);
        } catch (Exception e) {
            return returnError(HttpStatus.NOT_FOUND, e);
        }

    }
    @PostMapping("/demande")
    public ResponseEntity<?> saveDemande(@RequestHeader(value = "user_token") String value, @RequestBody DemandeRechargement demandeRechargement) {
        try {
            UserToken token = userTokenService.getByToken(value);
            demandeRechargement.setUser(token.getUser());
            demandeRechargement.setStatus(0);
            demandeRechargement.setDate(Date.valueOf(LocalDate.now()));

            rechargementService.create(demandeRechargement);

            return returnSuccess("", HttpStatus.ACCEPTED);
        } catch (TokenNotFoundException e) {
            return returnError(HttpStatus.UNAUTHORIZED, e);
        } catch (Exception e) {
            return returnError(HttpStatus.NOT_FOUND, e);
        }

    }
    @PutMapping("{id}")
    public ResponseEntity<?> accept(@RequestHeader(value = "admin_token") String value, @PathVariable("id") long id) {
        try {
            AdminToken token = adminTokenService.getByToken(value);
            DemandeRechargement demande = rechargementService.findById(id);
            rechargementService.accept(demande);
            return returnSuccess("",HttpStatus.ACCEPTED);
        } catch (TokenNotFoundException e) {
            return returnError(HttpStatus.UNAUTHORIZED, e);
        } catch (Exception e) {
            return returnError(HttpStatus.BAD_REQUEST, e);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> refuse(@RequestHeader(value = "admin_token") String value,@PathVariable("id") long id) {
        try {
            AdminToken token = adminTokenService.getByToken(value);
            DemandeRechargement demande = rechargementService.findById(id);
            rechargementService.refuse(demande);
            return returnSuccess("",HttpStatus.ACCEPTED);
        } catch (TokenNotFoundException e) {
            return returnError(HttpStatus.UNAUTHORIZED, e);
        } catch (Exception e) {
            return returnError(HttpStatus.NOT_FOUND, e);
        }
    }

}
