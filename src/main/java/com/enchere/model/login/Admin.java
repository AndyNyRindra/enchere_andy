package com.enchere.model.login;

import com.enchere.model.common.BaseModel;
import com.enchere.model.common.MongoBaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

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
