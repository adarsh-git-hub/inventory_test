/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.executors;

import inventory.hibernate.entities.BillPrefixTable;
import inventory.hibernate.entities.ClientTable;
import inventory.hibernate.entities.ItemTable;
import inventory.hibernate.utils.HibernateUtil;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

/**
 *
 * @author Eddy
 */
public class CommonTasks {

//  Custom Variables
    private static Session session;
//    private static BatchTable batchTable = null;

//    public static void clearNotification() {
//        BaseController.lblNotification.setText("");
//    }
//
//    public static void setNotification(String notification, Color textColor) {
//        BaseController.lblNotification.setText(notification);
//        BaseController.lblNotification.setTextFill(textColor);
//    }
//    public static void clearNotification() {
//        BaseController.lblNotification.setText("");
//    }
//
//    public static void notify(String notification, Color textColor) {
//        BaseController.lblNotification.setText(notification);
////        BaseController.lblNotification.set(textColor);
//    }
//    public static void serialNumberGenerator(Table table) {
//        if (table.getRowCount() > 0) {
//            for (int i = 0; i < table.getRowCount(); i++) {
//                table.setValueAt(i + 1, i, 0);
//            }
//        }
//    }
//    public static void clear_jTable(Table table) {
//        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
//        while (table.getRowCount() > 0) {
//            tableModel.removeRow(0);
//        }
//    }
//    public static void fillNAOnFocusGained(TextField formattedTextField, FocusEvent evt) {
//        Operate.clearOutputShown();
//        try {
//            if (!evt.isTemporary()) {
//                // This is needed to put the text field in edited mode, so that its processFocusEvent doesn't
//                // do anything. Otherwise, it calls setValue, and the selection is lost.
//                formattedTextField.setText(formattedTextField.getText());
//                formattedTextField.selectAll();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    public static void commonString_jFormattedTextFieldFocusLost(JFormattedTextField formattedTextField) {
//        Operate.clearOutputShown();
//        try {
//            if (formattedTextField.getText().trim().equals("")) {
//                formattedTextField.setText("N/A");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    // Existing Methods will be deleted and with be fetch from DAOFactories
    public static void fillComboboxByClientList(ComboBox clientCombo) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria clientNameCriteria = session.createCriteria(ClientTable.class);
            clientNameCriteria.setProjection(Projections.projectionList().add(Projections.property("clientName")));
            ArrayList<ClientTable> clientNameList = (ArrayList<ClientTable>) clientNameCriteria.list();
            Iterator<ClientTable> clientItr = clientNameList.iterator();

            if (clientItr.hasNext()) {
                clientCombo.setTooltip(new Tooltip());
                if (clientCombo != null) {
                    clientCombo.getItems().removeAll();
                }
                while (clientItr.hasNext()) {
                    clientCombo.getItems().add("" + clientItr.next());
                }
                // Enabling AutoComplete Text
//                AutoTextCompletionJComboBox.enable(clientCombo);
            }
//        } catch (IllegalArgumentException iae) {
//            fillComboboxByClientList(clientCombo);
//            iae.printStackTrace();
        } catch (IllegalArgumentException iae) {
            fillComboBoxByItemList(clientCombo);
            iae.printStackTrace();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
    }

    public static void fillComboBoxByItemList(ComboBox itemCombo) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria itemNameCriteria = session.createCriteria(ItemTable.class);
            itemNameCriteria.setProjection(Projections.projectionList().add(Projections.property("itemName")));
            ArrayList<ItemTable> itemNameList = (ArrayList<ItemTable>) itemNameCriteria.list();
            Iterator<ItemTable> itemItr = itemNameList.iterator();

            if (itemItr.hasNext()) {
                itemCombo.setTooltip(new Tooltip());
                if (itemCombo != null) {
                    itemCombo.getItems().removeAll();
                }
                while (itemItr.hasNext()) {
                    itemCombo.getItems().add("" + itemItr.next());
                }
                // Enabling AutoComplete Text
//                AutoTextCompletionJComboBox.enable(itemCombo);
            }
        } catch (IllegalArgumentException iae) {
            fillComboBoxByItemList(itemCombo);
            iae.printStackTrace();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
    }

    public void fillComboBoxByBillPrefix(ComboBox billPrefixCombo) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria billPrefixCriteria = session.createCriteria(BillPrefixTable.class);
            billPrefixCriteria.setProjection(Projections.projectionList().add(Projections.property("billPrefix")));
            ArrayList<BillPrefixTable> billPrefixList = (ArrayList<BillPrefixTable>) billPrefixCriteria.list();
            Iterator<BillPrefixTable> billPrefixItr = billPrefixList.iterator();

            if (billPrefixItr.hasNext()) {
                billPrefixCombo.setTooltip(new Tooltip());
                if (billPrefixCombo != null) {
                    billPrefixCombo.getItems().removeAll();
                }
                while (billPrefixItr.hasNext()) {
                    billPrefixCombo.getItems().add("" + billPrefixItr.next());
                }
                // Enabling AutoComplete Text
//                AutoTextCompletionJComboBox.enable(billPrefixCombo);
            }
        } catch (IllegalArgumentException iae) {
            fillComboBoxByBillPrefix(billPrefixCombo);
            iae.printStackTrace();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
    }

//    public static void fillComboboxByFullBatchList(JComboBox batchCombo, List batchList) {
//        try {
//            Iterator<BatchTable> batchItr = batchList.iterator();
//            if (batchItr.hasNext()) {
//                if (batchCombo != null) {
//                    batchCombo.removeAllItems();
//                }
//                while (batchItr.hasNext()) {
//                    batchTable = (BatchTable) batchItr.next();
//                    batchCombo.addItem(batchTable.getBatchNo());
//                }
//                // Enabling AutoComplete Text
//                AutoTextCompletionJComboBox.enable(batchCombo);
//            }
//        } catch (IllegalArgumentException iae) {
//            fillComboboxByFullBatchList(batchCombo, batchList);
//            iae.printStackTrace();
//        } catch (HibernateException ex) {
//            Logger.getLogger(Operate.class
//                    .getName()).log(Level.SEVERE, null, ex);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void fillComboboxByNonEmptyBatchList(JComboBox batchCombo, List batchList) {
//        try {
//            Iterator<BatchTable> batchItr = batchList.iterator();
//            if (batchItr.hasNext()) {
//                if (batchCombo != null) {
//                    batchCombo.removeAllItems();
//                }
//                while (batchItr.hasNext()) {
//                    batchTable = (BatchTable) batchItr.next();
//                    if (batchTable.getItemAvailableInBatch() > 0) {
//                        batchCombo.addItem(batchTable.getBatchNo());
//                    }
//                }
//                // Enabling AutoComplete Text
//                AutoTextCompletionJComboBox.enable(batchCombo);
//            }
//        } catch (IllegalArgumentException iae) {
//            fillComboboxByNonEmptyBatchList(batchCombo, batchList);
//            iae.printStackTrace();
//
//        } catch (HibernateException ex) {
//            Logger.getLogger(Operate.class
//                    .getName()).log(Level.SEVERE, null, ex);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
////
////    public static void setAvailableItemInBatch(JComboBox batchListCombo, JFormattedTextField availableItemBatch) {
////        Operate.clearOutputShown();
////        try {
////            String batchNo = batchListCombo.getSelectedItem().toString().trim();
////            session = HibernateUtils.getSessionFactory().openSession();
////            Criteria batchCriteria = session.createCriteria(BatchTable.class);
////            batchCriteria.add(Restrictions.eq("batchNo", batchNo));
////            Iterator<BatchTable> batchItr = batchCriteria.list().iterator();
////            if (batchItr.hasNext()) {
////                if (batchListCombo != null) {
////                    batchListCombo.removeAllItems();
////                }
////                if (batchItr.hasNext()) {
////                    availableItemBatch.setText("" + batchItr.next().getItemAvailableInBatch());
////                }
////                // Enabling AutoComplete Text
////                AutoTextCompletionJComboBox.enable(batchListCombo);
////            } else {
////                availableItemBatch.setText("0");
////            }
////
////            //VERY IMPORTANT TO COMMIT
////        } catch (HibernateException ex) {
////            Logger.getLogger(Operate.class
////                    .getName()).log(Level.SEVERE, null, ex);
////        } finally {
////            if (session.isOpen()) {
////                session.flush();
////                session.clear();
////                session.close();
////            }
////        }
////    }
//
//    public static boolean refresh() {
//        boolean isActive = false;
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        Date validity = null;
//
//        try {
//            validity = df.parse("2016-06-30");
//
//        } catch (ParseException ex) {
//            Logger.getLogger(Operate.class
//                    .getName()).log(Level.SEVERE, null, ex);
//        }
//
//        if (new Date().before(validity)) {
//            isActive = true;
//        } else {
//            isActive = false;
//        }
//        return isActive;
//    }
    public static Date getDatePickerDate(DatePicker datePicker) {
        LocalDate localDate = datePicker.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        return date;
    }

//    public static void numberTextFieldFormatting(TextField focussedTextField) {
//        focussedTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
//            @Override
//            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//                if (!newValue) {
//                    Platform.runLater(new Runnable() {
//                        @Override
//                        public void run() {
//                            System.out.println(".run()");
//                            if (focussedTextField.isFocused() && !focussedTextField.getText().isEmpty()) {
//                                focussedTextField.selectAll();
//                            }
//                        }
//                    });
//                } else {
//                    Platform.runLater(new Runnable() {
//                        @Override
//                        public void run() {
////                            if (focussedTextField.isFocused() && !focussedTextField.getText().isEmpty()) {
////                                focussedTextField.selectAll();
//                                focussedTextField.setText("0.0");
////                            }
//                        }
//                    });
//                }
//            }
//        });
//    }
    public static void stringTextFieldFormatting(TextField stringTextField) {
        System.out.println("Out of focus");

    }

    public static void commonDoubleTextFielsInput(TextField numberTextField) {

    }
}
