package com.example.justjava;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int counter;
    private Toast toast;
    private EditText editText;
    private TextView textViewNumber;
    private TextView textViewName;
    private TextView textViewWhipped;
    private TextView textViewChocolate;
    private TextView textViewQuantityResult;
    private TextView textViewTotal;
    private Button decreaseButton;
    private Button increaseButton;
    private Button orderButton;
    private CheckBox checkBox1;
    private CheckBox checkBox2;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewNumber = findViewById(R.id.tv_number);
        increaseButton = findViewById(R.id.increase_button);
        decreaseButton = findViewById(R.id.decrease_button);
        editText = findViewById(R.id.my_edit_text);
        textViewName = findViewById(R.id.tv_name);
        textViewWhipped = findViewById(R.id.tv_whipped);
        textViewChocolate = findViewById(R.id.tv_chocolate);
        textViewQuantityResult = findViewById(R.id.tv_quantity_result);
        textViewTotal = findViewById(R.id.tv_total);
        checkBox1 = findViewById(R.id.checkbox_1);
        checkBox2 = findViewById(R.id.checkbox_2);
        orderButton = findViewById(R.id.order_button);

        decreaseButton.setOnClickListener(view -> onDecreaseButtonClick());
        increaseButton.setOnClickListener(view -> onIncreaseButtonClick());
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                textViewName.setText("Name: " + s);
                textViewName.setVisibility(View.VISIBLE);
            }
        });

//        textViewName.setText("Name: " + editText.getText().toString());

        checkBox1.setOnCheckedChangeListener((buttonView, isChecked) -> onCheckbox_1_Changed(isChecked));
        checkBox2.setOnCheckedChangeListener((buttonView, isChecked) -> onCheckbox_2_Changed(isChecked));
        orderButton.setOnClickListener(view ->
        {
            if (counter > 0) {
                onClickOrderButton();
            } else {
                Toast.makeText(this, "Quantity cannot be zero!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void onCheckbox_1_Changed(boolean ifChecked) {
        textViewWhipped.setText("Add whipped cream? " + ifChecked);
        textViewWhipped.setVisibility(View.VISIBLE);
    }

    public void onCheckbox_2_Changed(boolean ifChecked) {
        textViewChocolate.setText("Add chocolate? " + ifChecked);
        textViewChocolate.setVisibility(View.VISIBLE);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onDecreaseButtonClick() {
        if (counter != 0) {
            counter--;
            increaseButton.setBackgroundColor(getColor(R.color.grey));
            increaseButton.setClickable(true);
            orderButton.setClickable(true);
            orderButton.setBackgroundColor(getColor(R.color.grey));
            toast = Toast.makeText(this, "Already Zero!", Toast.LENGTH_SHORT);
            textViewNumber.setText(String.valueOf(counter));
            textViewQuantityResult.setText("Quantity: " + String.valueOf(counter));
            textViewTotal.setText(getTotal(counter));
        } else if (toast != null) {
            toast.show();
            decreaseButton.setBackgroundColor(getColor(R.color.silver));
            decreaseButton.setClickable(false);
            toast = null;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onIncreaseButtonClick() {
        if (counter < 12) {
            counter++;
            textViewQuantityResult.setVisibility(View.VISIBLE);
            textViewTotal.setVisibility(View.VISIBLE);
            decreaseButton.setBackgroundColor(getColor(R.color.grey));
            decreaseButton.setClickable(true);
            orderButton.setClickable(true);
            orderButton.setBackgroundColor(getColor(R.color.grey));
            toast = Toast.makeText(this, "Max order is 12!", Toast.LENGTH_SHORT);
            textViewNumber.setText(String.valueOf(counter));
            textViewQuantityResult.setText("Quantity: " + String.valueOf(counter));
            textViewTotal.setText(getTotal(counter));
        } else if (toast != null) {
            toast.show();
            increaseButton.setBackgroundColor(getColor(R.color.silver));
            increaseButton.setClickable(false);
            toast = null;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onClickOrderButton() {
        orderButton.setClickable(false);
        orderButton.setBackgroundColor(getColor(R.color.silver));
        Toast.makeText(this, "Order received. Thank you!", Toast.LENGTH_LONG).show();
        checkBox1.setClickable(false);
        checkBox2.setClickable(false);
        decreaseButton.setClickable(false);
        decreaseButton.setBackgroundColor(getColor(R.color.silver));
        increaseButton.setClickable(false);
        increaseButton.setBackgroundColor(getColor(R.color.silver));
        editText.setFocusable(false);
    }

    private String getTotal(int counter) {
        return "Total: $" + (5 * counter);
    }
}