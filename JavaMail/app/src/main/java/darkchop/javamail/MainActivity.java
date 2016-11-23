package darkchop.javamail;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.buttonJavaMailGmail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        new JavaMailGmail();
                    }
                }).start();

            }
        });

        findViewById(R.id.buttonJavaMailHotmail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        new JavaMailHotmail();
                    }
                }).start();

            }
        });

        findViewById(R.id.buttonJavaMailGamilConAdjunto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        new JavaMailGamilConAdjunto(MainActivity.this);
                    }
                }).start();

            }
        });
    }




}
