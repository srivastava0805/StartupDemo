package in.startupjobs.ui.blogs;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import in.startupjobs.R;

public class WebBlogFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_web_blog, container, false);
        WebView mywebview = (WebView) root.findViewById(R.id.fragment_webviewblog_webview);
        progressBar = root.findViewById(R.id.web_progresbar_loading);
        mywebview.setWebViewClient(new MyWebViewClient());

        String url = "https://blog.startupjob.in/";

        mywebview.getSettings().setDomStorageEnabled(true);
        mywebview.getSettings().setAppCacheEnabled(true);
        mywebview.getSettings().setLoadsImagesAutomatically(true);
        mywebview.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        mywebview.getSettings().setJavaScriptEnabled(true);
        mywebview.loadUrl(url); // load a web page in a web view

        return root;
    }

    public static ProgressBar progressBar;

    private static class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            progressBar.setVisibility(View.VISIBLE);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            progressBar.setVisibility(View.GONE);
            super.onPageFinished(view, url);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

            super.onReceivedError(view, errorCode, description, failingUrl);
            progressBar.setVisibility(View.GONE);
            Toast.makeText(view.getContext(), "An error occurred", Toast.LENGTH_SHORT).show();
            Log.e("Error", description);
        }
    }
}
