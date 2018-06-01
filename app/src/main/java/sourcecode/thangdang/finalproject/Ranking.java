package sourcecode.thangdang.finalproject;

import java.io.Serializable;

/**
 * Created by Admin on 29-May-18.
 */

public class Ranking implements Serializable{
    private int mId;
    private String mName;
    private String mTime;

    public Ranking(){}

    public Ranking(int mId, String mName, String mTime) {
        this.mId = mId;
        this.mName = mName;
        this.mTime = mTime;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }
}
