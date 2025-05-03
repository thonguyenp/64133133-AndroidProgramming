package com.example.finalproject_basicjaplearning;

import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class KanjiAdapter extends RecyclerView.Adapter<KanjiAdapter.KanjiViewHolder> {

    Context context;

    private List<KanjiBank> kanjiList;
    private TextToSpeech tts = null;



    public KanjiAdapter(Context context, List<KanjiBank> kanjiList, TextToSpeech tts) {
        this.context = context;
        this.kanjiList = kanjiList;
        this.tts = tts;
    }
    public void updateData(List<KanjiBank> newList) {
        this.kanjiList = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public KanjiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View vItem = inflater.inflate(R.layout.item_kanji,parent,false);
        KanjiViewHolder holder = new KanjiViewHolder(vItem);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull KanjiViewHolder holder, int position) {
        KanjiBank kanji = kanjiList.get(position);
        holder.tvKanji.setText(kanji.kanji);
        holder.tvHanViet.setText("Hán Việt: " + kanji.hanViet);
        holder.tvMeaning.setText("Nghĩa: " + kanji.meaning);
        holder.tvOnReading.setText("Âm On: " + kanji.onReading);
        holder.tvKunReading.setText("Âm Kun: " + kanji.kunReading);
    }

    @Override
    public int getItemCount() {
        return kanjiList.size();
    }



    class KanjiViewHolder extends RecyclerView.ViewHolder {
        TextView tvKanji, tvHanViet, tvMeaning, tvOnReading, tvKunReading;
        ImageButton btnSpeak;

        public KanjiViewHolder(@NonNull View itemView) {
            super(itemView);
            tvKanji = itemView.findViewById(R.id.tvKanji);
            tvHanViet = itemView.findViewById(R.id.tvHanViet);
            tvMeaning = itemView.findViewById(R.id.tvMeaning);
            tvOnReading = itemView.findViewById(R.id.tvOnReading);
            tvKunReading = itemView.findViewById(R.id.tvKunReading);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    KanjiBank selectedKanji = kanjiList.get(getAdapterPosition());
                    String kanji = selectedKanji.kanji;
                    String hanViet = selectedKanji.hanViet;
                    String meaning = selectedKanji.meaning;
                    String onReading = selectedKanji.onReading;
                    String kunReading = selectedKanji.kunReading;

                    Intent iSub = new Intent(itemView.getContext(), SubKanjiActivity.class);

                    iSub.putExtra("kanji",kanji);
                    iSub.putExtra("hanViet",hanViet);
                    iSub.putExtra("meaning",meaning);
                    iSub.putExtra("onReading",onReading);
                    iSub.putExtra("kunReading",kunReading);

                    itemView.getContext().startActivity(iSub);
                }
            });
        }


    }
}
