package com.laughing.onenote;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.laughing.onenote.MenuActivity.*;


/**
 * Created by Nick on 2018/12/20.
 */

public class ListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private NoteAdapter mAdapter;
    private int mTypeCode;
    private static final String SEARCH_RESULT = "result";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        Intent intent = getActivity().getIntent();
        List<Note> noteList = NoteLab.get(getActivity()).getNotes();
        //检查来自SearchActivity传过来的searchList
        List<Note> searchList = (List<Note>) intent.getSerializableExtra(SEARCH_RESULT);
        if (searchList != null)
            noteList = searchList;
        mTypeCode = intent.getIntExtra(TYPE_CODE,0);
        List<Note> resultList = GetNoteList(noteList,mTypeCode);
        mRecyclerView = view.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new NoteAdapter(resultList);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    private List<Note> GetNoteList(List<Note> noteList, int typeCode) {
        List<Note> resultList;
        switch (typeCode){
            case TYPE_ALL:
                resultList = noteList;
                return resultList;
            case TYPE_WORK:
                resultList = GetResultList(noteList,"工作");
                return resultList;
            case TYPE_LIFE:
                resultList  = GetResultList(noteList,"生活");
                return resultList;
            case TYPE_OTHERS:
                resultList = GetResultList(noteList,"其他");
                return resultList;
            default:
                return noteList;
        }
    }

    private List<Note> GetResultList(List<Note> noteList, String type) {
        List<Note> resultList = new ArrayList<>();
        for (Note note : noteList){
            if (type.equals(note.getType()))
                resultList.add(note);
        }
        return resultList;
    }


    private class NoteHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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
            itemView.setOnClickListener(this);
            titletext = itemView.findViewById(R.id.note_title);
            datetext = itemView.findViewById(R.id.note_date);
            typetext = itemView.findViewById(R.id.note_type);

        }


        @Override
        public void onClick(View v) {
            Intent intent = AddActivity.newIntent(getActivity(),mNote.getId());
            startActivity(intent);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_note_list,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.new_note_bar:
                Note note = new Note();
                NoteLab.get(getActivity()).addNote(note);
                Intent intent1 = AddActivity.newIntent(getActivity(),note.getId());
                startActivity(intent1);
                return true;
            case R.id.search_note_bar:
                Intent intent2 = new Intent(getActivity(),SearchActivity.class);
                startActivity(intent2);
                return true;
             default: return super.onOptionsItemSelected(item);
        }

    }

}
