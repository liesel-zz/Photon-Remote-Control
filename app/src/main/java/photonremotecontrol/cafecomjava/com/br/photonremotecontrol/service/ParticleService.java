package photonremotecontrol.cafecomjava.com.br.photonremotecontrol.service;

import java.util.List;

import photonremotecontrol.cafecomjava.com.br.photonremotecontrol.Model.Particle;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
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

    @GET("/group/{id}/users")
    Observable<List<Particle>> groupList(@Path("id") int groupId, @Query("sort") String sort);

    @POST("/users/new")
    Observable<Particle> createUser(@Body Particle user);

}
