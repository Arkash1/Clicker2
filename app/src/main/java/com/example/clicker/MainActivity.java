package com.example.clicker;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int counter = 0;
    private boolean isBackground1 = true;
    private TextView counterText;
    private ImageView backgroundImage;
    private Button buttonIncrease, buttonDecrease, buttonChangeBackground;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Найдем элементы
        counterText = findViewById(R.id.counterText);
        backgroundImage = findViewById(R.id.backgroundImage);
        buttonIncrease = findViewById(R.id.buttonIncrease);
        buttonDecrease = findViewById(R.id.buttonDecrease);
        buttonChangeBackground = findViewById(R.id.buttonChangeBackground);

        // Загружаем состояние фона
        sharedPreferences = getSharedPreferences("CounterApp", MODE_PRIVATE);
        isBackground1 = sharedPreferences.getBoolean("background_state", true);
        updateCounter();
        updateBackground();

        // Обработчики кнопок
        buttonIncrease.setOnClickListener(v -> {
            counter++;
            updateCounter();
        });

        buttonDecrease.setOnClickListener(v -> {
            counter--;
            updateCounter();
        });

        buttonChangeBackground.setOnClickListener(v -> {
            isBackground1 = !isBackground1;
            updateBackground();
        });
    }

    // Обновление счётчика на экране
    private void updateCounter() {
        counterText.setText("Счёт: " + counter);
    }

    // Смена фона + цветов кнопок и текста
    private void updateBackground() {
        if (isBackground1) {
            backgroundImage.setImageResource(R.drawable.background1);
            updateButtonColors(Color.parseColor("#FF6200EE"), Color.WHITE);
        } else {
            backgroundImage.setImageResource(R.drawable.background2);
            updateButtonColors(Color.parseColor("#FF03DAC5"), Color.BLACK);
        }

        // Сохраняем фон
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("background_state", isBackground1);
        editor.apply();
    }

    // Метод смены цветов кнопок
    private void updateButtonColors(int buttonColor, int textColor) {
        buttonIncrease.setBackgroundColor(buttonColor);
        buttonIncrease.setTextColor(textColor);

        buttonDecrease.setBackgroundColor(buttonColor);
        buttonDecrease.setTextColor(textColor);

        buttonChangeBackground.setBackgroundColor(buttonColor);
        buttonChangeBackground.setTextColor(textColor);

        counterText.setTextColor(textColor);
    }
}
