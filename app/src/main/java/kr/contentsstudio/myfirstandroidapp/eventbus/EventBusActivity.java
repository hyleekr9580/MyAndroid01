package kr.contentsstudio.myfirstandroidapp.eventbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import kr.contentsstudio.myfirstandroidapp.R;

public class EventBusActivity extends AppCompatActivity {
    public interface MyEventListener {
        //void onEvent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);

    }

    //최대한 간단하게..
    class MyClass {
        private MyEventListener mmListener;

        public void setOnMyEventListener(MyEventListener Listenet) {
            mmListener = Listenet;
        }

        public void eventPublish() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
