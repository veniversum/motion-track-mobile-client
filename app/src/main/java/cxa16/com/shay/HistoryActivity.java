package cxa16.com.shay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class HistoryActivity extends AppCompatActivity {

    ListView listView ;
    Spinner dropdown;
    ArrayAdapter<String> adapter;

    String[] allList;
    String[] allListFull;
    float[] allPrice;

    String[] foodList;
    String[] foodListFull;
    float[] foodPrice;

    String[] groceriesList;
    String[] groceriesListFull;
    float[] groceriesPrice;

    String[] apparelsList;
    String[] apparelsListFull;
    float[] apparelsPrice;

    String[] financialList;
    String[] financialListFull;
    float[] financialPrice;

    Context myContext;

    private ImageButton closeButton;

    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_history);

        myContext = this;

        closeButton = (ImageButton) findViewById(R.id.button_close);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });


        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);

        allList  = new String[] { "Dunkin' Donut",
                "Food Junction",
                "Tian Tian Chicken Pie",
                "NTUC",
                "Cold Strage",
                "Sports Link",
                "Forever 21",
                "GE Travel Insurance"
        };

        allListFull  = new String[] { "Dunkin' Donut - $13.50",
                "Food Junction - $6.70",
                "Tian Tian Chicken Pie - $3.8",
                "NTUC - $87.00",
                "Cold Strage - $12.10",
                "Sports Link - $96.85",
                "Forever 21 - $35.00",
                "GE Travel Insurance - $76.20"
        };

        allPrice = new float[] {
                13.50f,
                6.70f,
                3.8f,
                87.00f,
                12.10f,
                96.85f,
                35.00f,
                76.20f
        };

        // Defined Array values to show in ListView
        foodList = new String[] { "Dunkin' Donut",
                "Food Junction",
                "Tian Tian Chicken Pie"
        };

        foodListFull  = new String[] { "Dunkin' Donut - $13.50",
                "Food Junction - $6.70",
                "Tian Tian Chicken Pie - $3.8"
        };


        foodPrice = new float[] {
                13.50f,
                6.70f,
                3.8f
        };


        groceriesList = new String[] {
                "NTUC",
                "Cold Strage"
        };

        groceriesListFull  = new String[] {
                "NTUC - $87.00",
                "Cold Strage - $12.10"
        };


        groceriesPrice = new float[] {
                87.00f,
                12.10f
        };


        apparelsList = new String[] {
                "Sports Link",
                "Forever 21"
        };

        apparelsListFull  = new String[] {
                "Sports Link - $96.85",
                "Forever 21 - $35.00"
        };


        apparelsPrice = new float[] {
                96.85f,
                35.00f
        };


        financialList = new String[] {
                "GE Travel Insurance"
        };

        financialListFull  = new String[] {
                "GE Travel Insurance - $76.20"
        };


        financialPrice = new float[] {
                76.20f
        };

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        List<String> stringList = new ArrayList<String>(Arrays.asList(allListFull));

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, stringList);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
            }

        });

        dropdown = (Spinner)findViewById(R.id.spinner1);
        String[] items = new String[]{"All", "Food", "Groceries", "Apparels", "Financial Products"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(spinnerAdapter);


        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                //String  itemValue = (String) dropdown.getItemAtPosition(position);


                adapter.clear();

                String[] newValues;
                String[] newValuesFull;

                switch (itemPosition) {
                    case 0:
                        newValuesFull = allListFull;
                        newValues = allList;
                        break;
                    case 1:
                        newValuesFull = foodListFull;
                        newValues = foodList;
                        break;
                    case 2:
                        newValuesFull = groceriesListFull;
                        newValues = groceriesList;
                        break;
                    case 3:
                        newValuesFull = apparelsListFull;
                        newValues = apparelsList;
                        break;
                    case 4:
                        newValuesFull = financialListFull;
                        newValues = financialList;
                        break;
                    default:
                        newValuesFull = allListFull;
                        newValues = allList;
                        break;
                }
                List<String> stringList = new ArrayList<String>(Arrays.asList(newValues));
                adapter.addAll(stringList);
                adapter.notifyDataSetChanged();

                setData(newValues.length, 100, newValues);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        pieChart = (PieChart) findViewById(R.id.chart);
        pieChart.setUsePercentValues(true);
        pieChart.setDescription("");
        pieChart.setExtraOffsets(5, 10, 5, 5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);


        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);

        pieChart.setTransparentCircleColor(Color.WHITE);
        pieChart.setTransparentCircleAlpha(110);

        pieChart.setHoleRadius(58f);
        pieChart.setTransparentCircleRadius(61f);

        pieChart.setDrawCenterText(true);

        pieChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        pieChart.setRotationEnabled(true);
        pieChart.setHighlightPerTapEnabled(true);

        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
        //pieChart.setOnChartValueSelectedListener(this);

        String [] values = getCategoryList("All");
        setData(values.length, 100, values);

        pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);

        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setEntryLabelTextSize(10f);

        pieChart.getLegend().setEnabled(false);


    }

    private void setData(int count, float range, String[] values) {



        float mult = range;

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < count ; i++) {
            entries.add(new PieEntry((float) ((Math.random() * mult) + mult / 5), values[i % values.length]));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Election Results");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        // add a lot of colors

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
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.BLACK);
        pieChart.setData(data);

        // undo all highlights
        pieChart.highlightValues(null);

        pieChart.invalidate();
    }

    private String[] getCategoryList (String category) {

        String[] list1 = joinList(foodList, groceriesList);
        String[] list2 = joinList(list1, apparelsList);
        String[] list = joinList(list2, financialList);

        if (category.equals("Food")){
            list = foodList;
        } else if (category.equals("Groceries")){
            list = groceriesList;
        } else if (category.equals("Apparels")){
            list = apparelsList;
        } else if (category.equals("Financial Products")){
            list = financialList;
        }

        return list;

    }


    private float[] getCategoryPrice (String category) {

        float[] list1 = joinPrice(foodPrice, groceriesPrice);
        float[] list2 = joinPrice(list1, apparelsPrice);
        float[] list = joinPrice(list2, financialPrice);

        if (category.equals("Food")){
            list = foodPrice;
        } else if (category.equals("Groceries")){
            list = groceriesPrice;
        } else if (category.equals("Apparels")){
            list = apparelsPrice;
        } else if (category.equals("Financial Products")){
            list = financialPrice;
        }

        return list;

    }

    private String[] joinList(String[] A, String[] B) {
        int aLen = A.length;
        int bLen = B.length;
        String[] C= new String[aLen+bLen];
        System.arraycopy(A, 0, C, 0, aLen);
        System.arraycopy(B, 0, C, aLen, bLen);
        return C;
    }

    private float[] joinPrice(float[] A, float[] B) {
        int aLen = A.length;
        int bLen = B.length;
        float[] C= new float[aLen+bLen];
        System.arraycopy(A, 0, C, 0, aLen);
        System.arraycopy(B, 0, C, aLen, bLen);
        return C;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }


}
