package ru.list.victor_90.study.moneykeeper.ui.fragments.tabs;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import ru.list.victor_90.study.moneykeeper.R;
import ru.list.victor_90.study.moneykeeper.database.BaseDBHelper;
import ru.list.victor_90.study.moneykeeper.database.models.ReportPayment;
import ru.list.victor_90.study.moneykeeper.ui.fragments.BaseTabFragment;

public class TabFragment2 extends BaseTabFragment implements OnChartValueSelectedListener {

    private static PieChart mChart;
    private static BaseDBHelper helper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab2_pie_chart, container, false);

        helper = new BaseDBHelper(getContext());

        mChart = (PieChart) view.findViewById(R.id.pie_chart);
        mChart.setUsePercentValues(true);
        mChart.setExtraOffsets(5, 10, 5, 5);
        mChart.setDescription(getString(R.string.pie_description));
        mChart.setRotationEnabled(false);

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.WHITE);

        mChart.setTransparentCircleColor(Color.WHITE);

        mChart.setHoleRadius(30f);
        mChart.setTransparentCircleRadius(35f);

        mChart.setDrawCenterText(true);

        mChart.setRotationAngle(0);

        mChart.setHighlightPerTapEnabled(true);

        mChart.setOnChartValueSelectedListener(this);


        setData(helper.getCategoryCapacity(), 100);

        mChart.animateY(2200, Easing.EasingOption.EaseInOutElastic);

        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        return view;
    }

    public static void setData(int count, float range) {
        ArrayList<ReportPayment> paymentValues = helper.getPaymentValuesByCategory();

        if (paymentValues != null) {
            count = paymentValues.size();
            ArrayList<Entry> noteValues = new ArrayList<Entry>();
            for (int i = 0; i < count; i++) {
                noteValues.add(new Entry(paymentValues.get(i).getCount(), i));
            }

            ArrayList<String> noteLabels = new ArrayList<String>();
            for (int i = 0; i < count; i++) {
                noteLabels.add(paymentValues.get(i).getCategory());
            }

            PieDataSet dataSet = new PieDataSet(noteValues, "");
            dataSet.setSliceSpace(3f);
            dataSet.setSelectionShift(5f);

            ArrayList<Integer> colors = new ArrayList<Integer>();

            for (int c : ColorTemplate.VORDIPLOM_COLORS)
                colors.add(c);

            for (int c : ColorTemplate.JOYFUL_COLORS)
                colors.add(c);

            for (int c : ColorTemplate.COLORFUL_COLORS)
                colors.add(c);

            for (int c : ColorTemplate.LIBERTY_COLORS)
                colors.add(c);

            for (int c : ColorTemplate.PASTEL_COLORS)
                colors.add(c);

            colors.add(ColorTemplate.getHoloBlue());
            dataSet.setColors(colors);

            PieData data = new PieData(noteLabels, dataSet);
            data.setValueFormatter(new PercentFormatter());
            data.setValueTextSize(11f);
            data.setValueTextColor(Color.BLACK);

            mChart.setData(data);
            mChart.highlightValues(null);
            mChart.invalidate();
        }
    }

    @Override
    public void onValueSelected(Entry entry, int dataSetIndex, Highlight highlight) {
        if (entry == null) {
            return;
        }
        mChart.setCenterText("всего: " + entry.getVal());
    }

    public static void invalidate() {
        setData(0, 0);
    }

    @Override
    public void onNothingSelected() {
        mChart.setCenterText("");
    }
}