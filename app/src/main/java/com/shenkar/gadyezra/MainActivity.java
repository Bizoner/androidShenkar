package com.shenkar.gadyezra;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    TextView result;
    EditText Number1;
    EditText Number2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = (TextView) findViewById(R.id.result);
        Number1 = (EditText) findViewById(R.id.Number1);
        Number2 = (EditText) findViewById(R.id.Number2);
    }

    public void calculateResult(View view) {

        String First = Number1.getText().toString();
        String Second = Number2.getText().toString();
        int FirstValue = Integer.parseInt(First);
        int SecondValue = Integer.parseInt(Second);
        int FinalResult = FirstValue + SecondValue;
        result.setText(String.valueOf(FinalResult));
    }
}