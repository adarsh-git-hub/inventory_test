/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.hibernate.DAOImplementations;

import inventory.hibernate.DAO.ClientDAO;
import inventory.hibernate.entities.ClientTable;
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
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Eddy
 */
public class ClientDAOImpl implements ClientDAO {

    // Custom Variables
    Session session = null;
    Transaction transaction = null;
    private ClientTable clientTable = new ClientTable();

    @Override
    public boolean saveNewClient(String clientName, double clientsSaleDue, double clientsPurDue, String clientsEmail, long clientsPh1, long clientsPh2, String clientsAddress, String clientsTIN, String clientsServiceNo, String clientType) {
        boolean clientSaved = false;
        try {
            if (!clientName.equals("")) {
//             Setting Client's Data to be stored
                clientTable.setClientName(clientName);
                clientTable.setDueSaleAmount(clientsSaleDue);
                clientTable.setDuePurchaseAmount(clientsPurDue);
                clientTable.setClientEmail(clientsEmail);
                clientTable.setClientPhone1(clientsPh1);
                clientTable.setClientPhone2(clientsPh2);
                clientTable.setClientAddress(clientsAddress);
                clientTable.setClientTin(clientsTIN);
                clientTable.setClientServiceNumber(clientsServiceNo);
                clientTable.setClientType(clientType);

                session = HibernateUtil.getSessionFactory().openSession();
                transaction = session.beginTransaction();
                session.save(clientTable);
                transaction.commit();
                clientSaved = true;
//                System.out.println("Client's Details Saved");
            } else {
                System.out.println("Please Enter Valid Client Name");
            }
        } catch (ConstraintViolationException cve) {
            cve.printStackTrace();
            System.out.println("Client already Exist, Duplicate Item Not Allowed.");
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
        return clientSaved;
    }

    @Override
    public boolean updateExistingClient(long clientPKeyDB, String clientName, double clientsSaleDue, double clientsPurDue, String clientsEmail, long clientsPh1, long clientsPh2, String clientsAddress, String clientsTIN, String clientsServiceNo, String clientType) {
        boolean clientUpdated = false;
        if (clientPKeyDB != -1) {
            try {
                if (!clientName.equals("")) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Criteria clientUpdateCriteria = session.createCriteria(ClientTable.class);
                    clientUpdateCriteria.add(Restrictions.eq("clientIdPk", clientPKeyDB));
                    Iterator clientUpdateItr = clientUpdateCriteria.list().iterator();
                    if (clientUpdateItr.hasNext()) {
                        clientTable = (ClientTable) clientUpdateItr.next();

                        // Setting Client's Data to be stored
                        clientTable.setClientName(clientName);
                        clientTable.setDueSaleAmount(clientsSaleDue);
                        clientTable.setDuePurchaseAmount(clientsPurDue);
                        clientTable.setClientEmail(clientsEmail);
                        clientTable.setClientPhone1(clientsPh1);
                        clientTable.setClientPhone2(clientsPh2);
                        clientTable.setClientAddress(clientsAddress);
                        clientTable.setClientTin(clientsTIN);
                        clientTable.setClientServiceNumber(clientsServiceNo);
                        clientTable.setClientType(clientType);

                        transaction = session.beginTransaction();
                        session.merge(clientTable);
                        transaction.commit();
                        clientUpdated = true;
//                        System.out.println("Client Table Updated");
                    } else {
                        System.out.println("No Record Found");
                    }
                } else {
                    System.out.println("Please Enter Valid Client Name");
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
        return clientUpdated;
    }

    @Override
    public boolean deleteExistingClient(long clientPKeyDB) {
        boolean clientDeleted = false;
        if (clientPKeyDB != -1) {
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                Criteria clientDeleteCriteria = session.createCriteria(ClientTable.class);
                clientDeleteCriteria.add(Restrictions.eq("clientIdPk", clientPKeyDB));
                Iterator clientDeleteItr = clientDeleteCriteria.list().iterator();
                if (clientDeleteItr.hasNext()) {
                    clientTable = (ClientTable) clientDeleteItr.next();
                    if (clientTable.getBillListSale().isEmpty()
                            && clientTable.getBillListPurchase().isEmpty()
                            && clientTable.getChallanListSale().isEmpty()
                            && clientTable.getChallanListPurchase().isEmpty()
                            && clientTable.getPaymentByClientList().isEmpty()
                            && clientTable.getPaymentByUsList().isEmpty()) {

                        transaction = session.beginTransaction();
                        session.delete(clientTable);
                        transaction.commit();
                        System.out.println("Client Deleted");
                        clientDeleted = true;
                    } else {
                        System.out.println("This Client is associated with Bills, and cannot be deleted");
                    }
                } else {
                    System.out.println("No Such Client found");
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
        return clientDeleted;
    }

    @Override
    public ArrayList<ClientTable> selectAllClients() {
        ArrayList<ClientTable> clientList = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria clientSelectCriteria = session.createCriteria(ClientTable.class);
            Iterator clientSelectItr = clientSelectCriteria.list().iterator();
            while (clientSelectItr.hasNext()) {
                clientTable = (ClientTable) clientSelectItr.next();
                clientList.add(clientTable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientList;
    }

    @Override
    public List getAllClientNames() {
        List clientNames = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria clientSelectCriteria = session.createCriteria(ClientTable.class);
//            ProjectionList clientProjList = Projections.projectionList();
            Projection clientNameProj = Projections.property("clientName");
//            Projection clientPh1Proj = Projections.property("clientPhone1");
//            Projection clientAddressProj = Projections.property("clientName");
//            clientProjList.add(clientNameProj);
//            clientProjList.add(clientPh1Proj);
//            clientProjList.add(clientAddressProj);
            clientSelectCriteria.setProjection(clientNameProj);
            clientNames = clientSelectCriteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientNames;
    }

    @Override
    public ClientTable selectClientWithName(String clientName) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria clientSelectCriteria = session.createCriteria(ClientTable.class);
            clientSelectCriteria.add(Restrictions.eq("clientName", clientName));
            Iterator clientSelectItr = clientSelectCriteria.list().iterator();
            if (clientSelectItr.hasNext()) {
                clientTable = (ClientTable) clientSelectItr.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientTable;
    }

    @Override
    public ClientTable selectClientViaId(long clientPKeyDB) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            clientTable = (ClientTable) session.get(ClientTable.class, clientPKeyDB);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientTable;
    }
    
}
