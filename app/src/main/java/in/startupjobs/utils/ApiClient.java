package in.startupjobs.utils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    //    public static final String BASE_URL = "http://3.7.137.119:5000/api/";
    public static final String BASE_URL = "https://api.startupjob.in/v1/";
    public static Retrofit retrofit = null;

    public static Retrofit getClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request request;
                Request original = chain.request();
                if (AppConstants.mLoginData != null &&
                        AppConstants.mLoginData.getToken() != null) {
                    request = original.newBuilder()
                            .header("Authorization","Bearer "+ AppConstants.mLoginData.getToken())
                            .method(original.method(), original.body())
                            .build();
                } else request = original.newBuilder()
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        });

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}