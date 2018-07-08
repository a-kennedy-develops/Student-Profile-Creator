package com.example.alexk.studentprofilecreator;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    //Declaring constants
    public static final int REQ_CODE = 100;
    public static final String VALUE_KEY = "value";
    public static final String PROFILE_KEY = "Profile";

    //This function checks for valid email entry
    public static boolean isEmailValid(String email) {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        return matcher.matches();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Declaring variables for the UI components
        final RadioButton radio_SIS = findViewById(R.id.radioButton_1);
        final RadioButton radio_CIS = findViewById(R.id.radioButton_2);
        final RadioButton radio_BIO = findViewById(R.id.radioButton_3);

        final EditText edit_Name = findViewById(R.id.editText_name);
        final EditText edit_Email = findViewById(R.id.editText_email);

        final TextView textMood = findViewById(R.id.textView_dynamic);

        final Button button_sub = findViewById(R.id.button_submit);

        final ImageView imageMood = findViewById(R.id.imageView_mood);
        final ImageButton imageAvatar = findViewById(R.id.imageButton_avatar);

        final SeekBar moodBar = findViewById(R.id.seekBar);

        // Listener for imageAvater (imageButton)
        // Creates an intent and starts an activity for result
        imageAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AvatarSelector.class);
                startActivityForResult(intent, REQ_CODE);
            }
        });


        // Listener for moodBar (Seekbar)
        // Uses switch statement to update UI
        moodBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                switch (progress) {
                    case 0: {
                        textMood.setText(R.string.angry_moodBarString);
                        imageMood.setImageResource(R.drawable.angry);
                        break;
                    }
                    case 1: {
                        textMood.setText(R.string.sad_moodBarString);
                        imageMood.setImageResource(R.drawable.sad);
                        break;
                    }
                    case 2: {
                        textMood.setText(R.string.happy_moodBarString);
                        imageMood.setImageResource(R.drawable.happy);
                        break;
                    }
                    case 3: {
                        textMood.setText(R.string.awesome_moodBarString);
                        imageMood.setImageResource(R.drawable.awesome);
                        break;
                    }
                    default: {
                        textMood.setText(R.string.error_moodBarString);
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Listener for submit button
        button_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Retrieving values from editTexts, radioButton, and imageView for Profile object
                String email = edit_Email.getText().toString();
                String name = edit_Name.getText().toString();
                String mood = textMood.getText().toString();
                String radio;
                if (radio_SIS.isChecked()) {
                    radio = (String) radio_SIS.getText();
                } else if (radio_CIS.isChecked()) {
                    radio = (String) radio_CIS.getText();
                } else {
                    radio = (String) radio_BIO.getText();
                }

                // Converting imageView into a Byte Array
                final byte[] avatarArray = AvatarSelector.createByteArrayPNG(imageAvatar);

                //TODO: Transfer byte array as opposed to String for mood
                final byte[] moodArray = AvatarSelector.createByteArrayPNG(imageMood);

                try {

                    // Performing input validation
                    if (!isEmailValid(email) || (!name.matches("[a-zA-Z_ ]*") || !name.equals(""))) {
                        throw new InputMismatchException();

                        //Creating Profile object and packaging into intent
                    } else {
                        Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                        intent.putExtra(PROFILE_KEY, new Profile(name, email, radio, mood, avatarArray));
                        Log.d("demo", "Pre-Start flag");
                        startActivity(intent);
                    }

                    //For failed input validation
                } catch (InputMismatchException e) {
                    if (!isEmailValid(email) && (!name.matches("[a-zA-Z_ ]*") || !name.equals(""))) {
                        Toast.makeText(MainActivity.this, "Please re-enter your name and email", Toast.LENGTH_LONG).show();
                    } else if (!isEmailValid(email)) {
                        Toast.makeText(MainActivity.this, "Please re-enter your email", Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(MainActivity.this, "Please re-enter your name", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    // onActivityResult for the returned value from the Select Avatar screen
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE) {
            if (resultCode == RESULT_OK) {
                ImageButton imageAvatar = findViewById(R.id.imageButton_avatar);

                Bitmap bitmap = DisplayActivity.ByteArrayToBitmap(data.getByteArrayExtra(VALUE_KEY));
                imageAvatar.setImageBitmap(bitmap);

            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(MainActivity.this, "No image was chosen. Transfer canceled.", Toast.LENGTH_LONG).show();
            }
        }

    }
}
