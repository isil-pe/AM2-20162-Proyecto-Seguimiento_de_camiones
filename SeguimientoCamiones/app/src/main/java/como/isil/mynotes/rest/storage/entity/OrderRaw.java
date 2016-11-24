package como.isil.mynotes.rest.storage.entity;

/**
 * Created by Alumno-J on 23/11/2016.
 */
public class OrderRaw {

    public String getId_order() {
        return id_order;
    }

    public void setId_order(String id_order) {
        this.id_order = id_order;
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

    private String id_order;
    private String latitud;
    private String longitud;
}
