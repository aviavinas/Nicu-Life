package com.nicu.life.Recycler;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.nicu.life.FirebaseClass.FireStorage;
import com.nicu.life.R;

public class Adaptor {

    public static class Category extends FirestoreRecyclerAdapter<Card.Category, Category.CategoryHolder> {
        private OnItemClickListener listener;

        public Category(@NonNull FirestoreRecyclerOptions<Card.Category> options) {
            super(options);
        }

        @Override
        protected void onBindViewHolder(@NonNull final CategoryHolder viewHolder, int i, @NonNull Card.Category card) {
            viewHolder.title.setText(card.getName());
            viewHolder.count.setText(card.getCount()+" flavours");

            try {
                Glide.with(viewHolder.itemView.getContext())
                        .applyDefaultRequestOptions(new RequestOptions().override(40, 40))
                        .load(FireStorage.getInstance().getImgRef(card.getIcon())).into(viewHolder.icon);
            } catch (Exception ex) {}
        }

        @NonNull
        @Override
        public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_category, parent, false);
            return new CategoryHolder(v);
        }

        public class CategoryHolder extends RecyclerView.ViewHolder {
            private TextView title, count;
            private ImageView icon;

            public CategoryHolder(@NonNull final View itemView) {
                super(itemView);
                icon = itemView.findViewById(R.id.icon);
                title = itemView.findViewById(R.id.title);
                count = itemView.findViewById(R.id.count);

//                itemView.setOnClickListener(v -> {
//                    int pos = getAdapterPosition();
//                    if(pos!=RecyclerView.NO_POSITION && listener!=null) {
//                        DocumentSnapshot currentSnapshot = getSnapshots().getSnapshot(pos);
//
//                        Intent classRoomIntent = new Intent(itemView.getContext() , MyClassRoom.class);
//                        classRoomIntent.putExtra("courseId", currentSnapshot.getId());
//                        itemView.getContext().startActivity(classRoomIntent);
//                    }
//                });
            }
        }

        public interface OnItemClickListener {
            void onItemClick(DocumentSnapshot documentSnapshot, int pos);
        }

        void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }
    }

    public static class Product extends FirestoreRecyclerAdapter<Card.Product, Product.ProductHolder> {
        private OnItemClickListener listener;

        public Product(@NonNull FirestoreRecyclerOptions<Card.Product> options) {
            super(options);
        }

        @Override
        protected void onBindViewHolder(@NonNull final ProductHolder viewHolder, int i, @NonNull Card.Product card) {
            viewHolder.title.setText(card.getTitle());
            viewHolder.price.setText("₹ "+card.getPrice().toString());
            viewHolder.qnt.setText("1");
            viewHolder.desc.setText(card.getDesc());

            try {
                Glide.with(viewHolder.itemView.getContext()).applyDefaultRequestOptions(new RequestOptions().override(132, 150))
                        .load(FireStorage.getInstance().getImgRef(card.getImg())).into(viewHolder.img);
            } catch (Exception ex) {}
        }

        @NonNull
        @Override
        public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_product_act, parent, false);
            return new ProductHolder(v);
        }

        public class ProductHolder extends RecyclerView.ViewHolder {
            private TextView title, qnt, price, desc;
            private ImageView img;

            public ProductHolder(@NonNull final View itemView) {
                super(itemView);

                title = itemView.findViewById(R.id.title);
                price = itemView.findViewById(R.id.price);
                desc = itemView.findViewById(R.id.desc);
                qnt = itemView.findViewById(R.id.qnt);
                img = itemView.findViewById(R.id.img);

//                itemView.setOnClickListener(v -> {
//                    int pos = getAdapterPosition();
//                    if(pos!=RecyclerView.NO_POSITION && listener!=null) {
//                        DocumentSnapshot currentSnapshot = getSnapshots().getSnapshot(pos);
//
//                        Intent classRoomIntent = new Intent(itemView.getContext() , MyClassRoom.class);
//                        classRoomIntent.putExtra("courseId", currentSnapshot.getId());
//                        itemView.getContext().startActivity(classRoomIntent);
//                    }
//                });
            }
        }

        public interface OnItemClickListener {
            void onItemClick(DocumentSnapshot documentSnapshot, int pos);
        }

        void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }
    }

    private static void pin(String msg) {
        Log.d("RCMGX", msg);
    }
}