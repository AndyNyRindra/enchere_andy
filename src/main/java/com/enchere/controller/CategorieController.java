package com.enchere.controller;

import com.enchere.exception.TokenNotFoundException;
import com.enchere.model.crud.Categorie;
import com.enchere.model.login.AdminToken;
import com.enchere.service.AdminTokenService;
import com.enchere.service.crud.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.enchere.util.ControllerUtil.returnSuccess;
import static com.enchere.util.ErrorDisplay.returnError;

@RestController
@CrossOrigin
@RequestMapping("/categories")
public class  CategorieController {
    @Autowired
    private CategorieService categorieService;
    @Autowired
    private AdminTokenService adminTokenService;


    @PostMapping
    public ResponseEntity<?> save(@RequestHeader(value = "admin_token") String value,@RequestBody Categorie categorie) {
        try {
            adminTokenService.getByToken(value);
            categorie=categorieService.save(categorie);
            return returnSuccess(categorie,HttpStatus.ACCEPTED); //202
        }catch (TokenNotFoundException e) {
            return returnError(HttpStatus.UNAUTHORIZED, e); //401
        } catch (Exception e) {
            return returnError(HttpStatus.BAD_REQUEST, e);  //400
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll(@RequestHeader(value = "admin_token") String value) {
        try {
            AdminToken token = adminTokenService.getByToken(value);
            List<Categorie> categorie = categorieService.getAll();
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
            Categorie categorie = categorieService.getById(id);
            return returnSuccess(categorie,HttpStatus.ACCEPTED);
        } catch (TokenNotFoundException e) {
            return returnError(HttpStatus.UNAUTHORIZED, e);
        } catch (Exception e) {
            return returnError(HttpStatus.NOT_FOUND, e);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@RequestHeader(value = "admin_token") String value,@RequestBody Categorie categorie, @PathVariable("id") long id) {
        try {
            adminTokenService.getByToken(value);
            categorie = categorieService.update(categorie, id);
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
            Categorie categorie = categorieService.delete(id);
            return returnSuccess(categorie,HttpStatus.ACCEPTED);
        } catch (TokenNotFoundException e) {
            return returnError(HttpStatus.UNAUTHORIZED, e);
        } catch (Exception e) {
            return returnError(HttpStatus.NOT_FOUND, e);
        }
    }


}
