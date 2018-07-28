package ru.alexey_podusov.workers.ui;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.alexey_podusov.workers.R;
import ru.alexey_podusov.workers.views.Specialty;

public class SpecialtiesAdapter extends RecyclerView.Adapter<SpecialtiesAdapter.SpecialtyHolder> {
    private List<Specialty> specialties = new ArrayList<>();
    private FragmentManager fragmentManager;

    public SpecialtiesAdapter(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public SpecialtyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.view_list_item_specialty, viewGroup, false);
        return new SpecialtyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpecialtyHolder specialtyHolder, int i) {
        specialtyHolder.setSpecialty(specialties.get(i));
    }

    @Override
    public int getItemCount() {
        return specialties.size();
    }

    public void setSpecialties(List<Specialty> specialties) {
        this.specialties = specialties;
        notifyDataSetChanged();
    }

    public class SpecialtyHolder extends RecyclerView.ViewHolder {
        private TextView specialtyNameView;
        private long specialtyId;

        public SpecialtyHolder(@NonNull View itemView) {
            super(itemView);
            specialtyNameView = itemView.findViewById(R.id.specialty_name_text_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, WorkerListFramgent.create(specialtyId, specialtyNameView.getText().toString()))
                            .addToBackStack(WorkerListFramgent.class.getName())
                            .commit();
                }
            });
        }

        public void setSpecialty(Specialty specialty) {
            specialtyNameView.setText(specialty.getName());
            specialtyId = specialty.getSpecialtyResponseId();
        }
    }
}




