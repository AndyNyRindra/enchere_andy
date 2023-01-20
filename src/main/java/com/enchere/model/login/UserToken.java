package com.enchere.model.login;

import com.enchere.model.common.BaseModel;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Data
@Entity
@Table(name = "user_token")
public class UserToken extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "value", nullable = false)
    private String value;

    @Column(name = "date_expiration", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateExpiration;

    public UserToken(User user) throws Exception {
        this.user = user;
        setDateExpiration();
        setValue();
    }

    public UserToken() {

    }

    public void setDateExpiration() {
        LocalDateTime dateExpiration = LocalDateTime.now().plusMinutes(120);
        Instant instant = dateExpiration.toInstant(ZoneOffset.UTC);
        this.dateExpiration = Date.from(instant);
    }

    public void setValue() throws Exception {

        try {
            // getInstance() method is called with algorithm SHA-1
            String toHash = getUser().getEmail() + getUser().getMdp() + LocalDateTime.now();
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
