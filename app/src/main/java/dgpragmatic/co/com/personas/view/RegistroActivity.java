package dgpragmatic.co.com.personas.view;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import dgpragmatic.co.com.personas.R;
import dgpragmatic.co.com.personas.cliente.ParqueaderoRestClient;
import dgpragmatic.co.com.personas.domain.Vehiculo;
import dgpragmatic.co.com.personas.util.ViewUtil;

public class RegistroActivity extends AppCompatActivity {

    private EditText plateTxt;
    private EditText typeTxt;
    private EditText ccTxt;
    private ViewUtil viewUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        initComponents();
        viewUtil.setToolBar(getString(R.string.registrar));
    }

    private void initComponents() {
        plateTxt = findViewById(R.id.plateTxt);
        typeTxt = findViewById(R.id.tipeVehicleTxt);
        ccTxt = findViewById(R.id.fechaIngresoxt);
        viewUtil = new ViewUtil(this);
    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void onClickGuardar(View view) {
        final Vehiculo vehiculo = new Vehiculo();
        vehiculo.setPlaca(plateTxt.getText().toString());
        vehiculo.setTipoVehiculo(typeTxt.getText().toString());
        vehiculo.setCilindraje(Integer.parseInt(String.valueOf(ccTxt.getText().toString())));
        ParqueaderoRestClient parqueaderoRestClient = new ParqueaderoRestClient(null);
        Gson gson = new Gson();
        String JSON = gson.toJson(vehiculo);
        StringEntity entity = new StringEntity(JSON.toString(), "UTF-8");
        parqueaderoRestClient.set(RegistroActivity.this, "servicio/registraringresovehiculo", entity, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                showMessage("Error " + statusCode +" - " +responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                showMessage("El Vehiculo de placa: " + vehiculo.getPlaca() + " se guardo correctamente!");
                borrarCampos();
            }
        });
        /*Integer id = "".equals(idTxt.getText().toString())?0:Integer.parseInt(idTxt.getText().toString());
        persona.setId(id);
        persona.setNombre(nombreTxt.getText().toString());
        persona.setApellido(apellidoTxt.getText().toString());
        if(validate()){
            personaDAO.insertar(persona);
            Toast.makeText(this,R.string.registro_satisfactorio,Toast.LENGTH_SHORT).show();
            borrarCampos();
        }*/
    }

    private void borrarCampos() {
        plateTxt.setText("");
        ccTxt.setText("");
        typeTxt.setText("");
    }

    public void showMessage(String message){
        final AlertDialog.Builder informacion = new AlertDialog.Builder(RegistroActivity.this);
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

    private boolean validate() {
       /* boolean isValid = true;
        if(persona.getId() == 0){
            idTxt.setError(getString(R.string.requerido));
            isValid = false;
        }else if("".equals(persona.getNombre())){
            nombreTxt.setError(getString(R.string.requerido));
            isValid = false;
        }else if("".equals(persona.getApellido())){
            apellidoTxt.setError(getString(R.string.requerido));
            isValid = false;
        }else if(personaDAO.consultarPorId(persona.getId()) != null){
            Toast.makeText(this,R.string.persona_existe,Toast.LENGTH_SHORT).show();
            isValid = false;
        }
        return isValid;*/
        return  true;
    }
}
