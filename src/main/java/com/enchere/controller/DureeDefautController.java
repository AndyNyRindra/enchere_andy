package com.enchere.controller;

import com.enchere.exception.TokenNotFoundException;
import com.enchere.model.crud.DureeDefaut;
import com.enchere.service.AdminTokenService;
import com.enchere.service.crud.DureeDefautService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.enchere.util.ControllerUtil.returnSuccess;
import static com.enchere.util.ErrorDisplay.returnError;

@RestController
@CrossOrigin
@RequestMapping("/dureeDefauts")
public class DureeDefautController {
    @Autowired
    private DureeDefautService dureeDefautService;
    @Autowired
    private AdminTokenService adminTokenService;

    @PostMapping
    public ResponseEntity<?> save(@RequestHeader(value = "admin_token") String value,@RequestBody DureeDefaut categorie) {
        try {
            adminTokenService.getByToken(value);
            categorie=dureeDefautService.save(categorie);
            return returnSuccess(categorie, HttpStatus.ACCEPTED);
        }  catch (TokenNotFoundException e) {
            return returnError(HttpStatus.UNAUTHORIZED, e);
        } catch (Exception e) {
            return returnError(HttpStatus.BAD_REQUEST, e);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll(@RequestHeader(value = "admin_token") String value) {
        try {
            adminTokenService.getByToken(value);
            List<DureeDefaut> categorie = dureeDefautService.getAll();
            return returnSuccess(categorie,HttpStatus.ACCEPTED);
        } catch (TokenNotFoundException e) {
            return returnError(HttpStatus.UNAUTHORIZED, e);
        } catch (Exception e) {
            return returnError(HttpStatus.NOT_FOUND, e);
        }

    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@RequestHeader(value = "admin_token") String value,@PathVariable("id") long id) {
        try {
            adminTokenService.getByToken(value);
            DureeDefaut categorie = dureeDefautService.getById(id);
            return returnSuccess(categorie,HttpStatus.ACCEPTED);
        } catch (TokenNotFoundException e) {
            return returnError(HttpStatus.UNAUTHORIZED, e);
        } catch (Exception e) {
            return returnError(HttpStatus.NOT_FOUND, e);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@RequestHeader(value = "admin_token") String value,@RequestBody DureeDefaut categorie, @PathVariable("id") long id) {
        try {
            adminTokenService.getByToken(value);
            categorie = dureeDefautService.update(categorie, id);
            return returnSuccess(categorie,HttpStatus.ACCEPTED);
        } catch (TokenNotFoundException e) {
            return returnError(HttpStatus.UNAUTHORIZED, e);
        } catch (Exception e) {
            return returnError(HttpStatus.BAD_REQUEST, e);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@RequestHeader(value = "admin_token") String value,@PathVariable("id") long id) {
        try {
            adminTokenService.getByToken(value);
            DureeDefaut categorie = dureeDefautService.delete(id);
            return returnSuccess(categorie,HttpStatus.ACCEPTED);
        } catch (TokenNotFoundException e) {
            return returnError(HttpStatus.UNAUTHORIZED, e);
        } catch (Exception e) {
            return returnError(HttpStatus.NOT_FOUND, e);
        }
    }

}
