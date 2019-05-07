package dgpragmatic.co.com.personas.persistencia.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import dgpragmatic.co.com.personas.domain.Persona;

public class UtilIpDAO {
    private SQLiteDatabase db;
    private String tableName = "Ip";
    private String query = "";

    public UtilIpDAO(SQLiteDatabase db){
        this.db = db;
    }

    public void insertar(String Ip){
        eliminar();
        ContentValues row = new ContentValues();
        row.put("Ip", Ip);
        db.insert(tableName, null, row);
    }

    public String consultarIp(){
        query ="".concat("SELECT * FROM ").concat(tableName).concat(" Where 1=1");
        String Url = null;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Url = cursor.getString(cursor.getColumnIndex("Ip"));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return Url;
    }

    public void eliminar(){
        query ="".concat("Delete From ").concat(tableName);
        db.execSQL(query);
    }
}
