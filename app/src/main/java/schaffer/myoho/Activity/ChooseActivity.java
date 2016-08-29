package schaffer.myoho.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import schaffer.myoho.R;

public class ChooseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
    }

    public void boys(View view) {
        dumpToMain();
    }

    private void dumpToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        overridePendingTransition(R.anim.main_in,R.anim.choose_out);
        startActivity(intent);
    }

    public void girls(View view) {
        dumpToMain();
    }

    public void kids(View view) {
        dumpToMain();
    }

    public void lifeStyle(View view) {
        dumpToMain();
    }

}
