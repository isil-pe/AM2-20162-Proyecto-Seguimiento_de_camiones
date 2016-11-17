package como.isil.mynotes.rest.storage.entity;

import java.util.List;

import como.isil.mynotes.rest.entity.CheckPointEntity;

/**
 * Created by Alumno-J on 16/11/2016.
 */
public class CheckPointsResponse {

    private String message;
    private int offset;
    private List<CheckPointEntity> lobjCheckPoint;
    private String nextPage;
    private int totalObjects;

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

    public List<CheckPointEntity> getLobjCheckPoint() {
        return lobjCheckPoint;
    }

    public void setLobjCheckPoint(List<CheckPointEntity> lobjCheckPoint) {
        this.lobjCheckPoint = lobjCheckPoint;
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
}
