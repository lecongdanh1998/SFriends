package vn.edu.poly.sfriends.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "duno";
    private static final int DATABASE_VERSION = 1;
    private HashMap hp;
    public static String table_name = "dunothang";
    public static String ID = "id";
    public static String SO_GOC_CON_LAI = "so_goc_con_lai";
    public static String GOC = "goc";
    public static String LAI = "lai";
    public static String TONG_GOC_LAI = "tong_goc_lai";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " + table_name + "(" + ID + " integer primary key, " +
                        SO_GOC_CON_LAI + " double," + GOC + " double, " + LAI + " double," +
                        TONG_GOC_LAI + " double)"
        );
    }

    public void insertData(double so_goc_con_lai, double goc, double lai, double tong_goc_lai) {
        SQLiteDatabase db1 = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SO_GOC_CON_LAI, so_goc_con_lai);
        contentValues.put(GOC, goc);
        contentValues.put(LAI, lai);
        contentValues.put(TONG_GOC_LAI, tong_goc_lai);
        db1.insert(table_name, null, contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);

    }

    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + table_name + " ",
                null);
        return res;
    }

    public void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = getData();
        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(cursor.getColumnIndex(ID));
                String query = "DELETE FROM " + table_name + "WHERE id=" + id;
                db.execSQL(query);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    public void removeAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table_name, null, null);
    }
    //so_goc_con_lai, goc, lai, tong_goc_lai
}
