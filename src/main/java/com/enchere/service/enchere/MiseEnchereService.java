package com.enchere.service.enchere;

import com.enchere.exception.CustomException;
import com.enchere.model.enchere.Enchere;
import com.enchere.model.enchere.MiseEnchere;
import com.enchere.model.login.User;
import com.enchere.repo.enchere.MiseEnchereRepo;
import com.enchere.service.common.CrudService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class MiseEnchereService extends CrudService<MiseEnchere, MiseEnchereRepo> {

    @Autowired
    private EnchereService enchereService;

    public MiseEnchereService(MiseEnchereRepo repo) {
        super(repo);
    }

    public Double calculMontantBloque(User user) {
        List<MiseEnchere> misesMaxUser = repo.findAllByUserAndEstPlusHaut(user, true);
        Double result = 0.0;
        if (misesMaxUser != null && misesMaxUser.size() > 0) {
            for (MiseEnchere m : misesMaxUser) {
                Enchere enchereCorrespondant = enchereService.findById(m.getIdEnchere());
                if (enchereCorrespondant.getStatus() == 0) {
                    result += m.getMontant();
                }
            }
        }
        return result;
    }
    @Override
    @Transactional(rollbackOn = Exception.class)
    public MiseEnchere create(MiseEnchere miseEnchere) throws CustomException {
        Enchere enchere = enchereService.findById(miseEnchere.getIdEnchere());
        MiseEnchere derniereMise = repo.findByIdEnchereOrderByDateDesc(enchere.getId());
        enchere.isEnchereOver();
//        if (miseEnchere.getUser().getCompte() - calculMontantBloque(miseEnchere.getUser()) < miseEnchere.getMontant()) {
//            throw new CustomException("Vous n'avez pas assez d'argent pour enchérir");
//        }
        if (derniereMise != null) {
            if (miseEnchere.getMontant() <= derniereMise.getMontant()) {
                throw new CustomException("Le montant de la mise doit etre supérieur à la derniere mise");
            }
        } else {
            if (miseEnchere.getMontant() <= enchere.getPrixMinimalVente()) {
                throw new CustomException("Le montant de la mise doit etre superieur au prix de depart");
            }
        }
        List<MiseEnchere> anciennesMises = repo.findAllByIdEnchere(enchere.getId());
        for (MiseEnchere ancienneMise : anciennesMises) {
            ancienneMise.setEstPlusHaut(false);
            repo.save(ancienneMise);
        }
        miseEnchere.setDate(Date.valueOf(LocalDate.now()));
        return repo.save(miseEnchere);
    }

}
