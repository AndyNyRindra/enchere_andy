package com.enchere.repo.stat;

import com.enchere.model.stat.UserRentable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRentableRepo extends JpaRepository<UserRentable, Long> {
}
