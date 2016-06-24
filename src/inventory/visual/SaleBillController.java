/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.visual;

import inventory.executors.AutoCompleteComboBox;
import inventory.executors.DateAndTimeProvider;
import inventory.executors.TextFieldDataFilter;
import inventory.hibernate.DAOFactories.BillPrefixDAOFactory;
import inventory.hibernate.DAOFactories.ClientDAOFactory;
import inventory.hibernate.DAOFactories.ItemDAOFactory;
import inventory.hibernate.DAOImplementations.BillPrefixDAOImpl;
import inventory.hibernate.entities.BillPrefixTable;
import inventory.hibernate.entities.ClientTable;
import inventory.hibernate.entities.ItemTable;
import inventory.hibernate.entities.SaleItemBill;
import inventory.hibernate.utils.HibernateUtil;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * FXML Controller class
 *
 * @author Eddy
 */
public class SaleBillController implements Initializable {

    // Custom Variables
    private Session session = null;
    private Transaction transaction = null;
    private ClientTable clientTable;
    private ItemTable itemTable;
    private TextFieldDataFilter textFieldDataFilter = new TextFieldDataFilter();

    private static String itemName, unit, clientsAddressSB, billPrefixSB, clientSB;
    private static long clientPhSB, saleItemBillPKeyDB = -1;
    private static double itemQty = 0, availableQty = 0, mrpSB = 0, vatSB = 0, saleRateSB = 0, saleTaxSB = 0, extraChargeSB = 0,
            itemTotal = 0, purDueSB = 0, saleDueDB = 0, grandTotalSB = 0;
    private Date geterateDate = DateAndTimeProvider.getCurrentDateTimeRaw();

    @FXML
    private TextField txtFGrandTotalSB;
    @FXML
    private Button btnNextBillSB;
    @FXML
    private ComboBox<String> comboItemName;
    @FXML
    private TextField txtFItemQty;
    @FXML
    private Label lblUnit;
    @FXML
    private TextField txtFAvailableQty;
    @FXML
    private TextField txtFMRPSB;
    @FXML
    private TextField txtFVATSB;
    @FXML
    private TextField txtFSaleRateSB;
    @FXML
    private TextField txtFSaleTaxSB;
    @FXML
    private TextField txtFExtraChargeSB;
    @FXML
    private TextField txtFItemTotal;
    @FXML
    private ComboBox<String> comboClientSB;
    @FXML
    private TextField txtFClientPh1SB;
    @FXML
    private TextField txtFClientsAddressSB;
    @FXML
    private DatePicker datePickerGenBillDateSB;
    @FXML
    private ComboBox<String> comboBillPrefixSB;
//    @FXML
//    private Button btnAddSB;
//    @FXML
//    private Button btnDeleteSB;
//    @FXML
//    private Button btnUpdateSB;
//    @FXML
//    private Button btnPrintSB;
    @FXML
    private TextField txtFPurDueSB;
    @FXML
    private TextField txtFSaleDueDB;
    @FXML
    private TableView<SaleItemBill> tvSaleBillTable;
    @FXML
    private TableColumn<ItemTable, Number> tcSNo;
    @FXML
    private TableColumn<ItemTable, String> tcItemName;
    @FXML
    private TableColumn<SaleItemBill, Double> tcQtySold;
    @FXML
    private TableColumn<ItemTable, String> tcUnit;
    @FXML
    private TableColumn<SaleItemBill, Double> tcSaleRateApplied;
    @FXML
    private TableColumn<SaleItemBill, Double> tcSaleTaxApplied;
    @FXML
    private TableColumn<SaleItemBill, Double> tcMRPApplied;
    @FXML
    private TableColumn<SaleItemBill, Double> tcVATApplied;
    @FXML
    private TableColumn<SaleItemBill, Double> tcOtherChargesApplied;
    @FXML
    private TableColumn<SaleItemBill, Double> tcItemTotal;
    @FXML
    private TableColumn<SaleItemBill, Long> tcSaleItemBillPKeyDB;
    @FXML
    private TextField txtFSaleBillRef;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        validateTextFields();

        // Client ComboBox Initialization
        comboClientSB.getItems().clear();
        comboClientSB.setItems(getClientNames());
        clientComboBoxListener();
        comboClientSB.getSelectionModel().selectFirst();
        AutoCompleteComboBox.autoCompleteComboBox(comboClientSB, AutoCompleteComboBox.AutoCompleteMode.CONTAINING);

        // ItemName ComboBox Initialization
        comboItemName.getItems().clear();
        comboItemName.setItems(getItemNames());
        itemComboBoxListener();
        comboItemName.getSelectionModel().selectFirst();
        AutoCompleteComboBox.autoCompleteComboBox(comboItemName, AutoCompleteComboBox.AutoCompleteMode.CONTAINING);

        // BillPrefix ComboBox Initialization
        comboBillPrefixSB.getItems().clear();
        comboBillPrefixSB.setItems(getBillPrefixNames());
        billPrefixComboBoxListener();
        comboBillPrefixSB.getSelectionModel().selectFirst();
        AutoCompleteComboBox.autoCompleteComboBox(comboBillPrefixSB, AutoCompleteComboBox.AutoCompleteMode.CONTAINING);

        // SaleBill Table Initialization
        setSaleBillTableViewCellValueFactory();
        tvSaleBillTable.setItems(getSaleBillObsvList());
        saleBillTableViewListener();
        tcSNo.setSortable(false);

        // TextField Validation
//        validateTextField();
        manageItemAmountFields();
    }

    @FXML
    private void onItemSelected(ActionEvent event) {
        try {
            itemName = comboItemName.getSelectionModel().getSelectedItem();
            System.out.println(itemName);
            manageItemAmountFields();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manageItemAmountFields();
        }
    }

    @FXML
    private void onItemQtyEntered(KeyEvent event) {
        try {
            if (txtFItemQty.getText().isEmpty()) {
                itemQty = 0;
            } else {
                itemQty = Double.parseDouble(txtFItemQty.getText());
            }
            System.out.println(itemQty);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manageItemAmountFields();
        }
    }

    @FXML
    private void onMRPEntered(KeyEvent event) {
        try {
            if (txtFMRPSB.getText().isEmpty()) {
                mrpSB = 0;
            } else {
                mrpSB = Double.parseDouble(txtFMRPSB.getText());
            }
            System.out.println(mrpSB);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manageItemAmountFields();
        }
    }

    @FXML
    private void onVatEntered(KeyEvent event) {
        try {
            if (txtFVATSB.getText().isEmpty()) {
                vatSB = 0;
            } else {
                vatSB = Double.parseDouble(txtFVATSB.getText());
            }
            System.out.println(vatSB);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manageItemAmountFields();
        }
    }

    @FXML
    private void onSaleRateEntered(KeyEvent event) {
        try {
            if (txtFSaleRateSB.getText().isEmpty()) {
                saleRateSB = 0;
            } else {
                saleRateSB = Double.parseDouble(txtFSaleRateSB.getText());
            }
            System.out.println(saleRateSB);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manageItemAmountFields();
        }
    }

    @FXML
    private void onSaleTaxEntered(KeyEvent event) {
        try {
            if (txtFSaleTaxSB.getText().isEmpty()) {
                saleTaxSB = 0;
            } else {
                saleTaxSB = Double.parseDouble(txtFSaleTaxSB.getText());
            }
            System.out.println(saleTaxSB);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manageItemAmountFields();
        }
    }

    @FXML
    private void onExtraChargeEntered(KeyEvent event) {
        try {
            if (txtFExtraChargeSB.getText().isEmpty()) {
                extraChargeSB = 0;
            } else {
                extraChargeSB = Double.parseDouble(txtFExtraChargeSB.getText());
            }
            System.out.println(extraChargeSB);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manageItemAmountFields();
        }
    }

    @FXML
    private void onClientSelectedSB(ActionEvent event) {
    }

    @FXML
    private void onGeneratedDateEntered(KeyEvent event) {
    }

    @FXML
    private void onBillPrefixSelected(ActionEvent event) {
    }

    @FXML
    private void onAddClicked(ActionEvent event) {
    }

    @FXML
    private void onDeleteClicked(ActionEvent event) {
    }

    @FXML
    private void onUpdateClicked(ActionEvent event) {
    }

    @FXML
    private void onPrintClicked(ActionEvent event) {
    }

    @FXML
    private void onNextBillClicked(ActionEvent event) {
    }

    //Setting CellFactory i.e. TableColumn with Hibernate Entity
    private void setSaleBillTableViewCellValueFactory() {
        //each cellValueFactory has been set according to the member variables of your entity class
        tcSaleItemBillPKeyDB.setCellValueFactory(new PropertyValueFactory("SaleItemBillIdPk"));
        tcSNo.setCellValueFactory(column -> new ReadOnlyObjectWrapper<Number>(tvSaleBillTable.getItems().indexOf(column.getValue()) + 1));
        tcItemName.setCellValueFactory(new PropertyValueFactory("itemName"));
        tcQtySold.setCellValueFactory(new PropertyValueFactory("saleQty"));
        tcUnit.setCellValueFactory(new PropertyValueFactory("itemUnit"));
        tcSaleRateApplied.setCellValueFactory(new PropertyValueFactory("appliedSaleRate"));
        tcSaleTaxApplied.setCellValueFactory(new PropertyValueFactory("appliedSaleTax"));
        tcSaleTaxApplied.setCellValueFactory(new PropertyValueFactory("appliedSaleTax"));
        tcMRPApplied.setCellValueFactory(new PropertyValueFactory("appliedSaleMRP"));
        tcVATApplied.setCellValueFactory(new PropertyValueFactory("appliedSaleVAT"));
        tcOtherChargesApplied.setCellValueFactory(new PropertyValueFactory("otherCharges"));
        tcItemTotal.setCellValueFactory(new PropertyValueFactory("saleItemAmount"));
    }

    //here you can add all your SaleItemBill data into a ObservableList
    private ObservableList<SaleItemBill> getSaleBillObsvList() {
        ObservableList<SaleItemBill> saleItemBillObsvList = FXCollections.observableArrayList();
        session = HibernateUtil.getSessionFactory().openSession();
        Criteria saleItemBillCriteria = session.createCriteria(SaleItemBill.class);
        saleItemBillCriteria.add(Restrictions.eq("saleItemBillIdPk", saleItemBillPKeyDB));
        List<SaleItemBill> saleItemBillList = saleItemBillCriteria.list();
//        List<SaleItemBill> itemCriteriaList = session.createCriteria(SaleItemBill.class).list();
        for (SaleItemBill saleBillItems : saleItemBillList) {
            saleItemBillObsvList.add(saleBillItems);
        }
        return saleItemBillObsvList;
    }

    // Listens to Row Selection of Table
    private void saleBillTableViewListener() {
        tvSaleBillTable.getSelectionModel().selectionModeProperty().addListener((observableValue, oldValue, newValue) -> {

        });
    }

    // Client Observable
    private ObservableList<String> getClientNames() {
        ObservableList clientNamesObsvList = FXCollections.observableArrayList(
                ClientDAOFactory.getInstance().getAllClientNames());
        return clientNamesObsvList;
    }

    // Client ComboBox Listener and set Client's Phone and address to TextFields
    private void clientComboBoxListener() {
        comboClientSB.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                clientTable = ClientDAOFactory.getInstance().selectClientWithName(comboClientSB.getSelectionModel().getSelectedItem());
                txtFClientPh1SB.setText("" + clientTable.getClientPhone1());
                txtFClientsAddressSB.setText(clientTable.getClientAddress());
            }
        });
    }

    // Item ComboBox Listener and set TextFields
    private ObservableList<String> getItemNames() {
        ObservableList itemNamesObsvList = FXCollections.observableArrayList(
                ItemDAOFactory.getInstance().getAllItemNames());
        return itemNamesObsvList;
    }

    private void itemComboBoxListener() {
        comboItemName.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                itemTable = ItemDAOFactory.getInstance().selectItemWithName(comboItemName.getSelectionModel().getSelectedItem());
                txtFAvailableQty.setText("" + itemTable.getAvailableItems());
                txtFMRPSB.setText("" + itemTable.getItemMRP());
                txtFVATSB.setText("" + itemTable.getItemVAT());
                txtFSaleRateSB.setText("" + itemTable.getItemSaleRate());
                txtFSaleTaxSB.setText("" + itemTable.getItemSaleTax());
                lblUnit.setText("" + itemTable.getItemUnit());
                txtFItemQty.setText("1");
                txtFExtraChargeSB.setText("0");
                txtFItemTotal.setText("0");
            }
        });
    }

    // BillPrefix ComboBox and set Bill No    
    private ObservableList<String> getBillPrefixNames() {
        ObservableList billPrefixNamesObsvList = FXCollections.observableArrayList(
                BillPrefixDAOFactory.getInstance().getAllBillPrefixNames());
        return billPrefixNamesObsvList;
    }

    private void billPrefixComboBoxListener() {
        comboBillPrefixSB.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                txtFSaleBillRef.setText(comboBillPrefixSB.getSelectionModel().getSelectedItem() + "/SB/"
                        + DateAndTimeProvider.getBillExtDate());
            }
        });
    }

    private void validateTextFields() {
        textFieldDataFilter.restrictDouble(txtFItemQty);
        textFieldDataFilter.restrictDouble(txtFAvailableQty);
        textFieldDataFilter.restrictDouble(txtFMRPSB);
        textFieldDataFilter.restrictDouble(txtFVATSB);
        textFieldDataFilter.restrictDouble(txtFSaleRateSB);
        textFieldDataFilter.restrictDouble(txtFSaleTaxSB);
        textFieldDataFilter.restrictDouble(txtFExtraChargeSB);
        textFieldDataFilter.restrictDouble(txtFItemTotal);
        textFieldDataFilter.restrictDouble(txtFPurDueSB);
        textFieldDataFilter.restrictDouble(txtFSaleDueDB);
        textFieldDataFilter.restrictDouble(txtFGrandTotalSB);
    }

    private void manageItemAmountFields() {
        double itemQty = 0, itemMRP = 0, itemVAT = 0, itemSaleRate = 0, itemSaleTax = 0, extraCharges = 0, itemTotal = 0;
        if (!txtFItemQty.getText().equals("")) {
            itemQty = Double.parseDouble(txtFItemQty.getText());
//        } else {
//            itemQty = 0;
        }
        if (!txtFMRPSB.getText().equals("")) {
            itemMRP = Double.parseDouble(txtFMRPSB.getText());
//        } else {
//            itemMRP = 0;
        }
        if (!txtFVATSB.getText().equals("")) {
            itemVAT = Double.parseDouble(txtFVATSB.getText());
//        } else {
//            itemVAT = 0;
        }
        if (!txtFSaleRateSB.getText().equals("")) {
            itemSaleRate = Double.parseDouble(txtFSaleRateSB.getText());
//        } else {
//            itemSaleRate = 0;
        }
        if (!txtFSaleTaxSB.getText().equals("")) {
            itemSaleTax = Double.parseDouble(txtFSaleTaxSB.getText());
//        } else {
//            itemSaleTax = 0;
        }
        if (!txtFExtraChargeSB.getText().equals("")) {
            extraCharges = Double.parseDouble(txtFExtraChargeSB.getText());
//        } else {
//            extraCharges = 0;
        }

        itemTotal = itemQty * ((itemSaleRate * (1 + itemSaleTax / 100)) + (itemMRP * itemVAT / 100)) + extraCharges;
        txtFItemTotal.setText(String.valueOf(itemTotal));

    }

    private void resetDataFields() {
        saleItemBillPKeyDB = -1;
        itemQty = 0;
        availableQty = 0;
        mrpSB = 0;
        vatSB = 0;
        saleRateSB = 0;
        saleTaxSB = 0;
        extraChargeSB = 0;
        itemTotal = 0;
        purDueSB = 0;
        saleDueDB = 0;
        grandTotalSB = 0;
    }

}
