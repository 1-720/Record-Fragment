package com.example.recordfragment.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recordfragment.R;
import com.example.recordfragment.model.Record;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Record> recordList;

    public RecyclerViewAdapter(Context context, List<Record> recordList) {
        this.context = context;
        this.recordList = recordList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {

        Record record = recordList.get(position);

        holder.recordTitle.setText(record.getRecordTitle());
        holder.recordedTime.setText(record.getRecordedTime());
        holder.recordComment.setText(record.getRecordComment());

        // unix time を読めるよう変換する
        DateFormat dateFormat = DateFormat.getDateInstance();
        String formattedDate = dateFormat.format(new Date(Long.valueOf(record.getDateRecordAdded())).getTime());

        holder.recordDateAdded.setText(formattedDate);

    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public int id;

        // ViewHolderに抱え込む変数たち
        public TextView recordTitle; // タイトル
        public TextView recordedTime; // 時間
        public TextView recordComment; // コメント
        public TextView recordDateAdded; // 追加日時

        public Button editButton; // 修正ボタン
        public Button deleteButton; // 削除ボタン

        public ViewHolder(@NonNull View itemView, Context ctx) {
            super(itemView);
            context = ctx;

            // 各要素の取得
            recordTitle = itemView.findViewById(R.id.list_row_title);
            recordedTime = itemView.findViewById(R.id.list_row_time);
            recordComment = itemView.findViewById(R.id.list_row_comment);
            recordDateAdded = itemView.findViewById(R.id.list_row_date_added);

            editButton = itemView.findViewById(R.id.edit_button);
            deleteButton = itemView.findViewById(R.id.delete_button);



        }
    }
}
