package dgpragmatic.co.com.personas.util;

import android.support.v7.app.AppCompatActivity;

public class ViewUtil {

    private AppCompatActivity activity;

    public ViewUtil(AppCompatActivity activity){
        this.activity = activity;
    }

    public  void setToolBar(String title){
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        activity.getSupportActionBar().setTitle(title);
    }

}
