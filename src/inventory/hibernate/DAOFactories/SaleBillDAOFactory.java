/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.hibernate.DAOFactories;

import inventory.hibernate.DAO.SaleBillDAO;
import inventory.hibernate.DAOImplementations.SaleBillDAOImpl;

/**
 *
 * @author Eddy
 */
public class SaleBillDAOFactory {

    public static SaleBillDAO getInstance() {
        return new SaleBillDAOImpl();
    }
}
