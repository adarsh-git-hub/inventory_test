/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.visual;

import inventory.executors.CommonTasks;
import inventory.executors.DateAndTimeProvider;
import inventory.hibernate.entities.BillPrefixTable;
import inventory.hibernate.entities.ClientTable;
import inventory.hibernate.entities.ItemTable;
import inventory.hibernate.entities.OrderPurchase;
import inventory.hibernate.entities.PurchaseItemChallan;
import inventory.hibernate.utils.HibernateUtil;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.hibernate.criterion.Restrictions;

/**
 * FXML Controller class
 *
 * @author Eddy
 */
public class PurchaseChallanController implements Initializable {

    //Custom Variables
    private static Session session = null;
    private static Transaction transaction = null;
//    private static BillDetails billDetails = new BillDetails();
    private static BillPrefixTable billPrefixTable = new BillPrefixTable();
    private static ClientTable clientTable = new ClientTable();
    private static OrderPurchase orderPurchase = new OrderPurchase();
    private static ItemTable itemTable = new ItemTable();
    private static PurchaseItemChallan purchaseItemChallan = new PurchaseItemChallan();
//    private static BatchTable batchTable = new BatchTable();
//    private static ItemTable itemTable = new ItemTable();
//    private static PurchaseTable purchaseTable = new PurchaseTable();
//    private static String currentBillDate = DateAndTimeProvider.getBillExtDate();
//    private static final DecimalFormat decimalFormat = new DecimalFormat("###0.##");

    @FXML
    private TextField txtFGrandTotal;
    @FXML
    private Button btnNextBill;
    @FXML
    private TableView<?> tablePurchaseChallan;
    @FXML
    private ComboBox<?> comboItemName;
    @FXML
    private TextField txtFItemQty;
    @FXML
    private TextField txtFAvailableItems;
    @FXML
    private TextField txtFMRP;
    @FXML
    private TextField txtFVAT;
    @FXML
    private TextField txtFPurRate;
    @FXML
    private TextField txtFPurTax;
    @FXML
    private TextField txtFOtherCharges;
    @FXML
    private TextField txtFTotalAmt;
    @FXML
    private ComboBox<?> comboClientName;
    @FXML
    private TextField txtFClientsPhone;
    @FXML
    private TextField txtFClientsAddress;
    @FXML
    private TextField txtFClientsChallanNo;
    @FXML
    private DatePicker datePickerGenDate;
    @FXML
    private ComboBox<?> comboPrefix;
    @FXML
    private TextField txtFOurChallanRef;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnPrint;
    @FXML
    private TextField txtFPreviousOutstanding;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CommonTasks.fillComboboxByClientList(comboClientName);
        CommonTasks.fillComboBoxByItemList(comboItemName);
//        BaseController.setNotification("Hii", Color.TEAL);
    }

    @FXML
    private void onClickNextBill(ActionEvent event) {
//        CommonTasks.fillComboboxByClientList(comboClientName);
    }

    @FXML
    private void onClientSelect(ActionEvent event) {
    }

    @FXML
    private void onClickAdd(ActionEvent event) {

        if ((comboClientName.getSelectionModel().isEmpty())
                && (comboItemName.getSelectionModel().isEmpty())
                && (comboPrefix.getSelectionModel().isEmpty())) {

            System.out.println("Insufficient Details. Please Check all fields");
        } else {
            try {
                //Fetching billPrefixTableID
                session = HibernateUtil.getSessionFactory().openSession();
                Criteria billPrefixCriteria = session.createCriteria(BillPrefixTable.class);
                Iterator billPrefixItr = billPrefixCriteria
                        .add(Restrictions.eq("billPrefix", comboPrefix.getSelectionModel().getSelectedItem().toString())).list().iterator();
                billPrefixTable = (BillPrefixTable) billPrefixItr.next();
                session.flush();
                session.clear();

                // Fetching clientID
                Criteria clientCriteria = session.createCriteria(ClientTable.class);
                Iterator clientItr = clientCriteria
                        .add(Restrictions.eq("clientName", comboClientName.getSelectionModel().toString())).list().iterator();
                clientTable = (ClientTable) clientItr.next();
                session.flush();
                session.clear();

                // Fetch Billing Data and saving to Database
                double purGrandTotal = Double.parseDouble(txtFGrandTotal.getText().trim());
//                double purPaidAmtCash = Double.parseDouble(purPaidAmtCash_jFormattedTextField.getText().trim());
//                double purPaidAmtChq = Double.parseDouble(purPaidAmtChq_jFormattedTextField.getText().trim());
//                double purAmtRemaining = purGrandTotal - purPaidAmtCash - purPaidAmtChq;

                // Setting Bill Data
                Criteria orderPurCriteria = session.createCriteria(OrderPurchase.class);
                orderPurCriteria.add(Restrictions.eq("orderNo", txtFOurChallanRef.getText().trim()));
                Iterator orderPurItr = orderPurCriteria.list().iterator();
                if (orderPurItr.hasNext()) {
                    orderPurchase = (OrderPurchase) orderPurItr.next();
                } else {
                    orderPurchase = new OrderPurchase();
                }
                session.flush();
                session.clear();

                orderPurchase.setOrderNo(txtFOurChallanRef.getText().trim());
                Timestamp currentTimestamp = DateAndTimeProvider.getCurrentDateTimeRaw();

                orderPurchase.setClientsOrderRef(txtFClientsChallanNo.getText());

                if (datePickerGenDate.getValue() != null) {
                    orderPurchase.setChallanGenerateDate(CommonTasks.getDatePickerDate(datePickerGenDate));
                } else {
                    orderPurchase.setChallanGenerateDate(currentTimestamp);
                }

                orderPurchase.setGrandTotal(0.0);

                orderPurchase.setClientTableFk(clientTable);
                orderPurchase.setBillPrefixTableFk(billPrefixTable);
//                }

                // Setting Purchase and Item Details
                // Fetching itemId
                Criteria itemCriteria = session.createCriteria(ItemTable.class);
                Iterator itemItr = itemCriteria.add(Restrictions.eq("itemName", comboItemName.getSelectionModel().getSelectedItem().toString().trim())).list().iterator();
//                if (batchItr.hasNext()) {
                itemTable = (ItemTable) itemItr.next();
                itemTable.setAvailableItems((itemTable.getAvailableItems())
                        + (Double.parseDouble(txtFItemQty.getText())));

                // Setting Purchase Details
                purchaseItemChallan.setPurchaseQty(Double.parseDouble(txtFItemQty.getText()));
                purchaseItemChallan.setAppliedPurchaseMRP(Double.parseDouble(txtFMRP.getText().trim()));
                purchaseItemChallan.setAppliedPurchaseVAT(Double.parseDouble(txtFVAT.getText().trim()));
                purchaseItemChallan.setAppliedPurchaseRate(Double.parseDouble(txtFPurRate.getText().trim()));
                purchaseItemChallan.setAppliedPurchaseTax(Double.parseDouble(txtFPurTax.getText().trim()));
                purchaseItemChallan.setOtherCharges(Double.parseDouble(txtFOtherCharges.getText().trim()));
                purchaseItemChallan.setTotalAmount(Double.parseDouble(txtFTotalAmt.getText().trim()));

                purchaseItemChallan.setOrderPurchaseFk(orderPurchase);

                // Modifying Available Items
                transaction = session.beginTransaction();
                session.flush();
                session.clear();

                session.saveOrUpdate(orderPurchase);
                session.flush();
                session.clear();

                session.update(itemTable);
                session.flush();
                session.clear();

                // Saving Purchase Details to Database
                session.save(purchaseItemChallan);
                transaction.commit();
                System.out.println("Saved");

            } catch (HibernateException | NoSuchElementException | NumberFormatException hibExp) {
                if (transaction != null) {
                    try {
                        transaction.rollback();
                    } catch (TransactionException te) {
                        te.printStackTrace();
                    }
                }
                System.out.println("Error");
                hibExp.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Problem");
            } finally {
                if (session.isOpen()) {
                    session.close();
                }
            }
        }
    }

    @FXML
    private void onClickDelete(ActionEvent event) {
    }

    @FXML
    private void onClickUpdate(ActionEvent event) {
    }

    @FXML
    private void onClickPrint(ActionEvent event) {
    }

    @FXML
    private void onPurQtySelect(ActionEvent event) {
//        CommonTasks.numberTextFieldFormatting(txtFItemQty);

    }

}
