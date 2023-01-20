package com.enchere.controller;

import com.enchere.exception.TokenNotFoundException;
import com.enchere.model.crud.Comission;
import com.enchere.service.AdminTokenService;
import com.enchere.service.crud.ComissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.enchere.util.ControllerUtil.returnSuccess;
import static com.enchere.util.ErrorDisplay.returnError;

@RestController
@CrossOrigin
@RequestMapping("/comissions")
public class ComissionController {
    @Autowired
    private ComissionService comissionService;
    @Autowired
    private AdminTokenService adminTokenService;


    @PostMapping
    public ResponseEntity<?> save(@RequestHeader(value = "admin_token") String value,@RequestBody Comission comission) {
        try {
            adminTokenService.getByToken(value);
            comission=comissionService.save(comission);
            return returnSuccess(comission, HttpStatus.ACCEPTED);
        }catch (TokenNotFoundException e) {
            return returnError(HttpStatus.UNAUTHORIZED, e);
        } catch (Exception e) {
            return returnError(HttpStatus.BAD_REQUEST, e);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll(@RequestHeader(value = "admin_token") String value) {
        try {
            adminTokenService.getByToken(value);
            List<Comission> categorie = comissionService.getAll();
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
            Comission categorie = comissionService.getById(id);
            return returnSuccess(categorie,HttpStatus.ACCEPTED);
        } catch (TokenNotFoundException e) {
            return returnError(HttpStatus.UNAUTHORIZED, e);
        } catch (Exception e) {
            return returnError(HttpStatus.NOT_FOUND, e);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@RequestHeader(value = "admin_token") String value,@RequestBody Comission categorie, @PathVariable("id") long id) {
        try {
            adminTokenService.getByToken(value);
            categorie = comissionService.update(categorie, id);
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
            Comission comission = comissionService.delete(id);
            return returnSuccess(comission,HttpStatus.ACCEPTED);
        } catch (TokenNotFoundException e) {
            return returnError(HttpStatus.UNAUTHORIZED, e);
        } catch (Exception e) {
            return returnError(HttpStatus.NOT_FOUND, e);
        }
    }

}
