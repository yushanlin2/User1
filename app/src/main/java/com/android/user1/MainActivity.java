package com.android.user1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Firebase mref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    TextView textView;
    Button b1;
    protected void onStart()
    {
        super.onStart();
        textView = (TextView)findViewById(R.id.textView);
        b1 = (Button)findViewById(R.id.button);
        // User 1 Reading from /first
        mref = new Firebase("https://junru-110.firebaseio.com/first");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String data = dataSnapshot.getValue(String.class);
                textView.setText(data);
                Toast.makeText(getBaseContext(), data, Toast.LENGTH_LONG).show();;
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // User 1 Writing to /second
                mref = new Firebase("https://junru-110.firebaseio.com/second");
                EditText editText = (EditText)findViewById(R.id.editText);
                String edit = editText.getText().toString();
                mref.setValue(edit);
            }
        });
    }
}
