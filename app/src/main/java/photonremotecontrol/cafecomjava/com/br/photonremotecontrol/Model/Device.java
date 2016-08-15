package photonremotecontrol.cafecomjava.com.br.photonremotecontrol.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Joel Backschat on 28/07/2016.
 */
public class Device extends RealmObject {
    @PrimaryKey
    private int id;
    private String device;
    private String token;
    private String name;
    private Date created;
    private Date LastOnlineVerifed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastOnlineVerifed() {
        return LastOnlineVerifed;
    }

    public void setLastOnlineVerifed(Date lastOnlineVerifed) {
        LastOnlineVerifed = lastOnlineVerifed;
    }
}