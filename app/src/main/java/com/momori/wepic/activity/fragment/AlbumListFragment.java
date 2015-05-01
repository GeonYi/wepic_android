package com.momori.wepic.activity.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.momori.wepic.R;
import com.momori.wepic.activity.card.SuggestedCard;
import com.nhaarman.listviewanimations.appearance.AnimationAdapter;
import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;
import com.nhaarman.listviewanimations.appearance.simple.ScaleInAnimationAdapter;
import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;
import com.nhaarman.listviewanimations.appearance.simple.SwingLeftInAnimationAdapter;
import com.nhaarman.listviewanimations.appearance.simple.SwingRightInAnimationAdapter;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;

/**
 * Created by Hyeon on 2015-05-01.
 */
public class AlbumListFragment extends Fragment {

    private CardListView mListView;
    private CardArrayAdapter mCardArrayAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_album_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initCard();
    }

    /**
     * This method builds a simple list of cards
     */
    private void initCard() {

        //Init an array of Cards
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i = 0; i < 25; i++) {

            SuggestedCard card = new SuggestedCard(this.getActivity());

            //Only for test scope, use different titles and colors
            card.setTitle("A simple colored card " + i);
            cards.add(card);
        }

        //Set the adapter
        mCardArrayAdapter = new CardArrayAdapter(getActivity(), cards);

        mListView = (CardListView) getActivity().findViewById(R.id.cardlistview_album_list);
        if (mListView != null) {
            setAlphaAdapter();
        }
    }


    /**
     * Alpha animation
     */
    private void setAlphaAdapter() {
        AnimationAdapter animCardArrayAdapter = new AlphaInAnimationAdapter(mCardArrayAdapter);
        animCardArrayAdapter.setAbsListView(mListView);
        if (mListView != null) {
            mListView.setExternalAdapter(animCardArrayAdapter,mCardArrayAdapter);
        }
    }

    /**
     * Left animation
     */
    private void setLeftAdapter() {
        AnimationAdapter animCardArrayAdapter = new SwingLeftInAnimationAdapter(mCardArrayAdapter);
        animCardArrayAdapter.setAbsListView(mListView);
        if (mListView != null) {
            mListView.setExternalAdapter(animCardArrayAdapter,mCardArrayAdapter);
        }
    }

    /**
     * Right animation
     */
    private void setRightAdapter() {
        AnimationAdapter animCardArrayAdapter = new SwingRightInAnimationAdapter(mCardArrayAdapter);
        animCardArrayAdapter.setAbsListView(mListView);
        if (mListView != null) {
            mListView.setExternalAdapter(animCardArrayAdapter,mCardArrayAdapter);
        }
    }

    /**
     * Bottom animation
     */
    private void setBottomAdapter() {
        AnimationAdapter animCardArrayAdapter = new SwingBottomInAnimationAdapter(mCardArrayAdapter);
        animCardArrayAdapter.setAbsListView(mListView);
        mListView.setExternalAdapter(animCardArrayAdapter,mCardArrayAdapter);
    }

    /**
     * Bottom-right animation
     */
    private void setBottomRightAdapter() {
        AnimationAdapter animCardArrayAdapter = new SwingBottomInAnimationAdapter(new SwingRightInAnimationAdapter(mCardArrayAdapter));
        animCardArrayAdapter.setAbsListView(mListView);
        if (mListView != null) {
            mListView.setExternalAdapter(animCardArrayAdapter,mCardArrayAdapter);
        }
    }

    /**
     * Scale animation
     */
    private void setScaleAdapter() {
        AnimationAdapter animCardArrayAdapter = new ScaleInAnimationAdapter(mCardArrayAdapter);
        animCardArrayAdapter.setAbsListView(mListView);
        if (mListView != null) {
            mListView.setExternalAdapter(animCardArrayAdapter,mCardArrayAdapter);
        }
    }
}
