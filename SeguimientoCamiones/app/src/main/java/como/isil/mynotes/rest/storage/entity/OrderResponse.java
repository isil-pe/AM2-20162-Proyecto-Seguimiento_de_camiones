package como.isil.mynotes.rest.storage.entity;

/**
 * Created by Alumno-J on 23/11/2016.
 */
public class OrderResponse {

    private String weight;
    private String origen_lat;
    private String destino_lat;
    private String origen_lon;
    private String destino_lon;
    private String client_RUC;
    private String id_vehiculo;
    private String detail;
    private String created;
    private String __class;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private String objectId;

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getOrigen_lat() {
        return origen_lat;
    }

    public void setOrigen_lat(String origen_lat) {
        this.origen_lat = origen_lat;
    }

    public String getDestino_lat() {
        return destino_lat;
    }

    public void setDestino_lat(String destino_lat) {
        this.destino_lat = destino_lat;
    }

    public String getOrigen_lon() {
        return origen_lon;
    }

    public void setOrigen_lon(String origen_lon) {
        this.origen_lon = origen_lon;
    }

    public String getDestino_lon() {
        return destino_lon;
    }

    public void setDestino_lon(String destino_lon) {
        this.destino_lon = destino_lon;
    }

    public String getClient_RUC() {
        return client_RUC;
    }

    public void setClient_RUC(String client_RUC) {
        this.client_RUC = client_RUC;
    }

    public String getId_vehiculo() {
        return id_vehiculo;
    }

    public void setId_vehiculo(String id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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
