package ru.alexey_podusov.workers.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ru.alexey_podusov.workers.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportFragmentManager().getFragments().isEmpty()) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, new SpecialtyListFragment_())
                    .commit();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
