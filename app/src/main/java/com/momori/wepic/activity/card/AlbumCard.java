package com.momori.wepic.activity.card;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.momori.wepic.R;
import com.momori.wepic.model.AlbumModel;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.CardThumbnail;

/**
 * Created by Hyeon on 2015-05-02.
 */
public class AlbumCard extends Card{

    private AlbumModel album;

    private int resourceIdThumb = -1;

    public AlbumCard(Context context, AlbumModel album) {
        this(context, R.layout.album_card_content, album);
    }

    public AlbumCard(Context context, int innerLayout, AlbumModel album) {
        super(context, innerLayout);
        this.album = album;
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view){

        CardHeader header = new CardHeader(getContext(), R.layout.album_card_header);
        header.setButtonOverflowVisible(true);
        header.setTitle(album.getAlbum_name());
        addCardHeader(header);

        CardThumbnail thumbnail = new CardThumbnail(getContext());
        if(resourceIdThumb > -1){
            thumbnail.setDrawableResource(resourceIdThumb);
        }else{
            thumbnail.setDrawableResource(R.drawable.ic_autorenew_white_24dp);
        }
        addCardThumbnail(thumbnail);
        setOnClickListener(new OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                Toast.makeText(getContext(), album.getAlbum_name(), Toast.LENGTH_SHORT).show();
            }
        });


        TextView createdateView = (TextView)parent.findViewById(R.id.album_card_content_createdate);
        createdateView.setText(album.getInit_create_datetime().toString());

        TextView usersView = (TextView)parent.findViewById(R.id.album_card_content_users);
        usersView.setText(album.getUsers_name());

    }
}
