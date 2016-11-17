package como.isil.mynotes.rest.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by Alumno-J on 16/11/2016.
 */
public class CheckPointEntity implements Serializable{
    private String latitud;
    private String longitud;
    private String id_order;
    private String created;
    private Date fecha;
    private String objectId;

    public CheckPointEntity() {
    }

    public CheckPointEntity(String latitud, String longitud, String id_order, String created, String objectId) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.id_order = id_order;
        this.created = created;
        this.objectId = objectId;
        this.fecha=ObtenerFecha();
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getId_order() {
        return id_order;
    }

    public void setId_order(String id_order) {
        this.id_order = id_order;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    private Date ObtenerFecha(){
        Calendar cal= Calendar.getInstance();
        cal.setTimeInMillis(Long.parseLong(this.created));
        return cal.getTime();
    }
}
