package bd.city.utility.management;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.management.R;

public class UserManagementActivity extends AppCompatActivity {
    UtilityDBAdapter userDbAdapter;
    TextView userList;
    EditText id, user, mob, mail, pass, roll;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_management);

        id = findViewById(R.id.et_id);
        user = findViewById(R.id.et_user);
        mob = findViewById(R.id.et_mobile);
        mail = findViewById(R.id.et_mail);
        pass = findViewById(R.id.et_pass);
        spinner = findViewById(R.id.roll_type);
        String[] userRoll = {"admin", "user"};
        ArrayAdapter adapter = new ArrayAdapter(UserManagementActivity.this, android.R.layout.simple_spinner_dropdown_item, userRoll);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        userList = findViewById(R.id.tv_userlist);
        userDbAdapter = new UtilityDBAdapter(this);
    }

    public void viewButton(View view){
        String user = userDbAdapter.getAllData();
        userList.setText(user);
    }
    public void updateButton(View view){
        userList.setText("");
        String userid = id.getText().toString();
        String name = user.getText().toString();
        String mobileNo = mob.getText().toString();
        String mailAdd = mail.getText().toString();
        String password = pass.getText().toString();
        String roll = spinner.getSelectedItem().toString();
        Boolean status = userDbAdapter.updateDataOfDb(userid, name, mobileNo, mailAdd, password, roll);
        if(status){
            Toast.makeText(this, "User data updated successfully!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "User data can not be updated!", Toast.LENGTH_LONG).show();
        }
    }
    public void deleteButton(View view){
        userList.setText("");
        String userid = id.getText().toString();
        int i = userDbAdapter.deleteDataFromDb(userid);
        if(i==1){
            Toast.makeText(this, "User data deleted successfully!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "User data can not be deleted!", Toast.LENGTH_LONG).show();
        }
    }
}