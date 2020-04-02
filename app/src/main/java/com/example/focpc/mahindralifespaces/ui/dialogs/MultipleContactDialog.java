package com.example.focpc.mahindralifespaces.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.mlife.R;

import com.example.focpc.mahindralifespaces.ui.activities.promote_us_contact.PromoteContactAdapter;
import com.example.focpc.mahindralifespaces.ui.activities.select_contact.ContactsAdapter;
import com.example.focpc.mahindralifespaces.ui.activities.select_contact.MultiContactAdapter;
import com.example.focpc.mahindralifespaces.utils.MlsUtils;

import java.util.List;

/**
 * Created by foc pc on 11-12-2017.
 */

public class MultipleContactDialog extends Dialog {
    private MultiContactAdapter multiAdapter;

    public MultipleContactDialog(@NonNull final Context context, final List<String> numbers,
                                 final int position, final int presentSlection,
                                 final RecyclerView.Adapter contactsAdapter) {
        super(context);
        getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(true);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.contact_listing_layout);
        Window window = getWindow();
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);

        RecyclerView contRecycler = findViewById(R.id.multiContRecycler);
        Button okBtn = findViewById(R.id.okBtn);
        multiAdapter = new MultiContactAdapter(numbers, presentSlection);
        contRecycler.setHasFixedSize(true);
        contRecycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        contRecycler.setAdapter(multiAdapter);
        okBtn.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View view) {

                                         if (multiAdapter != null && multiAdapter.getPresentPosition() != -1) {
                                             if (contactsAdapter instanceof PromoteContactAdapter) {
                                                 ((PromoteContactAdapter) contactsAdapter).
                                                         onContactClicked(MultipleContactDialog.this, position,multiAdapter.getPresentPosition());
                                             } else if ((contactsAdapter instanceof ContactsAdapter)) {

                                                 ((ContactsAdapter) contactsAdapter).onContactClicked(numbers.get(multiAdapter.getPresentPosition()),
                                                         MultipleContactDialog.this, position, multiAdapter.getPresentPosition());
                                             }

                                         } else if (multiAdapter != null) {
                                             MlsUtils.showToast(context, "Please Select a contact", true);
                                         }
                                     }
                                 }
        );
    }


}
