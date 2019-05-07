package dgpragmatic.co.com.personas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import dgpragmatic.co.com.personas.domain.Parqueadero;

public class cusAdapter extends BaseAdapter {
    Context context;
    List<Parqueadero> listItem;
    LayoutInflater inflater;

    public cusAdapter(Context context, List<Parqueadero> listItem){
        this.context=context;
        this.listItem=listItem;
        inflater= LayoutInflater.from(context);
    }

    class viewHolder{
        TextView txtPlate;
        TextView txtType;
        TextView txtCC;
        TextView txtFechaIngreso;
    }

    @Override
    public int getCount() {
      return  listItem.size();
    }

    @Override
    public Parqueadero getItem(int i) {
        return listItem.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
       viewHolder holder = null;
       if(view==null){
           holder = new viewHolder();
           view = inflater.inflate(R.layout.activity_persona_listar,null);
           holder.txtPlate=view.findViewById(R.id.textViewPlate);
           holder.txtType=view.findViewById(R.id.textViewTipoVehicle);
           holder.txtCC = view.findViewById(R.id.textViewCilindraje);
           holder.txtFechaIngreso = view.findViewById(R.id.textViewFechaIngreso);
           view.setTag(holder);
       }else{
           holder = (viewHolder) view.getTag();
       }
       holder.txtPlate.setText(listItem.get(i).getPlaca());
       String cc = listItem.get(i).getCilindraje()==null ? "N/A" : listItem.get(i).getCilindraje().toString();
       holder.txtCC.setText(cc);
       holder.txtType.setText(listItem.get(i).getTipoVehiculo());
       //holder.txtFechaIngreso.setText( String.format("%tc", listItem.get(i).getFechaIngreso()));
       holder.txtFechaIngreso.setText( new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss", Locale.getDefault()).format( listItem.get(i).getFechaIngreso()));
       return view;
    }
}
