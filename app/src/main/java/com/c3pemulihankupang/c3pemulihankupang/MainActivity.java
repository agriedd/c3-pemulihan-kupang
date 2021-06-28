package com.c3pemulihankupang.c3pemulihankupang;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.c3pemulihankupang.c3pemulihankupang.databinding.ActivityMainBinding;
import com.c3pemulihankupang.c3pemulihankupang.models.MenuItemLink;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.ContentApi;
import com.spotify.android.appremote.api.SpotifyAppRemote;

import com.spotify.protocol.client.CallResult;
import com.spotify.protocol.client.Subscription;
import com.spotify.protocol.types.ListItems;
import com.spotify.protocol.types.PlayerState;
import com.spotify.protocol.types.Track;

import org.jetbrains.annotations.NotNull;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private static final String CLIENT_ID = "2282371be3854961b2515eea4d038584";
    private static final String REDIRECT_URI = "https://c3pemulihankupang.com/callback.php";
    private SpotifyAppRemote mSpotifyAppRemote;
    private ActivityMainBinding binding;
    private ConnectionParams connectionParams;
    private String spotifyPlayId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MaterialToolbar toolbar = binding.appBarMain.toolbar;
        setSupportActionBar(toolbar);
        DrawerLayout drawer = binding.drawerLayout;
//        toolbar.setNavigationOnClickListener(v ->
//                drawer.openDrawer(GravityCompat.START, true)
//        );
        drawer.setScrimColor(getResources().getColor(R.color.transparent));
        drawer.setDrawerElevation(200f);
        CoordinatorLayout container = binding.appBarMain.container;
        drawer.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                final float diffScaledOffset = slideOffset * (1 - 1f);
                final float offsetScale = 1 - diffScaledOffset;
                container.setScaleX(offsetScale);
                container.setScaleY(offsetScale);

                final float xOffset = drawerView.getWidth() / 1f * slideOffset;
                final float xOffsetDiff = container.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                container.setTranslationX(xTranslation);
            }
        });
//        binding.appBarMain.bottomNavigationView.setupWith;
//        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        BottomNavigationView navigationView = binding.appBarMain.bottomNavigationView;

//        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_new, R.id.nav_podcast)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        binding.navView.setNavigationItemSelectedListener(item -> {
            if(item.getTitle().toString().equalsIgnoreCase("website")){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://c3pemulihankupang.com/"));
                startActivity(intent);
            } else if(item.getTitle().toString().equalsIgnoreCase("facebook")) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://facebook.com/C3RestorationKupang"));
                startActivity(intent);
            } else if(item.getTitle().toString().equalsIgnoreCase("instagram")) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/c3reachrestorationkupang/?igshid=82egtq6qbyhq"));
                startActivity(intent);
            } else if(item.getTitle().toString().equalsIgnoreCase("youtube")) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/c/C3ReachPemulihanKupang/videos"));
                startActivity(intent);
            }
            return true;
        });


        connectionParams =
            new ConnectionParams.Builder(CLIENT_ID)
                    .setRedirectUri(REDIRECT_URI)
                    .showAuthView(true)
                    .build();

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if(destination.getLabel().toString().equalsIgnoreCase("Podcast")){
                if(mSpotifyAppRemote == null || !mSpotifyAppRemote.isConnected())
                    connectSpotify();
            }
        });

        binding.navView.setItemIconTintList(null);
        binding.lokasi.setOnClickListener(v -> {
            Uri gmmIntentUri = Uri.parse("google.streetview:cbll=-10.158381594322405,123.61240518650916");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        });

        binding.appBarMain.titlePodcast.setSelected(true);
        binding.appBarMain.togglepodcast.setOnClickListener(v -> {

        });

        if(SpotifyAppRemote.isSpotifyInstalled(this)){
            if(spotifyPlayId != null){
                connectSpotify();
            }
        } else {
            Snackbar snackbar = Snackbar.make((View) binding.appBarMain.container, "Silahkan install Spotify agar aplikasi dapat berjalan dengan semestinya", Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction("mengerti", v -> {
                snackbar.dismiss();
            }).show();
        }


        binding.appBarMain.closeMiniplayer.setOnClickListener(v -> {
            mSpotifyAppRemote.getPlayerApi().pause().setResultCallback(data -> {
                SpotifyAppRemote.disconnect(mSpotifyAppRemote);
                binding.appBarMain.miniPlayerPodcast.setVisibility(View.GONE);
            });
        });
    }

    private void connectSpotify() {
        SpotifyAppRemote.connect(this, connectionParams,
                new Connector.ConnectionListener() {

                    @Override
                    public void onConnected(SpotifyAppRemote spotifyAppRemote) {
                        mSpotifyAppRemote = spotifyAppRemote;
                        Log.d("MainActivity", "Connected! Yay!");
                        // Now you can start interacting with App Remote
                        connected();
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        //                        Log.e("MainActivity", throwable.getMessage(), throwable);

                        // Something went wrong when attempting to connect! Handle errors here
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    private void connected() {

        binding.appBarMain.miniPlayerPodcast.setVisibility(View.VISIBLE);
        binding.appBarMain.togglepodcast.setImageResource(R.drawable.ic_round_play_arrow_24);

        mSpotifyAppRemote.getPlayerApi().getPlayerState().setResultCallback(data -> {
            if(data != null)
                binding.appBarMain.titlePodcast.setText(data.track.name);
            if(!data.isPaused)
                binding.appBarMain.togglepodcast.setImageResource(R.drawable.ic_baseline_pause_24);
        });

        binding.appBarMain.togglepodcast.setOnClickListener(v -> {
            mSpotifyAppRemote.getPlayerApi().getPlayerState().setResultCallback(data -> {
                if(!data.isPaused){
                    mSpotifyAppRemote.getPlayerApi().pause();
                    binding.appBarMain.togglepodcast.setImageResource(R.drawable.ic_round_play_arrow_24);
                    mSpotifyAppRemote.getPlayerApi().getPlayerState().setResultCallback(dataPlayerState -> {
                        if(dataPlayerState != null)
                            binding.appBarMain.titlePodcast.setText(dataPlayerState.track.name);
                    });
                } else {
                    playSpotifySong();
                }
            });
        });
//
//        spotifyAppRemote.getContentApi().getRecommendedContentItems(ContentApi.ContentType.SLEEP).setResultCallback(new CallResult.ResultCallback<ListItems>() {
//            @Override
//            public void onResult(ListItems data) {
//                Gson gson = new Gson();
//                Log.d("wtf", "onResult: "+ gson.toJson(data).toString());
//            }
//        });
//        mSpotifyAppRemote.getPlayerApi().play("spotify:show:2MY2D5GeF37S2xSIuFLUZS");
    }

    private void playSpotifySong() {
        binding.appBarMain.togglepodcast.setEnabled(false);
        mSpotifyAppRemote.getPlayerApi().play(spotifyPlayId).setResultCallback(dataEmpty -> {
            binding.appBarMain.togglepodcast.setEnabled(true);
            binding.appBarMain.miniPlayerPodcast.setVisibility(View.VISIBLE);
            binding.appBarMain.togglepodcast.setImageResource(R.drawable.ic_baseline_pause_24);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mSpotifyAppRemote.getPlayerApi().getPlayerState().setResultCallback(dataPlayerState -> {
                        binding.appBarMain.titlePodcast.setText(dataPlayerState.track.name);
                    });
                }
            }, 500);
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SpotifyAppRemote.disconnect(mSpotifyAppRemote);
    }

    public SpotifyAppRemote getmSpotifyAppRemote() {
        return mSpotifyAppRemote;
    }

    public void play(String id) {
        if(mSpotifyAppRemote.isConnected()){
            this.spotifyPlayId = id;
            playSpotifySong();
        } else {
            this.connectSpotify();
        }
    }
}