package com.cleanup.todoc.read.businesslogic.usecases;

import androidx.annotation.NonNull;

import java.util.Comparator;

public class TaskVO {

    private long id;

    private ProjectVO projectVO;

    @NonNull
    private String name;

    private long creationTimestamp;

    public TaskVO(long id, ProjectVO projectVO, @NonNull String name, long creationTimestamp) {
        this.id = id;
        this.projectVO = projectVO;
        this.name = name;
        this.creationTimestamp = creationTimestamp;
    }

    public long getId() {
        return id;
    }

    public ProjectVO getProjectVO() {
        return projectVO;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public long getCreationTimestamp() {
        return creationTimestamp;
    }

    /**
     * Comparator to sort task from A to Z
     */
    public static class TaskAZComparator implements Comparator<TaskVO> {
        @Override
        public int compare(TaskVO left, TaskVO right) {
            return left.getName().compareTo(right.getName());
        }
    }

    /**
     * Comparator to sort task from Z to A
     */
    public static class TaskZAComparator implements Comparator<TaskVO> {
        @Override
        public int compare(TaskVO left, TaskVO right) {
            return right.getName().compareTo(left.getName());
        }
    }

    /**
     * Comparator to sort task from last created to first created
     */
    public static class TaskRecentComparator implements Comparator<TaskVO> {
        @Override
        public int compare(TaskVO left, TaskVO right) {
            return (int) (right.getCreationTimestamp() - left.getCreationTimestamp());
        }
    }

    /**
     * Comparator to sort task from first created to last created
     */
    public static class TaskOldComparator implements Comparator<TaskVO> {
        @Override
        public int compare(TaskVO left, TaskVO right) {
            return (int) (left.getCreationTimestamp() - right.getCreationTimestamp());
        }
    }

}
