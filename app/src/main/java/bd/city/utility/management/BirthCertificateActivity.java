package bd.city.utility.management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.management.R;

import java.util.List;

public class BirthCertificateActivity extends AppCompatActivity {
    UtilityDBAdapter dbAdapter;
    EditText et_fn, et_ln, et_date, et_coun, et_div, et_dist, et_upa, et_post, et_vil, et_rh;
    Spinner et_gen, et_chlno, et_wd;
    String fname, lname, date, gender, childNo, country, div, dist, upazila, ward, post, vil, roadHouse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birth_certificate);
        dbAdapter = new UtilityDBAdapter(this);

        et_fn = findViewById(R.id.et_fname);
        et_ln = findViewById(R.id.et_lname);
        et_date = findViewById(R.id.et_birthdate);

        et_gen = findViewById(R.id.et_gen);
        String[] gend = new String[]{"Male", "Female"};
        ArrayAdapter genadapter = new ArrayAdapter(BirthCertificateActivity.this, android.R.layout.simple_spinner_dropdown_item, gend);
        genadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        et_gen.setAdapter(genadapter);

        et_chlno = findViewById(R.id.et_childNo);
        String[] childNo = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        ArrayAdapter childNoadapter = new ArrayAdapter(BirthCertificateActivity.this, android.R.layout.simple_spinner_dropdown_item, childNo);
        genadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        et_chlno.setAdapter(childNoadapter);

        et_coun = findViewById(R.id.et_country);
        et_div = findViewById(R.id.et_div);
        et_dist = findViewById(R.id.et_dist);
        et_upa = findViewById(R.id.et_upazila);

        et_wd = findViewById(R.id.et_ward);
        String[] wdNo = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10","11","12","13","14","15"};
        ArrayAdapter wdNoAdapter = new ArrayAdapter(BirthCertificateActivity.this, android.R.layout.simple_spinner_dropdown_item, wdNo);
        genadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        et_wd.setAdapter(wdNoAdapter);

        et_post = findViewById(R.id.et_post);
        et_vil = findViewById(R.id.et_vil);
        et_rh = findViewById(R.id.et_road);
    }
    /*

    public void viewMyCert(View view){
        String birthCert = dbAdapter.getAllCert();
        Toast.makeText(this, "Saved Certificate: "+birthCert, Toast.LENGTH_LONG).show();
    }

     */
    public void viewMyCert(View view){
        List<Certificate> birthCert = dbAdapter.getAllCert();
        StringBuilder sb = new StringBuilder();
        for(Certificate cert: birthCert){

            sb.append(cert.getFname()+", "+cert.getLname()+", "+cert.getDate()+", "+cert.getGender()+", "+
                    cert.getChildNo()+", "+cert.getCountry()+", "+cert.getDiv()+", "+cert.getDist()+", "+
                    cert.getUpazila()+", "+cert.getWard()+", "+ cert.getPost()+", "+cert.getVil()+cert.getRoadHouse());
        }
        Toast.makeText(this, "Saved Certificate: "+sb.toString(), Toast.LENGTH_LONG).show();
    }

    public void applyCert(View view){
        fname = et_fn.getText().toString();
        lname = et_ln.getText().toString();
        date = et_date.getText().toString();
        gender = et_gen.getSelectedItem().toString();
        childNo = et_chlno.getSelectedItem().toString();

        country = et_coun.getText().toString();
        div = et_div.getText().toString();
        dist = et_dist.getText().toString();
        upazila = et_upa.getText().toString();
        ward = et_wd.getSelectedItem().toString();;
        post = et_post.getText().toString();
        vil = et_vil.getText().toString();
        roadHouse = et_rh.getText().toString();

        if(dbAdapter.insertCert(fname, lname, date, gender, childNo, country, div, dist, upazila, ward, post, vil, roadHouse)<0){
            Toast.makeText(this, "Data can not be inserted!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Data inserted successfully!"+"\n"+dbAdapter.getAllCert()+"\n", Toast.LENGTH_LONG).show();
        }
    }

}