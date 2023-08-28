package com.example.glucontrolapp.DatabaseUtils;

import android.icu.util.Measure;

public interface AdapterListener {
    void onUpdate(Measures measures);
    void onDelete(int id, int pos);

}
