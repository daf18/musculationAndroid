package com.example.musculaction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.musculaction.adapter.RecyclerViewAdapter;
import com.example.musculaction.data.DatabaseHelper;
import com.example.musculaction.model.Exercice;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ExercicesActivity extends AppCompatActivity implements RecyclerViewAdapter.OnExerciceClickListener{
    private FloatingActionButton add_exercice_fab;
    private RecyclerView recyclerView;
    private ImageButton update_button, delete_button;
    int idCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercices);

        add_exercice_fab = findViewById(R.id.add_exercice_fab);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseHelper db = new DatabaseHelper(this);

        //get the category that was clicked on
        idCategory = getIntent().getExtras().getInt("id", 0);
        Log.d("intent", "onCreate: "+ idCategory);

        //get the exercice that was created as new exercice
       if(getIntent().getExtras().getParcelable("newExercice") != null) {
            Exercice newExercice = getIntent().getExtras().getParcelable("newExercice");
            db.addExercice(newExercice);
        }


        //get exercises from category
        List<Exercice> exercicesCategory = db.getAllExercisesOfCategory(idCategory);
        Log.d("intent", "onCreate: "+ exercicesCategory.size());

        //Set up adapter
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(exercicesCategory, getApplicationContext(), this);
        recyclerView.setAdapter(recyclerViewAdapter);

        add_exercice_fab.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), NewExercice.class);
            intent.putExtra("idCategory",idCategory);
            startActivity(intent);
        });
    }

    @Override
    public void onExerciceClick(int position) {
        DatabaseHelper db = new DatabaseHelper(this);
        Exercice exercice = db.getExercice(position+1);
        Log.d("Click", "onExerciceClick: "+ exercice.getTitle());
        //activity exercice details
        Intent intent = new Intent(getApplicationContext(), ExerciceDetails.class);
        intent.putExtra("exercice", (Parcelable) exercice);
        startActivity(intent);

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewExercice.class);
                intent.putExtra("exerciceEdit", (Parcelable) exercice);
                intent.putExtra("idCategory",idCategory);
                startActivity(intent);
            }
        });

    }
}