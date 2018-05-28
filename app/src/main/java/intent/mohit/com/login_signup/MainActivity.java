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

public class MainActivity extends AppCompatActivity {
    Button b1,b2;
    EditText e1,e2,e3,e4;
    String s1,s2,s3,s4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.btlogin);
        b2=(Button)findViewById(R.id.btsignup);
        e1=(EditText)findViewById(R.id.etuser);
        e2=(EditText)findViewById(R.id.etemail);
        e3=(EditText)findViewById(R.id.etpass);
        e4=(EditText)findViewById(R.id.etconfpass);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1=e1.getText().toString();
                s2=e2.getText().toString();
                s3=e3.getText().toString();
                s4=e4.getText().toString();

                if(s3.equals(s4)==false)
                    Toast.makeText(MainActivity.this, "PASSWORD don't match", Toast.LENGTH_SHORT).show();

                 else if(s1.equals("")||s2.equals("") || s3.equals(""))
                {
                    Toast.makeText(MainActivity.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
                }


                    else
                        {

                            int atpos = s2.indexOf("@");
                            int dotpos = s2.lastIndexOf(".");
                            if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= s2.length()) {
                                Toast.makeText(MainActivity.this, "Email Format Incorrect", Toast.LENGTH_SHORT).show();
                            }
                            //else {
                                Toast.makeText(MainActivity.this, "Password Match!!", Toast.LENGTH_SHORT).show();
                                SQLiteDatabase data = openOrCreateDatabase("netcamp", MODE_PRIVATE, null);
                                data.execSQL("create table if not exists student(name varchar , email varchar , password varchar)");
                                String s4 = "select * from student where (name='" + s1 + "' and password='" + s3 + "')";
                                Cursor c = data.rawQuery(s4, null);
                                if (c.getCount() > 0) {
                                    Toast.makeText(MainActivity.this, "User already exists!!", Toast.LENGTH_SHORT).show();

                                } else {
                                    data.execSQL("insert into student values('" + s1 + "','" + s2 + "','" + s3 + "')");
                                    Toast.makeText(MainActivity.this, "database updated", Toast.LENGTH_SHORT).show();
                                }
                            }
                            //}
                }

        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Second.class);
                i.putExtra("data",e1.getText().toString());
                startActivity(i);
                finish();
            }
        });
    }
}


