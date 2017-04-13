package com.yyyu.confetti.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yyyu.baselibrary.utils.MyLog;
import com.yyyu.baselibrary.utils.WindowUtils;
import com.yyyu.confetti.MyApplication;
import com.yyyu.confetti.R;
import com.yyyu.confetti.bean.ClassifyData;
import com.yyyu.confetti.bean.ContentCom;
import com.yyyu.confetti.bean.UserInfo;
import com.yyyu.confetti.di.component.DaggerMainActComponent;
import com.yyyu.confetti.di.module.MainActModule;
import com.yyyu.confetti.model.user_state.UserStateContext;
import com.yyyu.confetti.net.APIMethodManager;
import com.yyyu.confetti.net.IRequestCallback;
import com.yyyu.confetti.utils.RevealAnimUtils;
import com.yyyu.confetti.utils.RxBus;
import com.yyyu.confetti.view.fragment.ClassifyDetailFragment;
import com.yyyu.confetti.view.fragment.CollCateFragment;
import com.yyyu.confetti.view.fragment.HomeFragment;
import com.yyyu.confetti.view.inter.IMainView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * 功能：主界面
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/12
 */

public class MainActivity extends BaseActivity implements IMainView {


    @BindView(R.id.nv_left_menu)
    NavigationView nvLeftMenu;
    @BindView(R.id.dl_main)
    DrawerLayout dlMain;
    private FragmentTransaction ft;
    private Observable observable;
    private ImageView ivHeader;
    private TextView tvUsername;
    private int centerX;
    private int centerY;
    private int startRadius;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void beforeInit() {
        observable = RxBus.getInstance().register(this);
        Intent intent = getIntent();
        centerX = (int) intent.getFloatExtra("centerX" , 0);
        centerY = (int) intent.getFloatExtra("centerY" , 0);
        startRadius = (int) intent.getFloatExtra("startRadius" , 0);
    }


    @Override
    protected void initView() {
        DaggerMainActComponent.builder()
                .baseActComponent(getBaseActComponent())
                .mainActModule(new MainActModule())
                .build()
                .inject(this);
        disableNavigationViewScrollbars(nvLeftMenu);
        replaceWithFrag( new HomeFragment());
        View headerView = nvLeftMenu.inflateHeaderView(R.layout.draw_left_header);
        ivHeader = (ImageView) headerView.findViewById(R.id.iv_header);
        tvUsername = (TextView) headerView.findViewById(R.id.tv_username);
        RevealAnimUtils.animateRevealShow(dlMain , centerX , centerY , startRadius , WindowUtils.getSize(this)[1]);
        UserInfo userInfo = ((MyApplication)getApplication()).getUserInfo();
        if(userInfo != null){
            tvUsername.setText(userInfo.getUsername());
        }
    }

    @Override
    protected void initListener() {
        nvLeftMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                dlMain.closeDrawer(Gravity.LEFT);
                switch (item.getItemId()){
                    case R.id.menu_home:
                        replaceWithFrag( new HomeFragment());
                        break;
                    case R.id.menu_zhrb:
                        replaceWithFrag(ClassifyDetailFragment.getInstance(0));
                        break;
                    case R.id.menu_gkk:
                        replaceWithFrag(ClassifyDetailFragment.getInstance(1));
                        break;
                    case R.id.menu_js:
                        replaceWithFrag(ClassifyDetailFragment.getInstance(2));
                        break;
                    case R.id.menu_csdn:
                        replaceWithFrag(ClassifyDetailFragment.getInstance(3));
                        break;
                    case R.id.menu_coll:
                        UserStateContext stateContext =  ((MyApplication)getApplication()).getUserStateContext();
                        stateContext.oprCollMenu(MainActivity.this);
                        break;
                    case R.id.menu_download:
                        break;
                    case R.id.menu_setting:
                        break;
                    case R.id.menu_exit:
                        ActivityHolder.finishedAll();
                        break;

                }
                return true;
            }
        });

        observable.observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() {
            @Override
            public void call(Object object) {
                if(object instanceof  String){
                    if("open_drawer".equals(object)&& dlMain!=null){
                        dlMain.openDrawer(Gravity.LEFT);
                    }
                }else if(object instanceof  UserInfo){
                    UserInfo userInfo = (UserInfo) object;
                    tvUsername.setText(userInfo.getUsername());
                }

            }
        });

        ivHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlMain.closeDrawer(Gravity.START);
                UserStateContext stateContext =  ((MyApplication)getApplication()).getUserStateContext();
                stateContext.oprLeftUserIcon(MainActivity.this);
            }
        });

    }

    @Override
    protected void initData() {

    }

    private void replaceWithFrag(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.fl_main_content , fragment);
        ft.commit();
    }


    @Override
    public void onBackPressed() {
        if(dlMain.isDrawerOpen(Gravity.LEFT)){
            dlMain.closeDrawer(Gravity.LEFT);
        }else{
            super.onBackPressed();
        }
    }

    /**
     * 去除Navigation 的 scrollbar
     * @param navigationView
     */
    private void disableNavigationViewScrollbars(NavigationView navigationView) {
        if (navigationView != null) {
            NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
            if (navigationMenuView != null) {
                navigationMenuView.setVerticalScrollBarEnabled(false);
            }
        }
    }

    public static void startAction(Activity activity , float centerX , float centerY , float startRadius){
        Intent intent = new Intent(activity , MainActivity.class);
        intent.putExtra("centerX" , centerX);
        intent.putExtra("centerY" , centerY);
        intent.putExtra("startRadius" , startRadius);
        activity.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().unregister(this);
    }


    @Override
    public void toLoginAct() {
        LoginActivity.startAction(this);
    }

    @Override
    public void toCollFragment() {
        replaceWithFrag(new CollCateFragment());
    }
}
