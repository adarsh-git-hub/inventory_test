/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.visual;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Eddy
 */
public class BaseController/* extends StackPane*/ implements Initializable {

    @FXML
    private Label lblNotification;
    @FXML
    private StackPane stackPaneExec;
    @FXML
    private Button btnSaleChallan;
    @FXML
    private Button btnSaleBill;
    @FXML
    private Button btnPurchaseChallan;
    @FXML
    private Button btnPurchaseBill;
    @FXML
    private Button btnDashboard;
    @FXML
    private Button btnReturn;
    @FXML
    private Button btnView;
    @FXML
    private Button btnStock;
    @FXML
    private Button btnBillEdit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        notify("Welcome", Color.TEAL);
    }

    public void clearNotification() {
        lblNotification.setText("");
    }

    public void setNotification(String notification, Color textColor) {
        lblNotification.setText(notification);
        lblNotification.setTextFill(textColor);
    }
    
    public boolean setActivePane(String currentPanefxml) {
        if (currentPanefxml != null) {
            try {
                Parent rootParent = FXMLLoader.load(getClass().getResource(currentPanefxml));
//                final DoubleProperty opacity = opacityProperty();
//                
//                Timeline fade = new Timeline(
//                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
//                        new KeyFrame(new Duration(0.1), (ActionEvent t) -> {
                stackPaneExec.getChildren().removeAll(stackPaneExec.getChildren());   //remove the displayed pane
                stackPaneExec.getChildren().add(rootParent);
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

    @FXML
    private void onDashboardCllicked(ActionEvent event) {
        setActivePane("dashboard.fxml");
    }

    @FXML
    private void onSaleChallanClicked(ActionEvent event) {
        setActivePane("saleChallan.fxml");
    }

    @FXML
    private void onSaleBillClicked(ActionEvent event) {
        setActivePane("saleBill.fxml");
    }

    @FXML
    private void onPurchaseChallanClicked(ActionEvent event) {
        setActivePane("purchaseChallan.fxml");
    }

    @FXML
    private void onPurchaseBillClicked(ActionEvent event) {
        setActivePane("purchaseBill.fxml");
    }

    @FXML
    private void onReturnClicked(ActionEvent event) {
        setActivePane("return.fxml");
    }

    @FXML
    private void onViewClicked(ActionEvent event) {
        setActivePane("view.fxml");
    }

    @FXML
    private void onStockClicked(ActionEvent event) {
        setActivePane("stockBase.fxml");
    }

    @FXML
    private void onBillEditclicked(ActionEvent event) {
        setActivePane("billEdit.fxml");
    }

    

}
