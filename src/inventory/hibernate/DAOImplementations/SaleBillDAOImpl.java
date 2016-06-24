/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.hibernate.DAOImplementations;

import inventory.hibernate.DAO.SaleBillDAO;
import inventory.hibernate.entities.BillSale;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Eddy
 */
public class SaleBillDAOImpl implements SaleBillDAO {
// THIS IS NOT COMPLETED, AND RETURNNG DEFAULT VALUES
    // Custom Variables
    Session session = null;
    Transaction transaction = null;
    BillSale billSale = new BillSale();

    @Override
    public boolean saveNewSaleBill() {
        return false;
    }

    @Override
    public boolean updateExistingSaleBill() {
        return false;
    }

    @Override
    public boolean deleteExistingSaleBill() {
        return false;
    }

    @Override
    public ArrayList<BillSale> selectAllSaleBills() {
        return null;
    }

    @Override
    public ArrayList<BillSale> getSaleBillsBetween() {
        return null;
    }

    @Override
    public BillSale selectItemViaId(long saleBillPKeyDB) {
        return null;
    }

}
