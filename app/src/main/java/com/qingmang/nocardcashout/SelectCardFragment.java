package com.qingmang.nocardcashout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flipboard.bottomsheet.commons.BottomSheetFragment;
import com.qingmang.R;

/**
 * Created by jiangpw
 * on 2018/4/17 18:00
 */
public class SelectCardFragment extends BottomSheetFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_no_card, container, false);
    }
}
