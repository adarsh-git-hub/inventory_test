/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.hibernate.DAOFactories;

import inventory.hibernate.DAO.ClientDAO;
import inventory.hibernate.DAOImplementations.ClientDAOImpl;

/**
 *
 * @author Eddy
 */
public class ClientDAOFactory {

    public static ClientDAO getInstance() {
        return new ClientDAOImpl();
    }
}
