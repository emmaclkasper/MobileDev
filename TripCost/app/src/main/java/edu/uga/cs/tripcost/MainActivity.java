package edu.uga.cs.tripcost;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private float pricePerGallon = 0, totalCost, tempNum;
    private TextView result;
    private Button compute;
    private EditText price, MPG, distance;
    private Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView) findViewById(R.id.textView);
        compute = (Button) findViewById(R.id.button);
        compute.setOnClickListener(new ButtonClickListener());
        price = (EditText) findViewById(R.id.editText2);
        MPG = (EditText) findViewById(R.id.editText3);
        distance = (EditText) findViewById(R.id.editText);

        toast = Toast.makeText(getApplicationContext(), "Enter positive numbers", Toast.LENGTH_SHORT);
    }

   private class ButtonClickListener implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        String temp = price.getText().toString();
        float mpg = 0, mile = 0;
        if (!"".equals(temp)){
            pricePerGallon = Float.parseFloat(temp);
        }
        temp = distance.getText().toString();
        if (!"".equals(temp)){
            mile = Float.parseFloat(temp);
        }
        temp = MPG.getText().toString();
        if (!"".equals(temp)){
            mpg = Float.parseFloat(temp);
        }
        if(pricePerGallon < 1 || mile < 1 || mpg < 1) {
            toast.show();
        }else {
            tempNum = mile / mpg;
            totalCost = tempNum * pricePerGallon;
            DecimalFormat df = new DecimalFormat("###.##");
            result.setText("Price for road trip: $" + df.format(totalCost));
        }
    }
}
}
