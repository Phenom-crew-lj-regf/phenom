package be.bonana.phenom;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class AddChallenge extends AppCompatActivity {

    private String username = null;
    private String filename = null;
    private String title = null;
    private String description = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_challenge);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //TextView text = (TextView)findViewById(R.id.addChallenge_title);
        Toolbar toolbar_1 = (Toolbar)findViewById(R.id.addchallenge_title);

        Bundle bundle = getIntent().getExtras();

        if(bundle!=null)
        {
            Toast.makeText(this,"Helo",Toast.LENGTH_LONG);
            String j =(String) bundle.get("username");
            toolbar_1.setTitle(j);
            username =  j;


            if(bundle.get("filename")!=null){
                changeImageView();
            }
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.okkButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(filename!=null) {
                    addChallenge();
                }else{
                    Toast.makeText(AddChallenge.this,"Veuillez d'abord prendre une photo avant de confirmer le défi",Toast.LENGTH_LONG).show();;
                }
            }
        });

        ImageButton photoButton = (ImageButton) findViewById(R.id.imageView_addchallenge);
        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPhoto();
            }
        });






    }

    public void changeImageView(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle!=null)
        {
            this.filename =(String) bundle.get("filename");

            ImageButton image = (ImageButton) findViewById(R.id.imageView_addchallenge);
            image.setImageURI(Uri.parse(filename));// To display selected image in image view
        }

    }
    public void addChallenge(){

        EditText title_input = (EditText) findViewById(R.id.title_input);
        EditText description_input = (EditText) findViewById(R.id.desciption_input);
        this.title = title_input.getText().toString();
        this.description = description_input.getText().toString();
        Intent intent = new Intent(this, Profil.class);
        intent.putExtra("title", title);
        intent.putExtra("description", description);
        ImageButton image = (ImageButton) findViewById(R.id.imageView_addchallenge);



        Bundle bundle = getIntent().getExtras();

        if(username!=null) {
            FileManager manager = new FileManager();
            String line;
            if(filename!= null) {
                line = username+";"+ title + ";" + description + ";" + filename;
            }else{
                line = username+";"+title + ";" + description + ";";
            }
            manager.writeLine(line);

            intent.putExtra("filepath",(String) bundle.get("filepath"));
            intent.putExtra("username",username);
            startActivity(intent);
        }


    }

    public void addPhoto(){
        Intent intent = new Intent(this, CameraActivity.class);
        Bundle bundle = getIntent().getExtras();

   //         if (username != null && bundle != null) {
                intent.putExtra("username", username);

                intent.putExtra("filepath", (String) bundle.get("filepath"));
                startActivity(intent);
         //   }



    }

}
