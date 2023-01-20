package com.enchere.service;

import com.enchere.exception.LoginException;
import com.enchere.exception.ResourceNotFoundException;
import com.enchere.model.login.Admin;
import com.enchere.repo.login.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepo adminRepo;
    @Override
    public Admin login(String email, String mdp) throws LoginException {
        if (adminRepo.findByEmailAndMdp(email, mdp) != null) {
            return adminRepo.findByEmailAndMdp(email, mdp);
        }
        throw new LoginException("Email or password is incorrect");
    }

    @Override
    public Admin findAdminById(String id) {
        return adminRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }
}
