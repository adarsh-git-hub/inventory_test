/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.operations;

import inventory.hibernate.entities.Authentication;
import inventory.hibernate.entities.BillPrefixTable;
import inventory.hibernate.entities.ClientTable;
import inventory.hibernate.entities.ItemTable;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Eddy
 */
public class StockOperations {

    // Custom Variables
    private Session session = null;
    private Transaction transaction = null;
    private ItemTable itemTable = new ItemTable();
    private ClientTable clientTable = new ClientTable();
    private BillPrefixTable billPrifixTable = new BillPrefixTable();
    private Authentication authentication = new Authentication();

    // Item Modification
//    public void newItemStockEntry(String itemName, String category, String itemUnit, double itemAvailable, double lowAlertQty, double mrp, double vat, double purRate, double purTax, double saleRate, double saleTax) {
//        try {
//            if (!itemName.equals("")) {
////             Setting ItemTable Data to be stored
//                itemTable.setItemName(itemName);
//                itemTable.setAvailableItems(itemAvailable);
//                itemTable.setItemUnit(itemUnit);
//                itemTable.setItemLowAlertQty(lowAlertQty);
//                itemTable.setCategoryName(category);
//                itemTable.setItemMRP(mrp);
//                itemTable.setItemVAT(vat);
//                itemTable.setItemPurRate(purRate);
//                itemTable.setItemPurchaseTax(purTax);
//                itemTable.setItemSaleRate(saleRate);
//                itemTable.setItemSaleTax(saleTax);
//
//                session = HibernateUtil.getSessionFactory().openSession();
//                transaction = session.beginTransaction();
//                session.save(itemTable);
//                transaction.commit();
//                System.out.println("Item Saved");
//            } else {
//                System.out.println("Please Enter Valid Item name");
//            }
//        } catch (ConstraintViolationException cve) {
//            cve.printStackTrace();
//            System.out.println("Item Already Exist, Duplicate Item Not Allowed.");
//        } catch (HibernateException | NoSuchElementException | NumberFormatException hibExp) {
//            if (transaction != null) {
//                try {
//                    transaction.rollback();
//                } catch (TransactionException te) {
//                    te.printStackTrace();
//                }
//            }
//            System.out.println("Error");
//            hibExp.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Problem");
//        } finally {
//            if (session.isOpen()) {
//                session.close();
//            }
//        }
//    }

//    public void updateItemStockEntry(long itemPKeyDB, String itemName, double itemAvailable, String itemUnit, double lowAlertQty, String category, double mrp, double vat, double purRate, double purTax, double saleRate, double saleTax) {
//
//        if (itemPKeyDB != -1) {
//            try {
//                if (!itemName.equals("")) {
//                    session = HibernateUtil.getSessionFactory().openSession();
//                    Criteria itemUpdateCriteria = session.createCriteria(ItemTable.class);
//                    itemUpdateCriteria.add(Restrictions.eq("itemIdPk", itemPKeyDB));
//                    Iterator itemUpdateItr = itemUpdateCriteria.list().iterator();
//                    if (itemUpdateItr.hasNext()) {
//                        itemTable = (ItemTable) itemUpdateItr.next();
//
//                        // Setting ItemTable Data to be stored
//                        itemTable.setItemName(itemName);
//                        itemTable.setAvailableItems(itemAvailable);
//                        itemTable.setItemUnit(itemUnit);
//                        itemTable.setItemLowAlertQty(lowAlertQty);
//                        itemTable.setCategoryName(category);
//                        itemTable.setItemMRP(mrp);
//                        itemTable.setItemVAT(vat);
//                        itemTable.setItemPurRate(purRate);
//                        itemTable.setItemPurchaseTax(purTax);
//                        itemTable.setItemSaleRate(saleRate);
//                        itemTable.setItemSaleTax(saleTax);
//
//                        transaction = session.beginTransaction();
//                        session.update(itemTable);
//                        transaction.commit();
//                        System.out.println("Item Table Updated");
//                    } else {
//                        System.out.println("No Record Found");
//                    }
//                } else {
//                    System.out.println("Please Enter Valid Item Name");
//                }
//            } catch (Exception e) {
//                if (transaction != null) {
//                    try {
//                        transaction.rollback();
//                    } catch (TransactionException te) {
//                        te.printStackTrace();
//                    }
//                }
//                e.printStackTrace();
//            } finally {
//                if (session.isOpen()) {
//                    session.close();
//                }
//            }
//        } else {
//            System.out.println("No Row selected");
//        }
//
//    }

//    public void deleteItemStockEntry(long itemPKeyDB) {
//        if (itemPKeyDB != -1) {
//            try {
//                session = HibernateUtil.getSessionFactory().openSession();
//                Criteria itemDeleteCriteria = session.createCriteria(ItemTable.class);
//                itemDeleteCriteria.add(Restrictions.eq("itemIdPk", itemPKeyDB));
//                Iterator itemDeleteItr = itemDeleteCriteria.list().iterator();
//                if (itemDeleteItr.hasNext()) {
//                    itemTable = (ItemTable) itemDeleteItr.next();
//                    if (itemTable.getPurchaseItemBillList().isEmpty()
//                            && itemTable.getSaleItemBillList().isEmpty()
//                            && itemTable.getPurchaseItemOrderList().isEmpty()
//                            && itemTable.getSaleItemChallanList().isEmpty()) {
//
//                        transaction = session.beginTransaction();
//                        session.delete(itemTable);
//                        transaction.commit();
//                        System.out.println("Item Deleted");
//                    } else {
//                        System.out.println("This Item is associated with Bills, and cannot be deleted");
//                    }
//                } else {
//                    System.out.println("No Such Item found");
//                }
//            } catch (Exception e) {
//                if (transaction != null) {
//                    try {
//                        transaction.rollback();
//                    } catch (TransactionException te) {
//                        te.printStackTrace();
//                    }
//                }
//            } finally {
//                if (session.isOpen()) {
//                    session.close();
//                }
//            }
//        } else {
//            System.out.println("No Row selected");
//        }
//    }

    // Client Modification
//    public void newClientStockEntry(String clientName, double clientsSaleDue, double clientsPurDue, String clientsEmail, long clientsPh1, long clientsPh2, String clientsAddress, String clientsTIN, String clientsServiceNo, String clientType) {
//
//        try {
//            if (!clientName.equals("")) {
////             Setting Client's Data to be stored
//                clientTable.setClientName(clientName);
//                clientTable.setDueSaleAmount(clientsSaleDue);
//                clientTable.setDuePurchaseAmount(clientsPurDue);
//                clientTable.setClientEmail(clientsEmail);
//                clientTable.setClientPhone1(clientsPh1);
//                clientTable.setClientPhone2(clientsPh2);
//                clientTable.setClientAddress(clientsAddress);
//                clientTable.setClientTin(clientsTIN);
//                clientTable.setClientServiceNumber(clientsServiceNo);
//                clientTable.setClientType(clientType);
//
//                session = HibernateUtil.getSessionFactory().openSession();
//                transaction = session.beginTransaction();
//                session.save(clientTable);
//                transaction.commit();
//                System.out.println("Client's Details Saved");
//            } else {
//                System.out.println("Please Enter Valid Client Name");
//            }
//        } catch (ConstraintViolationException cve) {
//            cve.printStackTrace();
//            System.out.println("Client already Exist, Duplicate Item Not Allowed.");
//        } catch (HibernateException | NoSuchElementException | NumberFormatException hibExp) {
//            if (transaction != null) {
//                try {
//                    transaction.rollback();
//                } catch (TransactionException te) {
//                    te.printStackTrace();
//                }
//            }
//            System.out.println("Error");
//            hibExp.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Problem");
//        } finally {
//            if (session.isOpen()) {
//                session.close();
//            }
//        }
//
//    }

//    public void updateClientStockEntry(long clientPKeyDB, String clientName, double clientsSaleDue, double clientsPurDue, String clientsEmail, long clientsPh1, long clientsPh2, String clientsAddress, String clientsTIN, String clientsServiceNo, String clientType) {
//
//        if (clientPKeyDB != -1) {
//            try {
//                if (!clientName.equals("")) {
//                    session = HibernateUtil.getSessionFactory().openSession();
//                    Criteria clientUpdateCriteria = session.createCriteria(ClientTable.class);
//                    clientUpdateCriteria.add(Restrictions.eq("clientIdPk", clientPKeyDB));
//                    Iterator clientUpdateItr = clientUpdateCriteria.list().iterator();
//                    if (clientUpdateItr.hasNext()) {
//                        clientTable = (ClientTable) clientUpdateItr.next();
//
//                        // Setting Client's Data to be stored
//                        clientTable.setClientName(clientName);
//                        clientTable.setDueSaleAmount(clientsSaleDue);
//                        clientTable.setDuePurchaseAmount(clientsPurDue);
//                        clientTable.setClientEmail(clientsEmail);
//                        clientTable.setClientPhone1(clientsPh1);
//                        clientTable.setClientPhone2(clientsPh2);
//                        clientTable.setClientAddress(clientsAddress);
//                        clientTable.setClientTin(clientsTIN);
//                        clientTable.setClientServiceNumber(clientsServiceNo);
//                        clientTable.setClientType(clientType);
//
//                        transaction = session.beginTransaction();
//                        session.update(clientTable);
//                        transaction.commit();
//                        System.out.println("Client Table Updated");
//                    } else {
//                        System.out.println("No Record Found");
//                    }
//                } else {
//                    System.out.println("Please Enter Valid Client Name");
//                }
//            } catch (Exception e) {
//                if (transaction != null) {
//                    try {
//                        transaction.rollback();
//                    } catch (TransactionException te) {
//                        te.printStackTrace();
//                    }
//                }
//            } finally {
//                if (session.isOpen()) {
//                    session.close();
//                }
//            }
//        } else {
//            System.out.println("No Row selected");
//        }
//
//    }

//    public void deleteClientStockEntry(long clientPKeyDB) {
//        if (clientPKeyDB != -1) {
//            try {
//                session = HibernateUtil.getSessionFactory().openSession();
//                Criteria clientDeleteCriteria = session.createCriteria(ClientTable.class);
//                clientDeleteCriteria.add(Restrictions.eq("clientIdPk", clientPKeyDB));
//                Iterator clientDeleteItr = clientDeleteCriteria.list().iterator();
//                if (clientDeleteItr.hasNext()) {
//                    clientTable = (ClientTable) clientDeleteItr.next();
//                    if (clientTable.getBillListSale().isEmpty()
//                            && clientTable.getBillListPurchase().isEmpty()
//                            && clientTable.getChallanListSale().isEmpty()
//                            && clientTable.getChallanListPurchase().isEmpty()
//                            && clientTable.getPaymentByClientList().isEmpty()
//                            && clientTable.getPaymentByUsList().isEmpty()) {
//
//                        transaction = session.beginTransaction();
//                        session.delete(clientTable);
//                        transaction.commit();
//                        System.out.println("Client Deleted");
//                    } else {
//                        System.out.println("This Client is associated with Bills, and cannot be deleted");
//                    }
//                } else {
//                    System.out.println("No Such Client found");
//                }
//            } catch (Exception e) {
//                if (transaction != null) {
//                    try {
//                        transaction.rollback();
//                    } catch (TransactionException te) {
//                        te.printStackTrace();
//                    }
//                }
//            } finally {
//                if (session.isOpen()) {
//                    session.close();
//                }
//            }
//        } else {
//            System.out.println("No Row selected");
//        }
//    }

//    // Bill Prefix Modification
//    public void newBillPrefixStockEntry(String billPrefix, String billName, String ourEmail, String ourTin,
//            String ourDescription, String ourAddress, String ourServiceNo, long ourPh1, long ourPh2,
//            String bankAcc1, String ifsc1, String bankAcc2, String ifsc2) {
//
//        try {
//            if (!billPrefix.equals("")) {
////             Setting BillPrefix Data to be stored
//                billPrifixTable.setBillPrefix(billPrefix);
//                billPrifixTable.setBillName(billName);
//                billPrifixTable.setEmail(ourEmail);
//                billPrifixTable.setBillTin(ourTin);
//                billPrifixTable.setOurDescription(ourDescription);
//                billPrifixTable.setBillAddress(ourAddress);
//                billPrifixTable.setBillServiceNo(ourServiceNo);
//                billPrifixTable.setBillPhone1(ourPh1);
//                billPrifixTable.setBillPhone2(ourPh2);
//                billPrifixTable.setBankAccount1(bankAcc1);
//                billPrifixTable.setIfscBankAccount1(ifsc1);
//                billPrifixTable.setBankAccount2(bankAcc2);
//                billPrifixTable.setIfscBankAccount2(ifsc2);
//
//                session = HibernateUtil.getSessionFactory().openSession();
//                transaction = session.beginTransaction();
//                session.save(billPrifixTable);
//                transaction.commit();
//                System.out.println("Bill Prefix Details Saved");
//            } else {
//                System.out.println("Please Enter Valid Bill Prefix");
//            }
//        } catch (ConstraintViolationException cve) {
//            cve.printStackTrace();
//            System.out.println("Bill Prefix Already Exist, Duplicate Item Not Allowed.");
//        } catch (HibernateException | NoSuchElementException | NumberFormatException hibExp) {
//            if (transaction != null) {
//                try {
//                    transaction.rollback();
//                } catch (TransactionException te) {
//                    te.printStackTrace();
//                }
//            }
//            System.out.println("Error");
//            hibExp.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Problem");
//        } finally {
//            if (session.isOpen()) {
//                session.close();
//            }
//        }
//
//    }
//
//    public void updateBillPrefixStockEntry(long billPrefixPKeyDB, String billPrefix, String billName, String ourEmail,
//            String ourTin, String ourDescription, String ourAddress, String ourServiceNo, long ourPh1, long ourPh2,
//            String bankAcc1, String ifsc1, String bankAcc2, String ifsc2) {
//
//        if (billPrefixPKeyDB != -1) {
//            try {
//                if (!billPrefix.equals("")) {
//                    session = HibernateUtil.getSessionFactory().openSession();
//                    Criteria billPrefixUpdateCriteria = session.createCriteria(BillPrefixTable.class);
//                    billPrefixUpdateCriteria.add(Restrictions.eq("billPrefixIdPk", billPrefixPKeyDB));
//                    Iterator billPrefixUpdateItr = billPrefixUpdateCriteria.list().iterator();
//                    if (billPrefixUpdateItr.hasNext()) {
//                        billPrifixTable = (BillPrefixTable) billPrefixUpdateItr.next();
//
//                        // Setting BillPrefix Data to be stored
//                        billPrifixTable.setBillPrefix(billPrefix);
//                        billPrifixTable.setBillName(billName);
//                        billPrifixTable.setEmail(ourEmail);
//                        billPrifixTable.setBillTin(ourTin);
//                        billPrifixTable.setOurDescription(ourDescription);
//                        billPrifixTable.setBillAddress(ourAddress);
//                        billPrifixTable.setBillServiceNo(ourServiceNo);
//                        billPrifixTable.setBillPhone1(ourPh1);
//                        billPrifixTable.setBillPhone2(ourPh2);
//                        billPrifixTable.setBankAccount1(bankAcc1);
//                        billPrifixTable.setIfscBankAccount1(ifsc1);
//                        billPrifixTable.setBankAccount2(bankAcc2);
//                        billPrifixTable.setIfscBankAccount2(ifsc2);
//
//                        transaction = session.beginTransaction();
//                        session.update(billPrifixTable);
//                        transaction.commit();
//                        System.out.println("Bill Prefix Updated");
//                    } else {
//                        System.out.println("No Record Found");
//                    }
//                } else {
//                    System.out.println("Please Enter Valid Bill Prefix");
//                }
//            } catch (ConstraintViolationException cve) {
//                cve.printStackTrace();
//                System.out.println("Bill Prefix Already Exist, Duplicate Item Not Allowed.");
//            } catch (Exception e) {
//                if (transaction != null) {
//                    try {
//                        transaction.rollback();
//                    } catch (TransactionException te) {
//                        te.printStackTrace();
//                    }
//                }
//            } finally {
//                if (session.isOpen()) {
//                    session.close();
//                }
//            }
//        } else {
//            System.out.println("No Row selected");
//        }
//    }
//
//    public void deleteBillPrefixStockEntry(long billPrefixPKeyDB) {
//        if (billPrefixPKeyDB != -1) {
//            try {
//                session = HibernateUtil.getSessionFactory().openSession();
//                Criteria billPrefixDeleteCriteria = session.createCriteria(BillPrefixTable.class);
//                billPrefixDeleteCriteria.add(Restrictions.eq("billPrefixIdPk", billPrefixPKeyDB));
//                Iterator billPrefixDeleteItr = billPrefixDeleteCriteria.list().iterator();
//                if (billPrefixDeleteItr.hasNext()) {
//                    billPrifixTable = (BillPrefixTable) billPrefixDeleteItr.next();
//                    if (billPrifixTable.getBillTableSalelist().isEmpty()
//                            && billPrifixTable.getBillTablePurchaselist().isEmpty()
//                            && billPrifixTable.getChallanTableSalelist().isEmpty()
//                            && billPrifixTable.getChallanTablePurchaselist().isEmpty()) {
//
//                        transaction = session.beginTransaction();
//                        session.delete(billPrifixTable);
//                        transaction.commit();
//                        System.out.println("Bill Prefix Details Deleted");
//                    } else {
//                        System.out.println("This Bill Prefix is associated with Bills, and cannot be deleted");
//                    }
//                } else {
//                    System.out.println("No Such Bill Prefix found");
//                }
//            } catch (Exception e) {
//                if (transaction != null) {
//                    try {
//                        transaction.rollback();
//                    } catch (TransactionException te) {
//                        te.printStackTrace();
//                    }
//                }
//            } finally {
//                if (session.isOpen()) {
//                    session.close();
//                }
//            }
//        } else {
//            System.out.println("No Row selected");
//        }
//    }

    // Login Details Modification
//    public void newAuthenticationStockEntry(String userName, String password, String userType) {
//        try {
//            if (!userName.equals("")) {
//
////             Setting ItemTable Data to be stored
//                authentication.setUserName(userName);
//                authentication.setUserPsw(password);
//                authentication.setUserType(userType);
//
//                session = HibernateUtil.getSessionFactory().openSession();
//                transaction = session.beginTransaction();
//                session.save(authentication);
//                transaction.commit();
//                System.out.println("Login Details Saved");
//            } else {
//                System.out.println("Please Enter Valid Username");
//            }
//        } catch (ConstraintViolationException cve) {
//            cve.printStackTrace();
//            System.out.println("Login Data Already Exist, Duplicate Item Not Allowed.");
//        } catch (HibernateException | NoSuchElementException | NumberFormatException hibExp) {
//            if (transaction != null) {
//                try {
//                    transaction.rollback();
//                } catch (TransactionException te) {
//                    te.printStackTrace();
//                }
//            }
//            System.out.println("Error");
//            hibExp.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Problem");
//        } finally {
//            if (session.isOpen()) {
//                session.close();
//            }
//        }
//    }

//    public void updateAuthenticationStockEntry(long loginPKeyDB, String userName, String password, String userType) {
//        if (loginPKeyDB != -1) {
//            try {
//                if (!userName.equals("")) {
//                    session = HibernateUtil.getSessionFactory().openSession();
//                    Criteria authUpdateCriteria = session.createCriteria(Authentication.class);
//                    authUpdateCriteria.add(Restrictions.eq("userIdPk", loginPKeyDB));
//                    Iterator authUpdateItr = authUpdateCriteria.list().iterator();
//                    if (authUpdateItr.hasNext()) {
//                        authentication = (Authentication) authUpdateItr.next();
//                        authentication.setUserName(userName);
//                        authentication.setUserPsw(password);
//                        authentication.setUserType(userType);
//
//                        transaction = session.beginTransaction();
//                        session.update(authentication);
//                        transaction.commit();
//                        System.out.println("Login Data Updated");
//                    } else {
//                        System.out.println("No Record Found");
//                    }
//                } else {
//                    System.out.println("Please Enter Valid Username");
//                }
//            } catch (Exception e) {
//                if (transaction != null) {
//                    try {
//                        transaction.rollback();
//                    } catch (TransactionException te) {
//                        te.printStackTrace();
//                    }
//                }
//            } finally {
//                if (session.isOpen()) {
//                    session.close();
//                }
//            }
//        } else {
//            System.out.println("No Row selected");
//        }
//    }

//    public void deleteAuthenticationStockEntry(long loginPKeyDB) {
//        if (loginPKeyDB != -1) {
//            try {
//                session = HibernateUtil.getSessionFactory().openSession();
//                Criteria authDeleteCriteria = session.createCriteria(Authentication.class);
//                authDeleteCriteria.add(Restrictions.eq("userIdPk", loginPKeyDB));
////                List authDeleteList = authDeleteCriteria.list();
////                if (authDeleteList.size() > 0) {
//                Iterator authDeleteItr = authDeleteCriteria.list().iterator();
//                if (authDeleteItr.hasNext()) {
//                    authentication = (Authentication) authDeleteItr.next();
//
//                    transaction = session.beginTransaction();
//                    session.delete(authentication);
//                    transaction.commit();
//                    System.out.println("Login Id Deleted");
//                } else {
//                    System.out.println("No Record Found");
//                }
////                } else {
////                    System.out.println("Atleast One login id must be present login successfully");
////                }
//            } catch (Exception e) {
//                if (transaction != null) {
//                    try {
//                        transaction.rollback();
//                    } catch (TransactionException te) {
//                        te.printStackTrace();
//                    }
//                }
//            } finally {
//                if (session.isOpen()) {
//                    session.close();
//                }
//            }
//        } else {
//            System.out.println("No Row selected");
//        }
//    }

}
