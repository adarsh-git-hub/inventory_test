/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.hibernate.DAOImplementations;

import inventory.hibernate.DAO.BillPrefixDAO;
import inventory.hibernate.entities.BillPrefixTable;
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
public class BillPrefixDAOImpl implements BillPrefixDAO {
// Custom Variables

    Session session = null;
    Transaction transaction = null;
    private BillPrefixTable billPrefixTable = new BillPrefixTable();

    @Override
    public boolean saveNewBillPrefix(String billPrefix, String billName, String ourEmail, String ourTin,
            String ourDescription, String ourAddress, String ourServiceNo, long ourPh1, long ourPh2,
            String bankAcc1, String ifsc1, String bankAcc2, String ifsc2) {
        boolean billPrefixSaved = false;
        try {
            if (!billPrefix.equals("")) {
//             Setting BillPrefix Data to be stored
                billPrefixTable.setBillPrefix(billPrefix);
                billPrefixTable.setBillName(billName);
                billPrefixTable.setEmail(ourEmail);
                billPrefixTable.setBillTin(ourTin);
                billPrefixTable.setOurDescription(ourDescription);
                billPrefixTable.setBillAddress(ourAddress);
                billPrefixTable.setBillServiceNo(ourServiceNo);
                billPrefixTable.setBillPhone1(ourPh1);
                billPrefixTable.setBillPhone2(ourPh2);
                billPrefixTable.setBankAccount1(bankAcc1);
                billPrefixTable.setIfscBankAccount1(ifsc1);
                billPrefixTable.setBankAccount2(bankAcc2);
                billPrefixTable.setIfscBankAccount2(ifsc2);

                session = HibernateUtil.getSessionFactory().openSession();
                transaction = session.beginTransaction();
                session.save(billPrefixTable);
                transaction.commit();
                billPrefixSaved = true;
                System.out.println("Bill Prefix Details Saved");
            } else {
                System.out.println("Please Enter Valid Bill Prefix");
            }
        } catch (ConstraintViolationException cve) {
            cve.printStackTrace();
            System.out.println("Bill Prefix Already Exist, Duplicate Item Not Allowed.");
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
        return billPrefixSaved;
    }

    @Override
    public boolean updateExistingBillPrefix(long billPrefixPKeyDB, String billPrefix, String billName, String ourEmail, String ourTin, String ourDescription, String ourAddress, String ourServiceNo, long ourPh1, long ourPh2, String bankAcc1, String ifsc1, String bankAcc2, String ifsc2) {
        boolean billPrefixUpdated = false;
        if (billPrefixPKeyDB != -1) {
            try {
                if (!billPrefix.equals("")) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Criteria billPrefixUpdateCriteria = session.createCriteria(BillPrefixTable.class);
                    billPrefixUpdateCriteria.add(Restrictions.eq("billPrefixIdPk", billPrefixPKeyDB));
                    Iterator billPrefixUpdateItr = billPrefixUpdateCriteria.list().iterator();
                    if (billPrefixUpdateItr.hasNext()) {
                        billPrefixTable = (BillPrefixTable) billPrefixUpdateItr.next();

                        // Setting BillPrefix Data to be stored
                        billPrefixTable.setBillPrefix(billPrefix);
                        billPrefixTable.setBillName(billName);
                        billPrefixTable.setEmail(ourEmail);
                        billPrefixTable.setBillTin(ourTin);
                        billPrefixTable.setOurDescription(ourDescription);
                        billPrefixTable.setBillAddress(ourAddress);
                        billPrefixTable.setBillServiceNo(ourServiceNo);
                        billPrefixTable.setBillPhone1(ourPh1);
                        billPrefixTable.setBillPhone2(ourPh2);
                        billPrefixTable.setBankAccount1(bankAcc1);
                        billPrefixTable.setIfscBankAccount1(ifsc1);
                        billPrefixTable.setBankAccount2(bankAcc2);
                        billPrefixTable.setIfscBankAccount2(ifsc2);

                        transaction = session.beginTransaction();
                        session.merge(billPrefixTable);
                        transaction.commit();
                        billPrefixUpdated = true;
                        System.out.println("Bill Prefix Updated");
                    } else {
                        System.out.println("No Record Found");
                    }
                } else {
                    System.out.println("Please Enter Valid Bill Prefix");
                }
            } catch (ConstraintViolationException cve) {
                cve.printStackTrace();
                System.out.println("Bill Prefix Already Exist, Duplicate Item Not Allowed.");
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
        return billPrefixUpdated;
    }

    @Override
    public boolean deleteExistingBillPrefix(long billPrefixPKeyDB) {
        boolean billPrefixDeleted = false;
        if (billPrefixPKeyDB != -1) {
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                Criteria billPrefixDeleteCriteria = session.createCriteria(BillPrefixTable.class);
                billPrefixDeleteCriteria.add(Restrictions.eq("billPrefixIdPk", billPrefixPKeyDB));
                Iterator billPrefixDeleteItr = billPrefixDeleteCriteria.list().iterator();
                if (billPrefixDeleteItr.hasNext()) {
                    billPrefixTable = (BillPrefixTable) billPrefixDeleteItr.next();
                    if (billPrefixTable.getBillTableSalelist().isEmpty()
                            && billPrefixTable.getBillTablePurchaselist().isEmpty()
                            && billPrefixTable.getChallanTableSalelist().isEmpty()
                            && billPrefixTable.getChallanTablePurchaselist().isEmpty()) {

                        transaction = session.beginTransaction();
                        session.delete(billPrefixTable);
                        transaction.commit();
                        billPrefixDeleted = true;
                        System.out.println("Bill Prefix Details Deleted");
                    } else {
                        System.out.println("This Bill Prefix is associated with Bills, and cannot be deleted");
                    }
                } else {
                    System.out.println("No Such Bill Prefix found");
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
        return billPrefixDeleted;
    }

    @Override
    public ArrayList<BillPrefixTable> selectAllBillPrefix() {
        ArrayList<BillPrefixTable> billPrefixList = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria billPrefixSelectCriteria = session.createCriteria(BillPrefixTable.class);
            Iterator billPrefixSelectItr = billPrefixSelectCriteria.list().iterator();
            while (billPrefixSelectItr.hasNext()) {
                billPrefixTable = (BillPrefixTable) billPrefixSelectItr.next();
                billPrefixList.add(billPrefixTable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return billPrefixList;
    }

    @Override
    public List getAllBillPrefixNames() {
        List billPrefixNames = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria billPrefixSelectCriteria = session.createCriteria(BillPrefixTable.class);
//            ProjectionList billPrefixProjList = Projections.projectionList();
            Projection billPrefixNameProj = Projections.property("billPrefix");
//            billPrefixProjList.add(billPrefixNameProj);
            billPrefixSelectCriteria.setProjection(billPrefixNameProj);
            billPrefixNames = billPrefixSelectCriteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return billPrefixNames;
    }

    @Override
    public BillPrefixTable selectBillPrefixWithName(String billPrefix) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria billPrefixSelectCriteria = session.createCriteria(BillPrefixTable.class);
            billPrefixSelectCriteria.add(Restrictions.eq("billPrefix", billPrefix));
            Iterator billPrefixSelectItr = billPrefixSelectCriteria.list().iterator();
            if (billPrefixSelectItr.hasNext()) {
                billPrefixTable = (BillPrefixTable) billPrefixSelectItr.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return billPrefixTable;
    }

    @Override
    public BillPrefixTable selectBillPrefixViaId(long billPrefixPKeyDB) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            billPrefixTable = (BillPrefixTable) session.get(BillPrefixTable.class, billPrefixPKeyDB);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return billPrefixTable;
    }

}
