package com.example.timery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int sekundy = 0;
    private TextView textViewCzas;
    private Button buttonPauza;
    private Button buttonStart;
    private Button buttonReset;
    private Button buttonZapisz;
    private TextView textViewZapisaneCzasy;
    String czasDoWyswietlenia;
    String zapisaneCzasy = "Zapisane czasy: \n";
    private boolean dziala = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            sekundy = savedInstanceState.getInt("SEKUNDY");
        }

        uruchomZegar();

        textViewCzas = findViewById(R.id.textViewCzas);
        buttonPauza = findViewById(R.id.buttonPauza);
        buttonStart = findViewById(R.id.buttonStart);
        buttonReset = findViewById(R.id.buttonReset);
        buttonZapisz = findViewById(R.id.buttonZapisz);
        textViewZapisaneCzasy = findViewById(R.id.textViewZapisaneCzasy);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dziala = true;
            }
        });
        buttonPauza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dziala = false;
            }
        });
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sekundy = 0;
                dziala = false;
                wyswietl(sekundy);
            }
        });
        buttonZapisz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zapisaneCzasy += czasDoWyswietlenia + "\n";
                //TODO wyswietlic w TextView pod przyciskami
                textViewZapisaneCzasy.setText(zapisaneCzasy);
            }
        });
    }

    private void uruchomZegar(){
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                if(dziala) {
                    sekundy++;
                    wyswietl(sekundy);
                }
            }
        });
    }
    private void wyswietl(int sekundy){
        //TODO wyswietlic czas
        int s60 = sekundy % 60;
        int h60 = sekundy / 3600;
        int m60 = (sekundy - h60 * 3600) / 60;
        czasDoWyswietlenia = String.format("%02d:%02d:%02d", h60, m60, s60);
        textViewCzas.setText(czasDoWyswietlenia);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("SEKUNDY", sekundy);
    }
}