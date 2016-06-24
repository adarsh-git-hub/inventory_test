/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.hibernate.DAO;

import inventory.hibernate.entities.ItemTable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eddy
 */
public interface ItemDAO {

    public boolean saveNewItem(String itemName, String category, String itemUnit, double itemAvailable, double lowAlertQty, double mrp, double vat, double purRate, double purTax, double saleRate, double saleTax);

    public boolean updateExistingItem(long itemPKeyDB, String itemName, double itemAvailable, String itemUnit, double lowAlertQty, String category, double mrp, double vat, double purRate, double purTax, double saleRate, double saleTax);

    public boolean deleteExistingItem(long itemPKeyDB);

    public ArrayList<ItemTable> selectAllItems();

    public List getAllItemNames();

    public ItemTable selectItemWithName(String itemName);

    public ItemTable selectItemViaId(long itemPKeyDB);
}
