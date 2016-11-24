package como.isil.mynotes.rest.presenter;

import android.util.Log;

import java.util.List;

import como.isil.mynotes.rest.entity.OrderEntity;
import como.isil.mynotes.rest.storage.entity.OrdersResponse;
import como.isil.mynotes.rest.storage.request.ApiProy;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Alumno-J on 23/11/2016.
 */
public class OrdersPresenter {

    private static final String TAG = "OrdersPresenter";
    private final String ERROR_MESSAGE= "Ocurriò un error";

    private OrdersView ordersView;

    public   void attachedView(OrdersView ordersView){
        this.ordersView = ordersView;
    }

    public  void detachView(){
        this.ordersView=null;
    }

    public void loadOrders(){
        ordersView.showLoading();

        Call<OrdersResponse> call= ApiProy.getMyApiClient().orders();
        call.enqueue(new Callback<OrdersResponse>() {
            @Override
            public void onResponse(Call<OrdersResponse> call, Response<OrdersResponse> response) {
                if(response.isSuccessful()){

                    ordersSuccess(response.body());
                }else {
                    ordersError(ERROR_MESSAGE);
                }
            }

            @Override
            public void onFailure(Call<OrdersResponse> call, Throwable t) {
                String json="Error ";
                try {
                    json= new StringBuffer().append(t.getMessage()).toString();
                }catch (NullPointerException e) {}
                Log.v(TAG, "json >>>> " + json);

                ordersError(json);
            }
        });
    }

    private void ordersSuccess(OrdersResponse ordersResponse) {
        ordersView.hideLoading();

        if(ordersResponse!=null){
            List<OrderEntity> orders= ordersResponse.getData();
            ordersView.renderOrders(orders);
        }

    }
    private void ordersError(String messageError){
        ordersView.hideLoading();
        ordersView.onMessageError(messageError);
    }
}
