package be.vbgn.gradle.devConventions;

import be.vbgn.gradle.devConventions.conventions.Convention;
import be.vbgn.gradle.devConventions.conventions.Opinion;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ServiceLoader;

final class ConventionLoader<T> {


    /**
     * Initialization on demand holder
     *
     * @see https://en.wikipedia.org/wiki/Initialization_on_demand_holder_idiom
     */
    private static final class LazyHolder {

        private static final ConventionLoader<Convention> CONVENTION_INSTANCE = new ConventionLoader<>(
                Convention.class);
        private static final ConventionLoader<Opinion> OPINION_INSTANCE = new ConventionLoader<>(Opinion.class);
    }

    private final List<T> installedConventions;

    private ConventionLoader(Class<T> type) {
        ServiceLoader<T> conventionLoader = ServiceLoader.load(type);
        List<T> conventions = new ArrayList<>();
        for (T convention : conventionLoader) {
            conventions.add(convention);
        }

        installedConventions = Collections.unmodifiableList(conventions);
    }

    public List<T> getInstalledConventions() {
        return installedConventions;
    }


    public static List<Convention> conventions() {
        return LazyHolder.CONVENTION_INSTANCE.getInstalledConventions();
    }

    public static List<Opinion> opinions() {
        return LazyHolder.OPINION_INSTANCE.getInstalledConventions();
    }
}
