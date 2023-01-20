package com.enchere.service;

import com.enchere.exception.LoginException;
import com.enchere.exception.ResourceNotFoundException;
import com.enchere.exception.TokenNotFoundException;
import com.enchere.model.login.Admin;
import com.enchere.model.login.AdminToken;
import com.enchere.repo.login.AdminTokenRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class AdminTokenServiceImpl implements AdminTokenService{

    @Autowired
    private AdminTokenRepo adminTokenRepo;


    @Override
    public AdminToken saveAdminToken(AdminToken adminToken) {
        return adminTokenRepo.save(adminToken);
    }

    @Override
    public AdminToken getByToken(String value) {
        //System.out.println("hahha1");
        if (value == null) throw new TokenNotFoundException();
        //System.out.println("hahha2");
        AdminToken token = adminTokenRepo.findByValueAndDateExpirationGreaterThan(value, Date.valueOf(LocalDate.now()));
        //System.out.println("hahha3");
        if (token != null) return token;
        else throw new TokenNotFoundException();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void disableTokenByAdmin(Admin admin) throws Exception {
        List<AdminToken> tokens = adminTokenRepo.findALlByAdminId(admin.getId());
        for (AdminToken token: tokens) {
            token.setDateExpiration(Date.valueOf(LocalDate.now()));
            adminTokenRepo.save(token);
        }
    }

    @Override
    public void disableTokenByToken(String value) throws Exception {
        AdminToken token = adminTokenRepo.findByValue(value);
        token.setDateExpiration(Date.valueOf(LocalDate.now()));
        adminTokenRepo.save(token);
    }


}
