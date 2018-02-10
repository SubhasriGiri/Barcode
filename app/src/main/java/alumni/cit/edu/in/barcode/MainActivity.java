package alumni.cit.edu.in.barcode;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity {
    private ZXingScannerView scannerView;
    AnimationDrawable animationDrawable;
    RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout = (RelativeLayout)findViewById(R.id.relativeLayout);
        // onCreate
        animationDrawable =(AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(2000);

    }
    @Override
    protected void onResume() {
        super.onResume();
        if (animationDrawable != null && !animationDrawable.isRunning())
            animationDrawable.start();
    }


    public void scanCode(View view){
        scannerView=new ZXingScannerView(this);
        scannerView.setResultHandler(new ZXingScannerResultHandler());
        setContentView(scannerView);
        scannerView.startCamera();

    }
    public void onPause(){
        super.onPause();
        if (animationDrawable != null && animationDrawable.isRunning())
            animationDrawable.stop();
        scannerView.stopCamera();
    }
    class ZXingScannerResultHandler implements ZXingScannerView.ResultHandler
    {
        @Override
        public void handleResult (Result result){
            String resultCode = result.getText();
            int c=Integer.parseInt(resultCode);
            if(c >= 1400000 && c < 1700000) {
                Toast.makeText(MainActivity.this,resultCode, Toast.LENGTH_SHORT).show();
                setContentView(R.layout.activity_main);
                scannerView.stopCamera();

            }
            else {
                Toast.makeText(MainActivity.this, "invalid", Toast.LENGTH_SHORT).show();
                setContentView(R.layout.activity_main);
                scannerView.stopCamera();
            }

        }
    }
}
