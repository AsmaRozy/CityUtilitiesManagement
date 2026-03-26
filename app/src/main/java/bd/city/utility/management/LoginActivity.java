package bd.city.utility.management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.management.R;

import bd.city.utility.management.RegistrationActivity;
import bd.city.utility.management.ReportUtilityListActivity;
import bd.city.utility.management.UtilityDBAdapter;

public class LoginActivity extends AppCompatActivity {

    EditText user, pass;
    UtilityDBAdapter dbAdapter;
    private static final String ROLL = "Roll";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = findViewById(R.id.et_user);
        pass = findViewById(R.id.et_pass);
        dbAdapter = new UtilityDBAdapter(this);
    }

    public void gotoServiceDetails(View view){
        String username = user.getText().toString();
        String password = pass.getText().toString();
        String result = dbAdapter.checkDBUser(username, password);
        System.out.println("Result"+ result);
        int user_check = Integer.parseInt(result.substring(0,1));
        String user_roll = result.substring(2,7);
        System.out.println("Roll: "+user_roll);

        if(user_check > 0){
            Intent intent = new Intent(this, ReportUtilityListActivity.class);
            intent.putExtra("user", username);
            intent.putExtra("Roll", user_roll);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Wrong username or password!", Toast.LENGTH_LONG).show();
        }
    }

    public void gotoRegistration(View view){
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }
}