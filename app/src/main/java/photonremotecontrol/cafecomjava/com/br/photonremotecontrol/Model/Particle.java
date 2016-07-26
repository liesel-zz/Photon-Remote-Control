package photonremotecontrol.cafecomjava.com.br.photonremotecontrol.Model;

/**
 * Created by joel on 7/21/16.
 */
public class Particle {
    private String id;
    private String last_app;
    private boolean connected;
    private int return_value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLast_app() {
        return last_app;
    }

    public void setLast_app(String last_app) {
        this.last_app = last_app;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public int getReturn_value() {
        return return_value;
    }

    public void setReturn_value(int return_value) {
        this.return_value = return_value;
    }
}
