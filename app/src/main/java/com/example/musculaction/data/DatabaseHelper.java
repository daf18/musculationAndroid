package com.example.musculaction.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.musculaction.R;
import com.example.musculaction.model.Category;
import com.example.musculaction.model.Exercice;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Database related items
    public static int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "musculation";

    public static final String TABLE_CATEGORIES = "categories_table";
    public static final String TABLE_EXERCICES = "exercices_table";

    //Categories tables column names
    public static final String KEY_ID_CAT = "id";
    public static final String KEY_CATEGORY_TITLE = "title";
    public static final String KEY_IMG_CAT = "img";

    //Exercices tables column names
    public static final String KEY_ID_EX = "id";
    public static final String KEY_TITLE_EX = "title";
    public static final String KEY_IMG_EX = "img";
    public static final String KEY_DESC_EX = "description";
    public static final String KEY_DETAILS_EX = "details";
    public static final String KEY_YOUTUBE_EX = "youtubeUrl";
    public static final String KEY_EX_CAT = "category"; //foreign key

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CATEGORIES_TABLE = "CREATE TABLE " + TABLE_CATEGORIES + "("
                + KEY_ID_CAT + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_CATEGORY_TITLE + " TEXT,"
                + KEY_IMG_CAT + " INTEGER" + ")";
        db.execSQL(CREATE_CATEGORIES_TABLE);

        String CREATE_TABLE_EXERCICES = "CREATE TABLE " + TABLE_EXERCICES + "("
                + KEY_ID_EX + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_TITLE_EX + " TEXT,"
                + KEY_IMG_EX + " INTEGER,"
                + KEY_DESC_EX + " TEXT,"
                + KEY_DETAILS_EX + " TEXT,"
                + KEY_YOUTUBE_EX + " TEXT,"
                + KEY_EX_CAT + " INTEGER,"
                + " FOREIGN KEY ("+KEY_EX_CAT+") REFERENCES "+TABLE_CATEGORIES+"("+KEY_ID_CAT+"));";
        db.execSQL(CREATE_TABLE_EXERCICES);

        ContentValues values = new ContentValues();

        //Categories values

        //values.put(KEY_ID_CAT,"1");
        values.put(KEY_CATEGORY_TITLE,"Exercices Abdominaux");
        values.put(KEY_IMG_CAT, R.drawable.abdominaux);
        db.insert(TABLE_CATEGORIES,null, values);

        //values.put(KEY_ID_CAT,"2");
        values.put(KEY_CATEGORY_TITLE,"Exercices Biceps");
        values.put(KEY_IMG_CAT,R.drawable.biceps);
        db.insert(TABLE_CATEGORIES,null, values);

        //values.put(KEY_ID_CAT,"3");
        values.put(KEY_CATEGORY_TITLE,"Exercices Dos");
        values.put(KEY_IMG_CAT,R.drawable.dos);
        db.insert(TABLE_CATEGORIES,null, values);

        //Exercices values

        // values.put(KEY_ID_EX,"1");
        values.put(KEY_TITLE_EX,"Curl haltère");
        values.put(KEY_IMG_EX, R.drawable.curl_haltere);
        values.put(KEY_DESC_EX,"Une alternative à la barre qui permet de travailler les bras séparément. Les haltères permettent pas mal de variantes intéressantes. Avec elles, vous allez vous forger des biceps d\\'acier");
        values.put(KEY_DETAILS_EX,"Les haltères permettent pas mal de variantes intéressantes.");
        values.put(KEY_YOUTUBE_EX,"https://youtu.be/dh6Tcwy9a_o");
        values.put(KEY_EX_CAT,"2");
        db.insert(TABLE_EXERCICES,null, values);

        // values.put(KEY_ID_EX,"2");
        values.put(KEY_TITLE_EX,"Curl barre");
        values.put(KEY_IMG_EX, R.drawable.curl_barre);
        values.put(KEY_DESC_EX, "Cet exercice de musculation sollicite et développe les biceps. Le curl barre est l’exercice d’isolation de base pour les biceps.");
        values.put(KEY_DETAILS_EX,"Le curl barre est l’exercice d’isolation de base pour les biceps.");
        values.put(KEY_YOUTUBE_EX,"https://youtu.be/ZLRBO5wiPwM");
        values.put(KEY_EX_CAT,"2");
        db.insert(TABLE_EXERCICES,null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCICES);
        onCreate(db);
    }

    public void addCategory(Category category){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //insert values in content values
        values.put(KEY_CATEGORY_TITLE,category.getTitle());
        values.put(KEY_IMG_CAT, category.getImage());

        //insert data from values into db row
        db.insert(TABLE_CATEGORIES, null,values);

        Log.d("dbHandler", "addContact: item added");

        //closing db connection
        //db.close();
    }
    //Get ALL categories
    public List<Category> getAllCategories(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Category> categoryList = new ArrayList<>();

        //Select all contacts
        String selectAll = "SELECT * FROM "+ TABLE_CATEGORIES;
        Cursor cursor = db.rawQuery(selectAll, null);

        //Loop through our data
        if(cursor.moveToFirst()){
            do {
                Category cat = new Category(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));

                //ADD contact obj to our list
                categoryList.add(cat);

            } while (cursor.moveToNext());
        }
        return categoryList;
    }

    //get all exercises from a category
    public List<Exercice> getAllExercisesOfCategory(int categId){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Exercice> exercicesList = new ArrayList<>();

        //Select all contacts
        String selectAll = "SELECT * FROM "+ TABLE_EXERCICES + " WHERE category="+categId;
        Cursor cursor = db.rawQuery(selectAll, null);

        //Loop through our data
        if(cursor.moveToFirst()){
            do {
                //Exercice(int id, String title, int img, String description, String details, String youtubeUrl,int category)
                Exercice ex = new Exercice(cursor.getInt(0), //id
                        cursor.getString(1), //title
                        cursor.getInt(2),    //img
                        cursor.getString(3), //description
                        cursor.getString(4), //details
                        cursor.getString(5), //youtubeUrl
                        cursor.getInt(6));   //category

                //ADD exercice obj to our list
                exercicesList.add(ex);

            } while (cursor.moveToNext());
        }
        return exercicesList;
    }

    //Add exercise
    public void addExercice(Exercice exercice){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //insert values in content values
        values.put(KEY_TITLE_EX,exercice.getTitle());
        values.put(KEY_IMG_EX, exercice.getImg());
        values.put(KEY_DESC_EX, exercice.getDescription());
        values.put(KEY_DETAILS_EX, exercice.getDetails());
        values.put(KEY_YOUTUBE_EX, exercice.getYoutubeUrl());
        values.put(KEY_EX_CAT, exercice.getCategory());

        //insert data from values into db row
        db.insert(TABLE_EXERCICES, null,values);
        Log.d("dbHandler", "addContact: exercice added");

        //closing db connection
        //db.close();
    }

    //Get one exercice by id
    public Exercice getExercice(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_EXERCICES,new String[]{KEY_ID_EX, KEY_TITLE_EX,KEY_IMG_EX,KEY_DESC_EX,KEY_DETAILS_EX,KEY_YOUTUBE_EX,KEY_EX_CAT},
                KEY_ID_EX +"=?",new String[]{String.valueOf(id)},null,null,null);

        if(cursor != null )
            cursor.moveToFirst();

        Exercice exercice = new Exercice();
        //ID
        //exercice.setId(cursor.getInt(0));
        exercice.setTitle(cursor.getString(1));
        exercice.setImg(cursor.getInt(2));
        exercice.setDescription(cursor.getString(3));
        exercice.setDetails(cursor.getString(4));
        exercice.setYoutubeUrl(cursor.getString(5));
        exercice.setCategory(cursor.getInt(6));

        return exercice;
    }
    //UPDATE Exercice
    public int updateExercice(Exercice exercice){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_TITLE_EX,exercice.getTitle());
        values.put(KEY_IMG_EX, exercice.getImg());
        values.put(KEY_DESC_EX, exercice.getDescription());
        values.put(KEY_DETAILS_EX, exercice.getDetails());
        values.put(KEY_YOUTUBE_EX, exercice.getYoutubeUrl());
        values.put(KEY_EX_CAT, exercice.getCategory());

        //update row
        //update(tabelName, contentValues, where id = 2) //or other number
        return db.update(TABLE_EXERCICES, values, KEY_ID_EX + "=?",
                new String[]{String.valueOf(exercice.getId())});
    }

    //Delete one exercice
    public void deleteExercice(Exercice exercice){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_EXERCICES, KEY_ID_EX + "=?",
                new String[]{String.valueOf(exercice.getId())});
    }
}
