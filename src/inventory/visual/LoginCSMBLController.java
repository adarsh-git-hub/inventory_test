/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.visual;

import inventory.hibernate.entities.Authentication;
import inventory.hibernate.utils.HibernateUtil;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 * FXML Controller class
 *
 * @author Eddy
 */
public class LoginCSMBLController implements Initializable {

    // custom variables
    Session session;

    @FXML
    private TextField txtFUserName;
    @FXML
    private PasswordField pswFPassword;
    @FXML
    private Button btnLogIn;
    @FXML
    private Label lblLoginStatus;

//    public static String dashboardScreenId = "dashboardScreenId";
//    public static String dashboardScreenFile = "dashboard.fxml";
//    public static String saleScreenId = "saleScreenId";
//    public static String saleScreenFile = "sale.fxml";
//    public static String screen3ID = "screen3";
//    public static String screen3File = "Screen3.fxml";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

//    public void setAllPanes(){
//    PanesController paneContainer = new PanesController();
//        System.err.println(paneContainer.loadPane(dashboardScreenId, dashboardScreenFile));
//        System.err.println(paneContainer.loadPane(saleScreenId, saleScreenFile));
////        basePanesContainer.setPane(screen1ID);
//
////        paneContainer.setPane(dashboardScreenId);
//}
    @FXML
    private void onLoginPressed(ActionEvent event) {
        try {

            String uid = txtFUserName.getText();
            String psw = pswFPassword.getText();
            session = HibernateUtil.getSessionFactory().openSession();
            long loginIdCount = (long) session.createCriteria(Authentication.class).setProjection(Projections.rowCount()).uniqueResult();
            System.out.println(loginIdCount);
            Authentication authentication = new Authentication();
            if (loginIdCount < 1) {
                authentication.setUserName("admin");
                authentication.setUserPsw("admin");
                authentication.setUserType("admin");
                session.beginTransaction();
                session.save(authentication);
                session.getTransaction().commit();
            }
            if (session.isOpen()) {
                session.close();
            }
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria userDetailsCriteria = session.createCriteria(Authentication.class);
            userDetailsCriteria.add(Restrictions.eq("userName", uid));

            Iterator userItr = userDetailsCriteria.list().iterator();
//            if (userItr.hasNext()) {

                    Inventory.rootParent = null;
                    Inventory.rootParent = FXMLLoader.load(getClass().getResource("base.fxml"));
                    Inventory.scene = null;
                    Inventory.scene = new Scene(Inventory.rootParent);
                    Inventory.stage.setScene(Inventory.scene);
                    Inventory.stage.setResizable(true);
                    Inventory.stage.setMaximized(true);
                    Inventory.stage.setTitle("CSM Inventory Management");
                    Inventory.stage.show();

                    Inventory.stage.show();
//                } else {
//                    txtFUserName.requestFocus();
//                    txtFUserName.setText("");
//                    pswFPassword.setText("");
//                    lblLoginStatus.setText("Invalid Password");
//                }
//            } else {
//                txtFUserName.requestFocus();
//                txtFUserName.setText("");
//                pswFPassword.setText("");
//                lblLoginStatus.setText("Invalid User");
//
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
