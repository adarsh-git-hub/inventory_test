/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.hibernate.DAOImplementations;

import inventory.hibernate.DAO.ItemDAO;
import inventory.hibernate.entities.ItemTable;
import inventory.hibernate.utils.HibernateUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Eddy
 */
public class ItemDAOImpl implements ItemDAO {

    // Custom Variables
    Session session = null;
    Transaction transaction = null;
    private ItemTable itemTable = new ItemTable();

    @Override
    public boolean saveNewItem(String itemName, String category, String itemUnit, double itemAvailable, double lowAlertQty, double mrp, double vat, double purRate, double purTax, double saleRate, double saleTax) {
        boolean itemSaved = false;
        try {
            if (!itemName.equals("")) {
//             Setting ItemTable Data to be stored
                itemTable.setItemName(itemName);
                itemTable.setAvailableItems(itemAvailable);
                itemTable.setItemUnit(itemUnit);
                itemTable.setItemLowAlertQty(lowAlertQty);
                itemTable.setCategoryName(category);
                itemTable.setItemMRP(mrp);
                itemTable.setItemVAT(vat);
                itemTable.setItemPurRate(purRate);
                itemTable.setItemPurchaseTax(purTax);
                itemTable.setItemSaleRate(saleRate);
                itemTable.setItemSaleTax(saleTax);

                session = HibernateUtil.getSessionFactory().openSession();
                transaction = session.beginTransaction();
                session.save(itemTable);
                transaction.commit();
                itemSaved = true;
                System.out.println("Item Saved");
            } else {
                System.out.println("Please Enter Valid Item name");
            }
        } catch (ConstraintViolationException cve) {
            cve.printStackTrace();
            System.out.println("Item Already Exist, Duplicate Item Not Allowed.");
        } catch (HibernateException | NoSuchElementException | NumberFormatException hibExp) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (TransactionException te) {
                    te.printStackTrace();
                }
            }
            System.out.println("Error");
            hibExp.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Problem");
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return itemSaved;
    }

    @Override
    public boolean updateExistingItem(long itemPKeyDB, String itemName, double itemAvailable, String itemUnit, double lowAlertQty, String category, double mrp, double vat, double purRate, double purTax, double saleRate, double saleTax) {
        boolean itemUpdated = false;
        if (itemPKeyDB != -1) {
            try {
                if (!itemName.equals("")) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Criteria itemUpdateCriteria = session.createCriteria(ItemTable.class);
                    itemUpdateCriteria.add(Restrictions.eq("itemIdPk", itemPKeyDB));
                    Iterator itemUpdateItr = itemUpdateCriteria.list().iterator();
                    if (itemUpdateItr.hasNext()) {
                        itemTable = (ItemTable) itemUpdateItr.next();

                        // Setting ItemTable Data to be stored
                        itemTable.setItemName(itemName);
                        itemTable.setAvailableItems(itemAvailable);
                        itemTable.setItemUnit(itemUnit);
                        itemTable.setItemLowAlertQty(lowAlertQty);
                        itemTable.setCategoryName(category);
                        itemTable.setItemMRP(mrp);
                        itemTable.setItemVAT(vat);
                        itemTable.setItemPurRate(purRate);
                        itemTable.setItemPurchaseTax(purTax);
                        itemTable.setItemSaleRate(saleRate);
                        itemTable.setItemSaleTax(saleTax);

                        transaction = session.beginTransaction();
                        session.merge(itemTable);
                        transaction.commit();
                        itemUpdated = true;
                        System.out.println("Item Table Updated");
                    } else {
                        System.out.println("No Record Found");
                    }
                } else {
                    System.out.println("Please Enter Valid Item Name");
                }
            } catch (Exception e) {
                if (transaction != null) {
                    try {
                        transaction.rollback();
                    } catch (TransactionException te) {
                        te.printStackTrace();
                    }
                }
                e.printStackTrace();
            } finally {
                if (session.isOpen()) {
                    session.close();
                }
            }
        } else {
            System.out.println("No Row selected");
        }
        return itemUpdated;
    }

    @Override
    public boolean deleteExistingItem(long itemPKeyDB) {
        boolean itemDeleted = false;
        if (itemPKeyDB != -1) {
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                Criteria itemDeleteCriteria = session.createCriteria(ItemTable.class);
                itemDeleteCriteria.add(Restrictions.eq("itemIdPk", itemPKeyDB));
                Iterator itemDeleteItr = itemDeleteCriteria.list().iterator();
                if (itemDeleteItr.hasNext()) {
                    itemTable = (ItemTable) itemDeleteItr.next();
                    if (itemTable.getPurchaseItemBillList().isEmpty()
                            && itemTable.getSaleItemBillList().isEmpty()
                            && itemTable.getPurchaseItemOrderList().isEmpty()
                            && itemTable.getSaleItemChallanList().isEmpty()) {

                        transaction = session.beginTransaction();
                        session.delete(itemTable);
                        transaction.commit();
                        itemDeleted = true;
                        System.out.println("Item Deleted");
                    } else {
                        System.out.println("This Item is associated with Bills, and cannot be deleted");
                    }
                } else {
                    System.out.println("No Such Item found");
                }
            } catch (Exception e) {
                if (transaction != null) {
                    try {
                        transaction.rollback();
                    } catch (TransactionException te) {
                        te.printStackTrace();
                    }
                }
            } finally {
                if (session.isOpen()) {
                    session.close();
                }
            }
        } else {
            System.out.println("No Row selected");
        }
        return itemDeleted;
    }

    @Override
    public ArrayList<ItemTable> selectAllItems() {
        ArrayList<ItemTable> itemList = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria itemSelectCriteria = session.createCriteria(ItemTable.class);
            Iterator itemSelectItr = itemSelectCriteria.list().iterator();
            while (itemSelectItr.hasNext()) {
                itemTable = (ItemTable) itemSelectItr.next();
                itemList.add(itemTable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemList;
    }

    @Override
    public List getAllItemNames() {
        List itemNames = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria itemSelectCriteria = session.createCriteria(ItemTable.class);
//            ProjectionList itemProjList = Projections.projectionList();
            Projection itemNameProj = Projections.property("itemName");
//            itemProjList.add(itemNameProj);
            itemSelectCriteria.setProjection(itemNameProj);
            itemNames = itemSelectCriteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemNames;
    }

    @Override
    public ItemTable selectItemWithName(String itemName) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria itemSelectCriteria = session.createCriteria(ItemTable.class);
            itemSelectCriteria.add(Restrictions.eq("itemName", itemName));
            Iterator itemSelectItr = itemSelectCriteria.list().iterator();
            if (itemSelectItr.hasNext()) {
                itemTable = (ItemTable) itemSelectItr.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemTable;
    }

    @Override
    public ItemTable selectItemViaId(long itemPKeyDB) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            itemTable = (ItemTable) session.get(ItemTable.class, itemPKeyDB);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemTable;
    }

}
