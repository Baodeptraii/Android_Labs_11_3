package com.example.labs_11_3;
//
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {

    private List<Room> roomList;
    private OnRoomActionListener listener;

    public interface OnRoomActionListener {
        void onEdit(Room room, int position);
        void onDelete(Room room, int position);
        void onView(Room room, int position);
    }

    public RoomAdapter(List<Room> roomList, OnRoomActionListener listener) {
        this.roomList = roomList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        Room room = roomList.get(position);
        holder.tvRoomName.setText(room.getRoomName());
        holder.tvRoomId.setText("Room ID: " + room.getRoomId());
        holder.tvRoomPrice.setText("$" + (int)room.getPrice() + "/month");
        
        if (room.isOccupied()) {
            holder.tvTenantName.setVisibility(View.VISIBLE);
            holder.tvPhoneNumber.setVisibility(View.VISIBLE);
            holder.tvTenantName.setText("Tenant: " + room.getTenantName());
            holder.tvPhoneNumber.setText("Phone: " + room.getPhoneNumber());
            
            holder.tvStatusBadge.setText("Occupied");
            setStatusBadgeColor(holder.tvStatusBadge, "#E57373");
        } else {
            holder.tvTenantName.setVisibility(View.GONE);
            holder.tvPhoneNumber.setVisibility(View.GONE);
            
            holder.tvStatusBadge.setText("Available");
            setStatusBadgeColor(holder.tvStatusBadge, "#81C784");
        }

        holder.btnEdit.setOnClickListener(v -> listener.onEdit(room, position));
        holder.btnDelete.setOnClickListener(v -> listener.onDelete(room, position));
        holder.btnView.setOnClickListener(v -> listener.onView(room, position));
    }

    private void setStatusBadgeColor(TextView textView, String colorStr) {
        GradientDrawable shape = new GradientDrawable();
        shape.setCornerRadius(20);
        shape.setColor(Color.parseColor(colorStr));
        textView.setBackground(shape);
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    public static class RoomViewHolder extends RecyclerView.ViewHolder {
        TextView tvRoomName, tvRoomId, tvRoomPrice, tvTenantName, tvPhoneNumber, tvStatusBadge;
        ImageView btnView, btnEdit, btnDelete;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRoomName = itemView.findViewById(R.id.tvRoomName);
            tvRoomId = itemView.findViewById(R.id.tvRoomId);
            tvRoomPrice = itemView.findViewById(R.id.tvRoomPrice);
            tvTenantName = itemView.findViewById(R.id.tvTenantName);
            tvPhoneNumber = itemView.findViewById(R.id.tvPhoneNumber);
            tvStatusBadge = itemView.findViewById(R.id.tvStatusBadge);
            btnView = itemView.findViewById(R.id.btnView);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
