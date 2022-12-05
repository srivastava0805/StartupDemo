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
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("XSRF-TOKEN", "zIRRnHqW-qK-6U1bMfCLbmRuYPaoaj8WsU0w")
                        .header("Cookie", "_csrf=TDSWggY7u7uYhu27Psxkk1m-")
                        .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjMwNiwiZW1haWwiOiJzYW5kZWVwQHN0YXJ0dXBqb2IuaW4iLCJtb2JpbGVOdW1iZXIiOiI4MjI5MDM5OTQ2IiwibmFtZSI6IlNhbmRlZXAgS3VtYXIgWWFkYXYiLCJ1c2VyVHlwZSI6ImNhbmRpZGF0ZSIsImVtcGxveWVyVHlwZSI6bnVsbCwiZXh0cmFzIjp7fSwiaWF0IjoxNjcwMTQ3NTM5LCJleHAiOjE2Nzc5MjM1MzksImF1ZCI6InN0YXJ0dXBqb2IuaW4iLCJpc3MiOiJhY2NvdW50cy5zdGFydHVwam9iLmluIn0.X72FNcrQB-Q6zxufKuppaU0La6-MSZA7qjRXP2WJsHM")
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