package org.cs386group4.lumberjacknotes.controllers.adapters;

import android.app.SearchManager;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import org.cs386group4.lumberjacknotes.R;
import org.cs386group4.lumberjacknotes.models.Notes;
import org.cs386group4.lumberjacknotes.ui.MainActivity;

import java.util.Arrays;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>
{
    // Declares a list of all notes returned by the search by RecyclerView
    private final List<Notes> searchItems;

    /**
     * This constructor takes in a list of notes to display
     * @param searchItems list of notes to display
     */
    public SearchAdapter(@NonNull List<Notes> searchItems)
    {
        this.searchItems = searchItems;
    }

    @Override
    /**
     * Returns count of items in list
     */
    public int getItemCount()
    {
        return searchItems.size();
    }

    @Override
    /**
     * Displays the view for each item in the list
     */
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position)
    {
        // Get the MaterialTextView for the current ViewHolder item in the list
        MaterialTextView contentTextView = holder.getContentTextView();
        Notes currentNote = searchItems.get(position);

        holder.getNoteItemRoot().setOnClickListener(view ->
        {
            // Start MainActivity and send the current note index
            // TODO may need changes
            Intent intent = new Intent(holder.getNoteItemRoot().getContext(), MainActivity.class);
            intent.putExtra("note_position", position);
            holder.getNoteItemRoot().getContext().startActivity(intent);
        });

        contentTextView.setText(currentNote.getContent());
    }

    @NonNull
    @Override
    /**
     * Creates a view for each item in the list
     */
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        // Create new note item view for list item
        // TODO change the r.layout
        View noteItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_list_item, parent,
                false);

        return new ViewHolder(noteItem);
    }


    // Needs editing for SearchAdapter -------------------------------------------------------------------
    /**
     * Holds references to the user interface elements for a single item in the RecyclerView
     */
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private final View noteItemRoot;

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
            this.noteItemRoot = noteItemRoot;
            noteContent = noteItemRoot.findViewById(R.id.note_content);
        }

        /**
         * @return the content view of each note instance
         */
        @NonNull
        public MaterialTextView getContentTextView()
        {
            return noteContent;
        }

        /**
         * @return the root view of the layout
         */
        @NonNull
        public View getNoteItemRoot()
        {
            return noteItemRoot;
        }
    }
}
