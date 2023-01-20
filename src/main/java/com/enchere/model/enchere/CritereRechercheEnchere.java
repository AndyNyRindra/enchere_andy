package com.enchere.model.enchere;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CritereRechercheEnchere {

    private Date dateMin;
    private Date dateMax;
    private String motCle;
    private Long[] idCategorie;
    private Integer status;
    private Double prixMin;
    private Double prixMax;

    private String getCategorieCondition() {
        if (idCategorie == null || idCategorie.length == 0) {
            return "";
        }
        String condition = " AND (";
        for (int i = 0; i < idCategorie.length; i++) {
            condition += "id_categorie = " + idCategorie[i];
            if (i < idCategorie.length - 1) {
                condition += " OR ";
            }
        }
        condition += ")";
        return condition;
    }

    public String getDateCondition() {
        if (dateMin == null && dateMax == null) {
            return "";
        }
        String condition = " AND (";
        if (dateMin != null) {
            condition += "date_debut >= '" + dateMin + "'";
        }
        if (dateMax != null) {
            if (dateMin != null) {
                condition += " AND ";
            }
            condition += "date_debut <= '" + dateMax + "'";
        }
        condition += ")";
        return condition;
    }

    public String getPrixCondition() {
        if (prixMin == null && prixMax == null) {
            return "";
        }
        String condition = " AND (";
        if (prixMin != null) {
            condition += "prix_minimal_vente >= " + prixMin;
        }
        if (prixMax != null) {
            if (prixMin != null) {
                condition += " AND ";
            }
            condition += "prix_minimal_vente <= " + prixMax;
        }
        condition += ")";
        return condition;
    }

    public String getMotCleCondition() {
        if (motCle == null || motCle.isEmpty()) {
            return "";
        }
        return " AND (nom LIKE '%" + motCle + "%' OR description LIKE '%" + motCle + "%')";
    }

    public String getStatusCondition() {
        if (status == null) {
            return "";
        }
        return " AND status = " + status;
    }

    public String getCondition() {
        return "SELECT * from enchere where 1 = 1" + getDateCondition() + getMotCleCondition() + getCategorieCondition() + getPrixCondition() + getStatusCondition();
    }
}
