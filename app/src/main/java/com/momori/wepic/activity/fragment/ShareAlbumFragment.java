package com.momori.wepic.activity.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.momori.wepic.R;
import com.momori.wepic.activity.card.SuggestedCard;
import it.gmariotti.cardslib.library.view.CardViewNative;

public class ShareAlbumFragment extends Fragment {

    private CardViewNative cardView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.demo_fragment_native_misc_card, container, false);
        cardView = (CardViewNative) view.findViewById(R.id.carddemo_suggested);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       initCard();
    }

    private void initCard() {
        cardView.setCard(new SuggestedCard(getActivity()));
    }
}
