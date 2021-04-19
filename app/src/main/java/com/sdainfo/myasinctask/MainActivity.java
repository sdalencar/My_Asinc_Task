package com.sdainfo.myasinctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressBar);

        button.setOnClickListener(view -> {
            MyAsyncTask myAsyncTask = new MyAsyncTask();
            myAsyncTask.execute(10);

        });
    }


    /*
    * 1 parametropara a classe
    * 2tipo de valor para o progresso
    * 3retorno depois de terminar a tarefa
    * */
    class MyAsyncTask extends AsyncTask<Integer, Integer, String>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Integer... integers) {
            int numero = integers[0];
            for (int i = 0; i < numero; i++){
                publishProgress(i * 10);
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

            return "finalizado";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(MainActivity.this, "Terminou a task : " + s, Toast.LENGTH_SHORT).show();
            progressBar.setProgress(0);
            progressBar.setVisibility(View.INVISIBLE);
        }


    }


}
















