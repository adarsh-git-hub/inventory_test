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
public class ChequeDetails implements Serializable {

//    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "CHQ_DETAILS_ID_PK", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long chequeDetailsIdPk;

    @Column(name = "CHEQUE_NO", unique = true, updatable = false, nullable = false, length = 60)
    private String chequeNo;

    @Column(name = "CHEQUE_AMOUNT", nullable = false, precision = 52, scale = 0)
    private double chequeAmount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CHEQUE_DATE", nullable = true, length = 29)
    private Date chequeDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DEPOSITED_ON", nullable = true, length = 29)
    private Date depositedOn;

    @Column(name = "CHEQUE_BOUNCED", nullable = false)
    private boolean chequeBounced;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "PAYMENT_BY_US_ID_FK")
    private PaymentByUs paymentByUsFk;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "PAYMENT_BY_CLIENTS_ID_FK")
    private PaymentByClients paymentByClientsFk;

    public long getChequeDetailsIdPk() {
        return chequeDetailsIdPk;
    }

    public void setChequeDetailsIdPk(long chequeDetailsIdPk) {
        this.chequeDetailsIdPk = chequeDetailsIdPk;
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public double getChequeAmount() {
        return chequeAmount;
    }

    public void setChequeAmount(double chequeAmount) {
        this.chequeAmount = chequeAmount;
    }

    public Date getChequeDate() {
        return chequeDate;
    }

    public void setChequeDate(Date chequeDate) {
        this.chequeDate = chequeDate;
    }

    public Date getDepositedOn() {
        return depositedOn;
    }

    public void setDepositedOn(Date depositedOn) {
        this.depositedOn = depositedOn;
    }

    public boolean isChequeBounced() {
        return chequeBounced;
    }

    public void setChequeBounced(boolean chequeBounced) {
        this.chequeBounced = chequeBounced;
    }

    public PaymentByUs getPaymentByUsFk() {
        return paymentByUsFk;
    }

    public void setPaymentByUsFk(PaymentByUs paymentByUsFk) {
        this.paymentByUsFk = paymentByUsFk;
    }

    public PaymentByClients getPaymentByClientsFk() {
        return paymentByClientsFk;
    }

    public void setPaymentByClientsFk(PaymentByClients paymentByClientsFk) {
        this.paymentByClientsFk = paymentByClientsFk;
    }

}
