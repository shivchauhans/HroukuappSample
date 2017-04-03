package perx.artistsampleapp.common.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import perx.artistsampleapp.common.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shiv on 6/2/17.
 */
public class  ApiClient {

    private static  Retrofit retrofit = null;

    private static  Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private static OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build();

    /**
     * initialize and get Retrofit object
     *
     * @return Retrofit object
     */
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .client(okHttpClient).baseUrl(Constants.BASE_URL_HEROKUAPP)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}

