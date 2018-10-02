package com.nb.planner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ViewList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);
    }


    public void Back(View v){ finish(); }
    /*
        ListView list;
    EditText txt;
    ArrayAdapter<String> adapter;
    ArrayList<String> arr = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView)findViewById(R.id.list);
        txt = (EditText)findViewById(R.id.txt);
        txt.setOnKeyListener(new ListEdit());

        loadResourses();
        ReInitList();
    }
    private void loadResourses() {
        String[] tmp = getResources().getStringArray(R.array.names);
        arr.clear();
        for (String s:tmp) {
            arr.add(s);
        }
    }

    private void ReInitList(){
        adapter = new ArrayAdapter<>(this, R.layout.item, arr);
        list.setAdapter(adapter);
    }
    class ListEdit implements View.OnKeyListener{
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event){
            if((event.getAction() == KeyEvent.ACTION_DOWN)&&(keyCode == KeyEvent.KEYCODE_ENTER)) {
                arr.add(txt.getText().toString());
                adapter.notifyDataSetChanged();
                txt.setText("");
                return true;
            }
            return false;
        }
    }


        public void Show(View v){
        et1.setText("");
        et2.setText("");
        et3.setText("");
        users.clear();
        _ids.clear();
        Cursor c = _rdb.query(FIO.TABLE_NAME,null,null,null,null,null,null);
        while(c.moveToNext()){
            int index = c.getColumnIndexOrThrow(FIO.USER_ID);
            String UID = c.getString(index);
            index = c.getColumnIndexOrThrow(FIO.COLUMN_NAME);
            String Name = c.getString(index);
            index = c.getColumnIndexOrThrow(FIO.COLUMN_SNAME);
            String SName = c.getString(index);
            index = c.getColumnIndexOrThrow(FIO.COLUMN_FNAME);
            String FName = c.getString(index);

            users.add(Name+" "+SName+" "+FName);
            _ids.add(UID);
            Log.i("Kvadrat","Person: "+Name+" "+SName+" "+FName);
        }
        c.close();
        adapter.notifyDataSetChanged();

     */
}

/*
//    ArrayList<String> _id = new ArrayList<>();

        _list.setOnItemClickListener(new CListSelector());

    private class CListSelector implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            _currentUID = _id.get(position);
            String selection = FIO.USER_ID + " =?";
            String[] selectionArgs = {_currentUID};
            Cursor c = _rdb.query(FIO.TABLE_NAME, null, selection, selectionArgs, null, null, null);
            if (c.moveToNext()) {
                int index = c.getColumnIndexOrThrow(FIO.COLUMN_NAME);
                String Name = c.getString(index);
                index = c.getColumnIndexOrThrow(FIO.COLUMN_SNAME);
                String SName = c.getString(index);
                index = c.getColumnIndexOrThrow(FIO.COLUMN_FNAME);
                String FName = c.getString(index);

                _taskname.setText(Name);
                _description.setText(SName);
                _date.setText(FName);
                Log.i("Kvadrat", "Выбрано: " + Name + " " + SName + " " + FName);
            } else{
                _currentUID = null;
                _taskname.setText("");
                _description.setText("");
                _date.setText("");
                Log.i("Kvadrat", "Нет записи с id " + position);
            }
            c.close();
        }
    }

 */