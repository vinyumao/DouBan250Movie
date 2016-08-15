package com.kevin.douban250movie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

	private MovieService movieService;
	private List<Movie.SubjectsBean> subjects;
	private MovieAdapter adapter;
	@BindView(R.id.rv_movie)
	RecyclerView rvMovie;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		movieService = RetrofitManager.getInstance().create(MovieService.class);
		subjects = new ArrayList<>();
		adapter = new MovieAdapter(subjects);
		rvMovie.setLayoutManager(new LinearLayoutManager(this));
		rvMovie.addItemDecoration(new Mydecoration(this,Mydecoration.VERTICAL_LIST));
		rvMovie.setAdapter(adapter);

		movieService.getTopMovie(0,10)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.unsubscribeOn(Schedulers.io())
				.subscribe(new Subscriber<Movie>() {
					@Override
					public void onCompleted() {
						adapter.notifyDataSetChanged();
					}

					@Override
					public void onError(Throwable e) {
						Toast.makeText(MainActivity.this, "Get Top Movie Error!", Toast.LENGTH_SHORT).show();
						e.printStackTrace();
					}

					@Override
					public void onNext(Movie movie) {
						subjects.addAll(movie.getSubjects());
					}
				});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}
