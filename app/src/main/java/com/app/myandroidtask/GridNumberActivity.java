package com.app.myandroidtask;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.myandroidtask.databinding.ActivityGridNumberBinding;

public class GridNumberActivity extends AppCompatActivity {
    ActivityGridNumberBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityGridNumberBinding.inflate(getLayoutInflater());
        View view = b.getRoot();
        setContentView(view);
        populateGrid();
        b.ruleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                applyRule(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        applyRule(b.ruleSpinner.getSelectedItemPosition());

    }

    private void populateGrid() {
        for (int i = 1; i <= 100; i++) {
            TextView textView = new TextView(GridNumberActivity.this);
            textView.setText(String.valueOf(i));
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textView.setPadding(8, 8, 8, 8);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 0;
            params.height = 0;
            params.setMargins(4, 4, 4, 4);
            params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            textView.setLayoutParams(params);
            textView.setBackgroundColor(Color.WHITE);
            b.gridLayout.addView(textView);
        }
    }

    private void applyRule(int ruleIndex) {
        for (int i = 0; i < b.gridLayout.getChildCount(); i++) {
            TextView textView = (TextView) b.gridLayout.getChildAt(i);
            int number = Integer.parseInt(textView.getText().toString());
            textView.setBackgroundColor(Color.WHITE);
            switch (ruleIndex) {
                case 0:
                    if (isOdd(number)) textView.setBackgroundColor(Color.YELLOW);
                    break;

                case 1:
                    if (isEven(number)) textView.setBackgroundColor(Color.GREEN);
                    break;
                case 2:
                    if (isPrime(number)) textView.setBackgroundColor(Color.RED);
                    break;
                case 3:
                    if (isFibonacci(number)) textView.setBackgroundColor(Color.CYAN);
                    break;

            }
        }
    }


    private boolean isOdd(int num) {
        return num % 2 != 0;
    }

    private boolean isEven(int num) {
        return num % 2 == 0;
    }

    private boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i < num; i++) {
            if (num % i == 0) return false;

        }
        return true;
    }


    private boolean isFibonacci(int num) {
        if (num < 0) return false;
        int x = 5 * num * num;
        return isPerfctSequare(x + 4) || isPerfctSequare(x - 4);
    }

    private boolean isPerfctSequare(int x) {
        int s = (int) Math.sqrt(x);
        return s * s == x;
    }
}