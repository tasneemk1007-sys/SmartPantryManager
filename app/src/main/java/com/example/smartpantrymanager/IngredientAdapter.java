package com.example.smartpantrymanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {
    public interface Listener { void edit(Ingredient i); void delete(Ingredient i); }
    private final List<Ingredient> items; private final Listener listener;
    public IngredientAdapter(List<Ingredient> items, Listener listener) { this.items = items; this.listener = listener; }
    @NonNull @Override public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int type) { return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ingredient, parent, false)); }
    @Override public void onBindViewHolder(@NonNull ViewHolder h, int position) {
        Ingredient i = items.get(position); h.name.setText(i.getName());
        h.details.setText((i.getQuantity() == Math.rint(i.getQuantity()) ? String.valueOf((long)i.getQuantity()) : String.valueOf(i.getQuantity())) + " " + i.getUnit());
        h.expiry.setText(i.getExpiryDate() == null || i.getExpiryDate().isEmpty() ? "No expiry date" : "Expires: " + i.getExpiryDate());
        h.itemView.setOnClickListener(v -> listener.edit(i)); h.itemView.setOnLongClickListener(v -> { listener.delete(i); return true; });
    }
    @Override public int getItemCount() { return items.size(); }
    static class ViewHolder extends RecyclerView.ViewHolder { TextView name, details, expiry; ViewHolder(View v) { super(v); name=v.findViewById(R.id.ingredient_name); details=v.findViewById(R.id.ingredient_details); expiry=v.findViewById(R.id.ingredient_expiry); } }
}
