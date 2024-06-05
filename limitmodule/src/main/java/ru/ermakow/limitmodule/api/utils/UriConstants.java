package ru.ermakow.limitmodule.api.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UriConstants {

    public static final String LIMIT = "/limit";

    public static final String DECREASE_LIMIT = LIMIT + "/decrease";

    public static final String INCREASE_LIMIT = LIMIT + "/increase";
}
