package com.example.uzulmez.asynctaskexample;

import android.app.Application;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private  ProgressBar p,p2;
    private  TextView Mytext;
    private int k;
    private Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         Mytext = (TextView) findViewById(R.id.T1);
         p = (ProgressBar) findViewById(R.id.pr1);
         p2 = (ProgressBar) findViewById(R.id.pr2);
         btn1 = (Button) findViewById(R.id.B);

         btn1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 //Toast.makeText(getBaseContext() , "Done Counting to 100000", Toast.LENGTH_SHORT).show();
                 Toast.makeText(getBaseContext(),String.valueOf(k) , Toast.LENGTH_SHORT).show();             }
         });
       new Myasync().execute(100);
    }


    private class Myasync extends AsyncTask<Integer,String,String>{

        @Override
        protected String doInBackground(Integer... params ) {
            int myint = params[0];

             p.setVisibility(View.VISIBLE);


            try {
                for (int i=0; i<myint; i++) {
                    k=i;
                    publishProgress(String.valueOf(k)); // Calls onProgressUpdate()
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {

            super.onProgressUpdate(values);
            Mytext.setText(values[0]);
            p2.setProgress(Integer.parseInt(values[0]));
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            p.setVisibility(View.INVISIBLE);

        }
    }
}
