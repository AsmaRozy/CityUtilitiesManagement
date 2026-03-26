package bd.city.utility.management;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.management.R;

import java.util.Arrays;
import java.util.List;

public class UtilityServiceActivity extends AppCompatActivity {

    Button showlist;
    RadioGroup radioGroup;
    RadioButton selectedradioButton;
    ListView listView;
    ArrayAdapter<String> adminAdapter;
    ArrayAdapter<String> userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utility_service);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        listView=findViewById(R.id.item_list);
        showlist=findViewById(R.id.show_list);

    }
    public void showListItems(View view){
        int selectedId = radioGroup.getCheckedRadioButtonId();
        System.out.println(selectedId);
        selectedradioButton = (RadioButton) findViewById(selectedId);
        System.out.println(selectedradioButton.getText().toString());
        if(selectedId==-1){
            Toast.makeText(UtilityServiceActivity.this,"Nothing selected", Toast.LENGTH_SHORT).show();
        }
        else{
            if((selectedradioButton.getText().toString()).equals("User")){
                /// Getting list of Strings from your resource
                String[] userArray = getResources().getStringArray(R.array.user);
                List<String> userList = Arrays.asList(userArray);

                // Instanciating Adapter
                userAdapter = new ArrayAdapter<>(getBaseContext(),
                        android.R.layout.simple_list_item_1, userList);
                listView.setAdapter(userAdapter);
            }else{

                /// Getting list of Strings from your resource
                String[] adminArray = getResources().getStringArray(R.array.admin);
                List<String> adminList = Arrays.asList(adminArray);

                // Instanciating Adapter
                adminAdapter = new ArrayAdapter<>(getBaseContext(),
                        android.R.layout.simple_list_item_1, adminList);
                listView.setAdapter(adminAdapter);
            }

        }

    }
}