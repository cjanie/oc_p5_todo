package com.cleanup.todoc.read.businesslogic.usecases;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

public class ProjectVO {

    private long id;

    @NonNull
    private String name;

    @ColorInt
    private int color;

    public ProjectVO(long id, @NonNull String name, @ColorInt int color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
