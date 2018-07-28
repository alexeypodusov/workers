package ru.alexey_podusov.workers.models.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {
    @SerializedName("response")
    public List<WorkerResponse> workers;

    public static class WorkerResponse {
        @SerializedName("f_name")
        public String firstName;

        @SerializedName("l_name")
        public String lastName;

        public String birthday;

        @SerializedName("avatr_url")
        public String avatrUrl;

        @SerializedName("specialty")
        public List<SpecialtyResponse> specialties;
    }

    public static class SpecialtyResponse {
        @SerializedName("specialty_id")
        public long specialtyId;

        public String name;
    }

}
