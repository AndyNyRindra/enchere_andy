package com.enchere.controller;

import com.enchere.exception.TokenNotFoundException;
import com.enchere.model.DemandeRechargement;
import com.enchere.model.crud.Categorie;
import com.enchere.model.login.AdminToken;
import com.enchere.model.login.User;
import com.enchere.service.AdminTokenService;
import com.enchere.service.crud.CategorieService;
import com.enchere.service.login.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.enchere.util.ControllerUtil.returnSuccess;
import static com.enchere.util.ErrorDisplay.returnError;

@RestController
@CrossOrigin
@RequestMapping("/statistique")
public class StatistiqueController {
    @Autowired
    private CategorieService categorieService;
    @Autowired
    private AdminTokenService adminTokenService;
    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public ResponseEntity<?> getStatistique(@RequestHeader(value = "admin_token") String value,@PathVariable("id") long id) {
        try {
            AdminToken token = adminTokenService.getByToken(value);
            if(id==1){

                return returnSuccess(categorieService.top10Populaire(), HttpStatus.ACCEPTED);
            }else if (id==2){
                return returnSuccess(categorieService.top10Rentable(), HttpStatus.ACCEPTED);
            } else if (id==3) {
                return returnSuccess(userService.top10Populaire(), HttpStatus.ACCEPTED);
            } else if (id==4) {
                return returnSuccess(userService.top10Rentable(), HttpStatus.ACCEPTED);
            }else {
                return returnError(HttpStatus.NOT_FOUND, new Exception("Statistique non disponible"));
            }

        } catch (TokenNotFoundException e) {
            return returnError(HttpStatus.UNAUTHORIZED, e);
        } catch (Exception e) {
            return returnError(HttpStatus.NOT_FOUND, e);
        }

    }
}
