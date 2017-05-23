package com.iot.psychologicaltest;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity
{
    public static final int REQUEST_CODE_MENU = 1001;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        if (intent != null)
        {
            Content content = (Content) intent.getSerializableExtra("content");
            int Q_Num = content.getQ_num();

            getQuestion(Q_Num);
            getInstance(Q_Num);
        }
    }

    public void instance1clicked(View v)
    {
        Intent intent = new Intent(
                getApplicationContext(),
                ClientActivity.class
        );
        Content content = new Content(inputID);
        intent.putExtra("content", content);
        startActivityForResult(intent, REQUEST_CODE_MENU);
        Toast.makeText(
                getApplicationContext(),
                "instance1 clicked",
                Toast.LENGTH_SHORT).show();
    }

    public void instance2clicked(View v)
    {
        Toast.makeText(
                getApplicationContext(),
                "instance2 clicked",
                Toast.LENGTH_SHORT).show();
    }

    public void instance3clicked(View v)
    {
        Toast.makeText(
                getApplicationContext(),
                "instance3 clicked",
                Toast.LENGTH_SHORT).show();
    }

    public void instance4clicked(View v)
    {
        Toast.makeText(
                getApplicationContext(),
                "instance4 clicked",
                Toast.LENGTH_SHORT).show();
    }

    public void getQuestion(int Q_Num)
    {
        Resources resources = getResources();

        String[] questions = resources.getStringArray(R.array.questions);
        TextView questionView = (TextView) findViewById(R.id.question);

        questionView.setText(questions[Q_Num - 1]);
    }

    public void getInstance(int Q_Num)
    {
        Resources resources = getResources();

        String[] instances = resources.getStringArray(R.array.instances);
        StringTokenizer instance = new StringTokenizer(instances[Q_Num - 1], "|");

        Button instance1Button = (Button) findViewById(R.id.instance1);
        instance1Button.setText(instance.nextToken());

        Button instance2Button = (Button) findViewById(R.id.instance2);
        instance2Button.setText(instance.nextToken());

        Button instance3Button = (Button) findViewById(R.id.instance3);
        instance3Button.setText(instance.nextToken());

        Button instance4Button = (Button) findViewById(R.id.instance4);
        instance4Button.setText(instance.nextToken());
    }

}
