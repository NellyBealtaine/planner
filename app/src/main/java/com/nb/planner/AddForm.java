package com.nb.planner;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class AddForm extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    ArrayList<String> users = new ArrayList<>();
//    ArrayList<String> _id = new ArrayList<>();
    String _currentUID=null;
    ListView _list;
    Tasks _userDB;
    SQLiteDatabase _wdb, _rdb;
    EditText _taskname, _description, _expdate;
    SeekBar _importance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_form);
        _taskname = findViewById(R.id._taskname);
        _description = findViewById(R.id._description);
        _expdate = findViewById(R.id._expdate);
//        _userDB = new Tasks(this);
        _wdb = _userDB.getWritableDatabase();
        _rdb = _userDB.getReadableDatabase();
        _list = findViewById(R.id._list);
        _importance = findViewById(R.id._importance);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, users);
        _list.setAdapter(adapter);
    }

// Убирает клавиатуру с экрана
    @Override
    public boolean dispatchTouchEvent(@NonNull MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                v.clearFocus();
                InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

            }
        }

        return super.dispatchTouchEvent(event);
    }

    private static final class TASK{
        static final String TABLE_NAME="Tasks";
        static final String TASK_ID="TaskID";
        static final String COLUMN_NAME="TaskName";
        static final String COLUMN_DESC="Description";
//        static final String COLUMN_IMP="Importance";
//        static final String COLUMN_ADDDATE="AddDate";
        static final String COLUMN_EXPDATE="ExpDate";
//        static final String COL_DONE="Done";
    }

    public void Add(View v){
        String TaskName = _taskname.getText().toString();
        String Description = _description.getText().toString();
        String ExpDate = _expdate.getText().toString();
        if(TaskName.isEmpty()||Description.isEmpty()||ExpDate.isEmpty()) return;
        ContentValues values = new ContentValues();
        values.put(TASK.COLUMN_NAME, TaskName);
        values.put(TASK.COLUMN_DESC, Description);
        values.put(TASK.COLUMN_EXPDATE, ExpDate);
        long newRowId = _wdb.insert(TASK.TABLE_NAME, null, values);
//        values.put(FIO.USER_ID, newRowId);
        users.add(TaskName+" "+Description+" "+ExpDate);
//        _id.add(Long.toString(newRowId));
//        adapter.notifyDataSetChanged();
        Log.i("Planner","newRowId = "+newRowId);
        _taskname.setText("");
        _description.setText("");
        _expdate.setText("");
    }

    public void Home(View v){ finish(); }

    public void ViewTask(View v) {
        Intent intentview = new Intent(this, ViewList.class);
        startActivity(intentview);
    }

    class Tasks extends SQLiteOpenHelper{
        SQLiteDatabase _db;
        private static final String DB_NAME="Tasks";
        private static final int DB_VERSION=1;

        public Tasks(Context aContext){
            super(aContext, DB_NAME, null, DB_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db){
            _db = db;
            Log.i("Planner", "Creating database "+DB_NAME);
            _db.execSQL("Create table "+ TASK.TABLE_NAME + " ("
                    + TASK.TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + TASK.COLUMN_NAME + " TEXT, "
                    + TASK.COLUMN_DESC + " TEXT, "
                    + TASK.COLUMN_EXPDATE + " TEXT) ");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}
        @Override
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){}
        private void _dropTable(String aTableName){
            _db.execSQL("DROP TABLE IF EXISTS " +aTableName);
        }
    }

    /*
    public void Edit(View v){
        if (_currentUID==null) return;
        String Name = et1.getText().toString();
        String SName = et2.getText().toString();
        String FName = et3.getText().toString();
        if(Name.isEmpty()||SName.isEmpty()||FName.isEmpty()) return;
        ContentValues values = new ContentValues();
        values.put(FIO.COLUMN_NAME, Name);
        values.put(FIO.COLUMN_SNAME, SName);
        values.put(FIO.COLUMN_FNAME, FName);
        String selection = FIO.USER_ID+" =?";
        String[] selectionArgs={_currentUID};
        int count = _wdb.update(FIO.TABLE_NAME, values, selection, selectionArgs);
        Log.i("Kvadrat", "Измененных строк "+count);
        Show(null);
    }

    public void Delete(View v){
        if (_currentUID == null) return;
        String selection = FIO.USER_ID+" =?";
        String[] selectionArgs={_currentUID};
        int count = _wdb.delete(FIO.TABLE_NAME, selection, selectionArgs);
        Log.i("Kvadrat", "Удаленных строк "+count);
        Show(null);
    }


        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            _db = db;
            Log.i("Kvadrat", "onUpgrade().oldVersion "+oldVersion+"; newVersion"+newVersion);
            if((oldVersion==1)&&(newVersion==2)){
                String query = "ALTER TABLE "+TASK.TABLE_NAME+" ADD "+TASK.COL_DOCNUM+" INTEGER ";
                _db.execSQL(query);
            } else if((oldVersion==2)&&(newVersion==3)){
                String query = "ALTER TABLE Names RENAME TO "+TASK.TABLE_NAME;
                _db.execSQL(query);
            }else{
                _dropTable(FIO.TABLE_NAME);
                onCreate(db);
            }
        }
        @Override
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
            _db=db;
            Log.i("Kvadrat", "onDowngrade().oldVersion "+oldVersion+"; newVersion "+newVersion);
            if((oldVersion==2)&&(newVersion==1)){
            }else if((oldVersion==3)&&(newVersion==2)){
            }else{
                _dropTable(FIO.TABLE_NAME);
                onCreate(db);
            }
        }
        private void _dropTable(String aTableName){
            _db.execSQL("DROP TABLE IF EXISTS " +aTableName);
        }

     */
}
