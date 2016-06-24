/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.visual;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;

/**
 * FXML Controller class
 *
 * @author Eddy
 */
public class PurchaseBillController implements Initializable {

    @FXML
    private TextField txtFAddress;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onPurRateTextChanged(InputMethodEvent event) {
        
        
    }

    @FXML
    private void onPurQtyTextChanged(InputMethodEvent event) {
    }
    
}
