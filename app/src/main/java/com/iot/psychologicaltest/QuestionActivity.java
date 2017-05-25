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

public class QuestionActivity extends AppCompatActivity
{
    public static final int REQUEST_CODE_MENU = 1001;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Intent intent = getIntent();

        if (intent != null) {
            Content content = (Content) intent.getSerializableExtra("content");
            final int Q_Num = content.getQ_num();

            getQuestion(Q_Num);
            getInstance(Q_Num);

            Button instance1Button = (Button) findViewById(R.id.instance1);
            instance1Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    instanceClicked(Q_Num, 1);
                }
            });

            Button instance2Button = (Button) findViewById(R.id.instance2);
            instance2Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    instanceClicked(Q_Num, 2);
                }
            });

            Button instance3Button = (Button) findViewById(R.id.instance3);
            instance3Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    instanceClicked(Q_Num, 3);
                }
            });

            Button instance4Button = (Button) findViewById(R.id.instance4);
            instance4Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    instanceClicked(Q_Num, 4);
                }
            });
        }
    }

    public void backButtonClicked(View v){
        Intent intent = new Intent(
                getApplicationContext(),
                ListViewMain.class
        );
        startActivityForResult(intent, REQUEST_CODE_MENU);
        Toast.makeText(
                getApplicationContext(),
                "back clicked",
                Toast.LENGTH_SHORT).show();
    }

    public void instanceClicked(int Q_Num, int I_Num) {
        Intent intent = new Intent(
                getApplicationContext(),
                ResultActivity.class
        );
        Content content = new Content(Q_Num, I_Num);
        intent.putExtra("content", content);
        startActivityForResult(intent, REQUEST_CODE_MENU);

        Toast.makeText(
                getApplicationContext(),
                "instance" + I_Num + " clicked",
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