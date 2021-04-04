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
    /**
     * notesList is a list of all notes created by the user that will be searched.
     * by RecyclerView.
     */
    private final List<Notes> notesList;

    /**
     * Constructor that assigns the local notesLists to the users notesList so that
     * RecyclerView can retrieve the relevant data.
     * @param notesList - the users note list to be searched.
     */
    public NotesAdapter(@NonNull List<Notes> notesList)
    {
        this.notesList = notesList;
    }

    /**
     * Second Constructor that could potentially be implemented to handle
     * a different kind of notesList data structure.
     * @param notesList
     */
    public NotesAdapter(@NonNull Notes[] notesList)
    {
        this.notesList = Arrays.asList(notesList);
    }

    @Override
    public int getItemCount()
    {
        return notesList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position)
    {
        // Get the MaterialTextView for the current ViewHolder item in the list
        MaterialTextView contentTextView = holder.getContentTextView();

        Notes currentNotes = notesList.get(position);

        contentTextView.setText("Note item #" + position);
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

    /**
     * Holds references to the user interface elements for a single item in the RecyclerView
     */
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        /**
         * Variable that stores notes content in a way that can manipulated by RecyclerView
         */
        private final MaterialTextView noteContent;

        /**
         * Constructor that calls the superclass RecyclerView. Creates a ViewHolder Object and initializes
         * the contents of the notes to a viewHolder item. 
         * @param noteItemRoot - the individual note object that is to be displayed.
         */
        public ViewHolder(@NonNull View noteItemRoot)
        {
            super(noteItemRoot);

            noteContent = noteItemRoot.findViewById(R.id.note_content);
        }

        @NonNull
        /**
         * getContentTextView() returns the content of each note instance
         */
        public MaterialTextView getContentTextView()
        {
            return noteContent;
        }
    }
}
