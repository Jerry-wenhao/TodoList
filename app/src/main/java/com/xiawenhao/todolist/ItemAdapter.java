//package com.xiawenhao.todolist;
//
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
//public class ItemAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
//    private List<ItemDate> itemDate;
//
//    static class ViewHolder extends RecyclerView.ViewHolder {
//        CheckBox hasDone;
//        TextView title;
//        Button date;
//
//        public ViewHolder(View view) {
//            super(view);
//            hasDone = view.findViewById(R.id.item_checkbox);
//            title = view.findViewById(R.id.item_title_show);
//            date = view.findViewById(R.id.date_show);
//        }
//
//        public TaskAdapter(List<ItemDate> itemDates) {
//            itemDate = itemDates;
//        }
//    }
//
//    @NonNull
//    @Override
//    public TaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return null;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//}
