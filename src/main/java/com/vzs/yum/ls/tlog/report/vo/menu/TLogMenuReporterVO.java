package com.vzs.yum.ls.tlog.report.vo.menu;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.vzs.yum.ls.tlog.util.ListUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by byao on 9/15/16.
 */
public class TLogMenuReporterVO {
    private Map<String, List<String>> categoryToMenu = Maps.newHashMap();

    public void addEntry (String key, String menuName) {
        List<String> strings = categoryToMenu.get(key);
        if (strings == null) {
            strings = Lists.newArrayList();
        }
        strings.add(menuName);
        categoryToMenu.put(key, strings);
    }

    public List<String> getSheepMenuList() {
        List<String> sheepMenus = categoryToMenu.get("羊肉");
        if (ListUtils.isEmpty(sheepMenus)) {
            return Lists.newArrayList();
        }
        return sheepMenus;
    }

}
