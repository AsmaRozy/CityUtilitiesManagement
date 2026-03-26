package bd.city.utility.management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.management.R;

import java.util.List;

public class ReportUtilityListActivity extends AppCompatActivity {
    GridView gridView, reportView, adminView, adminReportView;
    TextView form_title, myIssue, adminIssues;
    String username, userType;
    UtilityDBAdapter dbAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbAdapter = new UtilityDBAdapter(this);
        Intent rollIntent = getIntent();
        username = rollIntent.getStringExtra("user");
        userType = rollIntent.getStringExtra("Roll");


        if(userType.equals("admin")){
            setContentView(R.layout.activity_adminlist);
            this.setTitle(getResources().getString(R.string.utility_portal));
            adminView = findViewById(R.id.admin_issuelist);
            //form_title.setText(getResources().getString(R.string.utility_solution));
            //myIssue.setVisibility(View.INVISIBLE);
            String[] utility_title = getResources().getStringArray(R.array.admin);
            Integer[] imgid = {R.drawable.account, R.drawable.birth, R.drawable.issued};
            ReportListAdapter adapter = new ReportListAdapter(this, imgid, utility_title);
            adminView.setAdapter(adapter);
            adminView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    if(position == 0) {
                        //code specific to 1st list item
                        gotoAction(position);
                    }
                    else if(position == 1) {
                        //code specific to 2nd list item
                        gotoAction(position);
                    }
                    else if(position == 2) {
                        //code specific to 3rd list item
                        gotoAction(position);
                    }
                }
            });

        }else{
            setContentView(R.layout.activity_report_utility_list);
            this.setTitle(getResources().getString(R.string.report_portal));
            form_title = findViewById(R.id.portal_title);
            form_title.setText(getResources().getString(R.string.report_issue));
            form_title.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            gridView = findViewById(R.id.utility_list);
            myIssue = findViewById(R.id.my_issues);
            reportView = findViewById(R.id.reported_issuelist);

            Integer[] imgid = {R.drawable.road, R.drawable.light, R.drawable.water, R.drawable.drainage, R.drawable.garbage, R.drawable.mosquito, R.drawable.birth, R.drawable.call};
            String[] report_title = getResources().getStringArray(R.array.user);

            ReportListAdapter adapter = new ReportListAdapter(this, imgid, report_title);
            gridView.setAdapter(adapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    if(position == 0) {
                        //code specific to first list item
                        View viewItem = gridView.getChildAt(position);
                        TextView text;
                        String issueType = "Select Issue";
                        if (viewItem != null) {
                            text = (TextView) viewItem.findViewById(R.id.title);
                            issueType = text.getText().toString();
                        }
                        gotoReport(username, issueType);
                    }

                    else if(position == 1) {
                        //code specific to 2nd list item
                        View viewItem = gridView.getChildAt(position);
                        TextView text;
                        String issueType = "Select Issue";
                        if (viewItem != null) {
                            text = (TextView) viewItem.findViewById(R.id.title);
                            issueType = text.getText().toString();
                        }
                        gotoReport(username, issueType);
                    }

                    else if(position == 2) {
                        View viewItem = gridView.getChildAt(position);
                        TextView text;
                        String issueType = "Select Issue";
                        if (viewItem != null) {
                            text = (TextView) viewItem.findViewById(R.id.title);
                            issueType = text.getText().toString();
                        }
                        gotoReport(username, issueType);
                    }
                    else if(position == 3) {
                        View viewItem = gridView.getChildAt(position);
                        TextView text;
                        String issueType = "Select Issue";
                        if (viewItem != null) {
                            text = (TextView) viewItem.findViewById(R.id.title);
                            issueType = text.getText().toString();
                        }
                        gotoReport(username, issueType);
                    }
                    else if(position == 4) {
                        View viewItem = gridView.getChildAt(position);
                        TextView text;
                        String issueType = "Select Issue";
                        if (viewItem != null) {
                            text = (TextView) viewItem.findViewById(R.id.title);
                            issueType = text.getText().toString();
                        }
                        gotoReport(username, issueType);
                    }
                    else if(position == 5) {
                        View viewItem = gridView.getChildAt(position);
                        TextView text;
                        String issueType = "Select Issue";
                        if (viewItem != null) {
                            text = (TextView) viewItem.findViewById(R.id.title);
                            issueType = text.getText().toString();
                        }
                        gotoReport(username, issueType);
                    }
                    else if(position == 6) {
                        View viewItem = gridView.getChildAt(position);
                        TextView text;
                        String issueType = "Select Issue";
                        if (viewItem != null) {
                            text = (TextView) viewItem.findViewById(R.id.title);
                            issueType = text.getText().toString();
                        }
                        applyBirthCert();
                    }
                    else if(position == 7) {
                        String callTo = "999";
                        String uri = "tel:" + callTo;
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse(uri));
                        startActivity(intent);
                    }
                }
            });

        }

    }
    public void gotoReport(String username, String issueType){
        Intent intent = new Intent(this, ReportActivity.class);
        intent.putExtra("User", username);
        intent.putExtra("Issue", issueType);
        startActivity(intent);
    }

    public void applyBirthCert(){
        Intent intent = new Intent(this, BirthCertificateActivity.class);
        startActivity(intent);
    }

    public void viewMyReport(View view){
        List<ReportModel> result = dbAdapter.getAllReport(username);
        Bitmap[] reportImage = new Bitmap[result.size()];
        String[] report_title = new String[result.size()];
        int i = 0;
        for(ReportModel modelData : result){
            Bitmap img = BitmapFactory.decodeByteArray(modelData.getImgArray(), 0, modelData.getImgArray().length);
            String sb = modelData.getTextResult();
            reportImage[i] = img;
            report_title[i] = sb;
            i++;
        }

        ViewReportAdapter adapter = new ViewReportAdapter(this, reportImage, report_title);
        reportView.setAdapter(adapter);
    }

    public void gotoAction(int position){
        if(position==0){
            Intent intent = new Intent(this, UserManagementActivity.class);
            startActivity(intent);
        }else if(position==1){
            Intent intent = new Intent(this, BirthCertificateActivity.class);
            startActivity(intent);
        }else{
            List<ReportModel> result = dbAdapter.getAllReport(userType);
            String[] ids = new String[result.size()];
            Bitmap[] reportImage = new Bitmap[result.size()];
            String[] addresses = new String[result.size()];
            String[] issues = new String[result.size()];
            String[] descriptions = new String[result.size()];
            String[] persons = new String[result.size()];
            String[] updates = new String[result.size()];
            String[] report_title = new String[result.size()];
            int i = 0;
            for(ReportModel modelData : result){
                Bitmap img = BitmapFactory.decodeByteArray(modelData.getImgArray(), 0, modelData.getImgArray().length);
                String sb = modelData.getTextResult();
                ids[i]=modelData.getId();
                reportImage[i] = img;
                addresses[i] = modelData.getAddress();
                issues[i] = modelData.getType();
                descriptions[i] = modelData.getDetail();
                persons[i] = modelData.getPerson();
                updates[i] = modelData.getStatus();
                report_title[i] = sb;
                i++;
            }
            adminIssues = findViewById(R.id.user_issues);
            adminIssues.setVisibility(View.VISIBLE);
            adminReportView = findViewById(R.id.reported_userissues);
            ViewReportAdapter adapter = new ViewReportAdapter(this, reportImage, report_title);
            adminReportView.setAdapter(adapter);
            adminReportView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    System.out.println("Result List: "+result);
                    for(int i=0; i<=result.size(); i++){
                        if(i==position){
                            String issueid = ids[i];
                            byte[] img = result.get(i).getImgArray();
                            String address = result.get(i).getAddress();
                            String type = result.get(i).getType();
                            String details = result.get(i).getDetail();
                            String person = result.get(i).getPerson();
                            String status = result.get(i).getStatus();

                            gotoReportCounter(issueid, img, address, type, details, person, status);
                        }
                    }
                }
            });

        }
    }

    public void gotoReportCounter(String issueid, byte[] img, String address, String type, String details, String person, String status){
        Intent intent = new Intent(this, ReportSolutionActivity.class);
        intent.putExtra("id", issueid);
        intent.putExtra("image", img);
        intent.putExtra("add", address);
        intent.putExtra("type", type);
        intent.putExtra("detail", details);
        intent.putExtra("per", person);
        intent.putExtra("status", status);
        startActivity(intent);
    }
}