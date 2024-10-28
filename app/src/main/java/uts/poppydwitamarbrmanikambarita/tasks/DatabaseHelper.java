package uts.poppydwitamarbrmanikambarita.tasks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

        public static final String DATABASE_NAME = "agenda.db";
        public static final String TABLE_NAME = "agenda_table";
        public static final String COL_1 = "ID";
        public static final String COL_2 = "NAMA";
        public static final String COL_3 = "DESKRIPSI";
        public static final String COL_4 = "STATUS";

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAMA TEXT, DESKRIPSI TEXT, STATUS TEXT)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }

        public boolean insertData(String nama, String deskripsi, String status) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_2, nama);
            contentValues.put(COL_3, deskripsi);
            contentValues.put(COL_4, status);
            long result = db.insert(TABLE_NAME, null, contentValues);
            return result != -1;
        }
    // Method untuk mengambil semua data agenda
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    // Method untuk memperbarui data agenda
    public boolean updateData(String id, String nama, String deskripsi, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, nama);
        contentValues.put(COL_3, deskripsi);
        contentValues.put(COL_4, status);

        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{id});
        return true;
    }

    // Method untuk menghapus data agenda
    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[]{id});
    }
}

