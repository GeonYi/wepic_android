package com.momori.wepic.activity.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.momori.wepic.R;
import com.momori.wepic.activity.card.SuggestedCard;
import it.gmariotti.cardslib.library.view.CardViewNative;

public class FragmentShareAlbum extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.demo_fragment_native_misc_card, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initCards();
    }

    private void initCards() {
        initCardSuggested();
    }

    /**
     * Builds a Material Card with Large and small icons as supplemental actions
     */
    /**
     * This method builds a suggested card example
     */
    private void initCardSuggested() {

        SuggestedCard card = new SuggestedCard(getActivity());
        CardViewNative cardView = (CardViewNative) getActivity().findViewById(R.id.carddemo_suggested);
        cardView.setCard(card);
    }
}
