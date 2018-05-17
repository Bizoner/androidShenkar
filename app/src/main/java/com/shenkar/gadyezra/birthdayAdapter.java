package com.shenkar.gadyezra;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shenkar.gadyezra.storage.NamedateEntity;

import java.util.List;

public class birthdayAdapter extends RecyclerView.Adapter<birthdayAdapter.BookViewHolder>{

    private List<NamedateEntity> birthdayList;

    public birthdayAdapter(List<NamedateEntity> birthdayList) {
        this.birthdayList = birthdayList;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);

        return new BookViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        holder.birthday.setText(birthdayList.get(position).getRecordAsString());
    }

    @Override
    public int getItemCount() {
        return birthdayList.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        public TextView birthday;

        public BookViewHolder(View view) {
            super(view);
            birthday = (TextView) view.findViewById(R.id.textView4);
        }
    }
}