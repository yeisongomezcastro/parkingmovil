package dgpragmatic.co.com.personas.util;

import android.app.Application;

import dgpragmatic.co.com.personas.persistencia.util.DataBaseHelper;

public class GlobalState extends Application {

    private DataBaseHelper dataBaseHelper;

    public DataBaseHelper getDataBaseHelper() {
        return dataBaseHelper;
    }

    public void setDataBaseHelper(DataBaseHelper dataBaseHelper) {
        this.dataBaseHelper = dataBaseHelper;
    }
}
