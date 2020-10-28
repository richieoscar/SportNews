package com.example.sportnews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    private TextView title;
    private TextView detailInfo;
    private ImageView image;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setUpActionBarTitle();
        bindViews();
        setDataToViews();


    }

    private void setDataToViews() {
        title.setText(getIntent().getStringExtra("title") );
        detailInfo.setText(getIntent().getStringExtra("info"));

        Glide.with(this).load(getIntent().getIntExtra("sport_images", 0)).into(image);
    }

    private void setUpActionBarTitle() {
        actionBar = getSupportActionBar();
        actionBar.setTitle(getIntent().getStringExtra("title"));
    }

    private void bindViews(){
        title = findViewById(R.id.textView_title_detail);
        detailInfo = findViewById(R.id.textView_info_detail);
        image = findViewById(R.id.sport_image_detail);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.item_share:
                
                shareNews();
                return true;

            case R.id.item_about:
                Toast.makeText(this, "About us", Toast.LENGTH_SHORT).show();

            default:
                return super.onOptionsItemSelected(item);

        }

    }

    private void shareNews() {
        String newsTitle = title.getText().toString();
        String newsInfo = detailInfo.getText().toString();
        String shareNews = newsTitle +"\n" +newsInfo;
        String mimeType = "text/plain";

        ShareCompat.IntentBuilder.from(this)
                .setType(mimeType)
                .setChooserTitle("Share News")
                .setText(shareNews)
                .startChooser();
    }
}