/**
 * FRUGAL BUY
 * <p>
 * Android application to compare unit price for up to three items to determine which is the cheapest.
 *
 * @author Emma Kasper
 * @since 2/5/2020
 */

package edu.uga.cs.frugalshopper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.io.IOError;
import java.math.RoundingMode;
import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {


    private double getPriceA;
    private double getPriceB;
    private double getPriceC;
    private int getPoundA;
    private int getPoundB;
    private int getPoundC;
    private int getOunceA;
    private int getOunceB;
    private int getOunceC;
    private double totalA;
    private double totalB;
    private double totalC;
    private EditText poundA;
    private EditText poundB;
    private EditText poundC;
    private EditText ounceA;
    private EditText ounceB;
    private EditText ounceC;
    private EditText priceA;
    private EditText priceB;
    private EditText priceC;
    private TextView result;
    private TextView unitA;
    private TextView unitB;
    private TextView unitC;
    private Button compare;
    private Toast toast;

    DecimalFormat df = new DecimalFormat("###.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * creating views for the input
         */

        poundA = findViewById(R.id.poundA);
        poundB = findViewById(R.id.poundB);
        poundC = findViewById(R.id.poundC);

        ounceA = findViewById(R.id.ounceA);
        ounceB = findViewById(R.id.ounceB);
        ounceC = findViewById(R.id.ounceC);

        priceA = findViewById(R.id.priceA);
        priceB = findViewById(R.id.priceB);
        priceC = findViewById(R.id.priceC);

        unitA = findViewById(R.id.unitA);
        unitB = findViewById(R.id.unitB);
        unitC = findViewById(R.id.unitC);

        /**
         * creating views for the output
         */
        compare = findViewById(R.id.compare);
        compare.setOnClickListener(new ButtonClickListener());
        result = findViewById(R.id.results);

    }

    /**
     * Returns a string to be displayed on the device of which item to purchase
     *
     * @param totalA double value of itemA's price
     * @param totalB double value of itemB's price
     * @param totalC double value of itemC's price
     * @return String returns string to display to device
     */

    String cheapestItem(double totalA, double totalB, double totalC) {

        String output = " ";

        if (totalA == totalB && (totalA == totalC)) {

            output = "Purchase item A, B or C";
        } else if ((totalA == totalB) && (totalA <= totalC)) {

            output = "Purchase item A or B";
        } else if (totalA == totalC && totalA <= totalB) {

            output = "Purchase item A or C";
        } else if (totalB == totalC && totalB <= totalA) {

            output = "Purchase item B or C";
        } else if (totalA <= totalB && totalA <= totalC) {

            output = "Purchase item A";

        } else if (totalB <= totalA && totalB <= totalC) {
            output = "Purchase item B";


        } else if (totalC <= totalA && totalC <= totalB) {
            output = "Purchase item C ";

        } else {

            output = " ";
            toast = Toast.makeText(getApplicationContext(), "Try again", Toast.LENGTH_SHORT);
            toast.show();

        }

        return output;
    }

    private class ButtonClickListener implements View.OnClickListener {

        /**
         * Action that occurs on a button press. Reads input entered by the user. Compares the input and determines
         * unit price of up to three items. Displays to user which item is the cheapest.
         *
         * @param v view
         * @throws ArithmeticException   arithmetic exception
         * @throws NumberFormatException number format exception
         * @throws Exception             all other exceptions
         * @throws IOError               input and out exception
         */

        @Override
        public void onClick(View v) {

            df.setRoundingMode(RoundingMode.CEILING);

            try {

                /* If there are only inputs for items B and C*/
                if ((poundA.getText().length() == 0 && ounceA.getText().length() == 0 && priceA.getText().length() == 0) &&
                        (poundB.getText().length() != 0) && (ounceB.getText().length() != 0) && (priceB.getText().length() != 0)
                        && (poundC.getText().length() != 0) && (ounceC.getText().length() != 0) && (priceC.getText().length() != 0)) {

                    /* reading price and weight */
                    getPoundB = Integer.parseInt(poundB.getText().toString());
                    getOunceB = Integer.parseInt(ounceB.getText().toString());
                    getPriceB = Double.parseDouble(priceB.getText().toString());


                    getPoundC = Integer.parseInt(poundC.getText().toString());
                    getOunceC = Integer.parseInt(ounceC.getText().toString());
                    getPriceC = Double.parseDouble(priceC.getText().toString());

                    /* calculating unit price */
                    try {
                        totalB = getPriceB / ((getPoundB * 16) + getOunceB);
                        totalC = getPriceC / ((getPoundC * 16) + getOunceC);

                        if (Double.isNaN(totalB) || Double.isNaN(totalC)) {

                            totalB = 0.0;
                            totalC = 0.0;

                        }

                    } catch (ArithmeticException ae) {

                        totalB = 0.0;
                        totalC = 0.0;

                    }

                    /* display unit price to user */
                    totalA = Double.POSITIVE_INFINITY;
                    unitB.setText("Unit price B: $" + df.format(totalB));
                    unitC.setText("Unit price C: $" + df.format(totalC));
                    unitA.setText("Unit price A:\n");
                    result.setText(cheapestItem(totalA, totalB, totalC));

                }
                /* If there are only inputs for items A and C*/
                else if ((poundB.getText().length() == 0 && ounceB.getText().length() == 0 && priceB.getText().length() == 0) &&
                        (poundA.getText().length() != 0) && (ounceA.getText().length() != 0) && (priceA.getText().length() != 0)
                        && (poundC.getText().length() != 0) && (ounceC.getText().length() != 0) && (priceC.getText().length() != 0)) {

                    /* reading price and weight */
                    getPoundA = Integer.parseInt(poundA.getText().toString());
                    getOunceA = Integer.parseInt(ounceA.getText().toString());
                    getPriceA = Double.parseDouble(priceA.getText().toString());

                    getPoundC = Integer.parseInt(poundC.getText().toString());
                    getOunceC = Integer.parseInt(ounceC.getText().toString());
                    getPriceC = Double.parseDouble(priceC.getText().toString());

                    /* calculating unit price */
                    try {
                        totalA = getPriceA / ((getPoundA * 16) + getOunceA);
                        totalC = getPriceC / ((getPoundC * 16) + getOunceC);


                        if (Double.isNaN(totalA) || Double.isNaN(totalC)) {
                            totalA = 0.0;
                            totalC = 0.0;

                        }

                    } catch (ArithmeticException ae) {

                        totalA = 0.0;
                        totalC = 0.0;

                    }
                    /* display unit price to user */
                    totalB = Double.POSITIVE_INFINITY;
                    unitA.setText("Unit price A: $" + df.format(totalA));
                    unitC.setText("Unit price C: $" + df.format(totalC));
                    unitB.setText("Unit price B: \n");

                    result.setText(cheapestItem(totalA, totalB, totalC));

                }
                /* If there are only inputs for items A and B */
                else if ((poundC.getText().length() == 0 && ounceC.getText().length() == 0 && priceC.getText().length() == 0) &&
                        (poundA.getText().length() != 0) && (ounceA.getText().length() != 0) && (priceA.getText().length() != 0)
                        && (poundB.getText().length() != 0) && (ounceB.getText().length() != 0) && (priceB.getText().length() != 0)) {

                    /* reading price and weight */
                    getPoundA = Integer.parseInt(poundA.getText().toString());
                    getOunceA = Integer.parseInt(ounceA.getText().toString());
                    getPriceA = Double.parseDouble(priceA.getText().toString());

                    getPoundB = Integer.parseInt(poundB.getText().toString());
                    getOunceB = Integer.parseInt(ounceB.getText().toString());
                    getPriceB = Double.parseDouble(priceB.getText().toString());

                    /* calculating unit price */
                    try {
                        totalB = getPriceB / ((getPoundB * 16) + getOunceB);
                        totalA = getPriceA / ((getPoundA * 16) + getOunceA);

                        if (Double.isNaN(totalA) || Double.isNaN(totalB)) {
                            totalA = 0.0;
                            totalB = 0.0;

                        }

                    } catch (ArithmeticException ae) {

                        totalA = 0.0;
                        totalB = 0.0;

                    }

                    /* display unit price to user */
                    totalC = Double.POSITIVE_INFINITY;
                    unitA.setText("Unit price A: $" + df.format(totalA));
                    unitB.setText("Unit price B: $" + df.format(totalB));
                    unitC.setText("Unit price C:\n");

                    result.setText(cheapestItem(totalA, totalB, totalC));

                }
                /* If there are only inputs for itemC*/

                else if ((poundA.getText().length() == 0 && ounceA.getText().length() == 0 && priceA.getText().length() == 0) &&
                        (poundB.getText().length() == 0) && (ounceB.getText().length() == 0) && (priceB.getText().length() == 0)
                        && (poundC.getText().length() != 0) && (ounceC.getText().length() != 0) && (priceC.getText().length() != 0)) {

                    /* reading price and weight */
                    getPoundC = Integer.parseInt(poundC.getText().toString());
                    getOunceC = Integer.parseInt(ounceC.getText().toString());
                    getPriceC = Double.parseDouble(priceC.getText().toString());

                    /* calculating unit price */
                    try {
                        totalC = getPriceC / ((getPoundC * 16) + getOunceC);

                        if (Double.isNaN(totalC)) {

                            totalC = 0.0;

                        }

                    } catch (ArithmeticException ae) {

                        totalC = 0.0;

                    }

                    totalA = Double.POSITIVE_INFINITY;
                    totalB = Double.POSITIVE_INFINITY;

                    /* display unit price to user */
                    unitC.setText("Unit price C: $" + df.format(totalC));
                    unitA.setText("Unit price A:\n");
                    unitB.setText("Unit price B:\n");

                    result.setText(cheapestItem(totalA, totalB, totalC));

                }
                /* If there are only inputs for item */
                else if ((poundB.getText().length() == 0 && ounceB.getText().length() == 0 && priceB.getText().length() == 0) &&
                        (poundA.getText().length() != 0) && (ounceA.getText().length() != 0) && (priceA.getText().length() != 0)
                        && (poundC.getText().length() == 0) && (ounceC.getText().length() == 0) && (priceC.getText().length() == 0)) {

                    /* reading price and weight */
                    getPoundA = Integer.parseInt(poundA.getText().toString());
                    getOunceA = Integer.parseInt(ounceA.getText().toString());
                    getPriceA = Double.parseDouble(priceA.getText().toString());

                    /* calculating unit price */
                    try {

                        totalA = getPriceA / ((getPoundA * 16) + getOunceA);
                        if (Double.isNaN(totalA)) {

                            totalA = 0.0;

                        }

                    } catch (ArithmeticException ae) {

                        totalA = 0.0;

                    }
                    /* display unit price to user */

                    totalB = Double.POSITIVE_INFINITY;
                    totalC = Double.POSITIVE_INFINITY;
                    unitA.setText("Unit price A: $" + df.format(totalA));
                    unitB.setText("Unit price B:\n");
                    unitC.setText("Unit price C:\n");

                    result.setText(cheapestItem(totalA, totalB, totalC));

                }

                /* If there are only inputs for items B*/
                else if ((poundC.getText().length() == 0 && ounceC.getText().length() == 0 && priceC.getText().length() == 0) &&
                        (poundA.getText().length() == 0) && (ounceA.getText().length() == 0) && (priceA.getText().length() == 0)
                        && (poundB.getText().length() != 0) && (ounceB.getText().length() != 0) && (priceB.getText().length() != 0)) {

                    /* reading price and weight */
                    getPoundB = Integer.parseInt(poundB.getText().toString());
                    getOunceB = Integer.parseInt(ounceB.getText().toString());
                    getPriceB = Double.parseDouble(priceB.getText().toString());

                    /* calculating unit price */
                    try {

                        totalB = getPriceB / ((getPoundB * 16) + getOunceB);

                        if (Double.isNaN(totalB)) {

                            totalB = 0.0;

                        }

                    } catch (ArithmeticException ae) {

                        totalB = 0.0;

                    }
                    /* display unit price to user */

                    totalA = Double.POSITIVE_INFINITY;
                    totalC = Double.POSITIVE_INFINITY;
                    unitB.setText("Unit price B: $" + df.format(totalB));
                    unitA.setText("Unit price A:\n");
                    unitC.setText("Unit price C:\n");

                    result.setText(cheapestItem(totalA, totalB, totalC));

                }

                /* If there are inputs for all items*/

                else if ((poundC.getText().length() != 0 && ounceC.getText().length() != 0 && priceC.getText().length() != 0) &&
                        (poundA.getText().length() != 0) && (ounceA.getText().length() != 0) && (priceA.getText().length() != 0)
                        && (poundB.getText().length() != 0) && (ounceB.getText().length() != 0) && (priceB.getText().length() != 0)) {

                    /* reading price and weight */
                    getPoundA = Integer.parseInt(poundA.getText().toString());
                    getOunceA = Integer.parseInt(ounceA.getText().toString());
                    getPriceA = Double.parseDouble(priceA.getText().toString());


                    getPoundB = Integer.parseInt(poundB.getText().toString());
                    getOunceB = Integer.parseInt(ounceB.getText().toString());
                    getPriceB = Double.parseDouble(priceB.getText().toString());


                    getPoundC = Integer.parseInt(poundC.getText().toString());
                    getOunceC = Integer.parseInt(ounceC.getText().toString());
                    getPriceC = Double.parseDouble(priceC.getText().toString());

                    /* calculating unit price */
                    try {

                        totalA = getPriceA / ((getPoundA * 16) + getOunceA);
                        totalB = getPriceB / ((getPoundB * 16) + getOunceB);
                        totalC = getPriceC / ((getPoundC * 16) + getOunceC);
                        if (Double.isNaN(totalA) || Double.isNaN(totalB) || Double.isNaN(totalC)) {

                            totalA = 0.0;
                            totalB = 0.0;
                            totalC = 0.0;

                        }

                    } catch (ArithmeticException ae) {

                        totalA = 0.0;
                        totalB = 0.0;
                        totalC = 0.0;

                    }

                    /* display unit price to user */
                    unitA.setText("Unit price A: $" + df.format(totalA));
                    unitB.setText("Unit price B: $" + df.format(totalB));
                    unitC.setText("Unit price C: $" + df.format(totalC));

                    result.setText(cheapestItem(totalA, totalB, totalC));

                }

                /* If the fields are not filled out properly*/
                else {
                    toast = Toast.makeText(getApplicationContext(), "Please ensure all fields are filled for your item or items.", Toast.LENGTH_LONG);
                    toast.show();

                }

            } catch (NumberFormatException nfe) {

                poundA.setText(null);
                poundB.setText(null);
                poundC.setText(null);

                ounceA.setText(null);
                ounceB.setText(null);
                ounceC.setText(null);

                priceA.setText(null);
                priceB.setText(null);
                priceC.setText(null);

                unitA.setText(null);
                unitB.setText(null);
                unitC.setText(null);


            } catch (Exception e) {

                e.printStackTrace();
                poundA.setText(null);
                poundB.setText(null);
                poundC.setText(null);

                ounceA.setText(null);
                ounceB.setText(null);
                ounceC.setText(null);

                priceA.setText(null);
                priceB.setText(null);
                priceC.setText(null);

                unitA.setText(null);
                unitB.setText(null);
                unitC.setText(null);


            } catch (IOError ioe) {

                poundA.setText(null);
                poundB.setText(null);
                poundC.setText(null);

                ounceA.setText(null);
                ounceB.setText(null);
                ounceC.setText(null);

                priceA.setText(null);
                priceB.setText(null);
                priceC.setText(null);

                unitA.setText(null);
                unitB.setText(null);
                unitC.setText(null);


            }


        }


    }

}