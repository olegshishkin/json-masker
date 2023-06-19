package com.github.olegshishkin.masker.json.replacer.impl;

import static org.apache.commons.lang3.StringUtils.left;
import static org.apache.commons.lang3.StringUtils.length;

import com.github.olegshishkin.masker.json.replacer.Replacer;

public class NameReplacer implements Replacer {

    @Override
    public Object replace(Object input) {
        if (input == null) {
            return null;
        }

        var len = length(input.toString());
        if (len == 0) {
            return input;
        }

        return left(input.toString(), 1) + '.';
    }
}
