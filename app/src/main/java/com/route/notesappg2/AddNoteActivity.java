package com.route.notesappg2;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.route.notesappg2.DataBase.Model.Note;
import com.route.notesappg2.DataBase.MyDataBase;
import com.route.notesappg2.base.BaseActivity;

import java.util.Calendar;

public class AddNoteActivity extends BaseActivity
        implements View.OnClickListener {

    protected EditText title;
    protected EditText content;
    protected TextView time;
    protected Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_add_note);
        initView();

    }

    String noteTime="";
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.time) {
            Calendar calendar = Calendar.getInstance();
            TimePickerDialog dialog =new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view,
                                              int hourOfDay,
                                              int minute) {
                            noteTime = hourOfDay+" : "+minute;
                            time.setText(noteTime);
                        }
                    },calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    false);
            dialog.show();
        } else if (view.getId() == R.id.add) {
            String titleS= title.getText().toString();
            String contentS =content.getText().toString();
            Note note = new Note(titleS,contentS,noteTime);
            MyDataBase.getInstance(this)
                    .notesDAO()
                    .addNote(note);
            showMessage(R.string.note_added_successfully,
                    R.string.ok,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            finish();
                        }
                    },false);
        }

    }

    private void initView() {
        title =  findViewById(R.id.title);
        content = (EditText) findViewById(R.id.content);
        time = (TextView) findViewById(R.id.time);
        time.setOnClickListener(AddNoteActivity.this);
        add = (Button) findViewById(R.id.add);
        add.setOnClickListener(AddNoteActivity.this);
    }
}
