package com.durga.balaji66.ganeshmanagement.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.durga.balaji66.ganeshmanagement.Models.Game;
import com.durga.balaji66.ganeshmanagement.R;

import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {

    private List<Game> playerList;
    private Context mCtx;

    public GameAdapter(List<Game> messages, Context mCtx) {
        this.playerList = messages;
        this.mCtx = mCtx;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_games, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Game game = playerList.get(position);
        holder.mName.setText(game.getCandidate_name());
        holder.mFatherName.setText(game.getFather_name());
        if((position % 2 == 0))
        {
            holder.mCardView.setCardBackgroundColor(ContextCompat.getColor(mCtx, R.color.colorLiteCyan));
        }
        else {
            holder.mCardView.setCardBackgroundColor(ContextCompat.getColor(mCtx, R.color.colorWhite));
        }

    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView mName;
        TextView mFatherName;
        private CardView mCardView;

        ViewHolder(View itemView) {
            super(itemView);

            mName = itemView.findViewById(R.id.textViewPlayerName);
            mFatherName = itemView.findViewById(R.id.textViewFatherName);
            mCardView =itemView.findViewById(R.id.cardView);
        }
    }

}
