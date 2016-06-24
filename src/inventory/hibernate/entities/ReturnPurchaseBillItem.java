/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.hibernate.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Eddy
 */
@Entity
public class ReturnPurchaseBillItem implements Serializable {

//    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RETURN_PUR_BILL_ID", unique = true, nullable = false)
    private Long returnPurchaseBillId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "RETURN_DATE", nullable = true, length = 29)
    private Date returnDate;

    @Column(name = "RETURN_REASON", unique = false, updatable = true, nullable = true, length = 100)
    private String reasonForReturn;

    @Column(name = "RETURN_QTY", nullable = false, precision = 52, scale = 0)
    private double returnedQty;

    @Column(name = "DEDUCT_AMT", nullable = false, precision = 52, scale = 0)
    private double deductAmount;

    @Column(name = "REFUND_AMT", nullable = false, precision = 52, scale = 0)
    private double refundAmount;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "PUR_ITEM_BILL_ID_FK")
    private PurchaseItemBill purchaseItemBillFk;

    public Long getReturnPurchaseBillId() {
        return returnPurchaseBillId;
    }

    public void setReturnPurchaseBillId(Long returnPurchaseBillId) {
        this.returnPurchaseBillId = returnPurchaseBillId;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getReasonForReturn() {
        return reasonForReturn;
    }

    public void setReasonForReturn(String reasonForReturn) {
        this.reasonForReturn = reasonForReturn;
    }

    public double getReturnedQty() {
        return returnedQty;
    }

    public void setReturnedQty(double returnedQty) {
        this.returnedQty = returnedQty;
    }

    public double getDeductAmount() {
        return deductAmount;
    }

    public void setDeductAmount(double deductAmount) {
        this.deductAmount = deductAmount;
    }

    public double getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(double refundAmount) {
        this.refundAmount = refundAmount;
    }

    public PurchaseItemBill getPurchaseItemBillFk() {
        return purchaseItemBillFk;
    }

    public void setPurchaseItemBillFk(PurchaseItemBill purchaseItemBillFk) {
        this.purchaseItemBillFk = purchaseItemBillFk;
    }

}
