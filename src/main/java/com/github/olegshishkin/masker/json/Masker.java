package com.github.olegshishkin.masker.json;

import static com.jayway.jsonpath.Option.AS_PATH_LIST;
import static java.util.regex.Pattern.MULTILINE;
import static org.apache.commons.lang3.StringUtils.left;
import static org.apache.commons.lang3.StringUtils.substring;

import com.github.olegshishkin.masker.json.dto.Replacement;
import com.github.olegshishkin.masker.json.replacer.impl.InnReplacer;
import com.github.olegshishkin.masker.json.replacer.impl.NameReplacer;
import com.google.common.collect.ImmutableList;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import java.util.List;
import java.util.regex.Pattern;

public class Masker {

    private static final Pattern JSON_PATTERN = Pattern.compile("\\{[\\s\\S]*\\}", MULTILINE);
    private static final List<Replacement> REPLACEMENTS = ImmutableList.<Replacement>builder()
            .add(new Replacement(JsonPath.compile("$.totalInn"), new InnReplacer()))
            .add(new Replacement(JsonPath.compile("$.intInn"), new InnReplacer()))
            .add(new Replacement(JsonPath.compile("$.items[*].inn"), new InnReplacer()))
            .add(new Replacement(JsonPath.compile("$.items[*].name"), new NameReplacer()))
            .build();
    public static final Configuration CONFIGURATION = Configuration
            .builder()
            .options(AS_PATH_LIST)
            .build();

    public static String mask(String input) {
        var matcher = JSON_PATTERN.matcher(input);

        if (!matcher.find()) {
            return input;
        }

        var start = matcher.start();
        var end = matcher.end();
        var txt = matcher.group();

        var ctx = JsonPath.using(CONFIGURATION).parse(txt);
        for (var r : REPLACEMENTS) {
            ctx = ctx.map(r.getPattern(), (val, c) -> r.getReplacer().replace(val));
        }

        return left(input, start) + ctx.jsonString() + substring(input, end);
    }
}
