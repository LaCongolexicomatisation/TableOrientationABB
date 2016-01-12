package fr.lpteprow.abb.tableorientationabb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Anthony on 12/01/2016.
 */

public class DataBase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "DBTableOrientation.db";
    public static final String TABLE_NAME = "sommets";
    public static final String ID ="id";
    public static final String LONGITUDE ="longitude" ;
    public static final String LATITUDE ="latitude";
    public static final String NOM ="nom" ;
    public static final String ALTITUDE ="altitude" ;

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY,"
                + LONGITUDE + " TEXT,"
                + LATITUDE + " TEXT,"
                + NOM + " TEXT,"
                + ALTITUDE + " INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void blabla(){
        String reqInsertion4Sommets [] ={
                "INSERT INTO sommets VALUES (938282869, '6.7736388990571', '47.7672319948997', 'Planche des Belles Filles', 1148);",
                "INSERT INTO sommets VALUES (1116621810, '6.84486789904718', '47.8224908948974', 'Ballon d''Alsace', 1247);",
                "INSERT INTO sommets VALUES (1762485578, '6.92214789903643', '47.7727801948995', 'Le Baerenkopf', 1074);"};
        SQLiteDatabase db = this.getWritableDatabase();
        for    (String s : reqInsertion4Sommets){
            db.execSQL(s);
        }
        db.close();
    }
}

