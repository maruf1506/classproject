package com.bidyarthibymmmcschool.Ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.bidyarthibymmmcschool.R;
import com.bidyarthibymmmcschool.BuildConfig;
import com.bidyarthibymmmcschool.Adapter.ClassAdapter;

import com.bidyarthibymmmcschool.Modal.Class;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    List<Class> productList;

    Toolbar toolbar;

    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;
    ClassAdapter adapter;

    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawerlayout);

        //Banner ads//
        if (getString(R.string.ads_switch).equals("true")){
            MobileAds.initialize(this, new OnInitializationCompleteListener() {
                @Override
                public void onInitializationComplete(InitializationStatus initializationStatus) {
                }
            });

            mAdView = findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }
        //Banner ADS//


        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.navigation_view);
        navigationView.setItemIconTintList(null);
        drawerLayout = findViewById(R.id.drawer);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    //COMMON

                    case R.id.home:
                        startActivity(getIntent());
                        break;
                    case R.id.principle:
                        Uri principle= Uri.fromFile(new File("//android_asset/principle.pdf"));
                        Intent ii = new Intent(MainActivity.this, pdf.class);
                        ii.putExtra("chapter_title","Message From The Principle");
                        ii.putExtra("chapter_link",principle);
                        startActivity(ii);
                        break;
                    case R.id.credit:
                        Uri credit= Uri.fromFile(new File("//android_asset/credit.pdf"));
                        Intent i = new Intent(MainActivity.this, pdf.class);
                        i.putExtra("chapter_title","Credit");
                        i.putExtra("chapter_link",credit);
                        startActivity(i);
                        break;

                    case R.id.exit:
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                        // Set the message show for the Alert time
                        builder.setMessage("Do you want to exit ?");

                        // Set Alert Title
                        builder.setTitle("Alert !");

                        // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
                        builder.setCancelable(false);

                        // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
                        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                            finish();
                            System.exit(0);
                        });

                        // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
                        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                            // If user click no then dialog box is canceled.
                            dialog.cancel();
                        });

                        // Create the Alert dialog
                        AlertDialog alertDialog = builder.create();
                        // Show the Alert Dialog box
                        alertDialog.show();
                        break;
                    case R.id.website:

                        Intent website = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.websiteurl)));
                        startActivity(website);
                        break;

                    case R.id.nav_privacy_policy:

                        Intent privacy = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.privacy)));
                        startActivity(privacy);

                        break;


                    case R.id.nav_terms_conditions:

                        Intent condition = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.termscondition)));
                        startActivity(condition);

                        break;


                    case R.id.more:

                        Intent more = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.moreurl)));
                        startActivity(more);
                        break;

                    //COMMON

                    //IMPORTANT//
                    case R.id.nav_rate:

                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
                        } catch (Exception ex) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id" + getPackageName())));
                        }


                        break;

                    //IMPORTANT//
                    case R.id.nav_share:
                        Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Bidyarthi by MMMC School");
                        String shareMessage = "This is the best application for Class 6-12 SEBA Sollution";
                        shareMessage = shareMessage + "http://play.google.com/store/apps/details?id" + BuildConfig.APPLICATION_ID;
                        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                        startActivity(Intent.createChooser(shareIntent, "choose one"));
                        break;


                    //IMPORTANT//

                }
                return true;

            }
        });


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_about_card_show);
        RelativeLayout relativeLayout = findViewById(R.id.rl);
        recyclerView.setAnimation(animation);
        classData();


    }

    private void classData() {
        productList = new ArrayList<>();
        productList.add(new Class(1, "Class-6", R.drawable.book_icon_red));
        productList.add(new Class(2, "Class-7", R.drawable.book_icon_red));
        productList.add(new Class(3, "Class-8", R.drawable.book_icon_red));
        productList.add(new Class(4, "Class-9", R.drawable.book_icon_red));
        productList.add(new Class(5, "Class-10", R.drawable.book_icon_red));
        productList.add(new Class(6, "Class-11", R.drawable.book_icon_red));
        productList.add(new Class(7, "Class-12", R.drawable.book_icon_red));
        productList.add(new Class(8, "Grammer", R.drawable.book_icon_red));
        productList.add(new Class(9, "Quiz", R.drawable.book_icon_red));
        productList.add(new Class(10, "Notice Board", R.drawable.book_icon_red));
        productList.add(new Class(11, "Exam Routines", R.drawable.book_icon_red));
        adapter = new ClassAdapter(this, productList);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filter(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void filter(String text) {
        ArrayList<Class> filteredList = new ArrayList<>();

        for (Class item : productList) {
            if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }
}