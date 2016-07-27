package photonremotecontrol.cafecomjava.com.br.photonremotecontrol;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import photonremotecontrol.cafecomjava.com.br.photonremotecontrol.Model.Particle;
import photonremotecontrol.cafecomjava.com.br.photonremotecontrol.service.ParticleService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.btnDown) Button btnDown;
    @BindView(R.id.btnleft) Button btnleft;
    @BindView(R.id.btnRight) Button btnRight;
    @BindView(R.id.btnUp) Button btnUp;
    @BindView(R.id.btnStop) Button btnStop;
    @BindString(R.string.device) String device;
    @BindString(R.string.token) String token;


    private ParticleService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        //create retrofit to make the calls
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.particle.io/v1/devices/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .build();
        apiService = retrofit.create(ParticleService.class);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @OnClick(R.id.btnleft)
    public void goLeft(){
        Observable<Particle> call = apiService.move(device,token, "left");
        this.makeAcall(call);
    }

    @OnClick(R.id.btnRight)
    public void goRight(){
        Observable<Particle> call = apiService.move(device,token, "right");
        this.makeAcall(call);
    }

    @OnClick(R.id.btnUp)
    public void goUp()
    {
        Observable<Particle> call = apiService.move(device,token, "up");
        this.makeAcall(call);
    }

    @OnClick(R.id.btnDown)
    public void goDown(){
        Observable<Particle> call = apiService.move(device,token, "down");
        this.makeAcall(call);
    }

    @OnClick(R.id.btnStop)
    public void stop(){
        Observable<Particle> call = apiService.move(device,token, "stop");
        this.makeAcall(call);
    }



    private void makeAcall(Observable observable){
        final ProgressDialog progress = ProgressDialog.show(this, "Wait",
                "Sending command...", true);
        Subscription subscription = observable
                .subscribeOn(Schedulers.io()) // optional if you do not wish to override the default behavior
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Particle>() {
                    @Override
                    public void onCompleted() {
                        progress.dismiss();
                        Log.i("onCompleted","onCompleted");

                    }

                    @Override
                    public void onError(Throwable e) {
                        progress.dismiss();
                        // cast to retrofit.HttpException to get the response code
                        if (e instanceof HttpException){
                            HttpException response = (HttpException)e;
                            int code = response.code();
                            Log.i("onError",response.getMessage());
                        }
                    }

                    @Override
                    public void onNext(Particle user) {
                        Log.i("onNext","onNext");
                    }
                });
    }
}
