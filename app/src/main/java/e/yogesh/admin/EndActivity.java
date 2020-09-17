package e.yogesh.admin;

import android.content.Intent;
import android.support.annotation.NonNull;
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

public class EndActivity extends AppCompatActivity {

    TextView tv2;
    TextView tv6;
    TextView tvname;
    TextView tvnumber;
    TextView tvmail;
    TextView tvrent;
    TextView tvstart_time;
    String finaemail;
    Button btnhome;
    TextView tvdemo;
    int start1;
    int start, end, rent, time;
    int total;
   // String start1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        FirebaseDatabase data = FirebaseDatabase.getInstance();
        final DatabaseReference myRef1 = data.getReference();

        finaemail = afterEnduser.getActivityINSTANCE().getdata();

        tv6 = (TextView) findViewById(R.id.tv6);
        tvname = (TextView) findViewById(R.id.tvname);
        tvnumber = (TextView) findViewById(R.id.tvnumber);
        tvmail = (TextView) findViewById(R.id.tvmail);
        tvrent = (TextView) findViewById(R.id.tvrent);
        tvstart_time = (TextView) findViewById(R.id.tvstart_time);
        //tvdemo=(TextView) findViewById(R.id.tvdemo);
        //Toast.makeText(this, finaemail, Toast.LENGTH_SHORT).show();

        tv2 = (TextView) findViewById(R.id.tv);
        btnhome = (Button) findViewById(R.id.btnhome);
        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                total=total+1;
                Intent intent = new Intent(EndActivity.this, SecondActivity.class);
                startActivity(intent);

            }
        });

        DateFormat df = new SimpleDateFormat("EEE d,yyyy hh:mm:ss");
        String date1 = df.format(Calendar.getInstance().getTime());
        myRef1.child("Customers").child(finaemail).child("End Time").setValue(date1);
        tv6.setText(date1);


        SimpleDateFormat sdf = new SimpleDateFormat("dHHmm");
        int st = Integer.parseInt(sdf.format(new Date()));
        //Toast.makeText(this, ""+st, Toast.LENGTH_SHORT).show();
        myRef1.child("Customers").child(finaemail).child("End Time_int").setValue(st);
        end = st;


        //Retriving:

        final DatabaseReference orders_Reference = myRef1.child("Customers").child(finaemail);

        orders_Reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    if ((data.getKey().equals("Start Time"))) {

                        {
                            String aahastart = data.getValue().toString();
                            Log.d("Specific Node Value", aahastart);
                            //Toast.makeText(EndActivity.this, aaha,Toast.LENGTH_SHORT).show();
                            tvstart_time.setText(aahastart);

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
                    if ((data.getKey().equals("End Time"))) {

                        {
                            String aahaend = data.getValue().toString();
                            Log.d("Specific Node Value", aahaend);
                            //Toast.makeText(EndActivity.this, aaha,Toast.LENGTH_SHORT).show();
                            // tv6.setText(aahaend);
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
                    if ((data.getKey().equals("Email:"))) {

                        {
                            String aahaemail = data.getValue().toString();
                            Log.d("Specific Node Value", aahaemail);
                            //Toast.makeText(EndActivity.this, aaha,Toast.LENGTH_SHORT).show();
                            tvmail.setText(aahaemail);
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
                            tvnumber.setText(aahanumber);
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
                            tvname.setText(aahaname);
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
                    if ((data.getKey().equals("Start Time_int"))) {

                        {
                            String aahaint = data.getValue().toString();
                            Log.d("Specific Node Value", aahaint);
                            //Toast.makeText(EndActivity.this, aaha,Toast.LENGTH_SHORT).show();
                            //start1 = aahaint;
                            start1=Integer.parseInt(aahaint);

                            time = end-start1;
                            SimpleDateFormat sdf1 = new SimpleDateFormat("EEEE");
                            Date d = new Date();
                            String dayofWeek = sdf1.format(d);
                           // Toast.makeText(EndActivity.this, ""+dayofWeek, Toast.LENGTH_SHORT).show();
                            switch (dayofWeek) {
                                case "Monday":
                                    if (time <= 100) {
                                        rent = 20;
                                    } else if (time >= 101 && time <=300) {
                                        rent = 40;
                                    } else if (time >= 301 && time <= 500) {
                                        rent = 60;
                                    } else if (time >= 501 && time <= 700) {
                                        rent = 80;
                                    } else if (time >= 701 && time <= 900) {
                                        rent = 100;
                                    } else if (time >= 901 && time <= 1100) {
                                        rent = 120;
                                    } else if (time>=1101 && time<=1300){
                                        rent = 140;
                                    } else if(time >=1301 && time<=1500){
                                        rent=160;
                                    } else if(time>=1501 && time<=1700){
                                        rent=180;
                                    } else{
                                        rent=200;
                                    }

                                    tvrent.setText("Rs." + rent);
                                    break;
                                case "Tuesday":
                                    if (time <= 100) {
                                        rent = 20;
                                    } else if (time >= 101 && time <=300) {
                                        rent = 40;
                                    } else if (time >= 301 && time <= 500) {
                                        rent = 60;
                                    } else if (time >= 501 && time <= 700) {
                                        rent = 80;
                                    } else if (time >= 701 && time <= 900) {
                                        rent = 100;
                                    } else if (time >= 901 && time <= 1100) {
                                        rent = 120;
                                    } else if (time>=1101 && time<=1300){
                                        rent = 140;
                                    } else if(time >=1301 && time<=1500){
                                        rent=160;
                                    } else if(time>=1501 && time<=1700){
                                        rent=180;
                                    } else{
                                        rent=200;
                                    }

                                    tvrent.setText("Rs." + rent);
                                    break;
                                case "Wednesday":
                                    if (time <= 100) {
                                        rent = 20;
                                    } else if (time >= 101 && time <=300) {
                                        rent = 40;
                                    } else if (time >= 301 && time <= 500) {
                                        rent = 60;
                                    } else if (time >= 501 && time <= 700) {
                                        rent = 80;
                                    } else if (time >= 701 && time <= 900) {
                                        rent = 100;
                                    } else if (time >= 901 && time <= 1100) {
                                        rent = 120;
                                    } else if (time>=1101 && time<=1300){
                                        rent = 140;
                                    } else if(time >=1301 && time<=1500){
                                        rent=160;
                                    } else if(time>=1501 && time<=1700){
                                        rent=180;
                                    } else{
                                        rent=200;
                                    }

                                    tvrent.setText("Rs." + rent);
                                    break;
                                case "Thursday":
                                    if (time <= 100) {
                                        rent = 20;
                                    } else if (time >= 101 && time <=300) {
                                        rent = 40;
                                    } else if (time >= 301 && time <= 500) {
                                        rent = 60;
                                    } else if (time >= 501 && time <= 700) {
                                        rent = 80;
                                    } else if (time >= 701 && time <= 900) {
                                        rent = 100;
                                    } else if (time >= 901 && time <= 1100) {
                                        rent = 120;
                                    } else if (time>=1101 && time<=1300){
                                        rent = 140;
                                    } else if(time >=1301 && time<=1500){
                                        rent=160;
                                    } else if(time>=1501 && time<=1700){
                                        rent=180;
                                    } else{
                                        rent=200;
                                    }

                                    tvrent.setText("Rs." + rent);
                                    break;
                                case "Friday":
                                    if (time <= 100) {
                                        rent = 20;
                                    } else if (time >= 101 && time <=300) {
                                        rent = 40;
                                    } else if (time >= 301 && time <= 500) {
                                        rent = 60;
                                    } else if (time >= 501 && time <= 700) {
                                        rent = 80;
                                    } else if (time >= 701 && time <= 900) {
                                        rent = 100;
                                    } else if (time >= 901 && time <= 1100) {
                                        rent = 120;
                                    } else if (time>=1101 && time<=1300){
                                        rent = 140;
                                    } else if(time >=1301 && time<=1500){
                                        rent=160;
                                    } else if(time>=1501 && time<=1700){
                                        rent=180;
                                    } else{
                                        rent=200;
                                    }

                                    tvrent.setText("Rs." + rent);
                                    break;
                                case "Saturday":
                                    if (time <= 100) {
                                        rent = 20;
                                    } else if (time >= 101 && time <=300) {
                                        rent = 40;
                                    } else if (time >= 301 && time <= 500) {
                                        rent = 60;
                                    } else if (time >= 501 && time <= 700) {
                                        rent = 80;
                                    } else if (time >= 701 && time <= 900) {
                                        rent = 100;
                                    } else if (time >= 901 && time <= 1100) {
                                        rent = 120;
                                    } else if (time>=1101 && time<=1300){
                                        rent = 140;
                                    } else if(time >=1301 && time<=1500){
                                        rent=160;
                                    } else if(time>=1501 && time<=1700){
                                        rent=180;
                                    } else{
                                        rent=200;
                                    }

                                    tvrent.setText("Rs." + rent);
                                    break;
                                case "Sunday":
                                    if (time <= 100) {
                                        rent = 20;
                                    } else if (time >= 101 && time <=300) {
                                        rent = 40;
                                    } else if (time >= 301 && time <= 500) {
                                        rent = 60;
                                    } else if (time >= 501 && time <= 700) {
                                        rent = 80;
                                    } else if (time >= 701 && time <= 900) {
                                        rent = 100;
                                    } else if (time >= 901 && time <= 1100) {
                                        rent = 120;
                                    } else if (time>=1101 && time<=1300){
                                        rent = 140;
                                    } else if(time >=1301 && time<=1500){
                                        rent=160;
                                    } else if(time>=1501 && time<=1700){
                                        rent=180;
                                    } else{
                                        rent=200;
                                    }

                                    tvrent.setText("Rs." + rent);
                                    break;



                            }

                            //tvdemo.setText(""+rent);
                            myRef1.child("Customers").child(finaemail).child("Rent").setValue(rent);


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



