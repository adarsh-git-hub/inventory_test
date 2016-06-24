/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.visual;

import inventory.executors.TextFieldDataFilter;
import inventory.hibernate.DAOFactories.ItemDAOFactory;
import inventory.hibernate.entities.ItemTable;
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
public class StockItemController implements Initializable {

    // Custom Variables
    static String itemName, category = "N/A", itemUnit = "N/A";
    static double itemAvailable = 0, lowAlertQty = 0, mrp = 0, vat = 0,
            purRate = 0, purTax = 0, saleRate = 0, saleTax = 0;
    static long itemPKeyDB;

    private static Session session = null;
    private TextFieldDataFilter textFieldDataFilter = new TextFieldDataFilter();
    private StockOperations stockOperations = new StockOperations();

    @FXML
    private TextField txtFItemName;
    @FXML
    private TextField txtFAvailableItems;
    @FXML
    private TextField txtFLowAlertQty;
    @FXML
    private TextField txtFItemUnit;
    @FXML
    private TextField txtFCategory;
    @FXML
    private TextField txtFMRP;
    @FXML
    private TextField txtFVAT;
    @FXML
    private TextField txtFPurRate;
    @FXML
    private TextField txtFPurTax;
    @FXML
    private TextField txtFSaleRate;
    @FXML
    private TextField txtFSaleTax;
    @FXML
    private TableView<ItemTable> tvItem;
    @FXML
    private TableColumn<ItemTable, Number> tcSNo = new TableColumn<ItemTable, Number>("#");
    @FXML
    private TableColumn<ItemTable, String> tcItemName;
    @FXML
    private TableColumn<ItemTable, Double> tcAvailableItems;
    @FXML
    private TableColumn<ItemTable, String> tcUnit;
    @FXML
    private TableColumn<ItemTable, Double> tcLowAlertQty;
    @FXML
    private TableColumn<ItemTable, String> tcCategory;
    @FXML
    private TableColumn<ItemTable, Double> tcMRP;
    @FXML
    private TableColumn<ItemTable, Double> tcVAT;
    @FXML
    private TableColumn<ItemTable, Double> tcPurRate;
    @FXML
    private TableColumn<ItemTable, Double> tcPurTax;
    @FXML
    private TableColumn<ItemTable, Double> tcSaleRate;
    @FXML
    private TableColumn<ItemTable, Double> tcSaleTax;
    @FXML
    private TableColumn<ItemTable, Long> tcItemId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setItemTableCellValueFactory();
        tvItem.setItems(getItemObsvList());
        itemTableViewListener();
        tcSNo.setSortable(false);
        validateTextField();
    }

    // Set Local Fields
    @FXML
    private void onItemNameEntered(KeyEvent event) {
        try {
//            if (txtFItemName.getText().isEmpty()) {
//                itemName = "N/A";
//            } else {
            itemName = txtFItemName.getText();
//            }
            System.out.println(itemName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onAvailableItemEntered(KeyEvent event) {
        try {
            if (txtFAvailableItems.getText().isEmpty()) {
                itemAvailable = 0;
            } else {
                itemAvailable = Double.parseDouble(txtFAvailableItems.getText());
            }
            System.out.println(itemAvailable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onLowAlertEntered(KeyEvent event) {
        try {
            if (txtFLowAlertQty.getText().isEmpty()) {
                lowAlertQty = 0;
            } else {
                lowAlertQty = Double.parseDouble(txtFLowAlertQty.getText());
            }
            System.out.println(lowAlertQty);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onUnitEntered(KeyEvent event) {
        try {
            if (txtFItemUnit.getText().isEmpty()) {
                itemUnit = "N/A";
            } else {
                itemUnit = txtFItemUnit.getText();
            }
            System.out.println(itemUnit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onCategoryEntered(KeyEvent event) {
        try {
            if (txtFCategory.getText().isEmpty()) {
                category = "N/A";
            } else {
                category = txtFCategory.getText();
            }
            System.out.println(category);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onMRPEntered(KeyEvent event) {
        try {
            if (txtFMRP.getText().isEmpty()) {
                mrp = 0;
            } else {
                mrp = Double.parseDouble(txtFMRP.getText());
            }
            System.out.println(mrp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onVATEntered(KeyEvent event) {
        try {
            if (txtFVAT.getText().isEmpty()) {
                vat = 0;
            } else {
                vat = Double.parseDouble(txtFVAT.getText());
            }
            System.out.println(vat);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onPurRateEntered(KeyEvent event) {
        try {
            if (txtFPurRate.getText().isEmpty()) {
                purRate = 0;
            } else {
                purRate = Double.parseDouble(txtFPurRate.getText());
            }
            System.out.println(purRate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onPurTaxEntered(KeyEvent event) {
        try {
            if (txtFPurTax.getText().isEmpty()) {
                purTax = 0;
            } else {
                purTax = Double.parseDouble(txtFPurTax.getText());
            }
            System.out.println(purTax);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onSaleRateEntered(KeyEvent event) {
        try {
            if (txtFSaleRate.getText().isEmpty()) {
                saleRate = 0;
            } else {
                saleRate = Double.parseDouble(txtFSaleRate.getText());
            }
            System.out.println(saleRate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onSaleTaxEntered(KeyEvent event) {
        try {
            if (txtFSaleTax.getText().isEmpty()) {
                saleTax = 0;
            } else {
                saleTax = Double.parseDouble(txtFSaleTax.getText());
            }
            System.out.println(saleTax);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Setting CellFactory i.e. TableColumn with Hibernate Entity
    private void setItemTableCellValueFactory() {
        //each cellValueFactory has been set according to the member variables of your entity class
        tcSNo.setCellValueFactory(column -> new ReadOnlyObjectWrapper<Number>(tvItem.getItems().indexOf(column.getValue()) + 1));
        tcItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        tcAvailableItems.setCellValueFactory(new PropertyValueFactory("availableItems"));
        tcUnit.setCellValueFactory(new PropertyValueFactory("itemUnit"));
        tcLowAlertQty.setCellValueFactory(new PropertyValueFactory("itemLowAlertQty"));
        tcCategory.setCellValueFactory(new PropertyValueFactory("categoryName"));
        tcMRP.setCellValueFactory(new PropertyValueFactory("itemMRP"));
        tcVAT.setCellValueFactory(new PropertyValueFactory("itemVAT"));
        tcPurRate.setCellValueFactory(new PropertyValueFactory("itemPurRate"));
        tcPurTax.setCellValueFactory(new PropertyValueFactory("itemPurchaseTax"));
        tcSaleRate.setCellValueFactory(new PropertyValueFactory("itemSaleRate"));
        tcSaleTax.setCellValueFactory(new PropertyValueFactory("itemSaleTax"));

        tcItemId.setCellValueFactory(new PropertyValueFactory("itemIdPk"));
    }

    //here you can add all your Enseignants into a ObservableList
    private ObservableList<ItemTable> getItemObsvList() {
        ObservableList<ItemTable> itemList = FXCollections.observableArrayList();
        session = HibernateUtil.getSessionFactory().openSession();
        List<ItemTable> itemCriteriaList = session.createCriteria(ItemTable.class).list();
        for (ItemTable itemTable : itemCriteriaList) {
            itemList.add(itemTable);
        }
        return itemList;
    }

    // Listens to Row Selection of Table
    private void itemTableViewListener() {
        try {
            //Add change listener
            tvItem.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
                //Check whether item is selected and set value of selected item to Label
                if (tvItem.getSelectionModel().getSelectedIndex() != -1) {
                    itemName = tvItem.getSelectionModel().getSelectedItem().getItemName();
                    txtFItemName.setText(itemName);

                    category = tvItem.getSelectionModel().getSelectedItem().getCategoryName();
                    txtFCategory.setText(category);

                    itemUnit = tvItem.getSelectionModel().getSelectedItem().getItemUnit();
                    txtFItemUnit.setText(itemUnit);

                    itemAvailable = tvItem.getSelectionModel().getSelectedItem().getAvailableItems();
                    txtFAvailableItems.setText(String.valueOf(itemAvailable));

                    lowAlertQty = tvItem.getSelectionModel().getSelectedItem().getItemLowAlertQty();
                    txtFLowAlertQty.setText(String.valueOf(lowAlertQty));

                    mrp = tvItem.getSelectionModel().getSelectedItem().getItemMRP();
                    txtFMRP.setText(String.valueOf(mrp));

                    vat = tvItem.getSelectionModel().getSelectedItem().getItemVAT();
                    txtFVAT.setText(String.valueOf(vat));

                    purRate = tvItem.getSelectionModel().getSelectedItem().getItemPurRate();
                    txtFPurRate.setText(String.valueOf(purRate));

                    purTax = tvItem.getSelectionModel().getSelectedItem().getItemPurchaseTax();
                    txtFPurTax.setText(String.valueOf(purTax));

                    saleRate = tvItem.getSelectionModel().getSelectedItem().getItemSaleRate();
                    txtFSaleRate.setText(String.valueOf(saleRate));

                    saleTax = tvItem.getSelectionModel().getSelectedItem().getItemSaleTax();
                    txtFSaleTax.setText(String.valueOf(saleTax));

                    itemPKeyDB = tvItem.getSelectionModel().getSelectedItem().getItemIdPk();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validateTextField() {
        textFieldDataFilter.restrictDouble(txtFAvailableItems);
        textFieldDataFilter.restrictDouble(txtFLowAlertQty);
        textFieldDataFilter.restrictDouble(txtFMRP);
        textFieldDataFilter.restrictDouble(txtFVAT);
        textFieldDataFilter.restrictDouble(txtFPurRate);
        textFieldDataFilter.restrictDouble(txtFPurTax);
        textFieldDataFilter.restrictDouble(txtFSaleRate);
        textFieldDataFilter.restrictDouble(txtFSaleTax);
    }

    public void resetDataMembers() {
        System.out.println("Test Reset Item");
        itemName = "";
        category = "N/A";
        itemUnit = "N/A";

        itemAvailable = 0;
        lowAlertQty = 0;
        mrp = 0;
        vat = 0;
        purRate = 0;
        purTax = 0;
        saleRate = 0;
        saleTax = 0;
        itemPKeyDB = -1;

    }

    void newItemEntry() {
        ItemDAOFactory.getInstance().saveNewItem(itemName, category, itemUnit, itemAvailable, lowAlertQty, mrp, vat,
                purRate, purTax, saleRate, saleTax);
//        stockOperations.newItemStockEntry(itemName, category, itemUnit, itemAvailable, lowAlertQty, mrp, vat,
//                purRate, purTax, saleRate, saleTax);
    }

    void updateItemEntry() {
        ItemDAOFactory.getInstance().updateExistingItem(itemPKeyDB, itemName, itemAvailable, itemUnit, lowAlertQty,
                category, mrp, vat, purRate, purTax, saleRate, saleTax);
//        stockOperations.updateItemStockEntry(itemPKeyDB, itemName, itemAvailable, itemUnit, lowAlertQty, category, mrp, vat, purRate, purTax, saleRate, saleTax);
    }

    void deleteItemEntry() {
        ItemDAOFactory.getInstance().deleteExistingItem(itemPKeyDB);
//        stockOperations.deleteItemStockEntry(itemPKeyDB);
    }

}
