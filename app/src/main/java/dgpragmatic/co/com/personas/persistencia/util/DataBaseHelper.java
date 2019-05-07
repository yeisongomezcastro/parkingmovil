package dgpragmatic.co.com.personas.persistencia.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    private String createTableUri ="CREATE TABLE Ip(Ip TEXT)";

    public DataBaseHelper(Context context, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableUri);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("".concat(" DROP TABLE IF EXISTS ").concat("Ip"));
        db.execSQL(createTableUri);
    }
}
