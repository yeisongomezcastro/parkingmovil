package dgpragmatic.co.com.personas.view;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;
import dgpragmatic.co.com.personas.ConsultarVehiculos;
import dgpragmatic.co.com.personas.R;
import dgpragmatic.co.com.personas.cliente.ParqueaderoRestClient;
import dgpragmatic.co.com.personas.domain.Parqueadero;
import dgpragmatic.co.com.personas.domain.Vehiculo;
import dgpragmatic.co.com.personas.util.ViewUtil;

public class ConsultaActivity extends AppCompatActivity {
    private Parqueadero parqueadero;
    private ViewUtil viewUtil;
    private TextView txtPlate;
    private EditText txtPlateConsulta;
    private TextView txtType;
    private TextView txtFechaIngreso;
    private TextView txtFechaSalida;
    private TextView txtValorPagar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
        initComponents();
        showComponents(View.GONE);
    }

    private void showComponents(int gone) {
        txtPlate.setVisibility(gone);
        txtType.setVisibility(gone);
        txtFechaIngreso.setVisibility(gone);
        txtFechaSalida.setVisibility(gone);
        txtValorPagar.setVisibility(gone);
        viewUtil.setToolBar(getString(R.string.consultar));
    }

    private void initComponents() {
        txtPlateConsulta = findViewById(R.id.plateTxt);
        txtPlate = findViewById(R.id.txvPlate);
        txtType = findViewById(R.id.tipeVehicleTxt);
        txtFechaIngreso = findViewById(R.id.fechaIngresoxt);
        txtFechaSalida = findViewById(R.id.fechaSalidaTxt);
        txtValorPagar = findViewById(R.id.valorPagotxt);
        viewUtil = new ViewUtil(this);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void onClickBuscar(View view) {
        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("Accept", "application/json"));
        final Parqueadero[] parking = {new Parqueadero()};
        String plate = txtPlateConsulta.getText().toString();
        String URL = "servicio/registrarsalidavehiculo/" + plate;
        ParqueaderoRestClient parqueaderoRestClient = new ParqueaderoRestClient(null);

        parqueaderoRestClient.get(ConsultaActivity.this, URL, headers.toArray(new Header[headers.size()]),
                null, new TextHttpResponseHandler(){

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        showMessage("Error - " + statusCode +" "+responseString);
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        Gson gson = new GsonBuilder().create();
                        parking[0] = gson.fromJson(responseString, Parqueadero.class);
                        txtPlate.setText(parking[0].getPlaca());
                        txtType.setText(parking[0].getTipoVehiculo());
                        txtFechaIngreso.setText(new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss", Locale.getDefault()).format(parking[0].getFechaIngreso()));
                        txtFechaSalida.setText(new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss", Locale.getDefault()).format(parking[0].getFechaSalida()));
                        txtValorPagar.setText(parking[0].getValorPago().toString());
                        showComponents(View.VISIBLE);
                    }
                });



/*
        int id = "".equals(idTxt.getText().toString())?0:Integer.parseInt(idTxt.getText().toString());
        if(validate(id)){
            persona = personaDAO.consultarPorId(id);
            if(persona != null){
                nombreTxt.setText(persona.getNombre());
                apellidoTxt.setText(persona.getApellido());
                showComponents(View.VISIBLE);
            }else{
                Toast.makeText(this,R.string.not_found,Toast.LENGTH_SHORT).show();
            }
        }*/
    }

    private boolean validate(int id) {
        txtPlateConsulta.setError(null);
        boolean isValid = true;
        if(id == 0){
            isValid = false;
            txtPlateConsulta.setError(getString(R.string.requerido));
        }
        return isValid;
    }

    public void showMessage(String message){
        final AlertDialog.Builder informacion = new AlertDialog.Builder(ConsultaActivity.this);
        informacion.setTitle("Mensage");
        informacion.setMessage(message);
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
