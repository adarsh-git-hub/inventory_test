/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.hibernate.DAO;

import inventory.hibernate.entities.BillSale;
import java.util.ArrayList;

/**
 *
 * @author Eddy
 */
public interface SaleBillDAO {
    
    public boolean saveNewSaleBill();

    public boolean updateExistingSaleBill();

    public boolean deleteExistingSaleBill();

    public ArrayList<BillSale> selectAllSaleBills();

    public ArrayList<BillSale> getSaleBillsBetween();

    public BillSale selectItemViaId(long saleBillPKeyDB); 
}
