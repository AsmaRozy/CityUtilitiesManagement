package bd.city.utility.management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.management.R;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.Locale;

public class ReportSolutionActivity extends AppCompatActivity {
    ImageView loaded_image;
    TextView location;
    Spinner statusUp;
    EditText issueId, issueType, details;

    byte[] image;
    String issue_id, address, type, detail, person, status;
    Bitmap imgBitmap;
    UtilityDBAdapter dbAdapter;
    String[] statusArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_solution);

        dbAdapter = new UtilityDBAdapter(this);

        issueId = findViewById(R.id.et_issueid);
        loaded_image = findViewById(R.id.capture_image);
        location = findViewById(R.id.tv_loc);
        issueType = findViewById(R.id.issue_type);
        details = findViewById(R.id.et_details);
        statusUp = findViewById(R.id.issue_status);
        statusArray = new String[]{"In process..", "Solved"};
        ArrayAdapter adapter = new ArrayAdapter(ReportSolutionActivity.this, android.R.layout.simple_spinner_dropdown_item, statusArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusUp.setAdapter(adapter);

        Intent intent = getIntent();
        issue_id = intent.getStringExtra("id");
        image = intent.getByteArrayExtra("image");
        imgBitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        address = intent.getStringExtra("add");
        type = intent.getStringExtra("type");
        detail = intent.getStringExtra("detail");
        person = intent.getStringExtra("per");
        status = intent.getStringExtra("status");
    }

    @Override
    protected void onResume() {
        super.onResume();
        issueId.setText(issue_id);
        loaded_image.setImageBitmap(imgBitmap);
        location.setText(address);
        issueType.setText(type);
        details.setText(detail);
        statusUp.setSelection((Arrays.asList(statusArray)).indexOf(status));
    }

    public void updateReport(View view){
        String statusUpdate = statusUp.getSelectedItem().toString();
        Boolean updateStatus = dbAdapter.updateReport(issue_id, image, address, type, detail, person, statusUpdate);

        if(updateStatus){
            Toast.makeText(this, "Report updated successfully!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Report data can not be updated!", Toast.LENGTH_LONG).show();
        }
    }

    public void deleteReport(View view){
        int i = dbAdapter.deleteReport(issue_id);
        if(i==1){
            Toast.makeText(this, "Report deleted successfully!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Report can not be deleted!", Toast.LENGTH_LONG).show();
        }
    }
}