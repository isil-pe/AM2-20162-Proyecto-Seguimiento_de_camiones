package como.isil.mynotes.rest.storage.entity;

import java.util.List;

import como.isil.mynotes.rest.entity.OrderEntity;

/**
 * Created by Alumno-J on 23/11/2016.
 */
public class OrdersResponse {
    private String message;
    private int offset;
    private List<OrderEntity> data;
    private String nextPage;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public List<OrderEntity> getData() {
        return data;
    }

    public void setData(List<OrderEntity> data) {
        this.data = data;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public int getTotalObjects() {
        return totalObjects;
    }

    public void setTotalObjects(int totalObjects) {
        this.totalObjects = totalObjects;
    }

    private int totalObjects;
}
