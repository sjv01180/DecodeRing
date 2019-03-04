package com.example.decodering;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.InputMismatchException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buildTable(View view) {
        float tableFont = getResources().getDimension(R.dimen.small_font);
        int rightPadding = (int) (getResources().getDimension(R.dimen.padding) / getResources().getDisplayMetrics().density);

        //get shift value from edit text
        EditText input = findViewById(R.id.input);

        int shift;
        try {
            shift = Integer.parseInt(input.getText().toString());

        } catch (InputMismatchException e) {
            shift = 0;
        }

        //build table
        TableLayout table = findViewById(R.id.table);
        table.removeAllViews();

        //build header row
        TableRow header = new TableRow(this);
        TextView plain = new TextView(this);

        plain.setText(getString(R.string.plaintext));
        plain.setTextSize(TypedValue.COMPLEX_UNIT_PX, tableFont);
        plain.setPadding(0, 0, rightPadding, 0);

        TextView cipher = new TextView(this);
        cipher.setText(R.string.ciphertext);
        cipher.setTextSize(TypedValue.COMPLEX_UNIT_PX, tableFont);

        header.addView(plain);
        header.addView(cipher);
        table.addView(header);

        for(int i = 0; i < 26; i++) {
            char plainLetter = (char) ('A' + i);
            char cipherLetter = (char) ('A' + (i + shift) % 26);

            TableRow row = new TableRow(this);
            TextView newPlain = new TextView(this);
            TextView newCipher = new TextView(this);

            newPlain.setText(Character.toString(plainLetter));
            newPlain.setTextSize(TypedValue.COMPLEX_UNIT_PX, tableFont);

            newCipher.setText(Character.toString(cipherLetter));
            newCipher.setTextSize(TypedValue.COMPLEX_UNIT_PX, tableFont);

            row.addView(newPlain);
            row.addView(newCipher);
            table.addView(row);
        }

        //profit!

    }
}
