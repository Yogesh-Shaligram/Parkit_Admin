package e.yogesh.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class starttime extends AppCompatActivity {
    TextView tv4;
    Button btnhome;
    String finalemail;
    TextView tvnamestart;
    TextView tvmailstart;
    TextView tvnumberstart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starttime);
        FirebaseDatabase data= FirebaseDatabase.getInstance();
        DatabaseReference myRef1= data.getReference();

        finalemail=qrscanner.getActivityINSTANCE().getdata();
        //Toast.makeText(this, finalemail, Toast.LENGTH_SHORT).show();
        tv4=(TextView) findViewById(R.id.tv4);
        btnhome=(Button) findViewById(R.id.btnhome);
        tvnamestart=(TextView) findViewById(R.id.tvnamestart);
        tvmailstart=(TextView)  findViewById(R.id.tvmailstart);
        tvnumberstart=(TextView) findViewById(R.id.tvnumberstart);





        DateFormat df=new SimpleDateFormat("EEE d,yyyy hh:mm:ss");
        String date= df.format(Calendar.getInstance().getTime());
        tv4.setText(date);
        myRef1.child("Customers").child(finalemail).child("Start Time").setValue(date);

        SimpleDateFormat sdf = new SimpleDateFormat("dHHmm");
        int st = Integer.parseInt(sdf.format(new Date()));
        Toast.makeText(this, ""+st, Toast.LENGTH_SHORT).show();
        myRef1.child("Customers").child(finalemail).child("Start Time_int").setValue(st);

        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(starttime.this, SecondActivity.class);
                startActivity(intent);
            }
        });


        final DatabaseReference orders_Reference = myRef1.child("Customers").child(finalemail);

        orders_Reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    if ((data.getKey().equals("Email:"))) {

                        {
                            String aahaemail = data.getValue().toString();
                            Log.d("Specific Node Value", aahaemail);
                            //Toast.makeText(EndActivity.this, aaha,Toast.LENGTH_SHORT).show();
                            tvmailstart.setText(aahaemail);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });



        orders_Reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    if ((data.getKey().equals("Car_no:"))) {

                        {
                            String aahanumber = data.getValue().toString();
                            Log.d("Specific Node Value", aahanumber);
                            //Toast.makeText(EndActivity.this, aaha,Toast.LENGTH_SHORT).show();
                            tvnumberstart.setText(aahanumber);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        orders_Reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    if ((data.getKey().equals("Name:"))) {

                        {
                            String aahaname = data.getValue().toString();
                            Log.d("Specific Node Value", aahaname);
                            //Toast.makeText(EndActivity.this, aaha,Toast.LENGTH_SHORT).show();
                            tvnamestart.setText(aahaname);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


    }
}
