package schaffer.myoho.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import schaffer.myoho.R;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
    }


    @Override
    public void onBackPressed() {
        overridePendingTransition(R.anim.main_in, R.anim.cart_out);
        super.onBackPressed();
    }
}
