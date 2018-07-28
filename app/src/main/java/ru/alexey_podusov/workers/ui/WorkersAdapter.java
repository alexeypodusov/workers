package ru.alexey_podusov.workers.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.alexey_podusov.workers.R;
import ru.alexey_podusov.workers.views.Worker;

public class WorkersAdapter extends RecyclerView.Adapter<WorkersAdapter.WorkerHolder> {
    private List<Pair<Worker, String>> workersWithAge = new ArrayList<>();
    private FragmentManager fragmentManager;

    public WorkersAdapter(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public WorkerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.view_list_item_worker, viewGroup, false);
        return new WorkerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkerHolder workerHolder, int i) {
        workerHolder.setWorkerWithAge(workersWithAge.get(i));
    }

    @Override
    public int getItemCount() {
        return workersWithAge.size();
    }

    public void setWorkersWithAge(List<Pair<Worker, String>> workers) {
        this.workersWithAge = workers;
        notifyDataSetChanged();
    }

    public class WorkerHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView ageTextView;
        private Context context;
        private long workerId;

        public WorkerHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.worker_name_text_view);
            ageTextView = itemView.findViewById(R.id.age_text_view);
            context = itemView.getContext();
            itemView.setOnClickListener(view -> fragmentManager.beginTransaction()
                    .replace(R.id.container, WorkerFragment.create(workerId))
                    .addToBackStack(WorkerFragment.class.getName())
                    .commit());
        }

        public void setWorkerWithAge(Pair<Worker, String> workerWithAge) {
            nameTextView.setText(workerWithAge.first.getFirstName() + " " + workerWithAge.first.getLastName());
            ageTextView.setText(context.getString(R.string.age_with_colon_text) + " " + workerWithAge.second);
            workerId = workerWithAge.first.getId();
        }
    }
}




