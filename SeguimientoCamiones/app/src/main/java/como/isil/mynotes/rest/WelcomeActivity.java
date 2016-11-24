package como.isil.mynotes.rest;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.isil.mynotes.rest.R;

import java.util.List;

import como.isil.mynotes.rest.entity.OrderEntity;
import como.isil.mynotes.rest.presenter.OrdersPresenter;
import como.isil.mynotes.rest.presenter.OrdersView;
import como.isil.mynotes.rest.storage.PreferencesHelper;
import como.isil.mynotes.rest.utils.CapitalizeString;
import como.isil.mynotes.rest.view.adapters.OrderAdapter;

public class WelcomeActivity extends AppCompatActivity implements OrdersView{

    private static final String TAG ="MainActivity" ;
    private static final int ACTION_ADD=1;
    private static final int ACTION_DETAIL=2;

    private TextView tviLogout,tviUser;
    private ListView lstOrders;
    private View rlayLoading,container;
    private List<OrderEntity> lsOrderEntities;
    private OrderAdapter orderAdapter;

    private OrdersPresenter ordersPresenter;

    TextView txvWelcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ordersPresenter= new OrdersPresenter();
        ordersPresenter.attachedView(this);
        init();
        loadCloud();
    }




    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void onMessageError(String message) {
        Snackbar snackbar = Snackbar
                .make(container,message, Snackbar.LENGTH_LONG);

        snackbar.show();
    }

    @Override
    public void renderOrders(List<OrderEntity> orders) {
        lsOrderEntities= orders;
        orderAdapter= new OrderAdapter(this,lsOrderEntities);
        lstOrders.setAdapter(orderAdapter);
    }

    private void init() {
        tviLogout= (TextView)findViewById(R.id.txvLogout);
        tviUser= (TextView)findViewById(R.id.txvWelcome);
        lstOrders= (ListView)(findViewById(R.id.lstOrders));
        rlayLoading= (findViewById(R.id.rlayLoading));

        //user Info
        String username = PreferencesHelper.getUserSession(this);
        if(username!=null)
        {
            tviUser.setText("Bienvenido "+ new CapitalizeString(username).first());
        }

        //events

       lstOrders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                OrderEntity orderEntity = (OrderEntity) adapterView.getAdapter().getItem(i);
                gotoOrderDetails(orderEntity);
            }
        });

        tviLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }
    private void gotoOrderDetails(OrderEntity orderEntity) {
        Intent intent= new Intent(this,NoteActivity.class);
        intent.putExtra("FRAGMENT",NoteActivity.DETAIL_NOTE);
        intent.putExtra("ORDER", orderEntity);
        startActivity(intent);
    }


    private void loadCloud() {
        ordersPresenter.loadOrders();
    }

    private void logout() {
        PreferencesHelper.signOut(this);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "onResumen");
        //loadData();
        loadCloud();
    }

}
