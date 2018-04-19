package sourcecode.thangdang.finalproject;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LevelOneActivity extends AppCompatActivity implements View.OnClickListener {
    private GameItem mGameItem1, mGameItem2, mGameItem3, mGameItem4;
    private Button mBtnItem1, mBtnItem2, mBtnItem3, mBtnItem4, mBtnBack;
    private TextView mTvResult;
    private boolean isFinish = false;
    MediaPlayer song;
    int percent = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_one);
        mGameItem1 = new GameItem(mBtnItem1, this.getResources().getString(R.string.item_1_lv1));
        mGameItem2 = new GameItem(mBtnItem2, this.getResources().getString(R.string.item_2_lv1));
        mGameItem3 = new GameItem(mBtnItem3, this.getResources().getString(R.string.item_3_lv1));
        mGameItem4 = new GameItem(mBtnItem4, this.getResources().getString(R.string.item_4_lv1));

        song= MediaPlayer.create(this,R.raw.lv_1);
        song.setLooping(true);
        song.start();

        init();
        setOnClick();

    }

    public void init(){
        mBtnItem1 = (Button)findViewById(R.id.btn_item1);
        mBtnItem2 = (Button)findViewById(R.id.btn_item2);
        mBtnItem3 = (Button)findViewById(R.id.btn_item3);
        mBtnItem4 = (Button)findViewById(R.id.btn_item4);
        mBtnBack = (Button)findViewById(R.id.btn_back);
        mTvResult = (TextView)findViewById(R.id.tv_result);
    }
    public void setOnClick(){
        mBtnItem1.setOnClickListener(this);
        mBtnItem2.setOnClickListener(this);
        mBtnItem3.setOnClickListener(this);
        mBtnItem4.setOnClickListener(this);
        mBtnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(percent >= 75)
            mBtnBack.setVisibility(View.VISIBLE);
        switch (view.getId()){
            case R.id.btn_item1:
                mGameItem1.isTouching(this);
                percent +=25;
                mTvResult.setText(String.valueOf(percent) + "%");
                break;
            case R.id.btn_item2:
                mGameItem2.isTouching(this);
                percent +=25;
                mTvResult.setText(String.valueOf(percent) + "%");
                break;
            case R.id.btn_item3:
                mGameItem3.isTouching(this);
                percent +=25;
                mTvResult.setText(String.valueOf(percent) + "%");
                break;
            case R.id.btn_item4:
                mGameItem4.isTouching(this);
                percent +=25;
                mTvResult.setText(String.valueOf(percent) + "%");
                break;
            case R.id.btn_back:
                //if(percent >= 90) {
                    song.stop();
                    isTheGameFinish();
              //  }
//                Log.d("check","level 1: " + isFinish);
                break;
        }
    }

    public void isTheGameFinish(){
          //  mBtnBack.setVisibility(View.VISIBLE);
            isFinish = true;
            Intent intent = new Intent();
//            Bundle bundle = new Bundle();
//            bundle.putBoolean("checkFinishLevel1",isFinish);
            intent.putExtra("package",isFinish);
            setResult(Configs.REQ_CODE_CHOOSE_LEVEL,intent);
            //startActivityForResult(intent,1);

            Log.d("check","lv1finish: " + isFinish);
            finish();
    }

}
