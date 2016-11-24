package como.isil.mynotes.rest.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.isil.mynotes.rest.R;

import java.util.List;

import como.isil.mynotes.rest.entity.OrderEntity;

/**
 * Created by Alumno-J on 23/11/2016.
 */
public class OrderAdapter extends BaseAdapter {

    private Context context;
    private List<OrderEntity> lsOrderEntities;

    public OrderAdapter(Context context, List<OrderEntity> lsOrderEntities) {
        this.context = context;
        this.lsOrderEntities = lsOrderEntities;
    }


    @Override
    public int getCount() {
        return lsOrderEntities.size();
    }

    @Override
    public Object getItem(int i) {
        return lsOrderEntities.get(i);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            v = inflater.inflate(R.layout.row_order, null);
            ViewHolder holder = new ViewHolder();
            holder.tviIdOrder = (TextView)v.findViewById(R.id.tviIDorder);
            holder.tviRUC = (TextView)v.findViewById(R.id.tviRUC);
            holder.tviDescripcion = (TextView)v.findViewById(R.id.tviDescripcion);
            holder.tviOrigen = (TextView)v.findViewById(R.id.tviOrigen);
            holder.tviDestino = (TextView)v.findViewById(R.id.tviDestino);
            v.setTag(holder);
        }
        OrderEntity entry = lsOrderEntities.get(position);
        if(entry != null) {
            ViewHolder holder = (ViewHolder)v.getTag();
            holder.tviIdOrder.setText("Id. Orden: " + entry.getObjectId());
            holder.tviRUC.setText("Ruc cliente: " + entry.getClient_RUC());
            holder.tviDescripcion.setText("Descripción :" +entry.getDetail());
            holder.tviOrigen.setText("Origen: "+ entry.getOrigen_lat() + "," + entry.getOrigen_lon());
            holder.tviDestino.setText("Destino: "+ entry.getDestino_lat() + "," + entry.getDestino_lon());
        }

        return v;
    }

    static class ViewHolder
    {
        TextView tviIdOrder, tviOrigen, tviDestino, tviRUC, tviDescripcion;
    }
}
