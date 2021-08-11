package cc.rememberme.demo.model;

import java.util.List;

import cc.rememberme.demo.constant.FeatureItemEnum;

/**
 * @author : guojialin
 * @date : 2021/8/7 21:37
 */
public class FeatureItem {

    public FeatureItem(FeatureItemEnum itemEunm, List<FeatureItem> children) {
        this.itemEnum = itemEunm;
        this.children = children;
    }

    /**
     * id
     */
    private FeatureItemEnum itemEnum;

    /**
     * 子页面
     */
    private List<FeatureItem> children;

    public int getId() {
        return this.itemEnum.getId();
    }

    public String getName() {
        return this.itemEnum.getName();
    }

    public List<FeatureItem> getChildren() {
        return children;
    }

    public void setChildren(List<FeatureItem> children) {
        this.children = children;
    }

    public FeatureItemEnum getItemEnum() {
        return itemEnum;
    }

    @Override
    public String toString() {
        return "FeatureItem{" +
                "itemEnum=" + itemEnum +
                ", children=" + children +
                '}';
    }
}
