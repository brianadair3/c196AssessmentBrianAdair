package com.example.c196assessmentbrianadair;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TermsAdapter extends RecyclerView.Adapter<TermsAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView termTitle;
        public ImageView editBtn;
        public ImageView delBtn;
        public ViewHolder(View termView) {
            super(termView);
            termTitle = termView.findViewById(R.id.item_term_view);
            editBtn = termView.findViewById(R.id.item_term_edit);
            delBtn = termView.findViewById(R.id.item_term_delete);
        }
    }
    private List<Term> terms;
    public TermsAdapter(List<Term> nTerms) {
        terms = nTerms;
    }

    @Override
    public TermsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View termsView = inflater.inflate(R.layout.item_term, parent, false);
        ViewHolder viewHolder = new ViewHolder(termsView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TermsAdapter.ViewHolder viewHolder, int position) {
        Term term = terms.get(position);
        TextView termTitle = viewHolder.termTitle;
        termTitle.setText(term.getTitle());
    }

    @Override
    public int getItemCount() {
        return terms.size();
    }

    public void setTerms(List<Term> terms) {
        this.terms = terms;
        notifyDataSetChanged();
    }
}
