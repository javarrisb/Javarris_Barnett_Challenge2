package com.company.challenge2.models;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class MonthService {
    @NotEmpty(message="You must enter a valid month number.")
    private int number;
    @NotEmpty(message="You must enter a valid month name.")
    private String name;

    public MonthService(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthService that = (MonthService) o;
        return getNumber() == that.getNumber() && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getName());
    }
}
