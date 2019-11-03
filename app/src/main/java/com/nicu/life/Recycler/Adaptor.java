package com.nicu.life.Recycler;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.nicu.life.Checkout;
import com.nicu.life.FirebaseClass.FireStorage;
import com.nicu.life.ProductView;
import com.nicu.life.R;
import com.nicu.life.RoomView;
import com.nicu.life.ServiceView;

import java.text.SimpleDateFormat;

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

        public Product(@NonNull FirestoreRecyclerOptions<Card.Product> options) {
            super(options);
        }

        @Override
        protected void onBindViewHolder(@NonNull final ProductHolder viewHolder, int i, @NonNull Card.Product card) {
            viewHolder.title.setText(card.getTitle());
            viewHolder.price.setText("₹ "+card.getPrice().toString());
            viewHolder.desc.setText(card.getDesc());

            viewHolder.btnOrder.setOnClickListener(v -> {
                Intent checkout = new Intent(viewHolder.itemView.getContext(), Checkout.class);
                checkout.putExtra("item", card.getTitle());
                checkout.putExtra("qnt", viewHolder.qnt);
                checkout.putExtra("amt", (Long)(viewHolder.qnt*card.getPrice()));
                checkout.putExtra("detail", "Food product");
                viewHolder.itemView.getContext().startActivity(checkout);
            });

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
            private TextView title, qntVw, price, desc;
            private ImageView img, btnAdd;
            private Button btnOrder;
            private Long qnt = 1L;

            public ProductHolder(@NonNull final View itemView) {
                super(itemView);

                title = itemView.findViewById(R.id.title);
                price = itemView.findViewById(R.id.price);
                desc = itemView.findViewById(R.id.desc);
                qntVw = itemView.findViewById(R.id.qnt);
                img = itemView.findViewById(R.id.img);
                btnAdd = itemView.findViewById(R.id.btn_add);
                btnOrder = itemView.findViewById(R.id.btn_order);

                itemView.setOnClickListener(v -> {
                    int pos = getAdapterPosition();
                    if(pos!=RecyclerView.NO_POSITION) {
                        DocumentSnapshot currentSnapshot = getSnapshots().getSnapshot(pos);

                        Intent intent = new Intent(itemView.getContext() , ProductView.class);
                        intent.putExtra("id", currentSnapshot.getId());
                        itemView.getContext().startActivity(intent);
                    }
                });

                btnAdd.setOnClickListener(v -> {
                    qntVw.setText(String.valueOf(++qnt));
                });
            }
        }
    }

    public static class ProductMenu extends FirestoreRecyclerAdapter<Card.Product, ProductMenu.ProductHolder> {

        public ProductMenu(@NonNull FirestoreRecyclerOptions<Card.Product> options) {
            super(options);
        }

        @Override
        protected void onBindViewHolder(@NonNull final ProductHolder viewHolder, int i, @NonNull Card.Product card) {
            viewHolder.title.setText(card.getTitle());

            try {
                Glide.with(viewHolder.itemView.getContext()).applyDefaultRequestOptions(new RequestOptions().override(132, 150))
                        .load(FireStorage.getInstance().getImgRef(card.getImg())).into(viewHolder.img);
            } catch (Exception ex) {}
        }

        @NonNull
        @Override
        public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_menu, parent, false);
            return new ProductHolder(v);
        }

        public class ProductHolder extends RecyclerView.ViewHolder {
            private TextView title;
            private ImageView img;

            public ProductHolder(@NonNull final View itemView) {
                super(itemView);

                title = itemView.findViewById(R.id.title);
                img = itemView.findViewById(R.id.img);

                itemView.setOnClickListener(v -> {
                    int pos = getAdapterPosition();
                    if(pos!=RecyclerView.NO_POSITION) {
                        DocumentSnapshot currentSnapshot = getSnapshots().getSnapshot(pos);

                        Intent intent = new Intent(itemView.getContext() , ProductView.class);
                        intent.putExtra("id", currentSnapshot.getId());
                        itemView.getContext().startActivity(intent);
                    }
                });
            }
        }
    }

    public static class Room extends FirestoreRecyclerAdapter<Card.Room, Room.RoomHolder> {

        public Room(@NonNull FirestoreRecyclerOptions<Card.Room> options) {
            super(options);
        }

        @Override
        protected void onBindViewHolder(@NonNull final RoomHolder viewHolder, int i, @NonNull Card.Room card) {
            viewHolder.title.setText(card.getTitle());
            viewHolder.price.setText("₹ "+card.getPrice().toString());
            viewHolder.adress.setText(card.getAddress());
            viewHolder.bedCount.setText(card.getRoom().toString());
            viewHolder.toiletCount.setText(card.getToilet().toString());

            try {
                Glide.with(viewHolder.itemView.getContext()).applyDefaultRequestOptions(new RequestOptions().override(200, 200))
                        .load(FireStorage.getInstance().getImgRef(card.getImg())).into(viewHolder.img);
            } catch (Exception ex) {}
        }

        @NonNull
        @Override
        public RoomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_room, parent, false);
            return new RoomHolder(v);
        }

        public class RoomHolder extends RecyclerView.ViewHolder {
            private TextView title, price, adress, bedCount, toiletCount;
            private ImageView img;

            public RoomHolder(@NonNull final View itemView) {
                super(itemView);

                title = itemView.findViewById(R.id.title);
                price = itemView.findViewById(R.id.price);
                adress = itemView.findViewById(R.id.adress);
                bedCount = itemView.findViewById(R.id.bed);
                toiletCount = itemView.findViewById(R.id.toilet);
                img = itemView.findViewById(R.id.img);

                itemView.setOnClickListener(v -> {
                    int pos = getAdapterPosition();
                    if(pos!=RecyclerView.NO_POSITION) {
                        DocumentSnapshot currentSnapshot = getSnapshots().getSnapshot(pos);

                        Intent intent = new Intent(itemView.getContext() , RoomView.class);
                        intent.putExtra("id", currentSnapshot.getId());
                        itemView.getContext().startActivity(intent);
                    }
                });
            }
        }
   }

    public static class Service extends FirestoreRecyclerAdapter<Card.Service, Service.ServiceHolder> {

        public Service(@NonNull FirestoreRecyclerOptions<Card.Service> options) {
            super(options);
        }

        @Override
        protected void onBindViewHolder(@NonNull final ServiceHolder viewHolder, int i, @NonNull Card.Service card) {
            viewHolder.name.setText(card.getName());

            try {
                Glide.with(viewHolder.itemView.getContext()).applyDefaultRequestOptions(new RequestOptions().override(100, 100))
                        .load(FireStorage.getInstance().getImgRef(card.getImg())).into(viewHolder.img);
            } catch (Exception ex) {}
        }

        @NonNull
        @Override
        public ServiceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_services, parent, false);
            return new ServiceHolder(v);
        }

        public class ServiceHolder extends RecyclerView.ViewHolder {
            private TextView name;
            private ImageView img;

            public ServiceHolder(@NonNull final View itemView) {
                super(itemView);

                name = itemView.findViewById(R.id.name);
                img = itemView.findViewById(R.id.img);

                itemView.setOnClickListener(v -> {
                    int pos = getAdapterPosition();
                    if(pos!=RecyclerView.NO_POSITION) {
                        DocumentSnapshot currentSnapshot = getSnapshots().getSnapshot(pos);

                        Intent intent = new Intent(itemView.getContext() , ServiceView.class);
                        intent.putExtra("id", currentSnapshot.getId());
                        itemView.getContext().startActivity(intent);
                    }
                });
            }
        }
    }

    public static class Order extends FirestoreRecyclerAdapter<Card.Order, Order.OrderHolder> {

        public Order(@NonNull FirestoreRecyclerOptions<Card.Order> options) {
            super(options);
        }

        @Override
        protected void onBindViewHolder(@NonNull final OrderHolder viewHolder, int i, @NonNull Card.Order card) {
            viewHolder.orderId.setText("#"+card.getOrderId());
            viewHolder.item.setText(card.getItem());
            viewHolder.qnt.setText(card.getQnt().toString());
            viewHolder.price.setText("₹ "+card.getAmt());
            viewHolder.date.setText(new SimpleDateFormat("HH:mm:ss dd MMM yyyy").format(card.getDate()));
        }

        @NonNull
        @Override
        public OrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_order, parent, false);
            return new OrderHolder(v);
        }

        public class OrderHolder extends RecyclerView.ViewHolder {
            private TextView orderId, item, qnt, price, date;

            public OrderHolder(@NonNull final View itemView) {
                super(itemView);

                orderId = itemView.findViewById(R.id.orderId);
                item = itemView.findViewById(R.id.title);
                qnt = itemView.findViewById(R.id.qnt);
                price = itemView.findViewById(R.id.price);
                date = itemView.findViewById(R.id.date);
            }
        }
    }

    private static void pin(String msg) {
        Log.d("RCMGX", msg);
    }
}