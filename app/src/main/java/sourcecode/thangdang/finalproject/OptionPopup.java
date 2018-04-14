package sourcecode.thangdang.finalproject;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class OptionPopup extends AppCompatActivity implements View.OnClickListener{
    private Button mBtnExitOps, mBtnSoundOn, mBtnSoundOff, mBtnMusicOn, mBtnMusicOff, mBtnSaveOpt;
    MediaPlayer song;
    SoundManager sound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_popup);
        song= MediaPlayer.create(this,R.raw.home_sound);
        song.setLooping(true);
        song.start();
        sound = SoundManager.getInstance();
        sound.init(this);

        mBtnExitOps = (Button)findViewById(R.id.btn_exit_option);
        mBtnMusicOn = (Button)findViewById(R.id.btn_music_option_on);
        mBtnMusicOff = (Button)findViewById(R.id.btn_music_option_off);
        mBtnSoundOn = (Button)findViewById(R.id.btn_sound_option_on);
        mBtnSoundOff = (Button)findViewById(R.id.btn_sound_option_off);
        mBtnSaveOpt = (Button)findViewById(R.id.btn_save_option);

        mBtnExitOps.setOnClickListener(this);
        mBtnMusicOn.setOnClickListener(this);
        mBtnMusicOff.setOnClickListener(this);
        mBtnSoundOn.setOnClickListener(this);
        mBtnSoundOff.setOnClickListener(this);
        mBtnSaveOpt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_exit_option:
                sound.playSound(R.raw.button_effect);
                break;
            case R.id.btn_music_option_on:
                sound.playSound(R.raw.button_effect);
                mBtnMusicOn.setVisibility(View.INVISIBLE);
                mBtnMusicOff.setVisibility(View.VISIBLE);

                break;
            case R.id.btn_music_option_off:
                sound.playSound(R.raw.button_effect);
                mBtnMusicOff.setVisibility(View.INVISIBLE);
                mBtnMusicOn.setVisibility(View.VISIBLE);

                break;

            case R.id.btn_sound_option_on:

                mBtnSoundOn.setVisibility(View.INVISIBLE);
                mBtnSoundOff.setVisibility(View.VISIBLE);

                break;
             case R.id.btn_sound_option_off:
                sound.playSound(R.raw.button_effect);
                mBtnSoundOff.setVisibility(View.INVISIBLE);
                mBtnSoundOn.setVisibility(View.VISIBLE);

                break;

            case R.id.btn_save_option:
                sound.playSound(R.raw.button_effect);

                if(mBtnMusicOff.getVisibility() == View.VISIBLE)
                    song.pause();
                if(mBtnMusicOn.getVisibility() == View.VISIBLE)
                    song.start();

                if(mBtnSoundOff.getVisibility() == View.VISIBLE)
                    sound.setMuted(true);
                if(mBtnSoundOn.getVisibility() == View.VISIBLE)
                    sound.setMuted(false);

                break;
        }
    }
}
