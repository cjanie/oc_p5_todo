package com.cleanup.todoc.ui;

import android.content.res.ColorStateList;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cleanup.todoc.read.businesslogic.usecases.TaskVO;
import com.cleanup.todoc.R;
import com.cleanup.todoc.read.businesslogic.usecases.ProjectVO;

import java.util.List;

//Adapter which handles the list of tasks to display in the dedicated RecyclerView
//@author Gaëtan HERFRAY

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TaskViewHolder> {

    // The list of tasks the adapter deals with
    @NonNull
    private List<TaskVO> tasks;

    // The listener for when a task needs to be deleted
    @NonNull
    private final DeleteTaskListener deleteTaskListener;

    // Instantiates a new TasksAdapter
    TasksAdapter(@NonNull final List<TaskVO> tasks, @NonNull final DeleteTaskListener deleteTaskListener) {
        this.tasks = tasks;
        this.deleteTaskListener = deleteTaskListener;
    }

    // Updates the list of tasks the adapter deals with
    void updateTasks(@NonNull final List<TaskVO> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_task, viewGroup, false);
        return new TaskViewHolder(view, deleteTaskListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder taskViewHolder, int position) {
        taskViewHolder.bind(tasks.get(position));
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    // Listener for deleting tasks
    public interface DeleteTaskListener {

        void onDeleteTask(long id);
    }

    // ViewHolder for task items in the tasks list</p>
    // @author Gaëtan HERFRAY
    class TaskViewHolder extends RecyclerView.ViewHolder {
        // The circle icon showing the color of the project
        private final AppCompatImageView imgProject;

        // The TextView displaying the name of the task
        private final TextView lblTaskName;

        //The TextView displaying the name of the project
        private final TextView lblProjectName;

        // The delete icon
        private final AppCompatImageView imgDelete;

        // The listener for when a task needs to be deleted
        private final DeleteTaskListener deleteTaskListener;

        // Instantiates a new TaskViewHolder
        TaskViewHolder(@NonNull View itemView, @NonNull DeleteTaskListener deleteTaskListener) {
            super(itemView);

            this.deleteTaskListener = deleteTaskListener;

            imgProject = itemView.findViewById(R.id.img_project);
            lblTaskName = itemView.findViewById(R.id.lbl_task_name);
            lblProjectName = itemView.findViewById(R.id.lbl_project_name);
            imgDelete = itemView.findViewById(R.id.img_delete);

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Object tag = view.getTag();
                    if (tag instanceof TaskVO) {
                        long id = ((TaskVO) tag).getId();
                        TaskViewHolder.this.deleteTaskListener.onDeleteTask(id);
                    }
                }
            });
        }

        // Binds a task to the item view
        void bind(TaskVO task) {
            lblTaskName.setText(task.getName());
            imgDelete.setTag(task);

            final ProjectVO taskProject = task.getProjectVO();
            if (taskProject != null) {
                imgProject.setSupportImageTintList(ColorStateList.valueOf(taskProject.getColor()));
                lblProjectName.setText(taskProject.getName());
            } else {
                imgProject.setVisibility(View.INVISIBLE);
                lblProjectName.setText("");
            }
        }
    }

}
