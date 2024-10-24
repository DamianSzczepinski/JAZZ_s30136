package org.example.queries.filters;

import org.example.model.Person;
import org.example.queries.search.SearchParameters;

import java.util.List;
import java.util.function.Predicate;

public class GeneralFilter implements IFilterPeople {
    private SearchParameters searchParameters;
    private final Predicate<SearchParameters> canFilterPredicate;
    private final DualPredicate filterDualPredicate;

    public GeneralFilter(Predicate<SearchParameters> canFilterPredicate, DualPredicate filterDualPredicate) {
        this.canFilterPredicate = canFilterPredicate;
        this.filterDualPredicate = filterDualPredicate;
    }

    @Override
    public void setSearchParameters(SearchParameters searchParameters) {
        this.searchParameters = searchParameters;
    }

    @Override
    public boolean canFilter() {
        return canFilterPredicate.test(searchParameters);
    }

    @Override
    public List<Person> filter(List<Person> people) {
        return people.stream()
                .filter(person -> filterDualPredicate.check(searchParameters, person))
                .toList();
    }
}
