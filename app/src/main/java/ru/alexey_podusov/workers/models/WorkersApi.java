package ru.alexey_podusov.workers.models;

import retrofit2.Call;
import retrofit2.http.GET;
import ru.alexey_podusov.workers.models.responses.WorkersResponse;

public interface WorkersApi {
    @GET("testTask.json")
    Call<WorkersResponse> getWorkers();
}
