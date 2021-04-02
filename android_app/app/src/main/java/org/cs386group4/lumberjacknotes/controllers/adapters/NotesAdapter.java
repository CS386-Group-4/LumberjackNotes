package org.cs386group4.lumberjacknotes.controllers.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import org.cs386group4.lumberjacknotes.R;
import org.cs386group4.lumberjacknotes.models.Notes;

import java.util.Arrays;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder>
{
    private final List<Notes> notesList;

    public NotesAdapter(@NonNull List<Notes> notesList)
    {
        this.notesList = notesList;
    }

    public NotesAdapter(@NonNull Notes[] notesList)
    {
        this.notesList = Arrays.asList(notesList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        // Create new note item view for list item
        View noteItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_list_item, parent,
                false);

        return new ViewHolder(noteItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position)
    {
        // Get the MaterialTextView for the current ViewHolder item in the list
        MaterialTextView contentTextView = holder.getContentTextView();

        Notes currentNotes = notesList.get(position);

        contentTextView.setText("Note item #" + position);
    }

    @Override
    public int getItemCount()
    {
        return notesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private final MaterialTextView noteContent;

        public ViewHolder(@NonNull View noteItemRoot)
        {
            super(noteItemRoot);

            noteContent = noteItemRoot.findViewById(R.id.note_content);
        }

        @NonNull
        public MaterialTextView getContentTextView()
        {
            return noteContent;
        }
    }
}
