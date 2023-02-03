package com.enchere.model.crud;

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
}
