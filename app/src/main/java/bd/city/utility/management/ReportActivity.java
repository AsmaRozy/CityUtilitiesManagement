package bd.city.utility.management;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.management.R;
import com.google.android.gms.maps.model.LatLng;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ReportActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    static final int PICK_MAP_POINT_REQUEST = 999;  // The request code
    static final int CAMERA_REQUEST = 123;
    TextView location;
    EditText details;
    ImageView captured_image;
    Bitmap imageCap;
    Geocoder geocoder;
    String[] reportArray;
    Spinner spinner;
    String user, selectedIssue;
    UtilityDBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        dbAdapter = new UtilityDBAdapter(this);

        Intent rollIntent = getIntent();
        user = rollIntent.getStringExtra("User");
        selectedIssue = rollIntent.getStringExtra("Issue");

        captured_image = findViewById(R.id.capture_image);
        location = findViewById(R.id.tv_loc);
        details = findViewById(R.id.et_details);

        geocoder = new Geocoder(this, Locale.ENGLISH);

        spinner = findViewById(R.id.issue_type);
        reportArray = getResources().getStringArray(R.array.user);
        ArrayAdapter adapter = new ArrayAdapter(ReportActivity.this, android.R.layout.simple_spinner_dropdown_item, reportArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        spinner.setSelection((Arrays.asList(reportArray)).indexOf(selectedIssue));
    }

    public void cameraButton(View view){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    public void locButton(View view){
        Intent pickPointIntent = new Intent(this, MapsActivity.class);
        startActivityForResult(pickPointIntent, PICK_MAP_POINT_REQUEST);
    }

    public void submitButton(View view){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        imageCap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteImg = stream.toByteArray();
        String address = location.getText().toString();
        String issue = spinner.getSelectedItem().toString();
        String description = details.getText().toString();
        String person = user;
        String status = "In process..";

        if(dbAdapter.insertReport(byteImg, address, issue, description, person, status)<0){
            Toast.makeText(this, "Data can not be inserted!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Data inserted successfully!", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_MAP_POINT_REQUEST) {
            // Make sure the request was successful
            List<Address> addresses = new ArrayList<>();
            if (resultCode == RESULT_OK) {
                LatLng latLng = (LatLng) data.getParcelableExtra("picked_point");
                try {
                    addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String address = addresses.get(0).getAddressLine(0);
                location.setText(address);
            }
        }else {
            if(requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK){
                imageCap = (Bitmap) data.getExtras().get("data");
                captured_image.setImageBitmap(imageCap);
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}