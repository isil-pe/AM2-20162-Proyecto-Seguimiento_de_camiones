package como.isil.mynotes.rest.storage.request;

import como.isil.mynotes.rest.storage.entity.CheckPointsResponse;
import como.isil.mynotes.rest.storage.entity.LogInProyRaw;
import como.isil.mynotes.rest.storage.entity.LogInProyResponse;

import como.isil.mynotes.rest.storage.entity.OrderRaw;
import como.isil.mynotes.rest.storage.entity.OrderResponse;
import como.isil.mynotes.rest.storage.entity.OrdersResponse;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;


/**
 * Created by em on 8/06/16.
 */
public class ApiProy {

    private static final String TAG = "ApiClient";
    private static final String API_BASE_URL="http://api.backendless.com";

    private static ServicesApiInterface servicesApiInterface;
    private static OkHttpClient.Builder httpClient;


    public static ServicesApiInterface getMyApiClient() {

        if (servicesApiInterface == null) {

            Retrofit.Builder builder =new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());
            httpClient =new OkHttpClient.Builder();
            httpClient.addInterceptor(interceptor());

            Retrofit retrofit = builder.client(httpClient.build()).build();
            servicesApiInterface = retrofit.create(ServicesApiInterface.class);
        }
        return servicesApiInterface;
    }

    public interface ServicesApiInterface {

        @Headers({
                "Content-Type: application/json",
                "application-id: D8B9D57F-444B-190A-FF6D-8EA3256EEA00",
                "secret-key: 3D6C9C91-2D25-00DD-FF11-CDDDC4362800",
                "application-type: REST"
        })
        //v1/users/login
        @POST("/v1/users/login")
        Call<LogInProyResponse> login(@Body LogInProyRaw raw);

        @Headers({
                "Content-Type: application/json",
                "application-id: D8B9D57F-444B-190A-FF6D-8EA3256EEA00",
                "secret-key: 3D6C9C91-2D25-00DD-FF11-CDDDC4362800",
                "application-type: REST"
        })
        //v1/data/checkpoints
        @GET("/v1/data/checkpoints")
        Call<CheckPointsResponse> checkpoints();


        @Headers({
                "Content-Type: application/json",
                "application-id: D8B9D57F-444B-190A-FF6D-8EA3256EEA00",
                "secret-key: 3D6C9C91-2D25-00DD-FF11-CDDDC4362800",
                "application-type: REST"
        })
        //v1/data/orders
        @GET("/v1/data/orders")
        Call<OrdersResponse> orders();


        @Headers({
                "Content-Type: application/json",
                "application-id: D8B9D57F-444B-190A-FF6D-8EA3256EEA00",
                "secret-key: 3D6C9C91-2D25-00DD-FF11-CDDDC4362800",
                "application-type: REST"
        })
        @POST("/v1/data/orders")
        Call<OrderResponse> addNote(@Body OrderRaw raw);
    }

    /*private static OkHttpClient.Builder client(){
        if(httpClient==null)httpClient=new OkHttpClient.Builder();
        return httpClient;
    }*/
    private  static  HttpLoggingInterceptor interceptor(){
        HttpLoggingInterceptor httpLoggingInterceptor= new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }
}
