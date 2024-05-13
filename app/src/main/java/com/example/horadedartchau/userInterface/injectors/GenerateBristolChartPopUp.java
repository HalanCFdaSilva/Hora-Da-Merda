package com.example.horadedartchau.userInterface.injectors;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.example.horadedartchau.DatePickerFragment;
import com.example.horadedartchau.R;

public class GenerateBristolChartPopUp {



    public static void gerarPopupSeletorMesAoClicarBotao(ImageButton button, Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.bristol_scale_pop_up, null, false);
                DatePickerFragment datePicker = new DatePickerFragment(viewGroup);
                PopupWindow pw = datePicker.getPopUpWindow();
                ImageView imageViewBackground = viewGroup.findViewById(R.id.bristol_scale_pop_up_image_view_background);
                datePicker.encerrarAoClicarFora(imageViewBackground);
                pw.showAtLocation(viewGroup, Gravity.CENTER, 0, 0);

            }
        });
    }

}


