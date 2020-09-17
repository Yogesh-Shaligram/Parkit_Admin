package e.yogesh.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText Name;
    EditText Password;
    Button Login;
    int counter=5;
    TextView Info;
    Button Reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name=(EditText) findViewById(R.id.etName);
        Password=(EditText) findViewById(R.id.etPassword);
        Login=(Button) findViewById(R.id.btnLogin);
        Info=(TextView) findViewById(R.id.tvInfo);
        Reset=(Button) findViewById(R.id.btnReset);
        Reset.setEnabled(false);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Name.getText().toString(), Password.getText().toString());
            }
        });
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this, ForgetPassword.class);
                startActivity(intent);
            }
        });

    }

    private void validate (String userName, String userPassword){
        if((userName.equals("admin")) && (userPassword.equals("12345"))){
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }
        else{
            counter --;
            Info.setText("No. of attempts Remaining: " + String.valueOf(counter));
            if(counter==0){
                Login.setEnabled(false);
                Reset.setEnabled(true);
            }

        }
    }
}
