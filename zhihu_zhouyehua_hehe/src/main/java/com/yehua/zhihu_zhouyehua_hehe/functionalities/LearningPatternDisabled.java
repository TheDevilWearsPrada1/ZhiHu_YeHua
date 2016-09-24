package com.yehua.zhihu_zhouyehua_hehe.functionalities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;


import com.yehua.zhihu_zhouyehua_hehe.R;
import com.yehua.zhihu_zhouyehua_hehe.bean.ThemesBean;
import com.yehua.zhihu_zhouyehua_hehe.mockedFragments.FragmentButton;
import com.yehua.zhihu_zhouyehua_hehe.mockedFragments.FragmentIndex;

import java.util.List;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;

/**
 * Created by neokree on 08/03/15.
 */
public class LearningPatternDisabled extends MaterialNavigationDrawer {

    @Override
    public void init(Bundle savedInstanceState) {
        // check in the styles.xml
        //设置首页
        MaterialSection oneSection = newSection("首页", R.drawable.home, new FragmentButton());
        this.addSection(oneSection);
        Intent intent = getIntent();
        List<ThemesBean> data = (List<ThemesBean>) intent.getSerializableExtra("data");
        for (int i = 0; i < data.size(); i++) {
            ThemesBean t=data.get(i);
            this.addSection(newSection(t.name, new FragmentIndex()));
        }
      /*  this.addSection(newSection("Section 1", new FragmentIndex()));

        this.addSection(newSection("Section 3", R.drawable.ic_mic_white_24dp,new FragmentButton()).setSectionColor(Color.parseColor("#9c27b0")));*/

    }
}
