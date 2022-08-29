package com.example.musculaction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musculaction.data.DatabaseHelper;
import com.example.musculaction.model.Exercice;

import java.util.regex.Pattern;

public class NewExercice extends AppCompatActivity {
    EditText title, description,details, youtube;
    Button saveExerciceButton;
    Exercice exercice;
    int categoryReceived;
    DatabaseHelper db;
    Pattern youtubePattern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_exercice);

        //img by defauld gain_muscle
        //category default the page i'm in...so category the one it was clicked
        //FIXME add animation

        title = findViewById(R.id.titreEx_et);
        description = findViewById(R.id.descEx_et);
        details = findViewById(R.id.detailsEx_et);
        youtube = findViewById(R.id.youtubeEx_et);
        saveExerciceButton = findViewById(R.id.saveEx_btn);
        db = new DatabaseHelper(getApplicationContext());
        //pattern for youtube link
        youtubePattern = Pattern.compile("^(http(s)?:\\/\\/)?((w){3}.)?youtu(be|.be)?(\\.com)?\\/.+");

        //get current category
        categoryReceived = getIntent().getExtras().getInt("idCategory", 0);
        Log.d("intent", "onCreate: "+ categoryReceived);

        saveExerciceButton.setOnClickListener(view -> {
            if (checkAllFields()){
                Intent intent = new Intent(getApplicationContext(), ExercicesActivity.class);
                //envoyer le produit a ExercicesActivity
                intent.putExtra("newExercice", (Parcelable) exercice);
                intent.putExtra("id",categoryReceived);
                startActivity(intent);

               // db.addExercice(exercice);

                finish();
            }
        });
    }
    private boolean checkAllFields(){
        String titleText = title.getText().toString();
        String descText = description.getText().toString();
        String detailsTxt = details.getText().toString();
        String youtubeTxt = youtube.getText().toString();

        if (titleText.isEmpty()) {
            title.setError("Nom de l'exercice requis!");
            return false;
        }

        if (descText.isEmpty()) {
            description.setError("Description de l'exercice requis!");
            return false;
        }

        if (detailsTxt.isEmpty()) {
            details.setError("Détails de l'exercice requis!");
            return false;
        }
        if (youtubeTxt.isEmpty()) {
            //youtube link by default
            youtubeTxt = "https://www.youtube.com/watch?v=s-dJnjryiT8";
        } else if(!youtubePattern.matcher(youtubeTxt).matches()){
            //if the provided youtube link is invalid, reset link to default value
            youtubeTxt = "https://www.youtube.com/watch?v=s-dJnjryiT8";
        }

        Toast.makeText(this,"Exercice "+titleText+" a été bien enregistré", Toast.LENGTH_LONG).show();

        //Exercice(String title, int img, String description, String details, String youtubeUrl,int category)
        exercice = new Exercice(titleText, R.drawable.gain_muscle,descText, detailsTxt,youtubeTxt,categoryReceived);

        Log.i("produit",exercice.toString());
        // if all required fields are filled , returns true
        return true;
    }
}