package dgpragmatic.co.com.personas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import dgpragmatic.co.com.personas.persistencia.dao.UtilIpDAO;
import dgpragmatic.co.com.personas.util.GlobalState;
import dgpragmatic.co.com.personas.util.ViewUtil;

public class IpActivity extends AppCompatActivity {

    EditText ip;
    private GlobalState globalState;
    private UtilIpDAO ipDAO;
    private ViewUtil viewUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip);
        initComponents();
        viewUtil.setToolBar("Configuración IP");
    }

    public void initComponents(){
        globalState = (GlobalState) getApplication();
        ipDAO = new UtilIpDAO(globalState.getDataBaseHelper().getWritableDatabase());
        ip = findViewById(R.id.editTextIp);
        viewUtil = new ViewUtil(this);
    }

    public void GuardarIp(View view) {
        String ipSave = ip.getText().toString();
        ipDAO.insertar(ipSave);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
