package com.example.zara_tpv.manager;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.zara_tpv.R;
import com.example.zara_tpv.adapter.DiscountAdapter;
import com.example.zara_tpv.adapter.ListProductsAdapter;
import com.example.zara_tpv.pojo.Discount;
import com.example.zara_tpv.pojo.Producto;
import com.example.zara_tpv.pojo.Type;
import com.example.zara_tpv.windows.DiscountWindow;
import com.example.zara_tpv.windows.ResumeShopWindow;

import java.util.ArrayList;
import java.util.List;

public class DialogManager {
    private Resources res;

    public static void openProgressBar(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_progress_bar);

        final ProgressBar progressBar = dialog.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
    }

    public static void openDialogCode(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog_code);

        final EditText numBarcode = dialog.findViewById(R.id.editText_num_barcode);
        final Button buttonInsertBarcode = dialog.findViewById(R.id.button_insert_barcode);

        buttonInsertBarcode.setOnClickListener((v) -> {
            try {
                int id = Integer.parseInt(numBarcode.getText().toString());
                ProductsManager.getProducto(id, ResumeShopWindow.getAdapter());
                dialog.dismiss();
            } catch (NumberFormatException ex) {
                Toast.makeText(context, "Solo se pueden insertar números", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }

    private static void openDialogSignUp(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog_sign_up);

        final EditText textIdentification = dialog.findViewById(R.id.editText_username_sign_up);
        final EditText textNumber = dialog.findViewById(R.id.editText_telephone_sign_up);
        final EditText textEmail = dialog.findViewById(R.id.editText_email_sign_up);
        final EditText textPassword = dialog.findViewById(R.id.editText_password_sign_up);
        final EditText textRepeatPassword = dialog.findViewById(R.id.editText_repeat_password_sign_up);
        final Button buttonSignUp = dialog.findViewById(R.id.button_sign_up);

        EditText[] textToValidate = {textIdentification, textEmail, textNumber, textPassword, textRepeatPassword};

        buttonSignUp.setOnClickListener((v) -> {
            if(ValidatorManager.passValidation(textToValidate, context)) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public static void openDialogError(Context context, String textError) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_error_dialog);

        final TextView textErr = dialog.findViewById(R.id.errorDesc);
        final Button buttonClose = dialog.findViewById(R.id.errorButton);
        textErr.setText(textError);

        buttonClose.setOnClickListener((v) -> {
            dialog.dismiss();
        });

        dialog.show();
    }

    public static void openDialogClotheTakeOut(Context context, ListProductsAdapter adapter, Producto clothe) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog_clothe_warehouse);

        final TextView textTitleName = dialog.findViewById(R.id.textView_title_name_clothe);
        final TextView textTitleSize = dialog.findViewById(R.id.textView_title_size_clothe);
        final TextView textTitleColor = dialog.findViewById(R.id.textView_title_color_clothe);

        final TextView textSize = dialog.findViewById(R.id.textView_size_clothe_dialog);
        final TextView textColor = dialog.findViewById(R.id.textView_color_clothe_dialog);

        final Button buttonTakeOutClothe = dialog.findViewById(R.id.button_take_out_clothe);

        Type type = TypesManager.getOneType(clothe.getId_tipo());
        textTitleName.setText(type.getNombre_tipo()+" "+((type.getLongitud_tipo()!=null) ? type.getLongitud_tipo() : clothe.getColor()));
        textTitleSize.setText(context.getString(R.string.textView_title_size_clothe));
        textTitleColor.setText(context.getString(R.string.textView_title_color_clothe));

        textSize.setText(String.valueOf(clothe.getTalla()));
        textColor.setText(clothe.getColor());

        buttonTakeOutClothe.setOnClickListener((v) -> {
            Toast.makeText(context, R.string.message_warehouse, Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

//        buttonDeleteClothe.setOnClickListener((v) -> {
//            adapter.removeItem(position);
//            Toast.makeText(context, context.getString(R.string.message_delete_clothe), Toast.LENGTH_SHORT).show();
//            dialog.dismiss();
//        });

        dialog.show();
    }

    public static void openDialogClotheResume(Context context, ListProductsAdapter adapter, Producto clothe, int position) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog_clothe_resume);

        final TextView textTitleName = dialog.findViewById(R.id.textView_title_name_clothe);
        final TextView textTitleSize = dialog.findViewById(R.id.textView_title_size_clothe);
        final TextView textTitleColor = dialog.findViewById(R.id.textView_title_color_clothe);

        final TextView textSize = dialog.findViewById(R.id.textView_size_clothe_dialog);
        final TextView textColor = dialog.findViewById(R.id.textView_color_clothe_dialog);

        final Button buttonDeleteClothe = dialog.findViewById(R.id.button_delete_clothe);

        Type type = TypesManager.getOneType(clothe.getId_tipo());
        textTitleName.setText(type.getNombre_tipo()+" "+type.getLongitud_tipo());
        textTitleSize.setText(context.getString(R.string.textView_title_size_clothe));
        textTitleColor.setText(context.getString(R.string.textView_title_color_clothe));

        textSize.setText(String.valueOf(clothe.getTalla()));
        textColor.setText(clothe.getColor());

        buttonDeleteClothe.setOnClickListener((v) -> {
            adapter.removeItem(position);
            Toast.makeText(context, context.getString(R.string.message_delete_clothe), Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        dialog.show();
    }

    public static void openDialogLogin(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog_sign_in);

        final EditText usernameSignIn = dialog.findViewById(R.id.editText_username_sign_in);
        final EditText passwordSignUp = dialog.findViewById(R.id.editText_password_sign_in);
        final Button buttonSignIn = dialog.findViewById(R.id.button_sign_in);
        final TextView textViewSignUp = dialog.findViewById(R.id.textView_sign_up);

        setSpannableString(textViewSignUp, context);

        buttonSignIn.setOnClickListener((v) -> {
            //int barcode = Integer.parseInt(numBarcode.getText().toString());
            if(!ValidatorManager.passValidationContent(usernameSignIn, context)
                    || !ValidatorManager.passValidationContent(usernameSignIn, context)) {
                openDialogError(context, context.getResources().getString(R.string.message_error_empty_field));
            } else {
                Intent intent = new Intent(context, DiscountWindow.class);
                context.startActivity(intent);
            }
        });

        textViewSignUp.setOnClickListener((v) -> {
            openDialogSignUp(context);
            dialog.dismiss();
        });

        dialog.show();
    }

    private static void setSpannableString(TextView textView, Context context) {
        final SpannableString registerString = new SpannableString(context.getString(R.string.textView_sign_up));
        applySpan(registerString, context.getString(R.string.textView_sign_up), new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
            }
        });

        textView.setText(registerString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private static void applySpan(SpannableString spannable, String target, ClickableSpan span) {
        final String spannableString = spannable.toString();
        final int start = spannableString.indexOf(target);
        final int end = start + target.length();
        spannable.setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    public static void openDialogDiscounts(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog_discounts);

        DiscountsManager dm = new DiscountsManager();

        final Button buttonApply = dialog.findViewById(R.id.button_apply_discount);

        buttonApply.setOnClickListener((v) -> {
            dialog.dismiss();
        });
    }
}
