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
public class DureeDefaut  extends BaseModel {
    @Column(name = "duree", nullable = false)
    private Double duree;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date=Date.valueOf(LocalDate.now());

    public void setDuree(Double duree) throws ValueInvalideException {
        if (duree.isNaN()|| duree.isInfinite()||duree<=0) throw new ValueInvalideException();
        this.duree=duree;
    }
}
