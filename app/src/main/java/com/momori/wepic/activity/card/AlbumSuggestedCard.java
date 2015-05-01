package com.momori.wepic.activity.card;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.momori.wepic.R;
import com.momori.wepic.model.AlbumModel;

import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.CardThumbnail;

/**
 * Created by Hyeon on 2015-05-01.
 */
public class AlbumSuggestedCard extends Card {

    private AlbumModel cardAlbum;

    public AlbumSuggestedCard(Context context, AlbumModel album) {
        this(context, R.layout.suggesstedcard_album_content, album);
    }

    public AlbumSuggestedCard(Context context, int innerLayout, AlbumModel album) {
        super(context, innerLayout);
        this.cardAlbum = album;
        init();
    }

    private void init() {

        //Add a header
        CardHeader header = new AlbumSuggestedCardHeader(getContext(), this.cardAlbum);
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




