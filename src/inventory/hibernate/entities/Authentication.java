/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.hibernate.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Eddy
 */
@Entity
public class Authentication implements Serializable {

//    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "USER_ID_PK", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userIdPk;

    @Column(name = "USER_NAME", unique = true, nullable = false, length = 20)
    private String userName;

    @Column(name = "USER_PSW", nullable = false, length = 20)
    private String userPsw;

    @Column(name = "USER_TYPE", nullable = false, length = 20)
    private String userType;

    public long getUserIdPk() {
        return userIdPk;
    }

    public void setUserIdPk(long userIdPk) {
        this.userIdPk = userIdPk;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPsw() {
        return userPsw;
    }

    public void setUserPsw(String userPsw) {
        this.userPsw = userPsw;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

}
