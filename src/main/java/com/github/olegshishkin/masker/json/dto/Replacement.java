package com.github.olegshishkin.masker.json.dto;

import com.github.olegshishkin.masker.json.replacer.Replacer;
import com.jayway.jsonpath.JsonPath;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Replacement {

    private final JsonPath pattern;
    private final Replacer replacer;
}
