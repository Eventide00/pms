package com.pms.base.model.dictionarytype;

import com.pms.base.util.treeutil.SumTree;

import lombok.Data;

@Data
public class DictionaryTypeSelectModel extends SumTree {
    private String id;
    private String parentId;
    private String fullName;
    private String enCode;
}
