package com.example.threads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextStart, editTextEnd, editTextDelay;

    TextView textViewResult;

    Button buttonStart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextStart = findViewById(R.id.etStart);
        editTextEnd = findViewById(R.id.etEnd);
        editTextDelay = findViewById(R.id.etDelay);
        buttonStart = findViewById(R.id.btnStart);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*int start = Integer.parseInt(editTextStart.getText().toString());
                int end = Integer.parseInt(editTextEnd.getText().toString());
                int delay = Integer.parseInt(editTextDelay.getText().toString());
                    */
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, buttonStart);
                popupMenu.getMenuInflater().inflate(R.menu.menu_layout, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.action_increase) {
                            Toast.makeText(MainActivity.this, "Increase", Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }

                });
                popupMenu.show();

            }
        });


    }
}