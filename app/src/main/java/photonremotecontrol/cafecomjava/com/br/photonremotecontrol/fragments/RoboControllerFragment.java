package photonremotecontrol.cafecomjava.com.br.photonremotecontrol.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import photonremotecontrol.cafecomjava.com.br.photonremotecontrol.R;
import photonremotecontrol.cafecomjava.com.br.photonremotecontrol.model.Particle;
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

/**
 * Created by joel on 8/14/16.
 */

public class RoboControllerFragment extends Fragment {

    @BindView(R.id.btnDow)
    Button btnDown;
    @BindView(R.id.btnLeft) Button btnleft;
    @BindView(R.id.btnRight) Button btnRight;
    @BindView(R.id.btnUp) Button btnUp;
    @BindView(R.id.btnStop) Button btnStop;
    @BindString(R.string.device) String device;
    @BindString(R.string.token) String token;


    private ParticleService apiService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.controller_fragment, container, false);

        //create retrofit to make the calls
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.particle.io/v1/devices/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .build();
        apiService = retrofit.create(ParticleService.class);
        return v;

    }

        @OnClick(R.id.btnLeft)
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

    @OnClick(R.id.btnDow)
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
        final ProgressDialog progress = ProgressDialog.show(this.getContext(), "Wait",
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
