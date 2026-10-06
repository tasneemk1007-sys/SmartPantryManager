package com.example.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private PantryDatabaseHelper db; private IngredientAdapter adapter; private final ArrayList<Ingredient> items = new ArrayList<>();
    @Override protected void onCreate(Bundle state) { super.onCreate(state); setContentView(R.layout.activity_main); setTitle("My Pantry"); db = new PantryDatabaseHelper(this);
        RecyclerView list=findViewById(R.id.ingredient_list); list.setLayoutManager(new LinearLayoutManager(this));
        adapter=new IngredientAdapter(items, new IngredientAdapter.Listener(){ public void edit(Ingredient i){ openEdit(i); } public void delete(Ingredient i){ confirmDelete(i); }}); list.setAdapter(adapter);
        ((FloatingActionButton)findViewById(R.id.add_ingredient_button)).setOnClickListener(v -> startActivity(new Intent(this, AddEditIngredientActivity.class)));
        findViewById(R.id.suggested_button).setOnClickListener(v -> startActivity(new Intent(this, SuggestedRecipesActivity.class)));
        findViewById(R.id.settings_button).setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));
    }
    @Override protected void onResume(){ super.onResume(); if(db!=null) loadItems(); }
    private void loadItems(){ items.clear(); items.addAll(db.getAllIngredients()); adapter.notifyDataSetChanged(); }
    private void openEdit(Ingredient i){ Intent x=new Intent(this, AddEditIngredientActivity.class); x.putExtra(AddEditIngredientActivity.EXTRA_ID,i.getId()); x.putExtra(AddEditIngredientActivity.EXTRA_NAME,i.getName()); x.putExtra(AddEditIngredientActivity.EXTRA_QUANTITY,i.getQuantity()); x.putExtra(AddEditIngredientActivity.EXTRA_UNIT,i.getUnit()); x.putExtra(AddEditIngredientActivity.EXTRA_EXPIRY,i.getExpiryDate()); startActivity(x); }
    private void confirmDelete(Ingredient i){ new AlertDialog.Builder(this).setTitle("Delete ingredient?").setMessage(i.getName()).setNegativeButton("Cancel",null).setPositiveButton("Delete",(d,w)->{db.deleteIngredient(i.getId());loadItems();Toast.makeText(this,"Ingredient deleted",Toast.LENGTH_SHORT).show();}).show(); }
}
