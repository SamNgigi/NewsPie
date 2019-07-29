package com.hai.jedi.newspie.View.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.hai.jedi.newspie.Models.Headline;
import com.hai.jedi.newspie.R;
import com.hai.jedi.newspie.Utils.TimeFormatter;
import com.hai.jedi.newspie.ViewModel.SharedViewModel;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleActivity extends AppCompatActivity {

    private final String TAG = ArticleActivity.class.getSimpleName().toUpperCase();

    private SharedViewModel sharedViewModel;

    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;
    @BindView(R.id.title_appbar)
    LinearLayout titleLayout;
    @BindView(R.id.toolbar)
    Toolbar toolBarDetail;
    @BindView(R.id.backdrop)
    ImageView articleImage;
    @BindView(R.id.webView)
    WebView article_webview;

    @BindView(R.id.title_on_appbar)
    TextView articleAppbar;
    @BindView(R.id.subtitle_on_appbar)
    TextView article_subtitle;
    @BindView(R.id.date)
    TextView article_date;
    @BindView(R.id.time)
    TextView article_time;
    @BindView(R.id.title)
    TextView article_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_activtiy);

        ButterKnife.bind(this);

        setSupportActionBar(toolBarDetail);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Headline article = Parcels.unwrap(getIntent().getParcelableExtra("article"));
        Log.d(TAG, article.getSource().getSource_name());
        Picasso.get().load(article.getImg_url()).into(articleImage);

        articleAppbar.setText(article.getSource().getSource_name());
        article_subtitle.setText(article.getTitle());
        article_date.setText(TimeFormatter.DateFormat(article.getPublished_at()));
        article_title.setText(article.getTitle());

        String author = null;
        if (article.getAuthor() != null || article.getAuthor() != "") {
            author = " \u2022 " + article.getAuthor();
        } else {
            author = "";
        }

        article_time.setText(String.format("%s%s • %s",
                                article.getSource().getSource_name(),
                                author,
                                TimeFormatter.DateToTimeFormat(article.getPublished_at())));

        initWebView(article.getArticle_url());



    }

    private void initWebView(String url){
        article_webview.getSettings().setLoadsImagesAutomatically(true);
        article_webview.getSettings().setJavaScriptEnabled(true);
        article_webview.getSettings().setDomStorageEnabled(true);
        article_webview.getSettings().setSupportZoom(true);
        article_webview.getSettings().setBuiltInZoomControls(true);
        article_webview.getSettings().setDisplayZoomControls(false);
        article_webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        article_webview.setWebViewClient(new WebViewClient());
        article_webview.loadUrl(url);
    }
}
