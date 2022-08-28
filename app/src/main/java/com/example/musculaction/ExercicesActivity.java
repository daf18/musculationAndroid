package com.example.musculaction;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.musculaction.data.DatabaseHelper;
import com.example.musculaction.model.Exercice;

import java.util.List;

public class ExercicesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercices);
        DatabaseHelper db = new DatabaseHelper(this);
        List<Exercice> exercicesCategory;

        int idCategory = getIntent().getExtras().getInt("id",0);

        Log.d("intent", "onCreate: "+ idCategory);

        exercicesCategory = db.getAllExercicesOfCategory(idCategory);

        Log.d("intent", "onCreate: "+ exercicesCategory.size());
    }
}