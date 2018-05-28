package intent.mohit.com.login_signup;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Second extends AppCompatActivity {
    Button b1,b2;
    EditText e3,e4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        b1=(Button)findViewById(R.id.buttonlogin);
        b2=(Button)findViewById(R.id.buttonsignup);
        e3=(EditText)findViewById(R.id.editText);
        e4=(EditText)findViewById(R.id.editText2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 =e3.getText().toString();
                String s2 =e4.getText().toString();
                if(s1.equals("")||s2.equals(""))
                {
                    Toast.makeText(Second.this, "fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    SQLiteDatabase data=openOrCreateDatabase("netcamp",MODE_PRIVATE,null);
                    data.execSQL("create table if not exists student(name varchar,email varchar, password varchar)");
                    String s3="select * from student where email='"+s1+"' and password='"+s2+"'";
                    Cursor d = data.rawQuery(s3,null);
                    if(d.getCount()>0)
                    {
                        Toast.makeText(Second.this, "login succesful", Toast.LENGTH_SHORT).show();
                        Intent j=getIntent();
                        String s6=j.getStringExtra("data");
                        Intent o = new Intent(Second.this,Third.class);

                        startActivity(o);
                        finish();
                    } else
                    {
                        Toast.makeText(Second.this, "Username and Password not matched", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Second.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
