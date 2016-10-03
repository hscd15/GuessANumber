/**
 * @description The purpose of this app is to do battle with the SIR-Standard Information Retrieval unit known as Gir.
 * You, the player has to guess any number between 1-1000, you have a total of 15 guesses before Gir wins,
 * @author {saad0033@algonquinlive.com}
 */

package com.algonquinlive.saad0033.guessanumber;

import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String ABOUT_DIALOG_TAG;

    static {
        ABOUT_DIALOG_TAG = "About Dialog";
    }

    private static int userCount = (int) 0;
    private static int theNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button playBtn = (Button) findViewById(R.id.play_btn);
        Button restBtn = (Button) findViewById(R.id.reset_btn);

        theNumber = randInt(0, 1000);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userCount++;
                EditText userGuessText = (EditText) findViewById(R.id.userGuess);

                String userGuess = userGuessText.getText().toString();
                boolean digitsOnly = TextUtils.isDigitsOnly(userGuessText.getText());

                if (userGuess.isEmpty()) {
                    userGuessText.setError("Enter a guess or GIR will win!!!");
                    userGuessText.requestFocus();
                } else {
                    if (digitsOnly) {
                        int UserGuess = Integer.parseInt(userGuess);

                        if (UserGuess > 1 && UserGuess < 1000) {
                            if (UserGuess == theNumber) {
                                if (userCount > 10) {
                                    Toast.makeText(getApplicationContext(), "You lost", Toast.LENGTH_SHORT).show();
                                    resetGame();
                                } else if (userCount <= 5) {
                                    Toast.makeText(getApplicationContext(), "You have a superior win!", Toast.LENGTH_SHORT).show();
                                    resetGame();
                                } else if (userCount > 5 && userCount <= 10) {
                                    Toast.makeText(getApplicationContext(), "You have an excellent win!", Toast.LENGTH_SHORT).show();
                                    resetGame();
                                }
                            } else if (UserGuess > theNumber) {
                                if (userCount > 10) {
                                    Toast.makeText(getApplicationContext(), "You lost", Toast.LENGTH_SHORT).show();
                                    resetGame();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Your guess is too high!", Toast.LENGTH_SHORT).show();
                                }
                            } else if (UserGuess < theNumber) {
                                if (userCount > 10) {
                                    Toast.makeText(getApplicationContext(), "You lost", Toast.LENGTH_SHORT).show();
                                    resetGame();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Your guess is too low!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } else {
                            userGuessText.setError("Number must be between 1-1000.");
                            userGuessText.requestFocus();
                        }
                    } else {
                        userGuessText.setError("You must enter numbers only.");
                        userGuessText.requestFocus();
                    }
                }
            }
        });

        restBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

        restBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "Secret number is " + theNumber, Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // TODO: add this method to handle when the user selects a menu item
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_about) {
            DialogFragment newFragment = new AboutDialogFragment();
            newFragment.show(getFragmentManager(), ABOUT_DIALOG_TAG);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public void resetGame() {
        userCount = 0;
        theNumber = randInt(0, 1000);
        Toast.makeText(getApplicationContext(), "Game is reset", Toast.LENGTH_LONG).show();
    }
}