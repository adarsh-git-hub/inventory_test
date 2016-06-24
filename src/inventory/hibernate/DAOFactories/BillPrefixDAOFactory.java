/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.hibernate.DAOFactories;

import inventory.hibernate.DAO.BillPrefixDAO;
import inventory.hibernate.DAOImplementations.BillPrefixDAOImpl;

/**
 *
 * @author Eddy
 */
public class BillPrefixDAOFactory {
    
    public static BillPrefixDAO getInstance() {
        return new BillPrefixDAOImpl();
    }
}
