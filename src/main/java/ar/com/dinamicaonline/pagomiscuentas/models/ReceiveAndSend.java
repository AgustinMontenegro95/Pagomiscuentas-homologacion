package ar.com.dinamicaonline.pagomiscuentas.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "api_ReceiveAndSend")
public class ReceiveAndSend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date createdOn;
    private int idOrigin;
    private long idAccount;
    private int receiveSend;
    private String messagge;

    public ReceiveAndSend(int id, Date createdOn, int idOrigin, long idAccount, int receiveSend, String messagge) {
        this.id = id;
        this.createdOn = createdOn;
        this.idOrigin = idOrigin;
        this.idAccount = idAccount;
        this.receiveSend = receiveSend;
        this.messagge = messagge;
    }

    public ReceiveAndSend() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public int getIdOrigin() {
        return idOrigin;
    }

    public void setIdOrigin(int idOrigin) {
        this.idOrigin = idOrigin;
    }

    public long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(long idAccount) {
        this.idAccount = idAccount;
    }

    public int getReceiveSend() {
        return receiveSend;
    }

    public void setReceiveSend(int receiveSend) {
        this.receiveSend = receiveSend;
    }

    public String getMessagge() {
        return messagge;
    }

    public void setMessagge(String messagge) {
        this.messagge = messagge;
    }

}
