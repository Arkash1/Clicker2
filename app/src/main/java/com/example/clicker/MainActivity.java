package com.example.clicker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int counter = 0;
    private TextView counterText;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Найдем элементы
        counterText = findViewById(R.id.counterText);
        Button buttonIncrease = findViewById(R.id.buttonIncrease);
        Button buttonDecrease = findViewById(R.id.buttonDecrease);
        ImageView backgroundImage = findViewById(R.id.backgroundImage);

        // Загрузка сохраненного значения счётчика
        sharedPreferences = getSharedPreferences("CounterApp", MODE_PRIVATE);
        counter = sharedPreferences.getInt("counter_value", 0);
        updateCounter();

        // Обработчики кнопок
        buttonIncrease.setOnClickListener(v -> {
            counter++;
            updateCounter();
        });

        buttonDecrease.setOnClickListener(v -> {
            counter--;
            updateCounter();
        });
    }

    // Метод обновления счётчика на экране
    private void updateCounter() {
        counterText.setText("Счёт: " + counter);

        // Сохранение значения в SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("counter_value", counter);
        editor.apply();
    }
}
