package com.momori.wepic.activity.card;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import com.momori.wepic.model.AlbumModel;

import it.gmariotti.cardslib.library.internal.CardThumbnail;

/**
 * Created by Hyeon on 2015-05-02.
 */
class AlbumSuggestedCardThumb extends CardThumbnail {

    private AlbumModel cardAlbum;

    public AlbumSuggestedCardThumb(Context context, AlbumModel album) {
        super(context);
        this.cardAlbum = album;
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