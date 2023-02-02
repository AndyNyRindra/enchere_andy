package com.enchere.controller;

import com.enchere.controller.common.CrudController;
import com.enchere.exception.TokenNotFoundException;
import com.enchere.model.enchere.CritereRechercheEnchere;
import com.enchere.model.enchere.Enchere;
import com.enchere.model.enchere.MiseEnchere;
import com.enchere.model.login.User;
import com.enchere.model.login.UserToken;
import com.enchere.service.enchere.EnchereService;
import com.enchere.service.enchere.MiseEnchereService;
import com.enchere.service.login.UserTokenService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.enchere.util.ControllerUtil.returnSuccess;
import static com.enchere.util.ErrorDisplay.returnError;

@RestController
@RequestMapping("/enchere")
@CrossOrigin
public class EnchereController extends CrudController<Enchere, EnchereService> {

    @Autowired
    private MiseEnchereService miseEnchereService;

    @Autowired
    private UserTokenService userTokenService;
    public EnchereController(EnchereService service) {
        super(service);
    }

    @PostMapping("/{id}/mise")
    public ResponseEntity<?> mise(@PathVariable("id") Long id, @RequestBody MiseEnchere mise, @RequestHeader(value = "user_token") String value) {
        try {

            UserToken token = userTokenService.getByToken(value);
            mise.setUser((User) Hibernate.unproxy(token.getUser()));
            mise.setIdEnchere(id);
            return returnSuccess(miseEnchereService.create(mise), HttpStatus.ACCEPTED);
        }catch (TokenNotFoundException e) {
            return returnError(HttpStatus.UNAUTHORIZED, e);
        } catch (Exception e) {
            return returnError(HttpStatus.BAD_REQUEST, e);
        }
    }

    @PostMapping("/recherche")
    public ResponseEntity<?> recherche(@RequestBody CritereRechercheEnchere criteres) {
        try {
            return returnSuccess(service.rechercheMultiCritere(criteres), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return returnError(HttpStatus.BAD_REQUEST, e);
        }
    }

    @GetMapping("/mine")
    public ResponseEntity<?> getMine(@RequestHeader(value = "user_token") String value) {
        try {
            UserToken token = userTokenService.getByToken(value);
            return returnSuccess(service.findAllByUser((User) Hibernate.unproxy(token.getUser())), HttpStatus.ACCEPTED);
        } catch (TokenNotFoundException e) {
            return returnError(HttpStatus.UNAUTHORIZED, e);
        } catch (Exception e) {
            return returnError(HttpStatus.BAD_REQUEST, e);
        }
    }

    @PostMapping("/new")
    public ResponseEntity<?> create(@RequestBody Enchere entity, @RequestHeader(value = "user_token") String value) {
        try {
            UserToken token = userTokenService.getByToken(value);
            entity.setUser((User) Hibernate.unproxy(token.getUser()));
            return returnSuccess(service.create(entity), HttpStatus.ACCEPTED);
        } catch (TokenNotFoundException e) {
            return returnError(HttpStatus.UNAUTHORIZED, e);
        } catch (Exception e) {
            return returnError(HttpStatus.BAD_REQUEST, e);
        }
    }

    @GetMapping("/check")
    public ResponseEntity<?> check() {
        try {
            service.begin();
            return returnSuccess(service.getJusteTermine(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return returnError(HttpStatus.BAD_REQUEST, e);
        }
    }

}
