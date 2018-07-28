package ru.alexey_podusov.workers.models;

import java.util.List;

import ru.alexey_podusov.workers.views.Worker;

public class WorkerFullInfoData {
    private Worker worker;
    private List<String> specialtyNames;
    private String age;

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public List<String> getSpecialtyNames() {
        return specialtyNames;
    }

    public void setSpecialtyNames(List<String> specialtyNames) {
        this.specialtyNames = specialtyNames;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
