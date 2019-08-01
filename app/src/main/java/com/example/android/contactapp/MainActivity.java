package com.example.android.contactapp;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.contactapp.models.Contacts;
import com.example.android.contactapp.utils.UniversalImageLoaer;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MainActivity extends AppCompatActivity implements ViewContactFragment.OnContactSelectedListener {

    private static final String TAG = "MainActivity";
    public static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initImageLoader();
        init();
    }

    /**
     * initialize the contact view fragment
     */
    private void init(){
        ViewContactFragment fragment = new ViewContactFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragment_container,fragment).commit();
    }

    private void initImageLoader(){
        UniversalImageLoaer universalImageLoaer = new UniversalImageLoaer(this);
        ImageLoader.getInstance().init(universalImageLoaer.getConfig());
    }

    @Override
    public void onContactSelected(Contacts contact) {
        ContactFragment fragment = new ContactFragment();
        Bundle args = new Bundle();
        args.putParcelable(getString(R.string.contact), contact);
        fragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,fragment);
        transaction.addToBackStack(getString(R.string.contact_fragment));
        transaction.commit();
    }

    /**
     * generalised method of asking permission
     * @param permissions list of permission
     */
    public void verifyPermission(String[] permissions){
        ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE);
    }

    /**
     * checks that the permission is granted or not
     * only one permission at a time
     * @param permissions
     * @return
     */
    public boolean checkPermission(String[] permissions){
        int permissionRequest = ActivityCompat.checkSelfPermission(this, permissions[0]);
        if(permissionRequest != PackageManager.PERMISSION_GRANTED){
            return false;
        }else{
            return true;
        }
    }
}
