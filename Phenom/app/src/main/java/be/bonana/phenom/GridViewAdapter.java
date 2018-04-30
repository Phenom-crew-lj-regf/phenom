package be.bonana.phenom;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

/**
 * Created by liron.bonana on 21/05/2017.
 */

public class GridViewAdapter extends BaseAdapter {

    // Declare variables
    private Activity activity;
    private String[] filepath;
    private String[] filename;
    private String[] descriptions;
    private boolean isGeneralView;


    private static LayoutInflater inflater = null;

    public GridViewAdapter(Activity a, String[] fpath, String[] fname, String[] descriptions,boolean isGeneralView) {
        activity = a;
        filepath = fpath;
        filename = fname;
        this.isGeneralView = isGeneralView;
        this.descriptions = descriptions;
        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public int getCount() {
        return filepath.length;

    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.gridview_item, null);
        // Locate the TextView in gridview_item.xml
        TextView text;
        TextView description;
        final TextView timer;
        ImageView image;
        if(isGeneralView) {
             text = (TextView) vi.findViewById(R.id.title);
            // Locate description in gridview_item.xml
             description = (TextView) vi.findViewById(R.id.grid_description);
            timer = (TextView) vi.findViewById(R.id.timer);
            // Locate the ImageView in gridview_item.xml
             image = (ImageView) vi.findViewById(R.id.image);
            // Decode the filepath with BitmapFactory followed by the position
            Bitmap bmp = BitmapFactory.decodeFile(filepath[position]);
            // Set the decoded bitmap into ImageView
            image.setImageBitmap(bmp);
            vi.findViewById(R.id.grid_description).setVisibility(View.VISIBLE);

        }else{
             text = (TextView) vi.findViewById(R.id.title);
            //Locate description in gridview_item.xml
             description = (TextView) vi.findViewById(R.id.grid_description);
            timer = (TextView) vi.findViewById(R.id.timer);
             //Locate the ImageView in gridview_item.xml
            image = (ImageView) vi.findViewById(R.id.image);
           // image.setImageURI(Uri.parse(filepath[position]));
            // Decode the filepath with BitmapFactory followed by the position
            File file = new File(filepath[position]);
          //  if(file.exists())
           image.setImageURI(Uri.parse(filepath[position]));
        //     Bitmap bmp = BitmapFactory.decodeFile(filepath[position]);
            // Set the decoded bitmap into ImageView
       //     image.setImageBitmap(bmp);
            vi.findViewById(R.id.grid_description).setVisibility(View.INVISIBLE);
        }



        // Set file name to the TextView followed by the position
        text.setText(filename[position]);

        // Set file name to the TextView followed by the position

        new CountDownTimer(30000000, 5000) {

            public void onTick(long millisUntilFinished) {
                timer.setText("Temps restant: " + String.format("%1$tH:%1$tM:%1$tS", millisUntilFinished));
            }

            public void onFinish() {
                timer.setText("Temps écoulé!");
            }
        }.start();

        description.setText(descriptions[position]);

        return vi;
    }
}