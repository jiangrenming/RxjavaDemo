package com.nld.rxjavademo.ui.activity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nld.rxjavademo.AndroidApplication;
import com.nld.rxjavademo.R;
import com.nld.rxjavademo.api.bean.Subjects;
import com.nld.rxjavademo.base.BaseActivity;
import com.nld.rxjavademo.local.UserDao;
import com.nld.rxjavademo.local.bean.User;
import com.nld.rxjavademo.local.impl.UserDaoImp;
import com.nld.rxjavademo.presenter.MoviesPresenter;
import com.nld.rxjavademo.ui.injector.component.DaggerMovieComponent;
import com.nld.rxjavademo.ui.injector.module.MovieModule;
import com.nld.rxjavademo.ui.view.IMovieView;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmObject;


/**
 * @author jiangrenming
 */
public class MainActivity extends BaseActivity<MoviesPresenter> implements IMovieView<List<Subjects>> {

  private static final String TAG = "MainActivity";

    @BindView(R.id.btn_one)
    Button btn_one;
    @BindView(R.id.txt)
    TextView txt;
    @BindView(R.id.btn_two)
    Button btn_two;

    @BindView(R.id.btn_three)
    Button btn_three;

    @BindView(R.id.btn_four)
    Button btn_four;

    @BindView(R.id.btn_five)
    Button btn_five;


    @Override
    public int attchLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInjector() {
        DaggerMovieComponent.builder()
                .applicationComponent(getAppComponent())
                .movieModule(new MovieModule(this))
                .build().inject(this);
    }

    @Override
    public void initDatas() {}

    @Override
    public void upDateViews() {
        mPresenter.getData();
    }
    @OnClick({R.id.btn_one,R.id.btn_two,R.id.btn_three,R.id.btn_four,R.id.btn_five})
    public  void onClick(View view){
        switch (view.getId()){
            case R.id.btn_one:
                mPresenter.getMoreData();
                break;
            case R.id.btn_two:  //添加数据库
                UserDao userDao = new UserDaoImp(getActivityRealm());
                User user = new User();
                user.setId(1);
                user.setAge(10);
                user.setName("黎明");
                userDao.insert(user);
                break;
            case R.id.btn_three:  //查询
                break;
            case R.id.btn_four:    //删除
                break;
            case R.id.btn_five:    //更新
                break;
            default:
                break;
        }
    }


    @Override
    public void loadData(List<Subjects> data) {
        if (data != null && data.size() > 0){
            StringBuilder sb  = new StringBuilder();
            for (int i = 0 ; i <data.size() ;i++){
                sb.append(data.get(i).getTitle()).append("-").append(data.get(i).getYear()).append("\n");
            }
            txt.setText(sb.toString());
        }
    }

    @Override
    public void loadMoreData(List<Subjects> data) {
        Log.i(TAG,data.toString());
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i < data.size(); i++){
            sb.append(data.get(i).getTitle()).append("-").append(data.get(i).getYear()).append("\n");
        }
        txt.setText(sb.toString());
    }

    @Override
    public void loadNoData() {
        Toast.makeText(this,"暂无数据了",Toast.LENGTH_SHORT).show();
    }
}
