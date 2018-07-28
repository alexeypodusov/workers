package ru.alexey_podusov.workers.models;

import io.reactivex.Maybe;
import retrofit2.http.GET;
import ru.alexey_podusov.workers.models.responses.Response;

public interface WorkersApi {
    @GET("testTask.json")
    Maybe<Response> getWorkers();
}
