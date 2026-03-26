package bd.city.utility.management;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

public class UtilityDBAdapter {

    UtilityDbOpenHelper utilityDbOpenHelper;

    public UtilityDBAdapter(Context context){
        //calling the constructor of MySQLiteOpenHelper inner class
        utilityDbOpenHelper = new UtilityDbOpenHelper(context);
        SQLiteDatabase db = utilityDbOpenHelper.getWritableDatabase();
    }


    //Method to insert data to the database
    public long insertReport(byte[] img, String address, String type, String details, String person, String status){
        //to get the existing database open to do any operations on it
        SQLiteDatabase db = utilityDbOpenHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(utilityDbOpenHelper.ISSUE_IMG, img);
        contentValues.put(utilityDbOpenHelper.ISSUE_ADDRESS, address);
        contentValues.put(utilityDbOpenHelper.ISSUE_TYPE, type);
        contentValues.put(utilityDbOpenHelper.ISSUE_DETAILS, details);
        contentValues.put(utilityDbOpenHelper.ISSUE_PER, person);
        contentValues.put(utilityDbOpenHelper.ISSUE_STATUS, status);

        long numberOfInsertedRow = db.insert(utilityDbOpenHelper.TABLE_UTILITY, null, contentValues);
        db.close();
        return numberOfInsertedRow;
    }

    //Method to query and view data from the database
    public List<ReportModel> getAllReport(String username){
        SQLiteDatabase db = utilityDbOpenHelper.getWritableDatabase();

        ReportModel model = null;
        List<ReportModel> reportList = new ArrayList<ReportModel>();
        StringBuffer sb;

        String[] columns = {utilityDbOpenHelper.ISSUE_ID, utilityDbOpenHelper.ISSUE_IMG, utilityDbOpenHelper.ISSUE_ADDRESS, utilityDbOpenHelper.ISSUE_TYPE, utilityDbOpenHelper.ISSUE_DETAILS, utilityDbOpenHelper.ISSUE_PER, utilityDbOpenHelper.ISSUE_STATUS};
        Cursor cursor = db.query(utilityDbOpenHelper.TABLE_UTILITY, columns, null, null, null, null, null, null);
        //byte[] img = new byte[0];
        while (cursor.moveToNext()){
            int indext1 = cursor.getColumnIndex(utilityDbOpenHelper.ISSUE_ID);
            int indext2 = cursor.getColumnIndex(utilityDbOpenHelper.ISSUE_IMG);
            int indext3 = cursor.getColumnIndex(utilityDbOpenHelper.ISSUE_ADDRESS);
            int indext4 = cursor.getColumnIndex(utilityDbOpenHelper.ISSUE_TYPE);
            int indext5 = cursor.getColumnIndex(utilityDbOpenHelper.ISSUE_DETAILS);
            int indext6 = cursor.getColumnIndex(utilityDbOpenHelper.ISSUE_PER);
            int indext7 = cursor.getColumnIndex(utilityDbOpenHelper.ISSUE_STATUS);

            String id = cursor.getString(indext1);
            byte[] img = cursor.getBlob(indext2);
            String address = cursor.getString(indext3);
            String type = cursor.getString(indext4);
            String detail = cursor.getString(indext5);
            String per = cursor.getString(indext6);
            String status = cursor.getString(indext7);
            sb = new StringBuffer();
            if(username.equals("admin")){
                sb.append("Issue Id: "+id+", Address: ''"+address+"'', Issue Type: "+type+", Details: "+detail+", Reported By: "+per+", Status: "+status);
                model = new ReportModel(img, id, address, type, detail, per, status, sb.toString());
            }else{
                sb.append("Issue Id: "+id+", Address: ''"+address+"'', Issue Type: "+type+", Details: "+detail+", Status: "+status);
                model = new ReportModel(img, sb.toString());
            }
            reportList.add(model);
        }
        db.close();
        return reportList;
    }

    //Method to delete any data record from the database based on id
    public int deleteReport(String id){
        SQLiteDatabase db = utilityDbOpenHelper.getWritableDatabase();
        int state = db.delete(utilityDbOpenHelper.TABLE_UTILITY, "Id = ?",new String[]{id});
        return state;
    }

    //Method to update any data record of the database based on id
    public boolean updateReport(String id, byte[] img, String address, String type, String details, String person, String status){
        SQLiteDatabase db = utilityDbOpenHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(utilityDbOpenHelper.ISSUE_IMG, img);
        contentValues.put(utilityDbOpenHelper.ISSUE_ADDRESS, address);
        contentValues.put(utilityDbOpenHelper.ISSUE_TYPE, type);
        contentValues.put(utilityDbOpenHelper.ISSUE_DETAILS, details);
        contentValues.put(utilityDbOpenHelper.ISSUE_PER, person);
        contentValues.put(utilityDbOpenHelper.ISSUE_STATUS, status);

        db.update(utilityDbOpenHelper.TABLE_UTILITY, contentValues, "Id = ?",new String[]{id});

        return true;
    }

    //User database CRUD operation
    //Method to insert data to the user table
    public long insertData(String name, String mobile, String mail, String pass){
        //to get the existing database open to do any operations on it
        SQLiteDatabase db = utilityDbOpenHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(utilityDbOpenHelper.USER_NAME, name);
        contentValues.put(utilityDbOpenHelper.USER_MOBILE, mobile);
        contentValues.put(utilityDbOpenHelper.USER_MAIL, mail);
        contentValues.put(utilityDbOpenHelper.USER_PASS, pass);
        contentValues.put(utilityDbOpenHelper.USER_ROLL, "user");

        long numberOfInsertedRow = db.insert(utilityDbOpenHelper.TABLE_NAME_USER, null, contentValues);
        db.close();
        return numberOfInsertedRow;
    }

    //Method to query and view data from the user table
    public String getAllData(){
        SQLiteDatabase db = utilityDbOpenHelper.getWritableDatabase();

        String[] columns = {utilityDbOpenHelper.USER_ID, utilityDbOpenHelper.USER_NAME, utilityDbOpenHelper.USER_MOBILE, utilityDbOpenHelper.USER_MAIL, utilityDbOpenHelper.USER_ROLL};
        StringBuffer sb = new StringBuffer();
        Cursor cursor = db.query(utilityDbOpenHelper.TABLE_NAME_USER, columns, null, null, null, null, null, null);

        while (cursor.moveToNext()){
            int indext1 = cursor.getColumnIndex(utilityDbOpenHelper.USER_ID);
            int indext2 = cursor.getColumnIndex(utilityDbOpenHelper.USER_NAME);
            int indext3 = cursor.getColumnIndex(utilityDbOpenHelper.USER_MOBILE);
            int indext4 = cursor.getColumnIndex(utilityDbOpenHelper.USER_MAIL);
            int indext5 = cursor.getColumnIndex(utilityDbOpenHelper.USER_ROLL);

            String id = cursor.getString(indext1);
            String name = cursor.getString(indext2);
            String mobile = cursor.getString(indext3);
            String mail = cursor.getString(indext4);
            String roll = cursor.getString(indext5);

            sb.append(id+"  "+name+"  "+mobile+"  "+mail+"  "+roll+"\n");
        }
        db.close();
        return sb.toString();
    }

    //Method to check the login user
    public String checkDBUser(String user, String pass ){
        SQLiteDatabase db = utilityDbOpenHelper.getWritableDatabase();

        String[] columns = {utilityDbOpenHelper.USER_NAME, utilityDbOpenHelper.USER_MOBILE, utilityDbOpenHelper.USER_PASS, utilityDbOpenHelper.USER_ROLL};
        String whereClause = "NAME = ? and Password = ?";
        String[] whereargs = new String[]{user, pass};
        StringBuffer sb = new StringBuffer();
        Cursor cursor = db.query(utilityDbOpenHelper.TABLE_NAME_USER, columns, whereClause, whereargs, null, null, null, null);
        int count = cursor.getCount();
        while (cursor.moveToNext()){
            int indext1 = cursor.getColumnIndex(utilityDbOpenHelper.USER_ROLL);
            String roll = cursor.getString(indext1);
            sb.append(count+" "+roll+"\n");
        }

        cursor.close();
        return sb.toString();
    }

    //Method to delete any data record from the user table based on id
    public int deleteDataFromDb(String id){
        SQLiteDatabase db = utilityDbOpenHelper.getWritableDatabase();
        int state = db.delete(utilityDbOpenHelper.TABLE_NAME_USER, "Id = ?",new String[]{id});
        return state;
    }

    //Method to update any data record of the user table based on id
    public boolean updateDataOfDb(String id, String name, String mobile, String mail, String pass, String roll){
        SQLiteDatabase db = utilityDbOpenHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(utilityDbOpenHelper.USER_ID, id);
        contentValues.put(utilityDbOpenHelper.USER_NAME, name);
        contentValues.put(utilityDbOpenHelper.USER_MOBILE, mobile);
        contentValues.put(utilityDbOpenHelper.USER_MAIL, mail);
        contentValues.put(utilityDbOpenHelper.USER_PASS, pass);
        contentValues.put(utilityDbOpenHelper.USER_ROLL, roll);

        db.update(utilityDbOpenHelper.TABLE_NAME_USER, contentValues, "Id = ?",new String[]{id});

        return true;
    }

    //Method to save into Certificate DB
    public long insertCert(String fname, String lname, String date, String gender, String childNo, String country,
                           String div, String dist, String upazila, String ward, String post, String vil, String roadHouse){
        //to get the existing database open to do any operations on it
        SQLiteDatabase db = utilityDbOpenHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(utilityDbOpenHelper.BIRTH_FN, fname);
        contentValues.put(utilityDbOpenHelper.BIRTH_LN, lname);
        contentValues.put(utilityDbOpenHelper.BIRTH_DATE, date);
        contentValues.put(utilityDbOpenHelper.BIRTH_GEN, gender);
        contentValues.put(utilityDbOpenHelper.BIRTH_CHILDNO, childNo);
        contentValues.put(utilityDbOpenHelper.BIRTH_COUNTRY, country);
        contentValues.put(utilityDbOpenHelper.BIRTH_COUNTRY, country);
        contentValues.put(utilityDbOpenHelper.BIRTH_DIV, div);
        contentValues.put(utilityDbOpenHelper.BIRTH_DIST, dist);
        contentValues.put(utilityDbOpenHelper.BIRTH_UPAZILA, upazila);
        contentValues.put(utilityDbOpenHelper.BIRTH_WARD, ward);
        contentValues.put(utilityDbOpenHelper.BIRTH_POST, post);
        contentValues.put(utilityDbOpenHelper.BIRTH_VILLAGE, vil);
        contentValues.put(utilityDbOpenHelper.BIRTH_RH, roadHouse);

        long numberOfInsertedRow = db.insert(utilityDbOpenHelper.TABLE_BIRTH, null, contentValues);
        db.close();
        return numberOfInsertedRow;
    }

    //Method to query and view data from the user table
    public List<Certificate> getAllCert(){
        SQLiteDatabase db = utilityDbOpenHelper.getWritableDatabase();
        List<Certificate> certList = new ArrayList<>();
        Certificate cert;

        String[] columns = {utilityDbOpenHelper.BIRTH_ID, utilityDbOpenHelper.BIRTH_FN, utilityDbOpenHelper.BIRTH_LN,
                utilityDbOpenHelper.BIRTH_DATE, utilityDbOpenHelper.BIRTH_GEN, utilityDbOpenHelper.BIRTH_CHILDNO,
                utilityDbOpenHelper.BIRTH_COUNTRY, utilityDbOpenHelper.BIRTH_DIV, utilityDbOpenHelper.BIRTH_DIST,
                utilityDbOpenHelper.BIRTH_UPAZILA, utilityDbOpenHelper.BIRTH_WARD, utilityDbOpenHelper.BIRTH_POST,
                utilityDbOpenHelper.BIRTH_VILLAGE, utilityDbOpenHelper.BIRTH_RH};
        StringBuffer sb = new StringBuffer();
        Cursor cursor = db.query(utilityDbOpenHelper.TABLE_BIRTH, columns, null, null, null, null, null, null);

        while (cursor.moveToNext()){
            int indext1 = cursor.getColumnIndex(utilityDbOpenHelper.BIRTH_ID);
            int indext2 = cursor.getColumnIndex(utilityDbOpenHelper.BIRTH_FN);
            int indext3 = cursor.getColumnIndex(utilityDbOpenHelper.BIRTH_LN);
            int indext4 = cursor.getColumnIndex(utilityDbOpenHelper.BIRTH_DATE);
            int indext5 = cursor.getColumnIndex(utilityDbOpenHelper.BIRTH_GEN);
            int indext6 = cursor.getColumnIndex(utilityDbOpenHelper.BIRTH_CHILDNO);
            int indext7 = cursor.getColumnIndex(utilityDbOpenHelper.BIRTH_COUNTRY);
            int indext8 = cursor.getColumnIndex(utilityDbOpenHelper.BIRTH_DIV);
            int indext9 = cursor.getColumnIndex(utilityDbOpenHelper.BIRTH_DIST);
            int indext10 = cursor.getColumnIndex(utilityDbOpenHelper.BIRTH_UPAZILA);
            int indext11 = cursor.getColumnIndex(utilityDbOpenHelper.BIRTH_WARD);
            int indext12 = cursor.getColumnIndex(utilityDbOpenHelper.BIRTH_POST);
            int indext13 = cursor.getColumnIndex(utilityDbOpenHelper.BIRTH_VILLAGE);
            int indext14 = cursor.getColumnIndex(utilityDbOpenHelper.BIRTH_RH);

            String id = cursor.getString(indext1);
            String fname = cursor.getString(indext2);
            String lname = cursor.getString(indext3);
            String birthDate = cursor.getString(indext4);
            String gen = cursor.getString(indext5);
            String child = cursor.getString(indext6);
            String country = cursor.getString(indext7);
            String div = cursor.getString(indext8);
            String dist = cursor.getString(indext9);
            String upazila = cursor.getString(indext10);
            String ward = cursor.getString(indext11);
            String post = cursor.getString(indext12);
            String vill = cursor.getString(indext13);
            String road = cursor.getString(indext14);
            cert = new Certificate(fname, lname, birthDate, gen, child, country, div, dist, upazila, ward, post, vill, road);
            certList.add(cert);

            sb.append(id+"  "+fname+"  "+lname+"  "+birthDate+"  "+gen+"  "+child+"\n"+
                    country+"  "+div+"  "+dist+"  "+upazila+"  "+ward+"  "+post+"  "+vill+"  "+road+"\n");
        }
        db.close();
        return certList;
    }

    // Private inner class of MySQLiteAdapter
    private static class UtilityDbOpenHelper extends SQLiteOpenHelper {

        private final static String DATABASE_NAME = "utility_db.db";
        private final static int DATABASE_VERSION = 1;

        private final static String TABLE_NAME_USER = "user";
        private final static String USER_ID = "Id";
        private final static String USER_NAME = "Name";
        private final static String USER_MOBILE = "Mobile";
        private final static String USER_MAIL = "Mail";
        private final static String USER_PASS = "Password";
        private final static String USER_ROLL = "Roll";


        private final static String CREATE_TABLE_USER = "CREATE TABLE "+TABLE_NAME_USER+"("+USER_ID+
                " INTEGER PRIMARY KEY AUTOINCREMENT, "+USER_NAME+" VARCHAR(250), "+USER_MOBILE+
                " VARCHAR(250),"+USER_MAIL+" VARCHAR(250),"+USER_PASS+" VARCHAR(250),"+USER_ROLL+" VARCHAR(250));";

        private final static String TABLE_UTILITY = "utility";
        private final static String ISSUE_ID = "Id";
        private final static String ISSUE_IMG = "Image";
        private final static String ISSUE_ADDRESS = "Address";
        private final static String ISSUE_TYPE = "Type";
        private final static String ISSUE_DETAILS = "Details";
        private final static String ISSUE_PER = "Person";
        private final static String ISSUE_STATUS = "Status";

        private final static String CREATE_TABLE_UTILITY = "CREATE TABLE "+TABLE_UTILITY+"("+ISSUE_ID+
                " INTEGER PRIMARY KEY AUTOINCREMENT, "+ISSUE_IMG+" BLOB, "+ISSUE_ADDRESS+
                " VARCHAR(250),"+ISSUE_TYPE+" VARCHAR(250),"+ISSUE_DETAILS+" VARCHAR(250),"+ISSUE_PER+" VARCHAR(250),"+ISSUE_STATUS+" VARCHAR(250));";

        //Table Birth Certificate
        private final static String TABLE_BIRTH = "certificate";
        private final static String BIRTH_ID = "Id";
        private final static String BIRTH_FN = "First_Name";
        private final static String BIRTH_LN = "Last_Name";
        private final static String BIRTH_DATE = "Date";
        private final static String BIRTH_GEN = "Gender";
        private final static String BIRTH_CHILDNO = "Child_No";
        private final static String BIRTH_COUNTRY = "Country";
        private final static String BIRTH_DIV = "Division";
        private final static String BIRTH_DIST = "District";
        private final static String BIRTH_UPAZILA = "Upazilla";
        private final static String BIRTH_WARD = "Ward";
        private final static String BIRTH_POST = "Post_Office";
        private final static String BIRTH_VILLAGE = "Village";
        private final static String BIRTH_RH = "Road_House";

        private final static String CREATE_TABLE_CERT = "CREATE TABLE "+TABLE_BIRTH+"("+BIRTH_ID+
                " INTEGER PRIMARY KEY AUTOINCREMENT, "+BIRTH_FN+" VARCHAR(250), "+BIRTH_LN+" VARCHAR(250), "+BIRTH_DATE+
                " VARCHAR(250),"+BIRTH_GEN+" VARCHAR(250),"+BIRTH_CHILDNO+" VARCHAR(250),"+BIRTH_COUNTRY+" VARCHAR(250),"+
                BIRTH_DIV+" VARCHAR(250),"+BIRTH_DIST+" VARCHAR(250),"+BIRTH_UPAZILA+" VARCHAR(250),"+BIRTH_WARD+" VARCHAR(250),"+
                BIRTH_POST+" VARCHAR(250),"+BIRTH_VILLAGE+" VARCHAR(250),"+BIRTH_RH+" VARCHAR(250));";


        private Context myContext;


        public UtilityDbOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            myContext = context;
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            try {
                sqLiteDatabase.execSQL(CREATE_TABLE_USER);
                sqLiteDatabase.execSQL(CREATE_TABLE_UTILITY);
                sqLiteDatabase.execSQL(CREATE_TABLE_CERT);
            }catch (SQLException e){
                e.printStackTrace();
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_UTILITY);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_USER);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_BIRTH);
            onCreate(sqLiteDatabase);
        }
    }
}