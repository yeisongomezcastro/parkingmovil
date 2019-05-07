package dgpragmatic.co.com.personas;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;
import dgpragmatic.co.com.personas.cliente.ParqueaderoRestClient;
import dgpragmatic.co.com.personas.domain.Parqueadero;
import dgpragmatic.co.com.personas.persistencia.dao.UtilIpDAO;
import dgpragmatic.co.com.personas.util.GlobalState;
import dgpragmatic.co.com.personas.util.ViewUtil;

public class ConsultarVehiculos extends AppCompatActivity {
    private  String ip;
    private cusAdapter customAdapter;
    private ListView listViewPersonas;
    private ViewUtil viewUtil;
    private GlobalState globalState;
    private UtilIpDAO ipDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_vehiculos);
        initComponents();
        viewUtil.setToolBar("Vehiculos Parqueados");
        getVehicles();
    }

    private void getVehicles(){
        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("Accept", "application/json"));
        final List<Parqueadero> listParking = new ArrayList<>();
        ip = ObtenerIp();
        if(ip!=null){
            ParqueaderoRestClient parqueaderoRestClient = new ParqueaderoRestClient(ip);
            parqueaderoRestClient.get(ConsultarVehiculos.this, "servicio/consultarvehiculosparqueados", headers.toArray(new Header[headers.size()]),
                    null, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                            Gson gson = new GsonBuilder().create();
                            Parqueadero parqueadero;
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    parqueadero = gson.fromJson(String.valueOf(response.getJSONObject(i)), Parqueadero.class);
                                    listParking.add(parqueadero);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            listParking.clear();
                            customAdapter = new cusAdapter(ConsultarVehiculos.this,listParking);
                            listViewPersonas.setAdapter(customAdapter);

                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                            super.onFailure(statusCode, headers, responseString, throwable);
                            showMesage(responseString);
                            customAdapter = new cusAdapter(ConsultarVehiculos.this,listParking);
                            listViewPersonas.setAdapter(customAdapter);


                        }
                    });
        }else{
            showMesage("La direccion ip no esta configurada,favor configurela en las opciones en la pantalla principal");
        }

    }


    private void initComponents() {
        listViewPersonas = findViewById(R.id.listViewVehiculos);
        globalState = (GlobalState) getApplication();
        ipDAO = new UtilIpDAO(globalState.getDataBaseHelper().getWritableDatabase());
        viewUtil = new ViewUtil(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public String ObtenerIp(){
        return ipDAO.consultarIp();
    }

    public void showMesage(String Message){
        final AlertDialog.Builder informacion = new AlertDialog.Builder(ConsultarVehiculos.this);
        informacion.setTitle("Mensage");
        informacion.setMessage(Message);
        informacion.setCancelable(false);
        informacion.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                informacion.setCancelable(true);
            }
        });
        informacion.show();
    }

}
