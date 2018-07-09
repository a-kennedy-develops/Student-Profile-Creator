package com.example.alexk.studentprofilecreator;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.alexk.studentprofilecreator.MainActivity.PROFILE_KEY;


public class DisplayActivity extends AppCompatActivity {

    // This method converts a byte array into a bitmap
    public static Bitmap convertByteArrayToBitmap(byte[] byteArray) {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length); }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO: Clean up this activity_display layout
        setContentView(R.layout.activity_display);

//        Log.d("demo", "Made it to display");

        if (getIntent() != null && getIntent().getExtras() != null) {

            Profile profile = getIntent().getExtras().getParcelable(PROFILE_KEY);
            Log.d("demo", "Extracting profile data from intent...");

            //Setting student name
            TextView studentName = findViewById(R.id.textView_sendName);
            studentName.setText(profile.getName());

            //Setting student email
            TextView studentEmail = findViewById(R.id.textView_sendEmail);
            studentEmail.setText(profile.getEmail());

            //Setting department
            TextView department = findViewById(R.id.textView_sendDepart);
            department.setText(profile.getDepartment());

            //Setting student mood (String)
            TextView moodText = findViewById(R.id.textView_sendMood);
            moodText.setText(profile.getMoodText());

            //Setting mood image view
            ImageView imageViewMood = findViewById(R.id.imageView_moodDisplay);
            Bitmap bitmapMoodIntent = convertByteArrayToBitmap(profile.getMoodImage());
            imageViewMood.setImageBitmap(bitmapMoodIntent);

            //Setting Avatar image view
            ImageView imageViewAvatar = findViewById(R.id.imageView_avatarDisplay);
            Bitmap bitmapAvatarIntent = convertByteArrayToBitmap(profile.getAvatar());
            imageViewAvatar.setImageBitmap(bitmapAvatarIntent);

        }
    }
}