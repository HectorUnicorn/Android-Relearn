package cc.rememberme.demo.ui.main.listener;

import android.view.View;

/**
 *
 * @author : guojialin
 * @date : 2021/8/8 11:56
 */
public interface OnRVItemClickListener {

    /**
     * 当Item点击回调
     * @param view
     * @param position
     */
    public void OnItemClicked(View view, int position);

}
