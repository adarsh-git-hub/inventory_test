/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.hibernate.DAOFactories;

import inventory.hibernate.DAO.ItemDAO;
import inventory.hibernate.DAOImplementations.ItemDAOImpl;

/**
 *
 * @author Eddy
 */
public class ItemDAOFactory {

    public static ItemDAO getInstance() {
        return new ItemDAOImpl();
    }
}
