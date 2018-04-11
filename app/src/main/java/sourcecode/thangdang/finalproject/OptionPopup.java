package sourcecode.thangdang.finalproject;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class OptionPopup extends AppCompatActivity implements View.OnClickListener{
    private Button mBtnExitOps, mBtnSoundOpt, mBtnMusicOpt, mBtnSaveOpt;
    int countSound = 0;
    int countMusic = 0;
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
        mBtnMusicOpt = (Button)findViewById(R.id.btn_music_option);
        mBtnSoundOpt = (Button)findViewById(R.id.btn_sound_option);
        mBtnSaveOpt = (Button)findViewById(R.id.btn_save_option);

        mBtnExitOps.setOnClickListener(this);
        mBtnMusicOpt.setOnClickListener(this);
        mBtnSoundOpt.setOnClickListener(this);
        mBtnSaveOpt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_exit_option:
                sound.playSound(R.raw.button_effect);
                break;
            case R.id.btn_music_option:
                sound.playSound(R.raw.button_effect);
                countMusic++;
                break;
            case R.id.btn_sound_option:
                sound.playSound(R.raw.button_effect);
                countSound++;
                break;
            case R.id.btn_save_option:
                sound.playSound(R.raw.button_effect);
                if(countMusic % 2 != 0) {
                    song.pause();

                }
                else
                    song.start();
                if(countSound % 2 != 0) {
                    sound.setMuted(true);
                    Log.d("check","count: " + countSound);

                }
                else {
                    sound.setMuted(false);
                    Log.d("check after","count: " + countSound);
                }
                break;
        }
    }
}
