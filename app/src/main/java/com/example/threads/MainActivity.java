package com.example.threads;

import static java.lang.Math.abs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Runnable {

    EditText editTextStart, editTextEnd, editTextDelay;

    TextView textViewResult;

    Button buttonStart;

    Thread thread;

    boolean isRunning = false;
    int start, end, delay, result = 0;
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (isRunning) {
                try {
                    Thread.sleep(delay);
                    synchronized (this) {
                        result++;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textViewResult.setText(String.valueOf(abs(result)));
                            }
                        });
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextStart = findViewById(R.id.etStart);
        editTextEnd = findViewById(R.id.etEnd);
        editTextDelay = findViewById(R.id.etDelay);
        buttonStart = findViewById(R.id.btnStart);
        textViewResult = findViewById(R.id.tvCounter);

        thread = new Thread(MainActivity.this);
        thread.start();
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popupMenu = new PopupMenu(MainActivity.this, buttonStart);
                popupMenu.getMenuInflater().inflate(R.menu.menu_layout, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.action_increase) {
                            isRunning = true;
                            start = Integer.parseInt(editTextStart.getText().toString());
                            end = Integer.parseInt(editTextEnd.getText().toString());
                            delay = Integer.parseInt(editTextDelay.getText().toString());
                            result = start;
                            Toast.makeText(MainActivity.this, "Increase", Toast.LENGTH_SHORT).show();


                        }
                        if(item.getItemId() == R.id.action_decrease){
                            isRunning = true;
                            start = Integer.parseInt(editTextStart.getText().toString());
                            end = Integer.parseInt(editTextEnd.getText().toString());
                            delay = Integer.parseInt(editTextDelay.getText().toString());
                            result = end * -1;
                            Toast.makeText(MainActivity.this, "Decrease", Toast.LENGTH_SHORT).show();

                        }
                        if (item.getItemId() == R.id.action_pause) {
                            isRunning = false;
                            Toast.makeText(MainActivity.this, "Pausa", Toast.LENGTH_SHORT).show();
                        }
                        if (item.getItemId() == R.id.action_continue) {
                            isRunning = true;
                            Toast.makeText(MainActivity.this, "Resume", Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }

                });
                popupMenu.show();

            }
        });


    }
}