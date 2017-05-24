package com.karloleary.userinput;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class JustJavaActivity extends AppCompatActivity {

    private int count = 1;
    private int price = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_justjava);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox creamCheckBox      = (CheckBox)findViewById(R.id.cream_cb);
        CheckBox chocolateCheckBox  = (CheckBox)findViewById(R.id.chocolate_cb);
        EditText nameEditText       = (EditText)findViewById(R.id.name_ed);

        String yes      = getString(R.string.yes);
        String no       = getString(R.string.no);
        String message  = getString(
                R.string.summary,
                nameEditText.getText(),
                count,
                creamCheckBox.isChecked()       ? yes : no,
                chocolateCheckBox.isChecked()   ? yes : no,
                calculatePrice()
        );

        displayMessage(message);


//        String[] names = {"justjava@gmail.com"};
//
//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setData(Uri.parse("mailto:"));
//        intent.putExtra(Intent.EXTRA_EMAIL, names);
//        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order: " + nameEditText.getText());
//        intent.putExtra(Intent.EXTRA_TEXT, message);
//        if (intent.resolveActivity(getPackageManager()) != null)
//            startActivity(intent);
    }


    public void incrementQuantity(View view) {
        if (count < 5)
            count++;
        displayCount();
    }

    public void decrementQuantity(View view) {
        if (count > 1)
            count--;
        displayCount();
    }


    private int calculatePrice() {
        CheckBox creamCheckBox= (CheckBox)findViewById(R.id.cream_cb);
        CheckBox chocolateCheckBox= (CheckBox)findViewById(R.id.chocolate_cb);

        int total = count * price;

        if (creamCheckBox.isChecked())
            total += count;

        if (chocolateCheckBox.isChecked())
            total += (count * 2);

        return total;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayCount() {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_textview);
        quantityTextView.setText(Integer.toString(count));
    }


    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.summary_textview);
        priceTextView.setText(message);
    }
}
