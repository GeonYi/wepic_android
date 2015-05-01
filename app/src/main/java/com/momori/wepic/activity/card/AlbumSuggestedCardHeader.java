package com.momori.wepic.activity.card;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.momori.wepic.R;
import com.momori.wepic.model.AlbumModel;

import it.gmariotti.cardslib.library.internal.CardHeader;

/**
 * Created by Hyeon on 2015-05-02.
 */
class AlbumSuggestedCardHeader extends CardHeader {

    private AlbumModel cardAlbum;

    public AlbumSuggestedCardHeader(Context context, AlbumModel album) {
        this(context, R.layout.suggestedcard_album_header , album);
    }

    public AlbumSuggestedCardHeader(Context context, int innerLayout, AlbumModel album) {
        super(context, innerLayout);
        this.cardAlbum = album;
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