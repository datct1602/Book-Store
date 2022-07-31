package com.example.ps17284_chautandat_asm_duan.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, "name", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE THUTHU (USERNAME TEXT PRIMARY KEY, PASSWORD TEXT)";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO THUTHU VALUES ('tandat', '1') ";
        sqLiteDatabase.execSQL(sql);

        sql = "CREATE TABLE SACH (MASACH INTEGER PRIMARY KEY , TENSACH TEXT, TACGIA TEXT , GIA TEXT)";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO SACH VALUES (null, 'JAVA', 'JAVA', '100000')";
        sqLiteDatabase.execSQL(sql);


        sql = "CREATE TABLE LOAISACH (MALOAI INTEGER PRIMARY KEY , TENLOAI TEXT)";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO LOAISACH VALUES (null, 'IT')";
        sqLiteDatabase.execSQL(sql);

        sql = "CREATE TABLE PHIEUMUON (MAPHIEU INTEGER PRIMARY KEY , NGUOIMUON TEXT, SACHMUON TEXT, NGAYMUON TEXT)";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO PHIEUMUON VALUES (null, 'A', 'JAVA', '2021/10/05' )";
        sqLiteDatabase.execSQL(sql);

        sql = "CREATE TABLE THANHVIEN (MATV INTEGER PRIMARY KEY , TENTV TEXT, EMAIL TEXT, SDT TEXT, NGAYSINH TEXT)";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO THANHVIEN VALUES (null, 'A', 'a@gmail.com', '0123456789', '2002/01/06' )";
        sqLiteDatabase.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LOGIN");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS SACH");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LOAISACH");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PHIEUMUON");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS THANHVIEN");
        onCreate(sqLiteDatabase);
    }
}
