package com.bwf.framework.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bwf.framework.base.BaseModel;
import com.bwf.framework.tools.Constants;

import com.bwf.p1_landz.MyApplication;


public class DBHelper extends SQLiteOpenHelper {

    private static DBHelper dbHelper;

    public static DBHelper getInstence() {
        if (dbHelper == null)
            dbHelper = new DBHelper(MyApplication.getApplication().getApplicationContext());
        return dbHelper;
    }

    private DBHelper(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


            try {
                for (int i = 0; i < Constants.TABLES.length; i++) {
                    BaseModel baseModel = Constants.TABLES[i];
                    db.execSQL(baseModel.getCreateTableSql());

                }
            } catch (Exception e) {
                e.printStackTrace();
        }


    }


    /**
     * 数据库升级
     *
     * @param sqLiteDatabase
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {



        for (int i = oldVersion + 1; i < newVersion; i++) {
            switch (i) {
                case 2:

                    break;
            }
        }


    }

    /**
     * 数据库降级
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }


}
