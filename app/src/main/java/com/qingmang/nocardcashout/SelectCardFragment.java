package com.qingmang.nocardcashout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flipboard.bottomsheet.commons.BottomSheetFragment;
import com.qingmang.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by jiangpw
 * on 2018/4/17 18:00
 */
@SuppressLint("ValidFragment")
public class SelectCardFragment extends BottomSheetFragment {

    @BindView(R.id.rv_no_card_bank_card)
    RecyclerView rvNoCardBankCard;
    Unbinder unbinder;
    private NocardCashoutAdapter loanAdapter;
    private LinearLayoutManager linearLayoutManager;
    private boolean isCreditCard;

    @SuppressLint("ValidFragment")
    public SelectCardFragment(boolean isCreditCard) {
        this.isCreditCard = isCreditCard;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_no_card, container, false);
        unbinder = ButterKnife.bind(this, view);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        if (isCreditCard) {
            loanAdapter = new NocardCashoutAdapter(R.layout.item_no_card_cashout, NocardCashoutActivity.creditCards, isCreditCard);
        } else {
            loanAdapter = new NocardCashoutAdapter(R.layout.item_no_card_cashout, NocardCashoutActivity.debitCards, isCreditCard);
        }

        rvNoCardBankCard.setAdapter(loanAdapter);
        rvNoCardBankCard.setLayoutManager(linearLayoutManager);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
