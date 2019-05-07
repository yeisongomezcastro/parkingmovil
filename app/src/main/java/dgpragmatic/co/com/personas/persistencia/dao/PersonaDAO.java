package dgpragmatic.co.com.personas.persistencia.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dgpragmatic.co.com.personas.domain.Persona;

public class PersonaDAO {

    private SQLiteDatabase db;
    private String tableName = "persona";
    private String query = "";

    public PersonaDAO(SQLiteDatabase db){
        this.db = db;
    }

    public void insertar(Persona persona){
        ContentValues row = new ContentValues();
        row.put("id", persona.getId());
        row.put("nombres", persona.getNombre());
        row.put("apellidos", persona.getApellido());
        db.insert(tableName, null, row);
    }

    public Persona consultarPorId(Integer id){
        query ="".concat("SELECT * FROM ").concat(tableName).concat(" WHERE id=").concat(id+"");
        Persona persona = null;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                persona = new Persona();
                persona.setId(cursor.getInt(cursor.getColumnIndex("id")));
                persona.setNombre(cursor.getString(cursor.getColumnIndex("nombres")));
                persona.setApellido(cursor.getString(cursor.getColumnIndex("apellidos")));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return persona;
    }

    public void actualizar(Persona persona){

    }

    public void eliminar(Integer id){
        query ="".concat("Delete From ").concat(tableName).concat(" WHERE id=").concat(id+"");
        db.execSQL(query);
    }

    public List<Persona> listar(){
        List<Persona> listaPersonas = new ArrayList<>();
                query ="".concat("SELECT * FROM ").concat(tableName);
        Persona persona = null;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                persona = new Persona();
                persona.setId(cursor.getInt(cursor.getColumnIndex("id")));
                persona.setNombre(cursor.getString(cursor.getColumnIndex("nombres")));
                persona.setApellido(cursor.getString(cursor.getColumnIndex("apellidos")));
                listaPersonas.add(persona);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return listaPersonas;
    }

}
