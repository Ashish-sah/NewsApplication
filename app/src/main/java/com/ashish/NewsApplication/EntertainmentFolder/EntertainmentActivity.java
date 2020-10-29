package com.ashish.NewsApplication.EntertainmentFolder;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ashish.NewsApplication.BusinessFolder.BusinessActivity;
import com.ashish.NewsApplication.DayNightPreference;
import com.ashish.NewsApplication.Health.HealthActivity;
import com.ashish.NewsApplication.R;
import com.ashish.NewsApplication.Science.ScienceActivity;
import com.ashish.NewsApplication.Sports.SportsActivity;
import com.ashish.NewsApplication.list_item;
import com.bestsoft32.tt_fancy_gif_dialog_lib.TTFancyGifDialog;
import com.bestsoft32.tt_fancy_gif_dialog_lib.TTFancyGifDialogListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EntertainmentActivity extends AppCompatActivity {
    Toolbar toolbar;
    ActionBar actionBar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    BottomNavigationView bottomNavigationView;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    static List<list_item> list = new ArrayList<>();
    DayNightPreference dayNightPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Making object of class   dayNightPreference
        dayNightPreference = new DayNightPreference(this);
        //changing the theme when enabled
        if (dayNightPreference.loadNightModeState()) {
            setTheme(R.style.darkTheme);
        } else
            setTheme(R.style.ScreenTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertainment);
        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
        //handler
        recyclerView = findViewById(R.id.Recycler_View);
        //setting the fixed size of every item in recycler view
        recyclerView.setHasFixedSize(true);
        //setting layout manager for recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //This will help you to hide the bottom navigation when scrolling
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 && bottomNavigationView.isShown()) {
                    bottomNavigationView.setVisibility(View.GONE);
                } else if (dy < 0) {
                    bottomNavigationView.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        setupUi();
        checkConnection();
    }

    private void fetchEntertainmentNews() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading news.....");
        progressDialog.show();
        String NewsUrl = "https://gnews.io/api/v4/search?q=entertainment&token=6fd62d15cb116f9a7b62cf1b370a7ec8";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, NewsUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            //to fetch the array data
                            JSONArray jsonArr = jsonObject.getJSONArray("articles");

                            for (int i = 0; i < jsonArr.length(); i++) {
                                JSONObject newsDetail = jsonArr.getJSONObject(i);
                                list_item news = new list_item(
                                        newsDetail.getString("image"),
                                        newsDetail.getString("title"),
                                        newsDetail.getString("description"),
                                        newsDetail.getString("content"),
                                        newsDetail.getString("url"));
                                list.add(news);
                            }
                            adapter = new EntertainmentAdapterClass(getApplicationContext());
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    /*--------To  avoid closing the application on back pressed  -----------------*/
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);

        } else {
            customDialog();
        }
    }

    public void customDialog() {
        new TTFancyGifDialog.Builder(this)
                .setTitle("Are you sure  want  to Exit this App ? ")
                .setPositiveBtnText("Exit")
                .setPositiveBtnBackground("#22b573")
                .setNegativeBtnText("Cancel")
                .setNegativeBtnBackground("#c1272d")
                .setGifResource(R.drawable.tenor)      //pass your gif, png or jpg
                .isCancellable(true)
                .OnPositiveClicked(new TTFancyGifDialogListener() {
                    @Override
                    public void OnClick() {
                        finish();
                    }
                })
                .OnNegativeClicked(new TTFancyGifDialogListener() {
                    @Override
                    public void OnClick() {
                    }
                })
                .build();

    }

    public void setupUi() {
        //setting of toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        drawerLayout = findViewById(R.id.DrawerLayout);
        navigationView = findViewById(R.id.navigation_view);
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.entertainmentNews);
        //tell application to do to and fro in navigation menu when button is clicked to change states and create animations
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        setupNavigationView();
        setupBottomNavigation();
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
    }

    private void setupBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switchBottomScreen(item.getItemId());
                return true;
            }
        });
    }


    private void setupNavigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switchScreen(item.getItemId());
                return true;
            }
        });
    }

    private boolean switchBottomScreen(int id) {
        switch (id) {
            case R.id.businessNews: {
                startActivity(new Intent(getApplicationContext(), BusinessActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            }
            case R.id.entertainmentNews: {
                return true;

            }
            case R.id.healthNews: {
                startActivity(new Intent(getApplicationContext(), HealthActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            }
            case R.id.scienceNews: {
                startActivity(new Intent(getApplicationContext(), ScienceActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            }
            case R.id.sportsNews: {
                startActivity(new Intent(getApplicationContext(), SportsActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            }
        }
        return false;
    }

    private void switchScreen(int id) {
        switch (id) {
            case R.id.aboutDev: {
                Intent intent = new Intent(getApplicationContext(), EntertainmentDeveloper.class);
                startActivity(intent);
                drawerLayout.closeDrawers();
                finish();
                break;
            }
            case R.id.dark_mode: {
                //code for setting dark mode
                //true for dark mode, false for day mode, currently toggling on each click
                // DayNightPreference dayNightPreference=new DayNightPreference(this);
                dayNightPreference.setNightModeState(!dayNightPreference.loadNightModeState());
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                restartApp();
                break;
            }
            case R.id.share: {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                //set the type for the sharing thing ie format
                sharingIntent.setType("text/plain");
                String shareBody = "Application source code is available at: " + " \n\n Github : https://github.com/Ashish-sah/NewsApplication" + "\n\n Follow him on GitHub : https://github.com/Ashish-sah";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
                drawerLayout.closeDrawers();
                break;
            }
        }
    }

    //To check the connection Status
    public void checkConnection() {
        ConnectivityManager manager = (ConnectivityManager)
                getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        //get Active network info
        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
        //check network status
        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                fetchEntertainmentNews();
            }
            //now it see for mobile data
            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                fetchEntertainmentNews();
            }

        } else {
            //Initialize Dialog
            Dialog dialog = new Dialog(this);
            //set Content View
            dialog.setContentView(R.layout.no_internet_available);
            //set outside touch
            dialog.setCanceledOnTouchOutside(false);
            //set dialog width and height
            dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
            // set transparent background
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            // set animation
            dialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;
            //Initialize dialog variable
            Button btnTryAgain = dialog.findViewById(R.id.btnTryAgain);
            btnTryAgain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recreate();
                }
            });
            //Show dialog
            dialog.show();
        }
    }


    public void restartApp() {
        Intent i = new Intent(getApplicationContext(), EntertainmentActivity.class);
        startActivity(i);
        finish();
    }
}