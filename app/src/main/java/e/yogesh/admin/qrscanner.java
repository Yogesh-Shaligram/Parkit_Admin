package e.yogesh.admin;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.StringTokenizer;

public class qrscanner extends AppCompatActivity {
    Button scan_btn;
    TextView tv;

    static qrscanner INSTANCE;

    String email1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);
        INSTANCE=this;
        scan_btn=(Button) findViewById(R.id.scan_btn);
        tv=(TextView) findViewById(R.id.tv);
        final Activity activity= this;
        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator= new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Place the phone above the QR Code");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });
    }

    public  static qrscanner getActivityINSTANCE(){return INSTANCE;}
    public String getdata(){
        return this.email1;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result!= null){
            if(result.getContents()==null){
                Toast.makeText(this, "You cancelled the scanning",Toast.LENGTH_SHORT).show();
            }
            else{
               // Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                String data1 =result.getContents();
                tv.setText(data1);
                String email= tv.getText().toString();
               // Toast.makeText(this, email, Toast.LENGTH_SHORT).show();
                StringTokenizer tokenizer=new StringTokenizer(email," ");
                String first=tokenizer.nextToken();
                Toast.makeText(this, first, Toast.LENGTH_SHORT).show();
                email1=first;

                Intent intent=new Intent(this, starttime.class);
                startActivity(intent);


            }

        }
        else{
            super.onActivityResult(requestCode, resultCode, data);

        }
        super.onActivityResult(requestCode, resultCode, data);



    }
}
