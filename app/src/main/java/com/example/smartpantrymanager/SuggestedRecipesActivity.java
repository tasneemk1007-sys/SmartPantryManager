package com.example.smartpantrymanager;

import android.content.Intent;import android.os.Bundle;import android.view.View;import android.widget.TextView;import androidx.appcompat.app.AppCompatActivity;import androidx.recyclerview.widget.*;import java.util.*;import java.util.concurrent.*;

public class SuggestedRecipesActivity extends AppCompatActivity{
 private PantryDatabaseHelper db; private final ArrayList<Recipe> recipes=new ArrayList<>(); private RecipeAdapter adapter; private TextView empty; private ExecutorService executor;
 public void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_recipes);setTitle("Suggested Recipes");executor=Executors.newSingleThreadExecutor();db=new PantryDatabaseHelper(getApplicationContext());empty=findViewById(R.id.empty_recipes);RecyclerView list=findViewById(R.id.recipe_list);list.setLayoutManager(new LinearLayoutManager(this));adapter=new RecipeAdapter(recipes,r->{Intent i=new Intent(this,RecipeDetailActivity.class);i.putExtra("id",r.getId());startActivity(i);});list.setAdapter(adapter);}
 protected void onResume(){super.onResume();loadRecipes();}
 private void loadRecipes(){executor.execute(()->{final List<Recipe> result=db.getMatchingRecipes();if(isFinishing()||isDestroyed())return;runOnUiThread(()->{recipes.clear();recipes.addAll(result);adapter.notifyDataSetChanged();empty.setVisibility(recipes.isEmpty()?View.VISIBLE:View.GONE);});});}
 protected void onDestroy(){if(executor!=null)executor.shutdownNow();super.onDestroy();}
}
