package com.enchere.model.login;

import com.enchere.model.common.BaseModel;
import com.enchere.model.common.MongoBaseModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Getter
@Setter
@Document
public class AdminToken extends MongoBaseModel {

    private String adminId;

    private String value;
    private Date dateExpiration;

    public AdminToken(Admin user) {
        setAdminId(user.getId());
        setDateExpiration();
        setValue(user);
    }

    public AdminToken() {

    }

    public void setDateExpiration() {
        LocalDateTime dateExpiration = LocalDateTime.now().plusMinutes(120);
        Instant instant = dateExpiration.toInstant(ZoneOffset.UTC);
        this.dateExpiration = Date.from(instant);
    }

    public void setValue(Admin user) {

        try {
            // getInstance() method is called with algorithm SHA-1
            String toHash = user.getEmail() + user.getMdp() + LocalDateTime.now();
            MessageDigest md = MessageDigest.getInstance("SHA-1");

            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(toHash.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            this.value = hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }


    }
}
