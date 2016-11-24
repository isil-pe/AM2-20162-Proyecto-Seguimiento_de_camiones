package como.isil.mynotes.rest;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.isil.mynotes.rest.R;

import como.isil.mynotes.rest.entity.OrderEntity;
import como.isil.mynotes.rest.view.fragments.OrderDetailFragment;
import como.isil.mynotes.rest.view.listeners.OnOrderListener;

public class OrderActivity extends AppCompatActivity implements OnOrderListener{

    public static final  int DETAIL_ORDER=101;
    private static final String TAG ="OrderActivity";
    private OrderDetailFragment orderDetailsFragment= OrderDetailFragment.newInstance(null,null);
    private int fragmentSelected= DETAIL_ORDER;
    private OrderEntity orderEntity;

    private View rlayLoading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        validateExtras();
        rlayLoading= findViewById(R.id.rlayLoading);
        Bundle bundle= new Bundle();
        bundle.putSerializable("ORDER",orderEntity);
        changeFragment(fragmentSelected, bundle);
    }

    private void validateExtras() {
        if(getIntent().getExtras()!=null)
        {
            fragmentSelected= getIntent().getExtras().getInt("FRAGMENT",DETAIL_ORDER);
            orderEntity= (OrderEntity) getIntent().getExtras().getSerializable("ORDER");
        }
    }


    private  void changeFragment(int id,Bundle bundle)
    {
        Fragment fragment= null;
        switch (id)
        {

            case DETAIL_ORDER:
                fragment=orderDetailsFragment;
                break;
        }

        if(fragment!=null)
        {
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        }
    }


    @Override
    public void showParentLoading() {
        this.rlayLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideParentLoading() {
        this.rlayLoading.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String message) {

    }
}
