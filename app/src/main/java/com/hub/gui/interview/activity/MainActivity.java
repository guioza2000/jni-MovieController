package com.hub.gui.interview.activity;

import android.os.Bundle;
import android.util.Log;

import com.hub.gui.interview.R;
import com.hub.gui.interview.fragment.ListFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    public static final String TAG ="MainActivity";
    public static final int MAIN_CONTAINER = R.id.main_container;


    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Fragment f = new ListFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(MAIN_CONTAINER,f)
                .commit();
        Log.i(TAG,stringFromJNI());
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

}
