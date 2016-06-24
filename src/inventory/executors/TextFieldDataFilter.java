/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.executors;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 *
 * @author Eddy
 */
public class TextFieldDataFilter {

//    double doubleTxtFData = 0;
//    long longTxtFData = 0;
    public void restrictDouble(TextField doubleTextField) {
        doubleTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    Double.parseDouble(newValue);
                } catch (NumberFormatException nfe) {
                    if (!newValue.equals("")) {
                        doubleTextField.setText(oldValue);
                        System.out.println("Wrong Input");
                    }
                    nfe.printStackTrace();
                } catch (Exception e) {
                    if (!newValue.equals("")) {
                        doubleTextField.setText(oldValue);
                        System.out.println("Double Input Data Exception");
                    }
                    e.printStackTrace();
                }
            }
        });
//        return doubleTxtFData;
    }

    public void restrictLong(TextField longTextField) {
        longTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    Long.parseLong(newValue);
                } catch (NumberFormatException nfe) {
                    if (!newValue.equals("")) {
                        longTextField.setText(oldValue);
                        System.out.println("Wrong Input");
                    }
                    nfe.printStackTrace();
                } catch (Exception e) {
                    if (!newValue.equals("")) {
                        longTextField.setText(oldValue);
                        System.out.println("Long Input Data Exception");
                    }
                    e.printStackTrace();
                }
            }
        });
//        return longTxtFData;
    }

}
