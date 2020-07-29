package com.example.notes;

import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {
    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        LinearLayout contianerView;
        TextView textView;
        NoteViewHolder(View view) {
            super(view);
            contianerView = view.findViewById(R.id.noterow);
            textView = view.findViewById(R.id.noterowTxt);
            contianerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Note current = (Note) contianerView.getTag();
                    Intent intent = new Intent(view.getContext(), NoteActivity.class);
                    intent.putExtra("id", current.id);
                    intent.putExtra("contents", current.contents);
                    view.getContext().startActivity(intent);
                }
            });
        }
    }
    private List<Note> notes = new ArrayList<>();
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.noterow, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note current = notes.get(position);
        holder.textView.setText(current.contents);
        holder.contianerView.setTag(current);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
    public void reload() {
        notes = MainActivity.database.noteDao().getAllNotes();
        notifyDataSetChanged();
    }
}
