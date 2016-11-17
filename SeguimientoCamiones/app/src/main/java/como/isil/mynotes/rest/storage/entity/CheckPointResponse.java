package como.isil.mynotes.rest.storage.entity;

/**
 * Created by Alumno-J on 16/11/2016.
 */
public class CheckPointResponse {
    private String latitud;
    private String longitud;
    private String id_order;
    private String created;
    private String __class;
    private String objectId;

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

    public String get__class() {
        return __class;
    }

    public void set__class(String __class) {
        this.__class = __class;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
