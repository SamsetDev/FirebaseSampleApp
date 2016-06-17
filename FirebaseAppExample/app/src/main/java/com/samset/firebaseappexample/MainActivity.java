package com.samset.firebaseappexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.samset.firebaseappexample.adapter.UserAdapter;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private static final String URL="https://saveretrive.firebaseio.com/UserInfo";
    //https://crackling-inferno-7719.firebaseio.com/UserInfo
   //https://saveretrive.firebaseio.com
    private ListView listView;
    private Button btnsave,btnget;
    private EditText fname,lname,email,contect;
    Firebase mRef;
    String FNAME,LNAME,MAIL,CONTACT;
    private ArrayList<User> listdata=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fname=(EditText)findViewById(R.id.etfname);
        lname=(EditText)findViewById(R.id.etlname);
        email=(EditText)findViewById(R.id.etemail);
        contect=(EditText)findViewById(R.id.etcontact);
        listView=(ListView)findViewById(R.id.list);
        btnsave=(Button)findViewById(R.id.btnSave);
        btnget=(Button)findViewById(R.id.btnget);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mRef=new Firebase(URL);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
        btnget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData();
            }
        });



    }
    private void showData()
    {
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                listdata.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    //Getting the data from snapshot
                    User user=new User();
                    User person = postSnapshot.getValue(User.class);

                    user.setFirstname(postSnapshot.getValue(User.class).getFirstname());
                    user.setLastname(postSnapshot.getValue(User.class).getLastname());
                    user.setEmail(postSnapshot.getValue(User.class).getEmail());
                    user.setContact(postSnapshot.getValue(User.class).getContact());



                    listdata.add(user);
                    //Adding it to a string
                    String string = "Name: "+person.getFirstname()+"\nAddress: "+person.getContact()+"\n\n";

                    Log.e("Main","data dd "+string);

                    if (listdata.size()>0)
                    {
                        UserAdapter userAdapter=new UserAdapter(MainActivity.this,listdata);
                        listView.setAdapter(userAdapter);

                    }else
                    {
                        Toast.makeText(MainActivity.this,"No Data",Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });



        
    }

    private void saveData()
    {
        FNAME=fname.getText().toString();
        LNAME=lname.getText().toString();
        MAIL=email.getText().toString();
        CONTACT=contect.getText().toString();


        if (FNAME.isEmpty())
        {
            Toast.makeText(this,"Fill FirstName",Toast.LENGTH_SHORT).show();
        }else if (FNAME.isEmpty() && LNAME.isEmpty())
        {
            Toast.makeText(this,"Fill LastName",Toast.LENGTH_SHORT).show();
        }else if (!FNAME.isEmpty() && !LNAME.isEmpty() && email.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Fill Email",Toast.LENGTH_SHORT).show();
        } else if (!FNAME.isEmpty() && !LNAME.isEmpty() && !MAIL.isEmpty() && CONTACT.isEmpty()) {
            Toast.makeText(this, "Fill Contact",Toast.LENGTH_SHORT).show();
        }else if (!FNAME.isEmpty() && !LNAME.isEmpty() && !MAIL.isEmpty() && !CONTACT.isEmpty())
        {

            User user=new User(FNAME,LNAME,MAIL,CONTACT);
            mRef.push().setValue(user);
            fname.setText("");
            lname.setText("");
            email.setText("");
            contect.setText("");

        }
    }


}
