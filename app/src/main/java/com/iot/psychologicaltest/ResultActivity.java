package com.iot.psychologicaltest;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.StringTokenizer;

/**
 * Provides UI for the Detail page with Collapsing Toolbar.
 */
public class ResultActivity extends AppCompatActivity {

    ImageButton BackButton;
    ImageButton TwitButton;
    ImageButton HomeButton;


    public static final String EXTRA_POSITION = "position";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();

        if (intent != null) {
            Content content = (Content) intent.getSerializableExtra("content");
            final int I_Num=content.getI_num();
            getsubtitle(I_Num);
            getContents(I_Num);
        }


            HomeButton = (ImageButton) findViewById(R.id.home);
            BackButton = (ImageButton) findViewById(R.id.BackButton);
            TwitButton = (ImageButton) findViewById(R.id.TwitButton);


            // Set Collapsing Toolbar layout to the screen
            CollapsingToolbarLayout collapsingToolbar =
                    (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
            // Set title of Detail page
            //  collapsingToolbar.setTitle(getString(R.array.titleset));


            /// ok


            int postion = getIntent().getIntExtra(EXTRA_POSITION, 0);
            Resources resources = getResources();

            String[] places = resources.getStringArray(R.array.titleset);
            collapsingToolbar.setTitle(places[postion % places.length]);

            String[] placeDetails = resources.getStringArray(R.array.TestContents);
            TextView placeDetail = (TextView) findViewById(R.id.place_detail);
            placeDetail.setText(placeDetails[postion % placeDetails.length]);

            TypedArray placePictures = resources.obtainTypedArray(R.array.picture);
            ImageView placePicutre = (ImageView) findViewById(R.id.image);
            placePicutre.setImageDrawable(placePictures.getDrawable(postion % placePictures.length()));

            placePictures.recycle();

    }
    public void home (View v){
        Intent homeIntent =new Intent(getApplicationContext(),ListViewMain.class);
        startActivity(homeIntent);

    }

    public void backClicked (View v) {
        Intent backIntent = new Intent(getApplicationContext(), QuestionActivity.class);
        startActivity(backIntent);

    }

    public void twit (View v) {
        String strLink = null;
        try {
            strLink = String.format("http://twitter.com/intent/tweet?text=%s",
                    URLEncoder.encode("공유하고픈 심리테스트~ ", "utf-8"));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(strLink));
        startActivity(intent);

    }

    public void getsubtitle (int I_Num)
    {
        Resources resources = getResources();
        String[] sub = resources.getStringArray(R.array.Resulttitle);
        StringTokenizer resource = new StringTokenizer(sub[I_Num-1], "|");
        TextView SubView=(TextView)findViewById(R.id.subtitle);  //description부분 소제목
        SubView.setText(resource.nextToken());

    }

    public void getContents (int I_Num)
    {
        Resources resources=getResources();

        String[] Contents = resources.getStringArray(R.array.TestContents);
        StringTokenizer Content = new StringTokenizer(Contents[I_Num - 1], "|");

        TextView placeDetail = (TextView) findViewById(R.id.place_detail);
        placeDetail.setText(Content.nextToken());

    }
/*
    public void getPictures (int I_Num)
    {
        Resources resources=getResources();
        String[] Pictures = resources.getStringArray(R.array.picture);
        StringTokenizer picture = new StringTokenizer(Contents[I_Num - 1], "|");
        ImageView image = (ImageView) findViewById(R.id.image);
        image.setImageDrawable();
    }
*/


}