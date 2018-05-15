package sourcecode.thangdang.finalproject;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ChooseLevel extends AppCompatActivity implements View.OnClickListener{
    private Button mBtnLevelOne, mBtnLevelTwo, mBtnLevelThree, mBtnLevelFour, mBtnLevelFive, mBtnBack;
    private boolean canAccessLevel2 = false;
    private boolean canAccessLevel3 = false;
    private boolean canAccessLevel4 = false;
    private boolean canAccessLevel5 = false;
    MediaPlayer song;


    //  Intent getExtra = getIntent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_level);

        song= MediaPlayer.create(this,R.raw.choose_level);
        song.setLooping(true);
        song.start();

        init();
        setClickListener();
        //Bundle getExtra = getIntent().getExtras();
    }

    public void init(){
        mBtnLevelOne = (Button)findViewById(R.id.btn_level_one);
        mBtnLevelTwo = (Button)findViewById(R.id.btn_level_two);
        mBtnLevelThree = (Button)findViewById(R.id.btn_level_three);
        mBtnLevelFour = (Button)findViewById(R.id.btn_level_four);
        mBtnLevelFive = (Button)findViewById(R.id.btn_level_five);
    }

    public void setClickListener(){
        mBtnLevelOne.setOnClickListener(this);
        mBtnLevelTwo.setOnClickListener(this);
        mBtnLevelThree.setOnClickListener(this);
        mBtnLevelFour.setOnClickListener(this);
        mBtnLevelFive.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_level_one:
                song.stop();
                Intent intent = new Intent(this,LevelOneActivity.class);
                startActivityForResult(intent,Configs.REQ_CODE_CHOOSE_LEVEL);
                break;

            case R.id.btn_level_two:
                try {
//                    Bundle packBundle = getExtra.getBundleExtra("package");
//                    canAccessLevel2 = packBundle.getBoolean("checkFinishLevel1");

                    if(canAccessLevel2) {
                        Intent intentTwo = new Intent(this, LevelTwoActivity.class);
                        this.startActivity(intentTwo);
                    }
                }catch (Exception e){}
                Log.d("check","thang: " + canAccessLevel2);
                break;
            case R.id.btn_level_three:
                if(canAccessLevel3){
                    Intent intentThree = new Intent(this,LevelThreeActivity.class);
                    this.startActivity(intentThree);
                }
                break;
            case R.id.btn_level_four:
                if(canAccessLevel4) {
                    Intent intentFour = new Intent(this, LevelFourActivity.class);
                    this.startActivity(intentFour);
                }
                break;
            case R.id.btn_level_five:
                if(canAccessLevel5) {
                    Intent intentFive = new Intent(this, LevelOneActivity.class);
                    this.startActivity(intentFive);
                }
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == Configs.REQ_CODE_CHOOSE_LEVEL) {
            // Make sure the request was successful
            try {
                canAccessLevel2 = data.getBooleanExtra("package",false);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
