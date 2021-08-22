package com.cleanup.todoc.ui;

import androidx.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import com.cleanup.todoc.R;
import com.cleanup.todoc.read.businesslogic.usecases.TaskVO;
import com.cleanup.todoc.read.businesslogic.usecases.enums.SearchMethod;
import com.cleanup.todoc.read.businesslogic.usecases.enums.SortMethod;
import com.cleanup.todoc.ui.injections.Injection;
import com.cleanup.todoc.ui.injections.TaskViewModelFactory;
import com.cleanup.todoc.read.businesslogic.usecases.ProjectVO;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Gaëtan HERFRAY
 */
public class MainActivity extends AppCompatActivity implements TasksAdapter.DeleteTaskListener {

    // The view model that provides data
    private TaskViewModel taskViewModel;

    // The adapter which handles the list of tasks
    private TasksAdapter adapter;

    // The methods to be used to display tasks
    @NonNull
    private SortMethod sortMethod = SortMethod.NONE;

    private SearchMethod searchMethod = SearchMethod.NONE;

    //Dialog to create a new task
    @Nullable
    public AlertDialog dialog = null;

    // EditText that allows user to set the name of a task
    @Nullable
    private EditText dialogEditText = null;

    // Spinner that allows the user to associate a project to a task
    @Nullable
    private Spinner dialogSpinner = null;

    //The RecyclerView which displays the list of tasks
    @NonNull
    private RecyclerView listTasks;

    //The TextView displaying the empty state
    @NonNull
    private TextView lblNoTasks;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TaskViewModelFactory taskViewModelFactory = Injection.provideTaskViewModelFactory(this);
        this.taskViewModel = ViewModelProviders.of(this, taskViewModelFactory).get(TaskViewModel.class);

        setContentView(R.layout.activity_main);

        listTasks = findViewById(R.id.list_tasks);
        lblNoTasks = findViewById(R.id.lbl_no_task);

        this.adapter = new TasksAdapter(new ArrayList<>(), this);
        listTasks.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        listTasks.setAdapter(this.adapter);

        findViewById(R.id.fab_add_task).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddTaskDialog();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.updateTasks();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.filter_alphabetical) {
            this.sortMethod = SortMethod.ALPHABETICAL;
            this.updateTasks();
        } else if (id == R.id.filter_alphabetical_inverted) {
            this.sortMethod = SortMethod.ALPHABETICAL_INVERTED;
            this.updateTasks();
        } else if (id == R.id.filter_oldest_first) {
            this.sortMethod = SortMethod.OLD_FIRST;
            this.updateTasks();
        } else if (id == R.id.filter_recent_first) {
            this.sortMethod = SortMethod.RECENT_FIRST;
            this.updateTasks();
        } else if(id == R.id.filter_by_project) {
            this.searchMethod = SearchMethod.BY_PROJECT;
            this.showProjectsDialog();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDeleteTask(long id) {
        this.taskViewModel.deleteTask(id);
        this.updateTasks();
    }

    // Called when the user clicks on the positive button of the Create Task Dialog
    private void onPositiveButtonClick(DialogInterface dialogInterface) {
        // If dialog is open
        if (dialogEditText != null && dialogSpinner != null) {
            // Get the name of the task
            String taskName = dialogEditText.getText().toString();

            // Get the selected project to be associated to the task
            ProjectVO taskProject = null;
            if (dialogSpinner.getSelectedItem() instanceof ProjectVO) {
                taskProject = (ProjectVO) dialogSpinner.getSelectedItem();
            }

            // If a name has not been set
            if (taskName.trim().isEmpty()) {
                dialogEditText.setError(getString(R.string.empty_task_name));
            }
            // If both project and name of the task have been set
            else if (taskProject != null) {

                TaskVO task = new TaskVO(
                        0l,
                        taskProject,
                        taskName,
                        new Date().getTime()
                );

                this.addTask(task);

                dialogInterface.dismiss();
            }
            // If name has been set, but project has not been set (this should never occur)
            else{
                dialogInterface.dismiss();
            }
        }
        // If dialog is already closed
        else {
            dialogInterface.dismiss();
        }
    }

    // Shows the Dialog for adding a Task
    private void showAddTaskDialog() {
        final AlertDialog dialog = getAddTaskDialog();
        dialog.show();
        dialogEditText = dialog.findViewById(R.id.txt_task_name);
        dialogSpinner = dialog.findViewById(R.id.project_spinner);

        this.taskViewModel.getProjects().observe(this, projects -> {
            populateDialogSpinner(projects);
        });
    }

    @NonNull
    private AlertDialog getAddTaskDialog() {
        final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this, R.style.Dialog);

        alertBuilder.setTitle(R.string.add_task);
        alertBuilder.setView(R.layout.dialog_add_task);
        alertBuilder.setPositiveButton(R.string.add, null);
        alertBuilder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                dialogEditText = null;
                dialogSpinner = null;
                dialog = null;
            }
        });

        this.dialog = alertBuilder.create();

        // This instead of listener to positive button in order to avoid automatic dismiss
        this.dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button button = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onPositiveButtonClick(dialog);
                    }
                });
            }
        });

        return dialog;
    }

    //Set the projects spinner with data
    private void populateDialogSpinner(@NonNull ProjectVO[] allProjects) {
        final ArrayAdapter<ProjectVO> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, allProjects);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (dialogSpinner != null) {
            dialogSpinner.setAdapter(adapter);
        }
    }

    private void addTask(@NonNull TaskVO task) {
        this.taskViewModel.addTask(task);
        this.updateTasks();
    }

    // --- SORT functionnalities ----
    // Updates the list of tasks in the UI according to sort method
    private void updateTasks() {
        this.taskViewModel.listTasks().observe(this, tasks -> {

            if (tasks.size() == 0) {
                lblNoTasks.setVisibility(View.VISIBLE);
                listTasks.setVisibility(View.GONE);
            } else {
                lblNoTasks.setVisibility(View.GONE);
                listTasks.setVisibility(View.VISIBLE);
                this.taskViewModel.switchSortMethod(tasks, this.sortMethod);
                this.adapter.updateTasks(tasks);
            }
        });
    }

    // --- SEARCH functionality ---
    private void showProjectsDialog() {
        final AlertDialog dialog = this.getSearchByProjectDialog();
        dialog.show();

        GridView gridView = dialog.findViewById(R.id.projects_grid);
        this.taskViewModel.getProjects().observe(this, projects -> {
            ArrayAdapter<ProjectVO> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, projects);
            gridView.setAdapter(adapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    searchByProject(adapter.getItem(i).getId());
                }
            });
        });
    }

    private AlertDialog getSearchByProjectDialog() {
        final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this, R.style.Dialog);
        alertBuilder.setView(R.layout.projects);
        AlertDialog dialog = alertBuilder.create();
        return dialog;
    }

    private void searchByProject(long projectId) {
        this.taskViewModel.searchByProject(projectId).observe(this, tasks -> {
            this.adapter = new TasksAdapter(tasks, this);
            this.listTasks.setAdapter(this.adapter);
        });
    }

}
