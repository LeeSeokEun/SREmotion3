package com.example.leeseokeun.myapplication;

import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class HomeActivity extends AppCompatActivity {

    private AudioReader audioReader;
    private int sampleRate = 8000;
    private int inputBlockSize = 256;
    private int sampleDecimate = 1;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        audioReader = new AudioReader();
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        audioReader.startReader(sampleRate, inputBlockSize * sampleDecimate, new AudioReader.Listener()
        {
            @Override
            public final void onReadComplete(int dB)
            {
                receiveDecibel(dB);
            }

            @Override
            public void onReadError(int error)
            {
            }

        });
    }
    // 데시벨값이 음수에서 소리가 커지면 0에 가까워져서
    // 0에 가까운 숫자를 더해서 데시벨을 양수측으로 움직임
    // 기본 평소 대화소리를 40~60dB로 가정



    public void receiveDecibel(final int dB)
    {
        Log.e("###", dB+90 +" dB");

    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        audioReader.stopReader();
    }

}
