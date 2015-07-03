package mhufft.com.messengerproject;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.*;

public class MainActivity extends ActionBarActivity {

    //Handler for text scroll view
    private Handler uiHandle= new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Edit text and button inflates
        final EditText editText= (EditText) findViewById(R.id.editText1);
        final Button   sendButton = (Button) findViewById(R.id.button1);
        sendButton.setBackgroundColor(Color.rgb(255, 179, 102));

        //Edit text for message
        editText.setOnKeyListener(new OnKeyListener(){

            @Override
            public boolean onKey(View arg0, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    sendMessage(arg0);
                }
                return false;
            }

        });

        //Attach Button
        ImageButton attachButton = (ImageButton) findViewById(R.id.buttonAttach);
        attachButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Out of Project Scope", Toast.LENGTH_SHORT).show();
            }
        });

        //Action Bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Justin Lardinios");
        ColorDrawable color = new ColorDrawable();
        color.setColor(0xffff9900);
        actionBar.setBackgroundDrawable(color);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Menu Inflater adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Toasts for ActionBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.actionbar_call){
            Toast.makeText(this,"Out of Project Scope", Toast.LENGTH_SHORT).show();

        }
        if(id==R.id.actionbar_more){
            Toast.makeText(this,"Out of Project Scope", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    //Send message helper function
    public void sendMessage(View view) {
        EditText et= (EditText) findViewById(R.id.editText1);
        final String msg= et.getText().toString();
        et.setText("");
        updateTextView(msg);

    }

    //Helper for Text View
    public void updateTextView(String message) {
        final String msg= message;
        uiHandle.post(new Runnable() {  //uiHandle defined at top
            public void run() {
                TextView textView = (TextView)findViewById(R.id.textView);
                textView.setMovementMethod(new ScrollingMovementMethod());
                textView.append(msg);
                textView.append("\n");
                textView.append("\n");
                ScrollView sc= (ScrollView)findViewById(R.id.scrollView);
                sc.fullScroll(View.FOCUS_DOWN);
            }
        });
    }
}
