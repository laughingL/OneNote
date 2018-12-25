package com.laughing.onenote;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static com.laughing.onenote.MenuActivity.result;
import static com.laughing.onenote.MenuActivity.result;

/**
 * Created by Nick on 2018/12/20.
 */

public class ListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private NoteAdapter mAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        mRecyclerView = view.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<Note> noteList = new ArrayList<>();
        for (int i=0; i<100; i++) {
            Note note = new Note();
            note.setTitle(i+" -note");
            note.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            note.setType(String.valueOf(result));//可以判断result，来按需查询
            noteList.add(note);
        }
        mAdapter = new NoteAdapter(noteList);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }


    private class NoteHolder extends RecyclerView.ViewHolder {

        private TextView titletext;
        private TextView datetext;
        private TextView typetext;

        private Note mNote;

        public void bind(Note note) {
            mNote = note;
            titletext.setText(mNote.getTitle());
            datetext.setText(mNote.getDate());
            typetext.setText(mNote.getType());
        }

        public NoteHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_fragment_item, parent, false));

            titletext = itemView.findViewById(R.id.note_title);
            datetext = itemView.findViewById(R.id.note_date);
            typetext = itemView.findViewById(R.id.note_type);

        }
    }

    private class NoteAdapter extends RecyclerView.Adapter<NoteHolder> {

        private List<Note> mNotes;

        public NoteAdapter(List<Note> list) {
            mNotes = list;
        }

        @Override
        public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new NoteHolder(layoutInflater, parent);
        }



        @Override
        public void onBindViewHolder(NoteHolder holder, int position) {

            Note note = mNotes.get(position);
            holder.bind(note);
        }


        @Override
        public int getItemCount() {
            return mNotes.size();
        }
    }
}
