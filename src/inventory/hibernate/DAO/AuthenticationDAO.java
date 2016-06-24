/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.hibernate.DAO;

import inventory.hibernate.entities.Authentication;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eddy
 */
public interface AuthenticationDAO {

    public boolean saveNewLoginCredential(String userName, String password, String userType);

    public boolean updateExistingLoginCredential(long loginPKeyDB, String userName, String password, String userType);

    public boolean deleteExistingCredential(long loginPKeyDB);

    public ArrayList<Authentication> selectAllLoginCredentials();

//    public List getAllLoginCredentialNames();

    public Authentication selectLoginCredentialWithName(String userName);

    public Authentication selectLoginCredentialViaId(long loginPKeyDB);

}
