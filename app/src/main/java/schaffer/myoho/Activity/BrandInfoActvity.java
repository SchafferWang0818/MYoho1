package schaffer.myoho.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import schaffer.myoho.Fragment.FragmentInfo;
import schaffer.myoho.Fragment.FragmentTransInfo;
import schaffer.myoho.R;

public class BrandInfoActvity extends AppCompatActivity {

    private android.widget.TextView titleTv;
    private android.widget.FrameLayout frameLayout;
    private FragmentManager fm;
    private FragmentInfo fragmentInfo;
    private FragmentTransInfo transInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_info);
        this.frameLayout = (FrameLayout) findViewById(R.id.activity_brand_fragment_group);
        this.titleTv = (TextView) findViewById(R.id.activity_brand_titleTv);
        Intent intent = getIntent();
        String brand = intent.getStringExtra("brand");
        titleTv.setText(brand);
        fm = getSupportFragmentManager();
        fragmentInfo = new FragmentInfo();
        transInfo = new FragmentTransInfo();

        fm.beginTransaction().replace(R.id.activity_brand_fragment_group, fragmentInfo).commit();
    }

    public void showFragment(View view) {
        boolean isAdd = transInfo.isAdd;
        int backStackEntryCount = fm.getBackStackEntryCount();
        if (isAdd && backStackEntryCount == 1) {
            fm.popBackStack("back",FragmentManager.POP_BACK_STACK_INCLUSIVE);

        } else if (!isAdd && backStackEntryCount == 0) {
            fm.beginTransaction().add(R.id.activity_brand_fragment_group,transInfo, FragmentTransInfo.class.getSimpleName()).addToBackStack("back").commit();
        }
    }


}
