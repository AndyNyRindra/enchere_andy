package com.enchere.model.login;

import org.springframework.data.mongodb.core.mapping.Document;

import com.enchere.model.common.MongoBaseModel;

import lombok.Getter;
import lombok.Setter;

@Document
@Getter
@Setter
public class Admin extends MongoBaseModel {


    private String nom;

    private String email;

    private String mdp;

    public Admin() {

    }

}
