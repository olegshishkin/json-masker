package com.github.olegshishkin.masker.json.replacer.impl;

import static org.apache.commons.lang3.StringUtils.leftPad;
import static org.apache.commons.lang3.StringUtils.length;
import static org.apache.commons.lang3.StringUtils.right;

import com.github.olegshishkin.masker.json.replacer.Replacer;

public class InnReplacer implements Replacer {

    @Override
    public Object replace(Object input) {
        if (input == null) {
            return null;
        }

        var len = length(input.toString());
        if (len == 0) {
            return input;
        }

        return leftPad(right(input.toString(), 2), len, '*');
    }
}
