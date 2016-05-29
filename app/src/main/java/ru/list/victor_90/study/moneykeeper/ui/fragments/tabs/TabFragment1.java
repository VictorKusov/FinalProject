package ru.list.victor_90.study.moneykeeper.ui.fragments.tabs;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.list.victor_90.study.moneykeeper.R;
import ru.list.victor_90.study.moneykeeper.constants.Constants;
import ru.list.victor_90.study.moneykeeper.database.BaseDBHelper;
import ru.list.victor_90.study.moneykeeper.database.models.Category;
import ru.list.victor_90.study.moneykeeper.ui.fragments.BaseTabFragment;
import ru.list.victor_90.study.moneykeeper.ui.fragments.PaymentDialogFragment;
import ru.list.victor_90.study.moneykeeper.ui.fragments.listeners.OnItemClickListener;
import ru.list.victor_90.study.moneykeeper.ui.fragments.adapters.RVAdapter;

public class TabFragment1 extends BaseTabFragment {

    private RecyclerView recyclerView;
    private View view;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab1_categories, container, false);

        buildRecycleView();
        return view;
    }

    private void buildRecycleView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_items);
        recyclerView.setHasFixedSize(true);

        BaseDBHelper helper = new BaseDBHelper(getContext());
        final List<Category> categories = helper.getCategoryFromDatabase();

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), Constants.ITEMS_GRID_COLUMNS_COUNT);
        recyclerView.setLayoutManager(layoutManager);

        RVAdapter adapter = new RVAdapter(categories, new OnItemClickListener() {
            @Override
            public void onItemClick(Category category) {
                addNote(category);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void addNote(Category category) {
        // диалог для добавления записи об затратах
        DialogFragment paymentDialogFragment = new PaymentDialogFragment();

        // закидываем данные об категории
        Bundle bundle = new Bundle();
        bundle.putString("category", category.getCategory());
        bundle.putInt("iconId", category.getId());

        // помещаем данные в контейнер и запускаем
        paymentDialogFragment.setArguments(bundle);
        paymentDialogFragment.show(getFragmentManager(), category.getCategory());
    }
}