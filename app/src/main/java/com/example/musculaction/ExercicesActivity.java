package com.example.musculaction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.musculaction.adapter.RecyclerViewAdapter;
import com.example.musculaction.data.DatabaseHelper;
import com.example.musculaction.model.Exercice;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ExercicesActivity extends AppCompatActivity implements RecyclerViewAdapter.OnExerciceClickListener{
    private FloatingActionButton add_exercice_fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercices);

        add_exercice_fab = findViewById(R.id.add_exercice_fab);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseHelper db = new DatabaseHelper(this);

        //get the category that was clicked on
        int idCategory = getIntent().getExtras().getInt("id", 0);
        Log.d("intent", "onCreate: "+ idCategory);

        //get exercises from category
        List<Exercice> exercicesCategory = db.getAllExercisesOfCategory(idCategory);
        Log.d("intent", "onCreate: "+ exercicesCategory.size());

        //Set up adapter
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(exercicesCategory, getApplicationContext(), this);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void onExerciceClick(int position) {
        DatabaseHelper db = new DatabaseHelper(this);
        Exercice exercice = db.getExercice(position+1);
        Log.d("Click", "onExerciceClick: "+ exercice.getTitle());
        //FIXME start new activity here
    }
}