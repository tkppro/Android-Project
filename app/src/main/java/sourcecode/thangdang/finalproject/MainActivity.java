package sourcecode.thangdang.finalproject;


import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mBtnPlay, mBtnHelp, mBtnOption, mBtnHome;
    SoundManager sound;
//    Dialog dialog;
    MediaPlayer song;
    SaveStatus saveGameStatus;
//    ConstraintLayout mainLayout;
//    View myLayout;

    private Button mBtnExitOps, mBtnSoundOn, mBtnSoundOff, mBtnMusicOn, mBtnMusicOff, mBtnSaveOpt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        saveGameStatus = new SaveStatus(getSharedPreferences("game status",MODE_PRIVATE));
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
                Intent intent =  new Intent(this,ChooseLevel.class);
                this.startActivity(intent);
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
        final Dialog dialog = new Dialog(this);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_option_popup);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        song.isPlaying();
        dialog.show();

//        mainLayout = (ConstraintLayout)findViewById(R.id.layout_main);
//        LayoutInflater inflater = getLayoutInflater();
//        myLayout = inflater.inflate(R.layout.activity_option_popup, mainLayout, false);

        mBtnExitOps = (Button)dialog.findViewById(R.id.btn_exit_option);
        mBtnMusicOn = (Button)dialog.findViewById(R.id.btn_music_option_on);
        mBtnMusicOff = (Button)dialog.findViewById(R.id.btn_music_option_off);
        mBtnSoundOn = (Button)dialog.findViewById(R.id.btn_sound_option_on);
        mBtnSoundOff = (Button)dialog.findViewById(R.id.btn_sound_option_off);
        mBtnSaveOpt = (Button)dialog.findViewById(R.id.btn_save_option);

        Log.d("check","music status: " + saveGameStatus.getMusicStatus() + " " + "sound status: " + saveGameStatus.getSoundStatus());

        mBtnSoundOn.setVisibility(saveGameStatus.changeStringToStatus(saveGameStatus.getSoundStatus()));
        mBtnSoundOff.setVisibility(saveGameStatus.changeStringToStatus(saveGameStatus.getSoundStatus()));
        mBtnMusicOn.setVisibility(saveGameStatus.changeStringToStatus(saveGameStatus.getMusicStatus()));
        mBtnMusicOff.setVisibility(saveGameStatus.changeStringToStatus(saveGameStatus.getMusicStatus()));

        mBtnExitOps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sound.playSound(R.raw.button_effect);
                dialog.dismiss();
            }
        });
        mBtnSoundOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBtnSoundOn.setVisibility(View.INVISIBLE);
                mBtnSoundOff.setVisibility(View.VISIBLE);
            }
        });
        mBtnSoundOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sound.playSound(R.raw.button_effect);
                mBtnSoundOff.setVisibility(View.INVISIBLE);
                mBtnSoundOn.setVisibility(View.VISIBLE);
            }
        });

        mBtnMusicOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sound.playSound(R.raw.button_effect);
                mBtnMusicOn.setVisibility(View.INVISIBLE);
                mBtnMusicOff.setVisibility(View.VISIBLE);
            }
        });

        mBtnMusicOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sound.playSound(R.raw.button_effect);
                mBtnMusicOff.setVisibility(View.INVISIBLE);
                mBtnMusicOn.setVisibility(View.VISIBLE);
            }
        });

        mBtnSaveOpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sound.playSound(R.raw.button_effect);
                if(mBtnMusicOff.getVisibility() == View.VISIBLE) {
                    saveGameStatus.setMusicStatus(saveGameStatus.changeStatusToString(View.VISIBLE));
                    Log.d("btn","check" + " music off: "+saveGameStatus.getMusicStatus());
                    song.pause();
                }
                if(mBtnMusicOff.getVisibility() == View.INVISIBLE) {
                    saveGameStatus.setMusicStatus(saveGameStatus.changeStatusToString(View.INVISIBLE));
                    Log.d("btn","check" +" music on: " + saveGameStatus.getMusicStatus());
                    song.start();
                }
                if(mBtnSoundOff.getVisibility() == View.VISIBLE) {
                    saveGameStatus.setSoundStatus(saveGameStatus.changeStatusToString(View.VISIBLE));
                    Log.d("btn","check" +" sound off:" + saveGameStatus.getSoundStatus());
                    sound.setMuted(true);
                }
                if(mBtnSoundOff.getVisibility() == View.INVISIBLE) {
                    saveGameStatus.setSoundStatus(saveGameStatus.changeStatusToString(View.INVISIBLE));
                    Log.d("btn","check" +" sound on:" + saveGameStatus.getSoundStatus());
                    sound.setMuted(false);
                }

            }
        });

    }

}
