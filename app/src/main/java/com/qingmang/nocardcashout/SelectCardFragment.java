package com.qingmang.nocardcashout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flipboard.bottomsheet.commons.BottomSheetFragment;
import com.qingmang.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    @BindView(R.id.iv_no_card_cashout_close)
    ImageView ivNoCardCashoutClose;

    @OnClick(R.id.iv_no_card_cashout_close)
    void ivNoCardCashoutCloseOnclick() {
        dismiss();
    }

    private NocardCashoutAdapter nocardCashoutAdapter;
    private LinearLayoutManager linearLayoutManager;
    private boolean isCreditCard;

    private TextView cardCreditCode;
    private TextView cardDebitCode;

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
            nocardCashoutAdapter = new NocardCashoutAdapter(R.layout.item_no_card_cashout, NocardCashoutActivity.creditCards, isCreditCard);
        } else {
            nocardCashoutAdapter = new NocardCashoutAdapter(R.layout.item_no_card_cashout, NocardCashoutActivity.debitCards, isCreditCard);
        }

        rvNoCardBankCard.setAdapter(nocardCashoutAdapter);
        rvNoCardBankCard.setLayoutManager(linearLayoutManager);

        cardCreditCode = (TextView) getActivity().findViewById(R.id.tv_no_cash_credit_card);
        cardDebitCode = (TextView) getActivity().findViewById(R.id.tv_no_cash_debit_card);

        nocardCashoutAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (isCreditCard) {
                    cardCreditCode.setText(NocardCashoutActivity.creditCards.get(position).getCreditCode());
                } else {
                    cardDebitCode.setText(NocardCashoutActivity.debitCards.get(position).getBankCode());
                }

                dismiss();
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
