/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.hibernate.DAOFactories;

import inventory.hibernate.DAO.AuthenticationDAO;
import inventory.hibernate.DAOImplementations.AuthenticationDAOImpl;

/**
 *
 * @author Eddy
 */
public class AuthenticationDAOFactory {

    public static AuthenticationDAO getInstance() {
        return new AuthenticationDAOImpl();
    }
}
