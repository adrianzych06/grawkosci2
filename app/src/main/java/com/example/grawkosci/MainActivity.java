package com.example.grawkosci;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView kosc1, kosc2, kosc3, kosc4, kosc5;
    private TextView wynikLosowania, wynikGry, licznikRzutow;
    private int calkowityWynikGry = 0;
    private int liczbaRzutow = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kosc1 = findViewById(R.id.kosc1);
        kosc2 = findViewById(R.id.kosc2);
        kosc3 = findViewById(R.id.kosc3);
        kosc4 = findViewById(R.id.kosc4);
        kosc5 = findViewById(R.id.kosc5);

        wynikLosowania = findViewById(R.id.wynikLosowania);
        wynikGry = findViewById(R.id.wynikGry);
        licznikRzutow = findViewById(R.id.licznikRzutow);

        Button przyciskRzut = findViewById(R.id.przyciksRzut);
        Button przyciskReset = findViewById(R.id.przyciskreset);


        przyciskRzut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rzutKoscmi();
            }
        });
        przyciskReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetujGre();
            }
        });
    }

     private void rzutKoscmi() {
         Random random = new Random();
         int[] wynikiKosci = new int[5];

        
        for (int i = 0; i < 5; i++) {
            wynikiKosci[i] = random.nextInt(6) + 1;
        }
        wyswietlWynikiKosci(wynikiKosci);
        int wynikRzutu = obliczWynikRzutu(wynikiKosci);
        aktualizujWynik(wynikRzutu);
        aktualizujLicznikRzutow();
    }

    private void wyswietlWynikiKosci(int[] wynikiKosci) {
        kosc1.setText(String.valueOf(wynikiKosci[0]));
        kosc2.setText(String.valueOf(wynikiKosci[1]));
        kosc3.setText(String.valueOf(wynikiKosci[2]));
        kosc4.setText(String.valueOf(wynikiKosci[3]));
        kosc5.setText(String.valueOf(wynikiKosci[4]));
    }

     private int obliczWynikRzutu(int[] wynikiKosci) {
        int[] licznik = new int[7]; 
        for (int wynik : wynikiKosci) {
            licznik[wynik]++;
        }
        int wynikRzutu = 0;
        for (int i = 1; i <= 6; i++) {
            if (licznik[i] >= 2) { 
                wynikRzutu += i * licznik[i];
            }
        }
        wynikLosowania.setText("Wynik tego losowania: " + wynikRzutu);
        return wynikRzutu;
    }

    private void aktualizujWynik(int nowyWynik) {
        calkowityWynikGry += nowyWynik;
        wynikGry.setText("Wynik gry: " + calkowityWynikGry);
    }

    private void aktualizujLicznikRzutow() {
        liczbaRzutow++;
        licznikRzutow.setText("Liczba rzutów: " + liczbaRzutow);
    }

    private void resetujGre() {
        calkowityWynikGry = 0;
        liczbaRzutow = 0;

        kosc1.setText("?");
        kosc2.setText("?");
        kosc3.setText("?");
        kosc4.setText("?");
        kosc5.setText("?");

        wynikLosowania.setText("Wynik tego losowania: 0");
        wynikGry.setText("Wynik gry: 0");
        licznikRzutow.setText("Liczba rzutów: 0");
    }
}
