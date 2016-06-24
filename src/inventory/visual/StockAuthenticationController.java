/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.visual;

import inventory.hibernate.DAOFactories.AuthenticationDAOFactory;
import inventory.hibernate.entities.Authentication;
import inventory.hibernate.utils.HibernateUtil;
import inventory.operations.StockOperations;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import org.hibernate.Session;

/**
 * FXML Controller class
 *
 * @author Eddy
 */
public class StockAuthenticationController implements Initializable {

    private static String userName, password = "admin", userType = "N/A";
    private static long loginPKeyDB = -1;
    private static Session session = null;
//    private static Transaction transaction = null;
//    private static Authentication authentication = new Authentication();
    StockOperations stockOperations = new StockOperations();
//    StockOperations stockOperations = new StockOperations();

    @FXML
    private TextField txtFUserName;
    @FXML
    private TextField txtFPassword;
    @FXML
    private TextField txtFUserType;
    @FXML
    private TableView<Authentication> tvAuthentication;
    @FXML
    private TableColumn<Authentication, Number> tcSNo = new TableColumn<Authentication, Number>("#");
    @FXML
    private TableColumn<Authentication, String> tcUsernameAuthentication;
    @FXML
    private TableColumn<Authentication, String> tcPasswordAuthentication;
    @FXML
    private TableColumn<Authentication, String> tcUserTypeAuthentication;
    @FXML
    private TableColumn<Authentication, Long> tcLoginTableId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setAuthTableCellValueFactory();
        tvAuthentication.setItems(getAuthenticationObsvList());
        authenticationTableViewListener();
        tcSNo.setSortable(false);
    }

    @FXML
    private void onUserNameEntered(KeyEvent event) {
        try {
//            if (txtFUserName.getText().isEmpty()) {
//                userName = "admin";
//            } else {
            userName = txtFUserName.getText();
//            }
            System.out.println(userName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onPasswordEntered(KeyEvent event) {
        try {
            if (txtFPassword.getText().isEmpty()) {
                password = "admin";
            } else {
                password = txtFPassword.getText();
            }
            System.out.println(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onUserTypeEntered(KeyEvent event) {
        try {
            if (txtFUserType.getText().isEmpty()) {
                userType = "N/A";
            } else {
                userType = txtFUserType.getText();
            }
            System.out.println(userType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Setting CellFactory i.e. TableColumn with Hibernate Entity
    private void setAuthTableCellValueFactory() {
        //each cellValueFactory has been set according to the member variables of your entity class
        tcSNo.setCellValueFactory(column -> new ReadOnlyObjectWrapper<Number>(tvAuthentication.getItems().indexOf(column.getValue()) + 1));
        tcUsernameAuthentication.setCellValueFactory(new PropertyValueFactory("userName"));
        tcPasswordAuthentication.setCellValueFactory(new PropertyValueFactory("userPsw"));
        tcUserTypeAuthentication.setCellValueFactory(new PropertyValueFactory("userType"));
        tcLoginTableId.setCellValueFactory(new PropertyValueFactory("userIdPk"));
    }

    //here you can add all your Enseignants into a ObservableList
    private ObservableList<Authentication> getAuthenticationObsvList() {
        ObservableList<Authentication> authList = FXCollections.observableArrayList();
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Authentication> authCriteriaList = session.createCriteria(Authentication.class).list();
        for (Authentication auth : authCriteriaList) {
            authList.add(auth);
        }
        return authList;
    }

    // Listens to Row Selection of Table
    private void authenticationTableViewListener() {
        try {
            //Add change listener
            tvAuthentication.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
                //Check whether item is selected and set value of selected item to Label
                if (tvAuthentication.getSelectionModel().getSelectedIndex() != -1) {
                    userName = tvAuthentication.getSelectionModel().getSelectedItem().getUserName();
                    txtFUserName.setText(userName);
                    password = tvAuthentication.getSelectionModel().getSelectedItem().getUserPsw();
                    txtFPassword.setText(password);
                    userType = tvAuthentication.getSelectionModel().getSelectedItem().getUserType();
                    txtFUserType.setText(userType);

                    loginPKeyDB = tvAuthentication.getSelectionModel().getSelectedItem().getUserIdPk();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resetDataMembers() {
        System.out.println("Test Reset Auth");
        userName = "";
        password = "admin";
        userType = "N/A";
        loginPKeyDB = -1;
    }

    // Database Operations Calling Funcions
    public void newLoginEntry() {
        AuthenticationDAOFactory.getInstance().saveNewLoginCredential(userName, password, userType);
//        stockOperations.newAuthenticationStockEntry(userName, password, userType);
    }

    public void updateLoginEntry() {
        AuthenticationDAOFactory.getInstance().updateExistingLoginCredential(loginPKeyDB, userName, password, userType);
//        stockOperations.updateAuthenticationStockEntry(loginPKeyDB, userName, password, userType);
    }

    public void deleteLoginEntry() {
        AuthenticationDAOFactory.getInstance().deleteExistingCredential(loginPKeyDB);
//        stockOperations.deleteAuthenticationStockEntry(loginPKeyDB);
    }
    // End of Database Operations Call
}
