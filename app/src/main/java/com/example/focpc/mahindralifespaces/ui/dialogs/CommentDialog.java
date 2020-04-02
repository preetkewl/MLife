package com.example.focpc.mahindralifespaces.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.mlife.R;

import com.example.focpc.mahindralifespaces.ui.activities.select_contact.ContactItem;
import com.example.focpc.mahindralifespaces.utils.MlsUtils;

import java.util.List;

/**
 * Created by foc pc on 06-12-2017.
 */

public class CommentDialog extends Dialog implements View.OnClickListener {
    private DialogClickHandler dialogClickHandler;
    private EditText commentET;
    private CheckBox commentCB;
    private Button commentBtn;
    private Context context;
    private List<ContactItem> contactItemList;

    public CommentDialog(@NonNull Context context, List<ContactItem> contactItemList, DialogClickHandler dialogClickHandler) {
        super(context);
        this.context = context;
        this.contactItemList = contactItemList;
        getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(true);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.referral_comment_layout);
        Window window = getWindow();
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);

        commentET = findViewById(R.id.commentET);
        commentCB = findViewById(R.id.commentCB);
        commentBtn = findViewById(R.id.commentBtn);
        this.dialogClickHandler = dialogClickHandler;
        LinearLayout commentCbParent = findViewById(R.id.commentCbParent);
        commentBtn.setOnClickListener(this);
        commentCbParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentCB.setChecked(!commentCB.isChecked());
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.commentBtn:
                String comment = commentET.getText().toString().trim();
                if (!commentCB.isChecked()){
                    if (TextUtils.isEmpty(comment)) {
                        MlsUtils.showToast(context,"Enter your comment",false);
                        return;
                    }
                }
                dialogClickHandler.onCommentSubMitClicked(comment,contactItemList);
                break;
        }
    }

    public interface DialogClickHandler {
        void onCommentSubMitClicked(String comment,List<ContactItem> contactItemList);
    }





}
