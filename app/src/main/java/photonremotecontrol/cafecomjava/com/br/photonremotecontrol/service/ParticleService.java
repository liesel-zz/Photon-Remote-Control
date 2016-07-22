package photonremotecontrol.cafecomjava.com.br.photonremotecontrol.service;

import photonremotecontrol.cafecomjava.com.br.photonremotecontrol.Model.Particle;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by joel on 7/21/16.
 * http://randomdotnext.com/retrofit-rxjava/
 * http://square.github.io/retrofit/
 * https://github.com/ruler88/GithubDemo/blob/master/app/src/main/java/app/service/ServiceFactory.java
 * https://guides.codepath.com/android/Consuming-APIs-with-Retrofit
 */
public interface ParticleService {


    @GET("/users/{login}")
    Observable<Particle> getUser(@Path("login") String login);
}
