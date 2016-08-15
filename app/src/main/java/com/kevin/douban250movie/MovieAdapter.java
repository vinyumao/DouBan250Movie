package com.kevin.douban250movie;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 描述: TODO
 * --------------------------------------------
 * 工程:
 * #0000     mwy     创建日期: 2016-08-12 18:33
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

	private List<Movie.SubjectsBean> subjects;

	public MovieAdapter(List<Movie.SubjectsBean> subjects){
		this.subjects = subjects;
	}

	@Override
	public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_rv_list, null, false);
		return new MovieHolder(view);
	}

	@Override
	public int getItemCount() {
		return subjects == null ? 0 : subjects.size();
	}

	@Override
	public void onBindViewHolder(MovieHolder holder, int position) {
		holder.tvMovieName.setText(subjects.get(position).getTitle());
		Uri uri = Uri.parse(subjects.get(position).getImages().getMedium());
		holder.imgMovie.setImageURI(uri);
	}

	class MovieHolder extends RecyclerView.ViewHolder {
		@BindView(R.id.img_movie)
		SimpleDraweeView imgMovie;
		@BindView(R.id.tv_movie_name)
		TextView tvMovieName;

		public MovieHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}

}
