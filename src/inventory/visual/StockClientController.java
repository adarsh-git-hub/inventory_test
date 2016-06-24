/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.visual;

import inventory.executors.TextFieldDataFilter;
import inventory.hibernate.DAOFactories.ClientDAOFactory;
import inventory.hibernate.entities.ClientTable;
import inventory.hibernate.utils.HibernateUtil;
import inventory.operations.StockOperations;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
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
public class StockClientController implements Initializable {

    // Custom Variables
    private Session session = null;

    private static String clientName, clientsEmail = "N/A", clientsAddress = "N/A",
            clientsTIN = "N/A", clientsServiceNo = "N/A", clientType = "BOTH";
    private static double clientsPurDue = 0, clientsSaleDue = 0;
    private static long clientsPh1 = 0, clientsPh2 = 0, clientPKeyDB = -1;

    StockOperations stockOperations = new StockOperations();
    TextFieldDataFilter textFieldDataFilter = new TextFieldDataFilter();

    @FXML
    private TextField txtFClientName;
    @FXML
    private TextField txtFClientsEmail;
    @FXML
    private TextField txtFClientsPh1;
    @FXML
    private TextField txtFClientsPh2;
    @FXML
    private TextField txtFClientsAddress;
    @FXML
    private TextField txtFClientsTIN;
    @FXML
    private TextField txtFClientsServiceNo;
    @FXML
    private TextField txtFPurchaseDueAmount;
    @FXML
    private TextField txtFSaleDueAmount;
    @FXML
    private ComboBox<?> comboClientType;
    @FXML
    private TableView<ClientTable> tvClient;
    @FXML
    private TableColumn<ClientTable, Number> tcSNo = new TableColumn<ClientTable, Number>("#");
    @FXML
    private TableColumn<ClientTable, String> tcClientName;
    @FXML
    private TableColumn<ClientTable, String> tcClientType;
    @FXML
    private TableColumn<ClientTable, Double> tcClientSaleDue;
    @FXML
    private TableColumn<ClientTable, Double> tcClientPurchaseDue;
    @FXML
    private TableColumn<ClientTable, Long> tcClientsPh1;
    @FXML
    private TableColumn<ClientTable, Long> tcClientsPh2;
    @FXML
    private TableColumn<ClientTable, String> tcClientsEmail;
    @FXML
    private TableColumn<ClientTable, String> tcClientsAddress;
    @FXML
    private TableColumn<ClientTable, String> tcClientsTIN;
    @FXML
    private TableColumn<ClientTable, String> tcClientsServiceNo;
    @FXML
    private TableColumn<ClientTable, Long> tcClientId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setClientTableCellValueFactory();
        tvClient.setItems(getClientObsvList());
        clientTableViewListener();
        tcSNo.setSortable(false);
        validateTextField();
    }

    @FXML
    private void onClientsNameEntered(KeyEvent event) {
        try {
//            if (txtFClientName.getText().isEmpty()) {
//                clientName = "N/A";
//            } else {
            clientName = txtFClientName.getText();
//            }
            System.out.println(clientName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onClientsEmailEntered(KeyEvent event) {
        try {
            if (txtFClientsEmail.getText().isEmpty()) {
                clientsEmail = "N/A";
            } else {
                clientsEmail = txtFClientsEmail.getText();
            }
            System.out.println(clientsEmail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onClientsPh1Entered(KeyEvent event) {
        try {
            if (txtFClientsPh1.getText().isEmpty()) {
                clientsPh1 = 0;
            } else {
                clientsPh1 = Long.parseLong(txtFClientsPh1.getText());
            }
            System.out.println(clientsPh1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onClientsPh2Entered(KeyEvent event) {
        try {
            if (txtFClientsPh2.getText().isEmpty()) {
                clientsPh2 = 0;
            } else {
                clientsPh2 = Long.parseLong(txtFClientsPh2.getText());
            }
            System.out.println(clientsPh2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onClientAddressEntered(KeyEvent event) {
        try {
            if (txtFClientsAddress.getText().isEmpty()) {
                clientsAddress = "N/A";
            } else {
                clientsAddress = txtFClientsAddress.getText();
            }
            System.out.println(clientsAddress);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onClientTINEntered(KeyEvent event) {
        try {
            if (txtFClientsTIN.getText().isEmpty()) {
                clientsTIN = "N/A";
            } else {
                clientsTIN = txtFClientsTIN.getText();
            }
            System.out.println(clientsTIN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onClientsServiceNoEntered(KeyEvent event) {
        try {
            if (txtFClientsServiceNo.getText().isEmpty()) {
                clientsServiceNo = "N/A";
            } else {
                clientsServiceNo = txtFClientsServiceNo.getText();
            }
            System.out.println(clientsServiceNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onSaleDueEntered(KeyEvent event) {
        try {
            if (txtFSaleDueAmount.getText().isEmpty()) {
                clientsSaleDue = 0;
            } else {
                clientsSaleDue = Double.parseDouble(txtFSaleDueAmount.getText());
            }
            System.out.println(clientsSaleDue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onPurDueEntered(KeyEvent event) {
        try {
            if (txtFPurchaseDueAmount.getText().isEmpty()) {
                clientsPurDue = 0;
            } else {
                clientsPurDue = Double.parseDouble(txtFPurchaseDueAmount.getText());
            }
            System.out.println(clientsPurDue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onClientTypeChanged(ActionEvent event) {
        try {
            if (comboClientType.getSelectionModel().isEmpty()) {
                clientType = "BOTH";
            } else {
                clientType = comboClientType.getSelectionModel().getSelectedItem().toString();
            }
            System.out.println(clientType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Setting CellFactory i.e. TableColumn with Hibernate Entity
    private void setClientTableCellValueFactory() {
        //each cellValueFactory has been set according to the member variables of your entity class
        tcSNo.setCellValueFactory(column -> new ReadOnlyObjectWrapper<Number>(tvClient.getItems().indexOf(column.getValue()) + 1));
        tcClientId.setCellValueFactory(new PropertyValueFactory("clientIdPk"));
        tcClientName.setCellValueFactory(new PropertyValueFactory("clientName"));
        tcClientSaleDue.setCellValueFactory(new PropertyValueFactory("dueSaleAmount"));
        tcClientPurchaseDue.setCellValueFactory(new PropertyValueFactory("duePurchaseAmount"));
        tcClientType.setCellValueFactory(new PropertyValueFactory("clientType"));
        tcClientsAddress.setCellValueFactory(new PropertyValueFactory("clientAddress"));
        tcClientsEmail.setCellValueFactory(new PropertyValueFactory("clientEmail"));
        tcClientsPh1.setCellValueFactory(new PropertyValueFactory("clientPhone1"));
        tcClientsPh2.setCellValueFactory(new PropertyValueFactory("clientPhone2"));
        tcClientsServiceNo.setCellValueFactory(new PropertyValueFactory("clientServiceNumber"));
        tcClientsTIN.setCellValueFactory(new PropertyValueFactory("clientTin"));
    }

    //here you can add all your Enseignants into a ObservableList
    private ObservableList<ClientTable> getClientObsvList() {
        ObservableList<ClientTable> clientList = FXCollections.observableArrayList();
        session = HibernateUtil.getSessionFactory().openSession();
        List<ClientTable> clientCriteriaList = session.createCriteria(ClientTable.class).list();
        for (ClientTable clients : clientCriteriaList) {
            clientList.add(clients);
        }
        return clientList;
    }

    // Listens to Row Selection of Table
    private void clientTableViewListener() {
        try {
            //Add change listener
            tvClient.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
                //Check whether item is selected and set value of selected item to Label
                if (tvClient.getSelectionModel().getSelectedIndex() != -1) {
                    clientName = tvClient.getSelectionModel().getSelectedItem().getClientName();
                    txtFClientName.setText(clientName);

                    clientsEmail = tvClient.getSelectionModel().getSelectedItem().getClientEmail();
                    txtFClientsEmail.setText(clientsEmail);

                    clientsAddress = tvClient.getSelectionModel().getSelectedItem().getClientAddress();
                    txtFClientsAddress.setText(clientsAddress);

                    clientsTIN = tvClient.getSelectionModel().getSelectedItem().getClientTin();
                    txtFClientsTIN.setText(clientsTIN);

                    clientsServiceNo = tvClient.getSelectionModel().getSelectedItem().getClientServiceNumber();
                    txtFClientsServiceNo.setText(clientsServiceNo);

                    clientType = tvClient.getSelectionModel().getSelectedItem().getClientType();
//                    comboClientType;

                    clientsPurDue = tvClient.getSelectionModel().getSelectedItem().getDuePurchaseAmount();
                    txtFPurchaseDueAmount.setText(String.valueOf(clientsPurDue));

                    clientsSaleDue = tvClient.getSelectionModel().getSelectedItem().getDueSaleAmount();
                    txtFSaleDueAmount.setText(String.valueOf(clientsSaleDue));

                    clientsPh1 = tvClient.getSelectionModel().getSelectedItem().getClientPhone1();
                    txtFClientsPh1.setText(String.valueOf(clientsPh1));

                    clientsPh2 = tvClient.getSelectionModel().getSelectedItem().getClientPhone2();
                    txtFClientsPh1.setText(String.valueOf(clientsPh1));

                    clientPKeyDB = tvClient.getSelectionModel().getSelectedItem().getClientIdPk();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validateTextField() {
        textFieldDataFilter.restrictLong(txtFClientsPh1);
        textFieldDataFilter.restrictDouble(txtFSaleDueAmount);
        textFieldDataFilter.restrictLong(txtFClientsPh2);
        textFieldDataFilter.restrictDouble(txtFPurchaseDueAmount);
    }

    void resetDataMembers() {
        System.out.println("Test Reset Client");
        clientName = "";
        clientPKeyDB = -1;
        clientsSaleDue = 0;
        clientsPurDue = 0;
        clientsEmail = "N/A";
        clientsPh1 = 0;
        clientsPh2 = 0;
        clientsAddress = "N/A";
        clientsTIN = "N/A";
        clientsServiceNo = "N/A";
        clientType = "BOTH";
    }

    public void newCliententry() {
        ClientDAOFactory.getInstance().saveNewClient(clientName, clientsSaleDue, clientsPurDue, clientsEmail, clientsPh1, clientsPh2, clientsAddress, clientsTIN, clientsServiceNo, clientType);
//        System.out.println(ClientDAOFactory.getInstance().getClientAllNames());
//        stockOperations.newClientStockEntry(clientName, clientsSaleDue, clientsPurDue, clientsEmail, clientsPh1, clientsPh2, clientsAddress, clientsTIN, clientsServiceNo, clientType);
    }

    public void updateCliententry() {
        ClientDAOFactory.getInstance().updateExistingClient(clientPKeyDB, clientName, clientsSaleDue, clientsPurDue, clientsEmail, clientsPh1, clientsPh2, clientsAddress, clientsTIN, clientsServiceNo, clientType);
//        stockOperations.updateClientStockEntry(clientPKeyDB, clientName, clientsSaleDue, clientsPurDue, clientsEmail, clientsPh1, clientsPh2, clientsAddress, clientsTIN, clientsServiceNo, clientType);
    }

    public void deleteCliententry() {
        ClientDAOFactory.getInstance().deleteExistingClient(clientPKeyDB);
//        stockOperations.deleteClientStockEntry(clientPKeyDB);
    }

}
