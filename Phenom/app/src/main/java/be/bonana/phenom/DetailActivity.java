package be.bonana.phenom;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView text;
    ImageView imageview;
    QuickContactBadge contactBadge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Retrieve data from MainActivity on GridView item click
        Intent i = getIntent();

        // Get the position
        int position = i.getExtras().getInt("position");
        String profilPic = i.getExtras().getString("profilPicPath");


        // Get String arrays FilePathStrings
        String[] filepath = i.getStringArrayExtra("filepath");

        // Get String arrays FileNameStrings
        String[] titles = i.getStringArrayExtra("titles");
        String[] descriptions = i.getStringArrayExtra("descriptions");

        text = (TextView) findViewById(R.id.CTitle);
        text.setText(titles[position]);
        // Locate the TextView in view_image.xml
        text = (TextView) findViewById(R.id.CDescription);

        // Load the text into the TextView followed by the position
        text.setText(descriptions[position]);

        // Locate the ImageView in view_image.xml
        imageview = (ImageView) findViewById(R.id.challenge_image);

        // Decode the filepath with BitmapFactory followed by the position
        Bitmap bmp = BitmapFactory.decodeFile(filepath[position]);

        // Set the decoded bitmap into ImageView
        imageview.setImageBitmap(bmp);


        imageview = (ImageView) findViewById(R.id.user_profil);

        // Decode the filepath with BitmapFactory followed by the position
        bmp = BitmapFactory.decodeFile(profilPic);

        // Set the decoded bitmap into ImageView
        imageview.setImageBitmap(bmp);
    }
}
