package com.iot.psychologicaltest;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.StringTokenizer;

import static com.iot.psychologicaltest.R.array.resultTitles;
import static com.iot.psychologicaltest.R.array.results;

public class ResultActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();

        if (intent != null) {
            Content content = (Content) intent.getSerializableExtra("content");
            final int Q_Num = content.getQ_num();
            final int I_Num = content.getI_num();

            TextView testTitleView = (TextView) findViewById(R.id.testTitle);
            testTitleView.setText(getTestTitle(Q_Num));

            TextView resultTitleView = (TextView) findViewById(R.id.resultTitle);
            resultTitleView.setText(getResultTitle(Q_Num, I_Num));

            TextView resultView = (TextView) findViewById(R.id.result);
            resultView.setText(getResult(Q_Num, I_Num));

            ImageButton BackButton = (ImageButton) findViewById(R.id.BackButton);
            BackButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BackButtonClicked(Q_Num, 0);
                }
            });

            ImageButton TwitButton = (ImageButton) findViewById(R.id.TwitButton);
            TwitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TwitButtonClicked(Q_Num, I_Num);
                }
            });
        }
    }

    public void BackButtonClicked(int Q_Num, int I_Num) {
        Intent Intent = new Intent(getApplicationContext(), QuestionActivity.class);
        Content content = new Content(Q_Num, 0);
        Intent.putExtra("content", content);
        startActivity(Intent);
    }

    public void HomeButtonClicked(View v) {
        Intent intent = new Intent(
                getApplicationContext(),
                ListViewMain.class
        );
        startActivity(intent);
    }

    public void TwitButtonClicked(int Q_Num, int I_Num) {
        String strLink = null;
        try {
            strLink = String.format("http://twitter.com/intent/tweet?text=%s",
                    URLEncoder.encode(getTestTitle(Q_Num) + "\n\n" + getResult(Q_Num, I_Num), "utf-8"));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(strLink));
        startActivity(intent);
    }

    public String getTestTitle(int Q_Num) {
        Resources resources = getResources();
        String[] testTitles = resources.getStringArray(R.array.testTitles);

        return testTitles[Q_Num-1];
    }

    public String getResultTitle(int Q_Num, int I_Num) {
        Resources resources = getResources();

        StringTokenizer resultTitleResource =
                new StringTokenizer(resources.getStringArray(resultTitles)[Q_Num - 1], "|");
        String[] resultTitles = new String[resultTitleResource.countTokens()];
        int i = 0;
        while (resultTitleResource.hasMoreTokens()) {
            resultTitles[i] = resultTitleResource.nextToken();
            i++;
        }

        return resultTitles[I_Num - 1];
    }

    public String getResult(int Q_Num, int I_Num) {
        Resources resources = getResources();

        StringTokenizer resultResource =
                new StringTokenizer(resources.getStringArray(results)[Q_Num - 1], "|");
        String[] results = new String[resultResource.countTokens()];
        int i = 0;
        while (resultResource.hasMoreTokens()) {
            results[i] = resultResource.nextToken();
            i++;
        }

        return results[I_Num - 1];
    }
}