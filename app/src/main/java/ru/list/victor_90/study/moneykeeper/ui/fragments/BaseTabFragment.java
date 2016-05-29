package ru.list.victor_90.study.moneykeeper.ui.fragments;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import ru.list.victor_90.study.moneykeeper.R;
import ru.list.victor_90.study.moneykeeper.rest.networks.ServiceBroker;
import ru.list.victor_90.study.moneykeeper.ui.activities.LoginActivity;

public class BaseTabFragment extends Fragment {

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_logout, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_logout:{
                ServiceBroker.getInstance().logout();
                LoginActivity.show(getActivity());
                getActivity().finish();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setHasOptionsMenu(true);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        setHasOptionsMenu(false);
    }
}
