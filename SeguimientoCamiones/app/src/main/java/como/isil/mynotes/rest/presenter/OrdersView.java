package como.isil.mynotes.rest.presenter;

import android.content.Context;

import java.util.List;

import como.isil.mynotes.rest.entity.OrderEntity;

/**
 * Created by Alumno-J on 23/11/2016.
 */
public interface OrdersView {

    void showLoading();
    void hideLoading();
    Context getContext();

    void onMessageError(String message);
    void renderOrders(List<OrderEntity> orders);
}
