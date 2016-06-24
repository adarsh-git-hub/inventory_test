/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.hibernate.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Eddy
 */
@Entity
public class PaymentByClients implements Serializable {

//    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PAYMENT_BY_CLIENTS_ID_PK", unique = true, nullable = false)
    private Long paymentByClientsIdPk;

    @Column(name = "RECEIVED_AMOUNT", nullable = false, precision = 52, scale = 0)
    private double receivedAmount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PAYMENT_DATE", nullable = true, length = 29)
    private Date paymentDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID_FK")
    private ClientTable clientTableFk;

    @OneToMany(mappedBy = "paymentByClientsFk", cascade = CascadeType.ALL/*, fetch = FetchType.LAZY/*, orphanRemoval = false,*/)
//    @JoinColumn(name = "PURCHASE_ID_FK")
    @LazyCollection(LazyCollectionOption.TRUE)
    private Set<ChequeDetails> chequeList;

    public Long getPaymentByClientsIdPk() {
        return paymentByClientsIdPk;
    }

    public void setPaymentByClientsIdPk(Long paymentByClientsIdPk) {
        this.paymentByClientsIdPk = paymentByClientsIdPk;
    }

    public double getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(double receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public ClientTable getClientTableFk() {
        return clientTableFk;
    }

    public void setClientTableFk(ClientTable clientTableFk) {
        this.clientTableFk = clientTableFk;
    }

    public Set<ChequeDetails> getChequeList() {
        return chequeList;
    }

    public void setChequeList(Set<ChequeDetails> chequeList) {
        this.chequeList = chequeList;
    }

}
