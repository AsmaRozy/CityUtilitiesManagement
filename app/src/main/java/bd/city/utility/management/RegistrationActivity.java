package bd.city.utility.management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.management.R;

public class RegistrationActivity extends AppCompatActivity {

    EditText user, mobile, mail, pass;
    UtilityDBAdapter userDbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        user = findViewById(R.id.et_user);
        mobile = findViewById(R.id.et_mobile);
        mail = findViewById(R.id.et_mail);
        pass = findViewById(R.id.et_pass);
        userDbAdapter = new UtilityDBAdapter(this);
    }

    public void regiButton(View view){
        String name = user.getText().toString();
        String mobileNo = mobile.getText().toString();
        String mailAdd = mail.getText().toString();
        String password = pass.getText().toString();
        if(userDbAdapter.insertData(name, mobileNo, mailAdd, password)<0){
            Toast.makeText(this, "Data can not be inserted!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Data inserted successfully!"+"\n"+userDbAdapter.getAllData()+"\n", Toast.LENGTH_LONG).show();
        }

    }

}