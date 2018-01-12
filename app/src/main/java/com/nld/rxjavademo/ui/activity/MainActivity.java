package com.nld.rxjavademo.ui.activity;

import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.nld.rxjavademo.R;
import com.nld.rxjavademo.api.bean.Subjects;
import com.nld.rxjavademo.base.BaseActivity;
import com.nld.rxjavademo.presenter.MoviesPresenter;
import com.nld.rxjavademo.ui.injector.component.DaggerMovieComponent;
import com.nld.rxjavademo.ui.injector.module.MovieModule;
import com.nld.rxjavademo.ui.view.IMovieView;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author jiangrenming
 */
public class MainActivity extends BaseActivity<MoviesPresenter> implements IMovieView<List<Subjects>> {

  private static final String TAG = "MainActivity";

    @BindView(R.id.btn_one)
    Button btn_one;
    @BindView(R.id.txt)
    TextView txt;


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
    @OnClick(R.id.btn_one)
    public  void onClick(){
        mPresenter.getMoreData();
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
