package com.enchere.repo.enchere;

import com.enchere.model.enchere.Enchere;
import com.enchere.model.login.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnchereRepo extends JpaRepository<Enchere, Long> {

    public List<Enchere> findAllByUser(User user);
}
