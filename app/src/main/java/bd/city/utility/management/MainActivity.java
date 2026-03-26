package bd.city.utility.management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.window.SplashScreen;

import com.example.management.R;

public class MainActivity extends AppCompatActivity {
    private ImageView logo, building;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logo = (ImageView) findViewById(R.id.logo);
        building = (ImageView) findViewById(R.id.nagar_vobon);
        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mytransition);
        logo.startAnimation(myanim);
        building.startAnimation(myanim);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(5000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally {
                    Intent splash = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(splash);
                }
            }
        };
        timerThread.start();
    }

}