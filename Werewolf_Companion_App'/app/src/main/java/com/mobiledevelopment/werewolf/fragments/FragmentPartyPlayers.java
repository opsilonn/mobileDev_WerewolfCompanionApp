package com.mobiledevelopment.werewolf.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobiledevelopment.werewolf.R;
import com.mobiledevelopment.werewolf.activities.ActivityParty;
import com.mobiledevelopment.werewolf.adapters.AdapterPlayer;
import com.mobiledevelopment.werewolf.model.Player;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPartyPlayers extends Fragment
{
    private ActivityParty parentActivity;
    private RecyclerView recyclerView;


    /**
     * Constructor of the class
     * @param parentActivity Reference to the Activity
     */
    public FragmentPartyPlayers(final ActivityParty parentActivity)
    {
        this.parentActivity = parentActivity;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_party_players, container, false);
    }


    @Override
    public void onResume()
    {
        super.onResume();


        // We get the Recycler View
        recyclerView = parentActivity.findViewById(R.id.PartyRvPlayer);

        // We set the widgets
        setSwitch();
        setRecyclerView();
    }


    /**
     * Sets the Switch managing whether or not to display crucial data about the {@link Player}
     */
    private void setSwitch()
    {
        // We get the widget reference
        Switch switchHide = parentActivity.findViewById(R.id.PartyPlayerSwitchHide);

        // We set the Switch accordingly
        switchHide.setChecked(parentActivity.party.isDataHidden());

        // We add a listener
        switchHide.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // We change the value
            parentActivity.party.setDataHidden(isChecked);

            // We set the RecyclerView accordingly
            setRecyclerView();
        });
    }


    /**
     * Sets the RecyclerView displaying the {@link Player} accordingly
     */
    private void setRecyclerView()
    {
        // We instantiate a RecyclerView Adapter
        AdapterPlayer adapter = new AdapterPlayer(parentActivity, parentActivity.party);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(parentActivity));
    }
}
