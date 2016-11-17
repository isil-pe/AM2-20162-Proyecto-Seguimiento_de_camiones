package como.isil.mynotes.rest.presenter;

/**
 * Created by Alumno-J on 16/11/2016.
 */
public class CheckPointPresenter {

    private static final String TAG = "CheckpointsPresenter";
    private final String ERROR_MESSAGE= "Ocurrió un error";
    private CheckpointsView checkpointsView;

    public   void attachedView(CheckpointsView checkpointsView){
        this.checkpointsView = checkpointsView;
    }

    public  void detachView(){
        this.checkpointsView=null;
    }

    

}
