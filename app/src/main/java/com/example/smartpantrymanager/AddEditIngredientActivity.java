package com.example.smartpantrymanager;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddEditIngredientActivity extends AppCompatActivity {
    public static final String EXTRA_ID="ingredient_id", EXTRA_NAME="ingredient_name", EXTRA_QUANTITY="ingredient_quantity", EXTRA_UNIT="ingredient_unit", EXTRA_EXPIRY="ingredient_expiry";
    private EditText name, quantity, unit, expiry; private PantryDatabaseHelper db; private long id=-1;
    @Override protected void onCreate(Bundle state){ super.onCreate(state); setContentView(R.layout.activity_add_edit_ingredient); name=findViewById(R.id.ingredient_name_input); quantity=findViewById(R.id.quantity_input); unit=findViewById(R.id.unit_input); expiry=findViewById(R.id.expiry_input); db=new PantryDatabaseHelper(this);
        if(getIntent().hasExtra(EXTRA_ID)){id=getIntent().getLongExtra(EXTRA_ID,-1);setTitle("Edit Ingredient");name.setText(getIntent().getStringExtra(EXTRA_NAME));quantity.setText(String.valueOf(getIntent().getDoubleExtra(EXTRA_QUANTITY,0)));unit.setText(getIntent().getStringExtra(EXTRA_UNIT));expiry.setText(getIntent().getStringExtra(EXTRA_EXPIRY));}else setTitle("Add Ingredient");
        ((Button)findViewById(R.id.save_button)).setOnClickListener(v->save()); }
    private void save(){ String n=name.getText().toString().trim(), q=quantity.getText().toString().trim(), u=unit.getText().toString().trim(), e=expiry.getText().toString().trim();
        if(TextUtils.isEmpty(n)){name.setError("Enter an ingredient name");name.requestFocus();return;} if(TextUtils.isEmpty(q)){quantity.setError("Enter a quantity");quantity.requestFocus();return;} if(TextUtils.isEmpty(u)){unit.setError("Enter a unit");unit.requestFocus();return;}
        double number; try{number=Double.parseDouble(q);}catch(NumberFormatException ex){number=-1;} if(!Double.isFinite(number)||number<=0){quantity.setError("Quantity must be greater than zero");quantity.requestFocus();return;}
        Ingredient i=id<0?new Ingredient(n,number,u,e):new Ingredient(id,n,number,u,e); if(id<0)db.addIngredient(i);else db.updateIngredient(i); Toast.makeText(this,"Ingredient saved",Toast.LENGTH_SHORT).show();finish(); }
}
