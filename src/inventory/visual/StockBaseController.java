/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.visual;

import inventory.executors.AutoCompleteComboBox;
import inventory.operations.StockOperations;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Eddy
 */
public class StockBaseController implements Initializable {

    // Custom Variables    
    StockItemController stockItemController = new StockItemController();
    StockClientController stockClientController = new StockClientController();
    StockBillPrefixController stockBillPrefixController = new StockBillPrefixController();
    StockAuthenticationController stockAuthenticationController = new StockAuthenticationController();
    StockOperations stockOperations = new StockOperations();

//    private static Session session = null;
//    private static Transaction transaction = null;
//    private static ItemTable itemTable = new ItemTable();
//    AutoCompleteComboBoxListener<String> cbAutoComplete;
    @FXML
    private StackPane stackPaneStockMain;
    @FXML
    private ComboBox<?> comboSelectTable;
    @FXML
    private TextField txtFStockSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnNew;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AutoCompleteComboBox.autoCompleteComboBox(comboSelectTable, AutoCompleteComboBox.AutoCompleteMode.CONTAINING);
        comboSelectTable.getSelectionModel().selectFirst();
        onDBTableSelect(null);
    }

    @FXML
    private void onDBTableSelect(ActionEvent event) {
        try {
            if (comboSelectTable.getSelectionModel().getSelectedItem() != null) {
                switch (comboSelectTable.getSelectionModel().getSelectedItem().toString().trim()) {
                    case "ITEM":
                        setStockActivePane("stockItem.fxml");
                        break;
                    case "CLIENT":
                        setStockActivePane("stockClient.fxml");
                        break;
                    case "BILL PREFIX":
                        setStockActivePane("stockBillPrefix.fxml");
                        break;
                    case "LOGIN DETAILS":
                        setStockActivePane("stockAuthentication.fxml");
                        break;
                    default:
                        System.out.println("Do Nothing - Stock");
                        break;
                }
            } else {
                stackPaneStockMain.getChildren().removeAll(stackPaneStockMain.getChildren());
                stackPaneStockMain.getChildren().clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resetStockDataMembers();
        }
    }

    @FXML
    private void onStockSearch(ActionEvent event) {

    }

    @FXML
    private void onStockNewEntry(ActionEvent event) {
        try {
            switch (comboSelectTable.getSelectionModel().getSelectedItem().toString()) {
                case "ITEM":
                    stockItemController.newItemEntry();
                    break;

                case "CLIENT":
                    stockClientController.newCliententry();
                    break;

                case "BILL PREFIX":
                    stockBillPrefixController.newBillPrefixEntry();
                    break;

                case "LOGIN DETAILS":
                    stockAuthenticationController.newLoginEntry();
                    break;

                default:
                    System.out.println("Wrong Entry");
                    break;
            }
        } catch (NumberFormatException nfe) {
            System.out.println("CheckData");
            nfe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Problem Occured, Contact Developer");
        } finally {
            onDBTableSelect(null);
        }
    }

    @FXML
    private void onStockUpdateEntry(ActionEvent event) {
        try {
            switch (comboSelectTable.getSelectionModel().getSelectedItem().toString()) {
                case "ITEM":
                    stockItemController.updateItemEntry();
                    break;
                case "CLIENT":
                    stockClientController.updateCliententry();
                    break;
                case "BILL PREFIX":
                    stockBillPrefixController.updateBillPrefixEntry();
                    break;
                case "LOGIN DETAILS":
                    stockAuthenticationController.updateLoginEntry();
                    break;
                default:
                    System.out.println("Wrong Entry");
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Problem Occured, Contact Developer");
        } finally {
            onDBTableSelect(null);
        }
    }

    @FXML
    private void onStockDeleteEntry(ActionEvent event) {
        try {
            switch (comboSelectTable.getSelectionModel().getSelectedItem().toString()) {
                case "ITEM":
                    stockItemController.deleteItemEntry();
                    break;
                case "CLIENT":
                    stockClientController.deleteCliententry();
                    break;
                case "BILL PREFIX":
                    stockBillPrefixController.deleteBillPrefixEntry();
                    break;
                case "LOGIN DETAILS":
                    stockAuthenticationController.deleteLoginEntry();
                    break;
                default:
                    System.out.println("Wrong Entry");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Problem Occured, Contact Developer");
        } finally {
            onDBTableSelect(null);
        }
    }

    private void resetStockDataMembers() {
        stockItemController.resetDataMembers();
        stockClientController.resetDataMembers();
        stockBillPrefixController.resetDataMembers();
        stockAuthenticationController.resetDataMembers();
    }

    public boolean setStockActivePane(String currentPanefxml) {
        if (currentPanefxml != null) {
            try {
                Parent stockRootParent = FXMLLoader.load(getClass().getResource(currentPanefxml));
//                final DoubleProperty opacity = opacityProperty();
//                
//                Timeline fade = new Timeline(
//                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
//                        new KeyFrame(new Duration(0.1), (ActionEvent t) -> {
                stackPaneStockMain.getChildren().removeAll(stackPaneStockMain.getChildren());   //remove the displayed pane
                stackPaneStockMain.getChildren().add(stockRootParent);
//                            Timeline fadeIn = new Timeline(
//                                    new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
//                                    new KeyFrame(new Duration(0.1), new KeyValue(opacity, 1.0)));
//                            fadeIn.play();
//                        }, new KeyValue(opacity, 0.0)));
//                fade.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        } else {
            System.out.println("Pane hasn't been loaded!!! \n");
            return false;
        }
    }
}
