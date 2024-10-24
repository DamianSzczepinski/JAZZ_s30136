package org.example.queries.filters;

import org.example.model.Person;
import org.example.queries.search.SearchParameters;

import java.util.List;
import java.util.stream.Collectors;

public class ByNameFilter implements IFilterPeople {

    private SearchParameters searchParameters;

    @Override
    public void setSearchParameters(SearchParameters searchParameters) {
        this.searchParameters = searchParameters;
    }

    @Override
    public boolean canFilter() {
        return searchParameters.getName() != null && !searchParameters.getName().isEmpty();
    }

    @Override
    public List<Person> filter(List<Person> people) {
        return people.stream()
                .filter(person -> person.getName().contains(searchParameters.getName()))
                .collect(Collectors.toList());
    }
}
