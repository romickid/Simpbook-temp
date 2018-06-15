package nkucs1416.simpbook.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import nkucs1416.simpbook.network.UpdateSubcategory;
import nkucs1416.simpbook.util.Class2;

import java.util.ArrayList;


/**
 * 处理数据库subcategory接口类
 */

public class SubcategoryDb {

    private SQLiteDatabase db;
    /**
     * 创建一个subcategoryDb实例
     * @param db_instance
     */
    public SubcategoryDb(SQLiteDatabase db_instance) {
        db = db_instance;
    }

    /**
     *  插入一条subcategory数据
     *
     *  @param subcategory_name 二级分类名
     *  @param subcategory_color 二级分类颜色
     *  @param fatherId 所属一级分类id
     */

    public String insertSubcategory(String  subcategory_name, int subcategory_color, int fatherId) {
        int category_id = fatherId;
        try {
            Cursor cursor = db.query("c_subcategory", new String[]{"subcategory_name"},
                    "subcategory_name = ? AND status != -1 AND subcategory_fatherID = ?",
                    new String[]{subcategory_name, category_id + ""}, null, null, null);
            int count = cursor.getCount();
            if (count > 0)
                return "DUPLICATE ERROR";

            ContentValues values = new ContentValues();
            values.put("subcategory_name", subcategory_name);
            values.put("subcategory_color", subcategory_color);
            values.put("subcategory_fatherID", category_id);
            values.put("status", 0);
            values.put("anchor", 0);
            long result = db.insert("c_subcategory", null, values);

            if (result > -1)
                return "SUCCESS";
            else return "UNKNOW SQL ERROR";
        } catch (SQLException e) {
            return "UNKNOW SQL ERROR";
        }
    }

    public void updateSubcategoryStatus(int[] subcategoryIdList, int[] isdelete) {
        int length = subcategoryIdList.length;
        for(int i = 0;i < length;i++) {
            ContentValues values = new ContentValues();
            if(isdelete[i] == 0) {
                values.put("status", 2);
            } else {
                values.put("status", -2);
            }
            db.update("c_subcategory", values, "subcategory_id = ?", new String[]{subcategoryIdList[i]+""});
        }
    }

    public void updateSubcategoryData(ArrayList<Class2> subcategoryArrayList) {
        deleteAllLocalData();
        int length = subcategoryArrayList.size();
        for(int i = 0;i < length;i++) {
            Class2 subcategory = subcategoryArrayList.get(i);
            ContentValues values = new ContentValues();
            values.put("subcategory_id", subcategory.getId());
            values.put("subcategory_name", subcategory.getName());
            values.put("subcategory_color", subcategory.getColor());
            values.put("subcategory_fatherID", subcategory.getFatherId());
            values.put("status", subcategory.getStatus());
            db.insert("c_subcategory", null, values);
        }
    }

    private void deleteAllLocalData() {
        String DELETE_ALL = "delete from c_subcategory";
        db.execSQL(DELETE_ALL);
    }


    public String updateSubcategory(int subcategory_id, String subcategory_name, int subcategory_color, int fatherId) {
        int category_id = fatherId;
        try {
            Cursor cursor = db.query("c_subcategory", new String[]{"subcategory_name"},
                    "subcategory_name = ? AND status != -1 AND subcategory_fatherID = ? AND subcategory_id != ?",
                    new String[]{subcategory_name, category_id + "", subcategory_id+""}, null, null, null);
            int count = cursor.getCount();
            if (count > 0)
                return "DUPLICATE ERROR";

            ContentValues values = new ContentValues();
            values.put("subcategory_name", subcategory_name);
            values.put("subcategory_color", subcategory_color);
            values.put("subcategory_fatherID", category_id);
            values.put("status", 1);
            values.put("anchor", 0);
            int result = db.update("c_subcategory", values, "subcategory_id = ?",
                    new String[]{subcategory_id + ""});
            if (result > 0)
                return "SUCCESS";
            else return "UNKNOW SQL ERROR";
        } catch (SQLException e) {
            return "UNKNOW SQL ERROR";
        }
    }

    public String  deleteSubcategory(int subcategory_id) {
        RecordDb recorddb = new RecordDb(db);
        boolean access = recorddb.isHaveRecord("c_subcategory", subcategory_id);
        if (access)
            return "HAVE RECORD";

        ContentValues values = new ContentValues();
        values.put("status", -1);
        values.put("anchor", 0);
        int result = db.update("c_subcategory", values, "subcategory_id = ?", new String[]{subcategory_id+""});

        if (result > 0)
            return "SUCCESS";
        else return "UNKNOW ERROR";
    }

    public boolean isHaveSubcategory (int category_id) {
        try {
            Cursor cursor = db.query("c_subcategory", new String[]{"subcategory_fatherID"},
                    "subcategory_fatherID = ? AND status != -1", new String[]{category_id + ""},
                    null, null, null);
            int count = cursor.getCount();
            if (count > 0)
                return true;
            else return false;
        } catch (SQLException e) {
            return false;
        }
    }

    public ArrayList<Class2> subcategoryList (int category_id) {

        Cursor cursor = db.query("c_subcategory", null,
                "subcategory_fatherID = ? AND status > -1", new String[]{category_id+""},
                null, null, null);
        cursor.moveToFirst();
        int count = cursor.getCount();
        ArrayList<Class2> subcatagoryArray = new ArrayList();
        for (int i=0;i<count;i++){
            int nameIndex = cursor.getColumnIndex("subcategory_name");
            String  subcategory_name = cursor.getString(nameIndex);
            int colorIndex = cursor.getColumnIndex("subcategory_color");
            int subcategory_color = cursor.getInt(colorIndex);
            int idIndex = cursor.getColumnIndex("subcategory_id");
            int subcategory_id = cursor.getInt(idIndex);
            Class2 subcategory = new Class2(subcategory_id, subcategory_name, subcategory_color);
            subcatagoryArray.add(subcategory);
            cursor.moveToNext();
        }
        return subcatagoryArray;
    }

    public ArrayList<Class2> subcategoryListUpdate () {

        Cursor cursor = db.query("c_subcategory", null,
                "status < 2 AND status > -2",null,
                null, null, null);
        cursor.moveToFirst();
        int count = cursor.getCount();
        ArrayList<Class2> subcatagoryArray = new ArrayList();
        for (int i=0;i<count;i++){
            int nameIndex = cursor.getColumnIndex("subcategory_name");
            String  subcategory_name = cursor.getString(nameIndex);
            int colorIndex = cursor.getColumnIndex("subcategory_color");
            int subcategory_color = cursor.getInt(colorIndex);
            int idIndex = cursor.getColumnIndex("subcategory_id");
            int subcategory_id = cursor.getInt(idIndex);
            int fatherIdIndex = cursor.getColumnIndex("subcategory_fatherID");
            int fatherId = cursor.getInt(fatherIdIndex);
            int statusIndex = cursor.getColumnIndex("status");
            int status = cursor.getInt(statusIndex);
            Class2 subcategory = new Class2(subcategory_id, subcategory_name, subcategory_color, fatherId, status);
            subcatagoryArray.add(subcategory);
            cursor.moveToNext();
        }
        return subcatagoryArray;
    }

    public void printSubCategory(ArrayList<Class2> subcategoryArray) {
        System.out.println("test subcategory print ***************");
        for (int i = 0;i < subcategoryArray.size();i++) {
            Class2 subcategory  = subcategoryArray.get(i);
            int id = subcategory.getId();
            String name = subcategory.getName();
            int color = subcategory.getColor();
            System.out.println(id +" "+ name +" "+ color);
        }
    }

    public void synchDataBackend(Context context) {
        UserDb userDb = new UserDb(context);
        String token = userDb.getUserToken();
        UpdateSubcategory updateSubcategory = new UpdateSubcategory(subcategoryListUpdate(), token, context, db);
    }
}