package sourcecode.thangdang.finalproject;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mBtnPlay, mBtnHelp, mBtnOption, mBtnHome;
    SoundManager sound;
    Dialog dialog;
    MediaPlayer song;

    private Button mBtnExitOps, mBtnSoundOpt, mBtnMusicOpt, mBtnSaveOpt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        song= MediaPlayer.create(MainActivity.this,R.raw.home_sound);
        song.setLooping(true);
        song.start();
        sound = SoundManager.getInstance();
        sound.init(this);
        init();
        setClickListener();

    }


    public void init(){
        mBtnPlay = (Button)findViewById(R.id.btn_play);
        mBtnHome = (Button)findViewById(R.id.btn_home);
        mBtnHelp = (Button)findViewById(R.id.btn_help);
        mBtnOption = (Button)findViewById(R.id.btn_option);

    }

    public void setClickListener(){
        mBtnPlay.setOnClickListener(this);
        mBtnOption.setOnClickListener(this);
        mBtnHelp.setOnClickListener(this);
        mBtnHome.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_play:
                sound.playSound(R.raw.button_effect);
                break;
            case R.id.btn_home:
                sound.playSound(R.raw.button_effect);
                break;
            case R.id.btn_help:
                sound.playSound(R.raw.button_effect);
                break;
            case R.id.btn_option:
                sound.playSound(R.raw.button_effect);
                dialogOption();
                break;

        }
    }

    public  void  dialogOption() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_option_popup);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        song.isPlaying();
        dialog.show();

        mBtnExitOps = (Button)findViewById(R.id.btn_exit_option);
        mBtnMusicOpt = (Button)findViewById(R.id.btn_music_option);
        mBtnSoundOpt = (Button)findViewById(R.id.btn_sound_option);
        mBtnSaveOpt = (Button)findViewById(R.id.btn_save_option);
//
//        mBtnExitOps.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sound.playSound(R.raw.button_effect);
//                dialog.dismiss();
//            }
//        });
//        mBtnSoundOpt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sound.playSound(R.raw.button_effect);
//            }
//        });
//        mBtnMusicOpt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sound.playSound(R.raw.button_effect);
//            }
//        });
//        mBtnSaveOpt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sound.playSound(R.raw.button_effect);
//            }
//        });

    }
}
