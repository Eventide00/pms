package com.pms.base.model.dictionarydata;

import com.pms.base.util.treeutil.SumTree;

import lombok.Data;

@Data
public class DictionaryDataAllModel extends SumTree {
    private String  fullName;
    private String  enCode;
}
