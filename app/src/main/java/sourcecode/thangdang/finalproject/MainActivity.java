package sourcecode.thangdang.finalproject;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mBtnPlay, mBtnHelp, mBtnOption, mBtnHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MediaPlayer song= MediaPlayer.create(MainActivity.this,R.raw.home_sound);
        song.setLooping(true);
        song.start();
    }

    public void init(){
        mBtnPlay = (Button)findViewById(R.id.btn_play);
        mBtnHome = (Button)findViewById(R.id.btn_home);
        mBtnHelp = (Button)findViewById(R.id.btn_help);
        mBtnOption = (Button)findViewById(R.id.btn_option);
    }
    @Override
    public void onClick(View view) {

    }
}
