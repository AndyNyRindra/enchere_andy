package com.enchere.repo.login;

import com.enchere.model.login.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserTokenRepo extends JpaRepository<UserToken, Long> {
    @Query(value = "SELECT u.* FROM user_Token u where value =:value AND date_Expiration >= now()", nativeQuery = true)
    UserToken getUserTokenByToken(@Param(value = "value") String value);

    @Modifying
    @Transactional
    @Query(value = "update user_Token u set date_Expiration = now() where user_id = :user_id and date_Expiration >= now()", nativeQuery = true)
    void disableTokenByUser(@Param(value = "user_id") long admin_id);

    @Modifying
    @Transactional
    @Query(value = "update user_Token u set date_Expiration = now() where value =:value and date_Expiration >= now()", nativeQuery = true)
    void disableTokenByToken(@Param(value = "value") String value);
}
