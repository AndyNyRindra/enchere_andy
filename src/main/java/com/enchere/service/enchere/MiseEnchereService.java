package com.enchere.service.enchere;

import com.enchere.exception.CustomException;
import com.enchere.model.enchere.Enchere;
import com.enchere.model.enchere.MiseEnchere;
import com.enchere.model.login.User;
import com.enchere.repo.enchere.MiseEnchereRepo;
import com.enchere.service.common.CrudService;
import com.enchere.service.login.UserServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static com.enchere.util.DateUtil.getDateNow;

@Service
public class MiseEnchereService extends CrudService<MiseEnchere, MiseEnchereRepo> {

    @Autowired
    private EnchereService enchereService;

    @Autowired
    private UserServiceImpl userService;
    public MiseEnchereService(MiseEnchereRepo repo) {
        super(repo);
    }

    public Double calculMontantBloque(User user) {
        List<MiseEnchere> misesMaxUser = repo.findAllByUserAndEstPlusHaut(user, true);
        Double result = 0.0;
        if (misesMaxUser != null && misesMaxUser.size() > 0) {
            for (MiseEnchere m : misesMaxUser) {
                Enchere enchereCorrespondant = enchereService.findById(m.getIdEnchere());
                if (enchereCorrespondant.getStatus() == 1) {
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
        MiseEnchere derniereMise = repo.findByIdEnchereAndEstPlusHaut(enchere.getId(), true);
        if (enchere.getUser().getId().equals(miseEnchere.getUser().getId())) {
            throw new CustomException("Cette enchere vous appartient");
        }
        if (enchere.isEnchereOver()) {
            throw new CustomException("L'enchere est terminee");
        }
        if (userService.soldeCompte(miseEnchere.getUser()) - calculMontantBloque(miseEnchere.getUser()) < miseEnchere.getMontant()) {
            throw new CustomException("Vous n'avez pas assez d'argent pour ench??rir");
        }
        if (derniereMise != null) {
            if (miseEnchere.getMontant() <= derniereMise.getMontant()) {
                throw new CustomException("Le montant de la mise doit etre sup??rieur ?? la derniere mise");
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
        enchere.setPrixVente(miseEnchere.getMontant());
        enchereService.update(enchere);
        miseEnchere.setDate(getDateNow());
        miseEnchere.setEstPlusHaut(true);
        return repo.save(miseEnchere);
    }

}
