package Datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Alejandro on 13/04/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Proyecto.db";
    public static final String TABLE_NAME = "messages_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "DE";
    public static final String COL_3 = "PARA";
    public static final String COL_4 = "MENSAJE";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,DE INTEGER,PARA INTEGER,MENSAJE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(int de,int para,String mensaje) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,de);
        contentValues.put(COL_3,para);
        contentValues.put(COL_4, mensaje);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(int de, int para){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where (DE=" + de + " and " + "PARA=" + para + ") or (" + "DE=" + para + " and " + "PARA=" + de + ")", null);



        return res;
        /*StringBuffer buffer = new StringBuffer();
        ArrayList<String> cosas = new ArrayList<String>();

        while (res.moveToNext()) {
            if (de==res.getInt(1)){
                cosas.add("Yo: "+res.getString(3));
            }
            else {
                cosas.add("El: "+res.getString(3));
            }
        }

        return cosas;*/
    }
}