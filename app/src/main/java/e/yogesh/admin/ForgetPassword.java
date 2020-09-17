package e.yogesh.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ForgetPassword extends AppCompatActivity {
    Button showPassword;
    TextView answer1;
    TextView answer2;
    TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        showPassword=(Button) findViewById(R.id.btnshowPassword);
        answer1=(TextView) findViewById(R.id.etansewr1);
        answer2=(TextView) findViewById(R.id.etanswer2);
        info=(TextView) findViewById(R.id.tvinfo);
        showPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(answer1.getText().toString(), answer2.getText().toString());

            }
        });
    }

    private void validate(String answer1, String answer2){
        if((answer1.equals("pune") && (answer2.equals("pvg")))){
            Intent intent= new Intent(ForgetPassword.this, afterforgetPassword.class);
            startActivity(intent);

        }
        else{
            info.setText("Wrong Answer!");
        }
    }
}
