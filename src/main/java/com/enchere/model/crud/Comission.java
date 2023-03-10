package com.enchere.model.crud;

import com.enchere.exception.ValueInvalideException;
import com.enchere.model.common.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Comission  extends BaseModel {
    @Column(name = "valeur", nullable = false)
    private Double valeur;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date=Date.valueOf(LocalDate.now());
    public void setValeur(Double valeur) throws ValueInvalideException {
        if (valeur.isNaN()|| valeur.isInfinite()||valeur<=0) throw new ValueInvalideException();
        this.valeur=valeur;
    }
}
