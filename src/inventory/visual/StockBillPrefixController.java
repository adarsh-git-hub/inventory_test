/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.visual;

import inventory.executors.TextFieldDataFilter;
import inventory.hibernate.DAOFactories.BillPrefixDAOFactory;
import inventory.hibernate.entities.BillPrefixTable;
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
public class StockBillPrefixController implements Initializable {

    private static String billPrefix, billName = "N/A", ourEmail = "N/A", ourTin = "N/A", ourDescription = "N/A",
            ourAddress = "N/A", ourServiceNo = "N/A", bankAcc1 = "N/A",
            ifsc1 = "N/A", bankAcc2 = "N/A", ifsc2 = "N/A";

    private static long ourPh1 = 0, ourPh2 = 0, billPrefixPKeyDB = -1;

    private static Session session = null;
    TextFieldDataFilter textFieldDataFilter = new TextFieldDataFilter();
    StockOperations stockOperations = new StockOperations();

    @FXML
    private TextField txtFBillPrefix;
    @FXML
    private TextField txtFBillName;
    @FXML
    private TextField txtFOurEmail;
    @FXML
    private TextField txtFOurTin;
    @FXML
    private TextField txtFOurDescription;
    @FXML
    private TextField txtFOurAddress;
    @FXML
    private TextField txtFOurServiceNo;
    @FXML
    private TextField txtFOurPh1;
    @FXML
    private TextField txtFOurPh2;
    @FXML
    private TextField txtFBankAcc1;
    @FXML
    private TextField txtFIfsc1;
    @FXML
    private TextField txtFBankAcc2;
    @FXML
    private TextField txtFIfsc2;
    @FXML
    private TableView<BillPrefixTable> tvBillPrefix;
    @FXML
    private TableColumn<BillPrefixTable, Number> tcSNo = new TableColumn<BillPrefixTable, Number>("#");
    @FXML
    private TableColumn<BillPrefixTable, String> tcPrefix;
    @FXML
    private TableColumn<BillPrefixTable, String> tcBillName;
    @FXML
    private TableColumn<BillPrefixTable, Long> tcPh1;
    @FXML
    private TableColumn<BillPrefixTable, Long> tcPh2;
    @FXML
    private TableColumn<BillPrefixTable, String> tcEmail;
    @FXML
    private TableColumn<BillPrefixTable, String> tcAddress;
    @FXML
    private TableColumn<BillPrefixTable, String> tcDescription;
    @FXML
    private TableColumn<BillPrefixTable, String> tcTIN;
    @FXML
    private TableColumn<BillPrefixTable, String> tcServiceNo;
    @FXML
    private TableColumn<BillPrefixTable, String> tcBankAcc1;
    @FXML
    private TableColumn<BillPrefixTable, String> tcIFSC1;
    @FXML
    private TableColumn<BillPrefixTable, String> tcBankAcc2;
    @FXML
    private TableColumn<BillPrefixTable, String> tcIFSC2;
    @FXML
    private TableColumn<BillPrefixTable, Long> tcBillPrefixId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setBillPrefixTableCellValueFactory();
        tvBillPrefix.setItems(getBillPrefixObsvList());
        billPrefixTableViewListener();
        tcSNo.setSortable(false);
        validateTextField();
    }

    @FXML
    private void onBillPrefixEntered(KeyEvent event) {
        try {
//            if (txtFBillPrefix.getText().isEmpty()) {
//                billPrefix = "N/A";
//            } else {
            billPrefix = txtFBillPrefix.getText();
//            }
            System.out.println(billPrefix);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onBillNameEntered(KeyEvent event) {
        try {
            if (txtFBillName.getText().isEmpty()) {
                billName = "N/A";
            } else {
                billName = txtFBillName.getText();
            }
            System.out.println(billName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onEmailEntered(KeyEvent event) {
        try {
            if (txtFOurEmail.getText().isEmpty()) {
                ourEmail = "N/A";
            } else {
                ourEmail = txtFOurEmail.getText();
            }
            System.out.println(ourEmail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onTinEntered(KeyEvent event) {
        try {
            if (txtFOurTin.getText().isEmpty()) {
                ourTin = "N/A";
            } else {
                ourTin = txtFOurTin.getText();
            }
            System.out.println(ourTin);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onDescriptionEntered(KeyEvent event) {
        try {
            if (txtFOurDescription.getText().isEmpty()) {
                ourDescription = "N/A";
            } else {
                ourDescription = txtFOurDescription.getText();
            }
            System.out.println(ourDescription);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onAddressEntered(KeyEvent event) {
        try {
            if (txtFOurAddress.getText().isEmpty()) {
                ourAddress = "N/A";
            } else {
                ourAddress = txtFOurAddress.getText();
            }
            System.out.println(ourAddress);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onSNEntered(KeyEvent event) {
        try {
            if (txtFOurServiceNo.getText().isEmpty()) {
                ourServiceNo = "N/A";
            } else {
                ourServiceNo = txtFOurServiceNo.getText();
            }
            System.out.println(ourServiceNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onPh1Entered(KeyEvent event) {
        try {
            if (txtFOurPh1.getText().isEmpty()) {
                ourPh1 = 0;
            } else {
                ourPh1 = Long.parseLong(txtFOurPh1.getText());
            }
            System.out.println(ourPh1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onPh2Entered(KeyEvent event) {
        try {
            if (txtFOurPh2.getText().isEmpty()) {
                ourPh2 = 0;
            } else {
                ourPh2 = Long.parseLong(txtFOurPh2.getText());
            }
            System.out.println(ourPh2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onBankAcc1Entered(KeyEvent event) {
        try {
            if (txtFBankAcc1.getText().isEmpty()) {
                bankAcc1 = "N/A";
            } else {
                bankAcc1 = txtFBankAcc1.getText();
            }
            System.out.println(bankAcc1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onIfsc1Entered(KeyEvent event) {
        try {
            if (txtFIfsc1.getText().isEmpty()) {
                ifsc1 = "N/A";
            } else {
                ifsc1 = txtFIfsc1.getText();
            }
            System.out.println(ifsc1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onBankAcc2Entered(KeyEvent event) {
        try {
            if (txtFBankAcc2.getText().isEmpty()) {
                bankAcc2 = "N/A";
            } else {
                bankAcc2 = txtFBankAcc2.getText();
            }
            System.out.println(bankAcc2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onIfsc2Entered(KeyEvent event) {
        try {
            if (txtFIfsc2.getText().isEmpty()) {
                ifsc2 = "N/A";
            } else {
                ifsc2 = txtFIfsc2.getText();
            }
            System.out.println(ifsc2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Setting CellFactory i.e. TableColumn with Hibernate Entity
    private void setBillPrefixTableCellValueFactory() {
        //each cellValueFactory has been set according to the member variables of your entity class
        tcSNo.setCellValueFactory(column -> new ReadOnlyObjectWrapper<Number>(tvBillPrefix.getItems().indexOf(column.getValue()) + 1));
        tcPrefix.setCellValueFactory(new PropertyValueFactory("billName"));
        tcBillName.setCellValueFactory(new PropertyValueFactory("billPrefix"));
        tcPh1.setCellValueFactory(new PropertyValueFactory("billPhone1"));
        tcPh2.setCellValueFactory(new PropertyValueFactory("billPhone2"));
        tcEmail.setCellValueFactory(new PropertyValueFactory("email"));
        tcAddress.setCellValueFactory(new PropertyValueFactory("billAddress"));
        tcDescription.setCellValueFactory(new PropertyValueFactory("ourDescription"));
        tcTIN.setCellValueFactory(new PropertyValueFactory("billTin"));
        tcServiceNo.setCellValueFactory(new PropertyValueFactory("billServiceNo"));
        tcBankAcc1.setCellValueFactory(new PropertyValueFactory("bankAccount1"));
        tcIFSC1.setCellValueFactory(new PropertyValueFactory("ifscBankAccount1"));
        tcBankAcc2.setCellValueFactory(new PropertyValueFactory("bankAccount2"));
        tcIFSC2.setCellValueFactory(new PropertyValueFactory("ifscBankAccount2"));
        tcBillPrefixId.setCellValueFactory(new PropertyValueFactory("billPrefixIdPk"));
    }

    //here you can add all your Enseignants into a ObservableList
    private ObservableList<BillPrefixTable> getBillPrefixObsvList() {
        ObservableList<BillPrefixTable> billPrefixList = FXCollections.observableArrayList();
        session = HibernateUtil.getSessionFactory().openSession();
        List<BillPrefixTable> authCriteriaList = session.createCriteria(BillPrefixTable.class).list();
        for (BillPrefixTable billPrefixTable : authCriteriaList) {
            billPrefixList.add(billPrefixTable);
        }
        return billPrefixList;
    }

    // Listens to Row Selection of Table
    private void billPrefixTableViewListener() {
        try {
            //Add change listener
            tvBillPrefix.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
                //Check whether item is selected and set value of selected item to Label
                if (tvBillPrefix.getSelectionModel().getSelectedIndex() != -1) {
                    billPrefix = tvBillPrefix.getSelectionModel().getSelectedItem().getBillPrefix();
                    txtFBillPrefix.setText(billPrefix);

                    billName = tvBillPrefix.getSelectionModel().getSelectedItem().getBillName();
                    txtFBillName.setText(billName);

                    ourEmail = tvBillPrefix.getSelectionModel().getSelectedItem().getEmail();
                    txtFOurEmail.setText(ourEmail);

                    ourTin = tvBillPrefix.getSelectionModel().getSelectedItem().getBillTin();
                    txtFOurTin.setText(ourTin);

                    ourDescription = tvBillPrefix.getSelectionModel().getSelectedItem().getOurDescription();
                    txtFOurDescription.setText(ourDescription);

                    ourAddress = tvBillPrefix.getSelectionModel().getSelectedItem().getBillAddress();
                    txtFOurAddress.setText(ourAddress);

                    ourServiceNo = tvBillPrefix.getSelectionModel().getSelectedItem().getBillServiceNo();
                    txtFOurServiceNo.setText(ourServiceNo);

                    ourPh1 = tvBillPrefix.getSelectionModel().getSelectedItem().getBillPhone1();
                    txtFOurPh1.setText(String.valueOf(ourPh1));

                    ourPh2 = tvBillPrefix.getSelectionModel().getSelectedItem().getBillPhone2();
                    txtFOurPh2.setText(String.valueOf(ourPh2));

                    bankAcc1 = tvBillPrefix.getSelectionModel().getSelectedItem().getBankAccount1();
                    txtFBankAcc1.setText(bankAcc1);

                    ifsc1 = tvBillPrefix.getSelectionModel().getSelectedItem().getIfscBankAccount1();
                    txtFIfsc1.setText(ifsc1);

                    bankAcc2 = tvBillPrefix.getSelectionModel().getSelectedItem().getBankAccount2();
                    txtFBankAcc2.setText(bankAcc2);

                    ifsc2 = tvBillPrefix.getSelectionModel().getSelectedItem().getIfscBankAccount2();
                    txtFIfsc2.setText(ifsc2);

                    billPrefixPKeyDB = tvBillPrefix.getSelectionModel().getSelectedItem().getBillPrefixIdPk();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validateTextField() {
        textFieldDataFilter.restrictLong(txtFOurPh1);
        textFieldDataFilter.restrictLong(txtFOurPh2);
    }

    public void resetDataMembers() {
        System.out.println("Test Reset BillPrefix");
        billPrefix = "";
        billName = "N/A";
        ourEmail = "N/A";
        ourTin = "N/A";
        ourDescription = "N/A";
        ourAddress = "N/A";
        ourServiceNo = "N/A";
        bankAcc1 = "N/A";
        ifsc1 = "N/A";
        bankAcc2 = "N/A";
        ifsc2 = "N/A";

        ourPh1 = 0;
        ourPh2 = 0;
        billPrefixPKeyDB = -1;
    }

    // Database Operations Calling Funcions
    public void newBillPrefixEntry() {
        BillPrefixDAOFactory.getInstance().saveNewBillPrefix(billPrefix, billName, ourEmail, ourTin, ourDescription,
                ourAddress, ourServiceNo, ourPh1, ourPh2, bankAcc1, ifsc1, bankAcc2, ifsc2);
//        stockOperations.newBillPrefixStockEntry(billPrefix, billName, ourEmail, ourTin, ourDescription, ourAddress, ourServiceNo, ourPh1, ourPh2, bankAcc1, ifsc1, bankAcc2, ifsc2);
    }

    public void updateBillPrefixEntry() {
        BillPrefixDAOFactory.getInstance().updateExistingBillPrefix(billPrefixPKeyDB, billPrefix, billName, ourEmail,
                ourTin, ourDescription, ourAddress, ourServiceNo, ourPh1, ourPh2, bankAcc1, ifsc1, bankAcc2, ifsc2);
//        stockOperations.updateBillPrefixStockEntry(billPrefixPKeyDB, billPrefix, billName, ourEmail, ourTin, ourDescription, ourAddress, ourServiceNo, ourPh1, ourPh2, bankAcc1, ifsc1, bankAcc2, ifsc2);
    }

    public void deleteBillPrefixEntry() {
        BillPrefixDAOFactory.getInstance().deleteExistingBillPrefix(billPrefixPKeyDB);
//        stockOperations.deleteBillPrefixStockEntry(billPrefixPKeyDB);
    }
    // End of Database Operations Call

}
