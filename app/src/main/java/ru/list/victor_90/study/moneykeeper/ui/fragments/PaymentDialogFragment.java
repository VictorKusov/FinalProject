package ru.list.victor_90.study.moneykeeper.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ru.list.victor_90.study.moneykeeper.R;
import ru.list.victor_90.study.moneykeeper.database.BaseDBHelper;
import ru.list.victor_90.study.moneykeeper.database.models.Category;
import ru.list.victor_90.study.moneykeeper.database.models.Payment;
import ru.list.victor_90.study.moneykeeper.rest.networks.ServiceBroker;
import ru.list.victor_90.study.moneykeeper.ui.fragments.tabs.TabFragment2;
import ru.list.victor_90.study.moneykeeper.utils.DateParser;

public class PaymentDialogFragment extends DialogFragment implements View.OnClickListener {

    private ImageButton btnClr, btnZero, btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine;
    private Button btnCancel, btnOk;
    private ImageView logo;
    private TextView handle, edNote;
    private Bundle bundle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_payment, container, false);
        initViews(view);

        bundle = getArguments();
        Category category = new Category(
                bundle.getInt("iconId"),
                bundle.getString("category")
        );
        handle.setText(category.getCategory());
        logo.setImageResource(category.getIconById());
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgbtn_clear: {
                edNote.setText("");
                break;
            }
            case R.id.btn_ok: {
                makeNote();
                break;
            }
            case R.id.btn_cancel: {
                this.dismiss();
                break;
            }
            case R.id.btn_0: {
                valid("0");
                break;
            }
            case R.id.btn_1: {
                valid("1");
                break;
            }
            case R.id.btn_2: {
                valid("2");
                break;
            }
            case R.id.btn_3: {
                valid("3");
                break;
            }
            case R.id.btn_4: {
                valid("4");
                break;
            }
            case R.id.btn_5: {
                valid("5");
                break;
            }
            case R.id.btn_6: {
                valid("6");
                break;
            }
            case R.id.btn_7: {
                valid("7");
                break;
            }
            case R.id.btn_8: {
                valid("8");
                break;
            }
            case R.id.btn_9: {
                valid("9");
                break;
            }
        }
    }

    // метод упаковывает данные в объект Payment и записывает их в БД
    private void makeNote() {

        Payment payment = new Payment(
                DateParser.makeSQLDate(),
                Integer.valueOf(edNote.getText().toString()),
                "",
                bundle.getInt("iconId")
        );

        BaseDBHelper helper = new BaseDBHelper(getContext());
        helper.insertPayment(payment, ServiceBroker.getInstance().getToken());

        // сообщаем, что все добавилось хорошо
        Toast.makeText(getContext(), "Запись успешно добавлена", Toast.LENGTH_SHORT).show();
        TabFragment2.invalidate();

        // и выходим
        this.dismiss();
    }

    private void valid(String simbol) {
        if (edNote.getText().length() <= 10) {
            edNote.setText(edNote.getText().toString() + simbol);
        } else {
            Toast.makeText(getContext(), "Слишком большое значение", Toast.LENGTH_SHORT).show();
        }
    }


    private void initViews(View view) {
        btnZero = (ImageButton) view.findViewById(R.id.btn_0);
        btnOne = (ImageButton) view.findViewById(R.id.btn_1);
        btnTwo = (ImageButton) view.findViewById(R.id.btn_2);
        btnThree = (ImageButton) view.findViewById(R.id.btn_3);
        btnFour = (ImageButton) view.findViewById(R.id.btn_4);
        btnFive = (ImageButton) view.findViewById(R.id.btn_5);
        btnSix = (ImageButton) view.findViewById(R.id.btn_6);
        btnSeven = (ImageButton) view.findViewById(R.id.btn_7);
        btnEight = (ImageButton) view.findViewById(R.id.btn_8);
        btnNine = (ImageButton) view.findViewById(R.id.btn_9);
        btnCancel = (Button) view.findViewById(R.id.btn_cancel);
        btnOk = (Button) view.findViewById(R.id.btn_ok);

        btnClr = (ImageButton) view.findViewById(R.id.imgbtn_clear);
        edNote = (TextView) view.findViewById(R.id.ed_note);
        handle = (TextView) view.findViewById(R.id.txt_handle);
        logo = (ImageView) view.findViewById(R.id.logo);

        btnZero.setOnClickListener(this);
        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnFour.setOnClickListener(this);
        btnFive.setOnClickListener(this);
        btnSix.setOnClickListener(this);
        btnSeven.setOnClickListener(this);
        btnEight.setOnClickListener(this);
        btnNine.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        btnClr.setOnClickListener(this);
    }
}
