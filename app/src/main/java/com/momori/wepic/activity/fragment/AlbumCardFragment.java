package com.momori.wepic.activity.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.momori.wepic.R;
import com.momori.wepic.activity.MainActivity;
import com.momori.wepic.activity.card.AlbumCard;
import com.momori.wepic.model.AlbumModel;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardGridArrayAdapter;
import it.gmariotti.cardslib.library.view.CardGridView;


/**
 * Created by Hyeon on 2015-05-01.
 */
public class AlbumCardFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cardgridview, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initCards();
    }


    private void initCards() {

        ArrayList<Card> cards = new ArrayList<>();

        List<AlbumModel> album_list = getAlbumModel_list();
        for (AlbumModel album : album_list) {
            AlbumCard card = new AlbumCard(getActivity(), album);
            cards.add(card);
        }

        CardGridArrayAdapter mCardArrayAdapter = new CardGridArrayAdapter(getActivity(), cards);

        CardGridView listView = (CardGridView) getActivity().findViewById(R.id.cardgridview);
        listView.setAdapter(mCardArrayAdapter);
    }

    private List<AlbumModel> getAlbumModel_list(){
        Activity activity = getActivity();
        if(activity instanceof MainActivity){
            return ((MainActivity)activity).presenter.getAlbum_list();
        }else{
            return new ArrayList<>();
        }
    }
}
