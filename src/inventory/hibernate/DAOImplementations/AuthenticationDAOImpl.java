/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.hibernate.DAOImplementations;

import inventory.hibernate.DAO.AuthenticationDAO;
import inventory.hibernate.entities.Authentication;
import inventory.hibernate.utils.HibernateUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Eddy
 */
public class AuthenticationDAOImpl implements AuthenticationDAO {
// Custom Variables

    Session session = null;
    Transaction transaction = null;
    private Authentication authentication = new Authentication();

    @Override
    public boolean saveNewLoginCredential(String userName, String password, String userType) {
        boolean loginDetailsSaved = false;
        try {
            if (!userName.equals("")) {

//             Setting ItemTable Data to be stored
                authentication.setUserName(userName);
                authentication.setUserPsw(password);
                authentication.setUserType(userType);

                session = HibernateUtil.getSessionFactory().openSession();
                transaction = session.beginTransaction();
                session.save(authentication);
                transaction.commit();
                loginDetailsSaved = true;
                System.out.println("Login Details Saved");
            } else {
                System.out.println("Please Enter Valid Username");
            }
        } catch (ConstraintViolationException cve) {
            cve.printStackTrace();
            System.out.println("Login Data Already Exist, Duplicate Item Not Allowed.");
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
        return loginDetailsSaved;
    }

    @Override
    public boolean updateExistingLoginCredential(long loginPKeyDB, String userName, String password, String userType) {
        boolean loginDetailsUpdated = false;
        if (loginPKeyDB != -1) {
            try {
                if (!userName.equals("")) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Criteria authUpdateCriteria = session.createCriteria(Authentication.class);
                    authUpdateCriteria.add(Restrictions.eq("userIdPk", loginPKeyDB));
                    Iterator authUpdateItr = authUpdateCriteria.list().iterator();
                    if (authUpdateItr.hasNext()) {
                        authentication = (Authentication) authUpdateItr.next();
                        authentication.setUserName(userName);
                        authentication.setUserPsw(password);
                        authentication.setUserType(userType);

                        transaction = session.beginTransaction();
                        session.merge(authentication);
                        transaction.commit();
                        loginDetailsUpdated = true;
                        System.out.println("Login Data Updated");
                    } else {
                        System.out.println("No Record Found");
                    }
                } else {
                    System.out.println("Please Enter Valid Username");
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
        return loginDetailsUpdated;
    }

    @Override
    public boolean deleteExistingCredential(long loginPKeyDB) {
        boolean loginDetailsDeleted = false;
        if (loginPKeyDB != -1) {
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                Criteria authDeleteCriteria = session.createCriteria(Authentication.class);
                authDeleteCriteria.add(Restrictions.eq("userIdPk", loginPKeyDB));
//                List authDeleteList = authDeleteCriteria.list();
//                if (authDeleteList.size() > 0) {
                Iterator authDeleteItr = authDeleteCriteria.list().iterator();
                if (authDeleteItr.hasNext()) {
                    authentication = (Authentication) authDeleteItr.next();

                    transaction = session.beginTransaction();
                    session.delete(authentication);
                    transaction.commit();
                    loginDetailsDeleted = true;
                    System.out.println("Login Id Deleted");
                } else {
                    System.out.println("No Record Found");
                }
//                } else {
//                    System.out.println("Atleast One login id must be present login successfully");
//                }
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
        return loginDetailsDeleted;
    }

    @Override
    public ArrayList<Authentication> selectAllLoginCredentials() {
        ArrayList<Authentication> loginDetailsList = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria loginDetailsSelectCriteria = session.createCriteria(Authentication.class);
            Iterator loginDetailsSelectItr = loginDetailsSelectCriteria.list().iterator();
            while (loginDetailsSelectItr.hasNext()) {
                authentication = (Authentication) loginDetailsSelectItr.next();
                loginDetailsList.add(authentication);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginDetailsList;
    }

    @Override
    public Authentication selectLoginCredentialWithName(String userName) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria loginSelectCriteria = session.createCriteria(Authentication.class);
            loginSelectCriteria.add(Restrictions.eq("userName", userName));
            Iterator loginSelectItr = loginSelectCriteria.list().iterator();
            if (loginSelectItr.hasNext()) {
                authentication = (Authentication) loginSelectItr.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authentication;
    }

    @Override
    public Authentication selectLoginCredentialViaId(long loginPKeyDB) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            authentication = (Authentication) session.get(Authentication.class, loginPKeyDB);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authentication;
    }

//    @Override
//    public List getAllLoginCredentialNames() {
//        List loginUserNames = null;
//        try {
//            session = HibernateUtil.getSessionFactory().openSession();
//            Criteria loginDetailSelectCriteria = session.createCriteria(Authentication.class);
////            ProjectionList authProjList = Projections.projectionList();
//            Projection loginUserNameProj = Projections.property("userName");
////            authProjList.add(authNameProj);
//            loginDetailSelectCriteria.setProjection(loginUserNameProj);
//            loginUserNames = loginDetailSelectCriteria.list();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return loginUserNames;
//    }
//
}
