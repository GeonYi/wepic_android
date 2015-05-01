package com.momori.wepic.activity.card;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.momori.wepic.R;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.CardThumbnail;

/**
 * Created by Hyeon on 2015-05-01.
 */
public class AlbumSuggestedCard extends Card {

    public AlbumSuggestedCard(Context context) {
        this(context, R.layout.suggesstedcard_album_content);
    }

    public AlbumSuggestedCard(Context context, int innerLayout) {
        super(context, innerLayout);
        init();
    }

    private void init() {

        //Add a header
        SuggestedCardHeader header = new SuggestedCardHeader(getContext());
        addCardHeader(header);

        //Set click listener
        setOnClickListener(new OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                Toast.makeText(getContext(), "Click listener", Toast.LENGTH_LONG).show();
            }
        });

        //Set swipe on
        setSwipeable(true);

        //Add thumbnail
        CardThumbnail thumb = new SuggestedCardThumb(getContext());
        thumb.setUrlResource("https://lh5.googleusercontent.com/-N8bz9q4Kz0I/AAAAAAAAAAI/AAAAAAAAAAs/Icl2bQMyK7c/s265-c-k-no/photo.jpg");
//        thumb.setErrorResource(R.drawable.ic_error_loadingorangesmall);
        addCardThumbnail(thumb);
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        if (view != null) {
            TextView title = (TextView) view.findViewById(R.id.suggestedcard_album_content_title);
            TextView member = (TextView) view.findViewById(R.id.suggestedcard_album_content_member);
            TextView subtitle = (TextView) view.findViewById(R.id.suggestedcard_album_content_subtitle);

            if (title != null)
                title.setText("album name");

            if (member != null)
                //todo : get member
                member.setText("5");

            if (subtitle != null)
                //todo : album name
                subtitle.setText("aaaaa");
        }
    }


}

class AlbumSuggestedCardHeader extends CardHeader {

    public AlbumSuggestedCardHeader(Context context) {
        this(context, R.layout.suggestedcard_album_header);
    }

    public AlbumSuggestedCardHeader(Context context, int innerLayout) {
        super(context, innerLayout);
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        if (view != null) {
            TextView textView = (TextView) view.findViewById(R.id.suggested_album_header_title);

            if (textView != null) {
                textView.setText(R.string.card_shard_title);
            }
        }
    }
}

class AlbumSuggestedCardThumb extends CardThumbnail {

    public AlbumSuggestedCardThumb(Context context) {
        super(context);
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View viewImage) {
        if (viewImage != null) {

            if (parent!=null && parent.getResources()!=null){
                DisplayMetrics metrics=parent.getResources().getDisplayMetrics();

                int base = 100;

                if (metrics!=null){
                    viewImage.getLayoutParams().width = (int)(base*metrics.density);
                    viewImage.getLayoutParams().height = (int)(base*metrics.density);
                }else{
                    viewImage.getLayoutParams().width = 200;
                    viewImage.getLayoutParams().height = 200;
                }
            }
        }
    }
}
