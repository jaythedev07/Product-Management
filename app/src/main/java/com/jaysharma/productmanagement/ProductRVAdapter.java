package com.jaysharma.productmanagement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class ProductRVAdapter extends ListAdapter<ProductModal, ProductRVAdapter.ViewHolder> {
    private OnItemClickListener listener;

    ProductRVAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<ProductModal> DIFF_CALLBACK = new DiffUtil.ItemCallback<ProductModal>() {
        @Override
        public boolean areItemsTheSame(ProductModal oldItem, ProductModal newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(ProductModal oldItem, ProductModal newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getDetails().equals(newItem.getDetails()) &&
                    oldItem.getSerial().equals(newItem.getSerial()) &&
                    oldItem.getExpiery().equals(newItem.getExpiery()) &&
                    oldItem.getQuentity().equals(newItem.getQuentity()) &&
                    oldItem.getPrice().equals(newItem.getPrice());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_rv_item, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductModal model = getProductAt(position);
        holder.Name.setText(model.getName());
        holder.Details.setText(model.getDetails());
        holder.Serial.setText(model.getSerial());
        holder.Expiery.setText(model.getExpiery());
        holder.Quentity.setText(model.getQuentity());
        holder.Price.setText(model.getPrice());
    }

    public ProductModal getProductAt(int position) {
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Name, Details, Serial, Expiery, Quentity, Price;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.name);
            Details = itemView.findViewById(R.id.details);
            Serial = itemView.findViewById(R.id.serial);
            Expiery = itemView.findViewById(R.id.expiery);
            Quentity = itemView.findViewById(R.id.quentity);
            Price = itemView.findViewById(R.id.price);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(ProductModal model);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}


