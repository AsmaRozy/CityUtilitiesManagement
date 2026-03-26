package bd.city.utility.management;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

public class UserDBAdapter {

    UserDbOpenHelper userDbOpenHelper;

    public UserDBAdapter (Context context){
        //calling the constructor of MySQLiteOpenHelper inner class
        userDbOpenHelper = new UserDbOpenHelper(context);
        SQLiteDatabase db = userDbOpenHelper.getWritableDatabase();

    }

    //Method to insert data to the database
    public long insertData(String name, String mobile, String mail, String pass){
        //to get the existing database open to do any operations on it
        SQLiteDatabase db = userDbOpenHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(userDbOpenHelper.USER_NAME, name);
        contentValues.put(userDbOpenHelper.USER_MOBILE, mobile);
        contentValues.put(userDbOpenHelper.USER_MAIL, mail);
        contentValues.put(userDbOpenHelper.USER_PASS, pass);
        contentValues.put(userDbOpenHelper.USER_ROLL, "user");

        long numberOfInsertedRow = db.insert(userDbOpenHelper.TABLE_NAME_USER, null, contentValues);
        db.close();
        return numberOfInsertedRow;
    }

    //Method to query and view data from the database
    public String getAllData(){
        SQLiteDatabase db = userDbOpenHelper.getWritableDatabase();

        String[] columns = {userDbOpenHelper.USER_ID, userDbOpenHelper.USER_NAME, userDbOpenHelper.USER_MOBILE, userDbOpenHelper.USER_MAIL};
        StringBuffer sb = new StringBuffer();
        Cursor cursor = db.query(userDbOpenHelper.TABLE_NAME_USER, columns, null, null, null, null, null, null);

        while (cursor.moveToNext()){
            int indext1 = cursor.getColumnIndex(userDbOpenHelper.USER_ID);
            int indext2 = cursor.getColumnIndex(userDbOpenHelper.USER_NAME);
            int indext3 = cursor.getColumnIndex(userDbOpenHelper.USER_MOBILE);
            int indext4 = cursor.getColumnIndex(userDbOpenHelper.USER_MAIL);
            String id = cursor.getString(indext1);
            String name = cursor.getString(indext2);
            String mobile = cursor.getString(indext3);
            String mail = cursor.getString(indext4);

            sb.append(id+"  "+name+"  "+mobile+"  "+mail+"\n");
        }
        db.close();
        return sb.toString();
    }

    public String checkDBUser(String user, String pass ){
        SQLiteDatabase db = userDbOpenHelper.getWritableDatabase();

        String[] columns = {userDbOpenHelper.USER_NAME, userDbOpenHelper.USER_MOBILE, userDbOpenHelper.USER_PASS, userDbOpenHelper.USER_ROLL};
        String whereClause = "NAME = ? and Password = ?";
        String[] whereargs = new String[]{user, pass};
        StringBuffer sb = new StringBuffer();
        Cursor cursor = db.query(userDbOpenHelper.TABLE_NAME_USER, columns, whereClause, whereargs, null, null, null, null);
        int count = cursor.getCount();
        while (cursor.moveToNext()){
            int indext1 = cursor.getColumnIndex(userDbOpenHelper.USER_ROLL);
            String roll = cursor.getString(indext1);
            sb.append(count+" "+roll+"\n");
        }

        cursor.close();
        return sb.toString();
    }

    //Method to delete any data record from the database based on id
    public int deleteDataFromDb(String id){
        SQLiteDatabase db = userDbOpenHelper.getWritableDatabase();
        int state = db.delete(userDbOpenHelper.TABLE_NAME_USER, "Id = ?",new String[]{id});
        return state;
    }

    //Method to update any data record of the database based on id
    public boolean updateDataOfDb(String id, String name, String mobile, String mail, String pass){
        SQLiteDatabase db = userDbOpenHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(userDbOpenHelper.USER_ID, id);
        contentValues.put(userDbOpenHelper.USER_NAME, name);
        contentValues.put(userDbOpenHelper.USER_MOBILE, mobile);
        contentValues.put(userDbOpenHelper.USER_MAIL, mail);
        contentValues.put(userDbOpenHelper.USER_PASS, pass);
        contentValues.put(userDbOpenHelper.USER_ROLL, "Admin");

        db.update(userDbOpenHelper.TABLE_NAME_USER, contentValues, "Id = ?",new String[]{id});

        return true;
    }

    // Private inner class of MySQLiteAdapter
    private static class UserDbOpenHelper extends SQLiteOpenHelper {

        private final static String DATABASE_NAME = "city_utility.db";
        private final static int DATABASE_VERSION = 1;
        private final static String TABLE_NAME_USER = "user";
        private final static String USER_ID = "Id";
        private final static String USER_NAME = "Name";
        private final static String USER_MOBILE = "Mobile";
        private final static String USER_MAIL = "Mail";
        private final static String USER_PASS = "Password";
        private final static String USER_ROLL = "Roll";

        private final static String TABLE_NAME_SERVICE = "service";
        private final static String SERVICE_ID = "Id";
        private final static Bitmap ISSUE_LOCATION = null;
        private final static String ISSUE_ADDRESS = "Address";
        private final static String ISSUE_TYPE = "Issue";
        private final static String ISSUE_DETAILS = "Roll";


        private final static String CREATE_TABLE_USER = "CREATE TABLE "+TABLE_NAME_USER+"("+USER_ID+
                " INTEGER PRIMARY KEY AUTOINCREMENT, "+USER_NAME+" VARCHAR(250), "+USER_MOBILE+
                " VARCHAR(250),"+USER_MAIL+" VARCHAR(250),"+USER_PASS+" VARCHAR(250),"+USER_ROLL+" VARCHAR(250));";

        private final static String CREATE_TABLE_SERVICE = "CREATE TABLE "+TABLE_NAME_SERVICE+"("+USER_ID+
                " INTEGER PRIMARY KEY AUTOINCREMENT, "+USER_NAME+" VARCHAR(250), "+USER_MOBILE+
                " VARCHAR(250));";
        private Context myContext;


        public UserDbOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            myContext = context;
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            try {
                sqLiteDatabase.execSQL(CREATE_TABLE_USER);
            }catch (SQLException e){
                e.printStackTrace();
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }

}

