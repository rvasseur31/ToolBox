package com.example.toolbox.seekBarPreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Build;
import android.preference.Preference;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.toolbox.R;

import java.util.Locale;

public class SeekBarPreference extends Preference implements SeekBar.OnSeekBarChangeListener {

    private static final int DEFAULT_CURRENT_VALUE = 5;
    private static final int DEFAULT_MAX_VALUE = 10;

    private TextView textValue, textMeasurement, textTitle, textSummery;
    SeekBar seekBar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SeekBarPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public SeekBarPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SeekBarPreference, defStyleAttr, 0);
        final int interval = a.getInteger(R.styleable.SeekBarPreference_rvs_interval_float, 1);
        final int minValue = a.getInteger(R.styleable.SeekBarPreference_rvs_minValue, 0);
        final int maxValue = a.getInteger(R.styleable.SeekBarPreference_rvs_maxValue, 5);
        final int defaultValue = a.getInteger(R.styleable.SeekBarPreference_rvs_view_defaultValue_float, 2);
        final String Title = a.getString(R.styleable.SeekBarPreference_rvs_view_title);
        final String Summery = a.getString(R.styleable.SeekBarPreference_rvs_view_summary);
        final String Unit = a.getString(R.styleable.SeekBarPreference_rvs_measurementUnit);

        textTitle.setText(Title);
        textSummery.setText(Summery);
        textMeasurement.setText(Unit);
        seekBar.setMax(maxValue * 10);
        seekBar.setProgress(defaultValue * 10);
        a.recycle();
    }

    public SeekBarPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SeekBarPreference(Context context) {
        super(context);
    }

    @Override
    protected View onCreateView(ViewGroup parent) {
        super.onCreateView(parent);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.seekbar, parent, false);
        textValue = view.findViewById(R.id.seekbar_value);
        textMeasurement = view.findViewById(R.id.measurement_unit);
        textTitle = view.findViewById(R.id.Title);
        textSummery = view.findViewById(R.id.Summary);
        seekBar = view.findViewById(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(this);


        SharedPreferences preferences = getSharedPreferences();
        int value = preferences.getInt(getKey(), 0);

        textTitle.setText("");
        textSummery.setText("");
        textValue.setText(value);
        textMeasurement.setText("");
        seekBar.setMax(DEFAULT_MAX_VALUE);

        return view;

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        textValue.setText(i);
        SharedPreferences.Editor editor = getEditor();
        editor.putInt(getKey(), i);
        editor.apply();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

}
