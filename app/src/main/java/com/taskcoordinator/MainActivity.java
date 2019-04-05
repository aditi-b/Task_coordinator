package com.taskcoordinator;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.taskcoordinator.adapter.ViewPagerAdapter;
import com.taskcoordinator.fragments.FragmentCall;
import com.taskcoordinator.fragments.FragmentContacts;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    String[] app = {Manifest.permission.READ_CONTACTS};
    private static final int MY_PERMISSIONS_REQUEST = 1;

    public static boolean hasPermissions(Context context, Activity activity, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                } else {
                    ActivityCompat.requestPermissions(activity, permissions,
                            MY_PERMISSIONS_REQUEST);
                }

            }
        }
        return true;
    }

    public void showAlertDialogButtonClicked() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("My title");
        builder.setMessage("Open settings to grant permision");

        builder.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getBaseContext().getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            }
        });

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!hasPermissions(MainActivity.this, MainActivity.this, app)) {
            ActivityCompat.requestPermissions(MainActivity.this, app, MY_PERMISSIONS_REQUEST);
        }

        ViewPager viewPager = findViewById(R.id.tab_viewpager);
//        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tabLayout);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(new FragmentCall(), "Image");
        viewPagerAdapter.addFragment(new FragmentContacts(), "Contacts");


        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager); //Assigning viewpager to tab layout

        Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.ic_insert_photo_black_24dp);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.ic_contacts);

    }

    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST: {
                for (int i = 0, len = permissions.length; i < len; i++) {
                    String permission = permissions[i];
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {

                        boolean showRationale = shouldShowRequestPermissionRationale(permission);
                        if (!showRationale) {

                            showAlertDialogButtonClicked();

                        }

                    } else {

                        requestPermissions(
                                permissions,
                                MY_PERMISSIONS_REQUEST);
                    }
                    return;
                }

            }
        }
    }
}
