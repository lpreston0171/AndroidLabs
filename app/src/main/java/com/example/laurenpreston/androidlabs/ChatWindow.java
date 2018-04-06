package com.example.laurenpreston.androidlabs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatWindow extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "ChatWindow";
    private Button sendButton;
    private ListView listView;
    private EditText textInput;
    private ChatAdapter messageAdapter;
    private ArrayList<String> list = new ArrayList<>();

    private ChatDatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        this.sendButton = findViewById(R.id.sendButton);
        this.listView = findViewById(R.id.the_list);
        this.textInput = findViewById(R.id.editText1);

        final ChatAdapter messageAdapter = new ChatAdapter(this);
        listView.setAdapter(messageAdapter);

        // database hook-up
        this.dbHelper = new ChatDatabaseHelper(this );
        db = dbHelper.getWritableDatabase();

        //cursor hook-up
        Cursor cursor = db.query(ChatDatabaseHelper.TABLE_NAME, new String[]{ChatDatabaseHelper.KEY_MESSAGE},null,null,null,null,null);
        int columnIndex = cursor.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE);
        Log.i("ChatWindow", "Cursor’s  column count =" + cursor.getColumnCount());
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            String message = cursor.getString(columnIndex);
            list.add(message);
            Log.i("ChatWindow", "message retrieved from Cursor" + message);
            cursor.moveToNext();
        }

        textInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCodePressed, KeyEvent event) {
                if (keyCodePressed == event.KEYCODE_ENTER) {
                    sendButton.performClick();
                }
                return false;
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                String data = textInput.getText().toString();
                list.add(data);
                ContentValues contentValues = new ContentValues();
                contentValues.put(dbHelper.KEY_MESSAGE, textInput.getText().toString());
                db.insert(ChatDatabaseHelper.TABLE_NAME, null, contentValues);
                messageAdapter.notifyDataSetChanged(); //this restarts the process of getCount() & getView()
                textInput.setText("");
            }
        });

    }

    private class ChatAdapter extends ArrayAdapter<String> {

        public ChatAdapter(Context ctx) {
            super(ctx, 0);
        }

        public int getCount()  { return list.size();}

        public String getItem(int position) { return list.get(position);}

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result = null ;
            if(position%2 == 0)
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            else
                result = inflater.inflate(R.layout.chat_row_outgoing, null);
            TextView message = result.findViewById(R.id.message_text);
            message.setText(   getItem(position)  ); // get the string at position
            return result;

        }

        public long getId(int position) {
            return position;
        }
    }

    protected void onResume(){
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");
    }

    protected void onStart(){
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }

    protected void onPause(){
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    protected void onStop(){
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }
}
