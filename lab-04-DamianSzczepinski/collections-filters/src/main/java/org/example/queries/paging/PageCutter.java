package org.example.queries.paging;

import org.example.model.Person;
import org.example.queries.search.Page;

import java.util.List;

public class PageCutter implements ICutToPage {

    @Override
    public List<Person> cut(Page page, List<Person> data) {
        int startIndex = page.getSize() * (page.getPageNumber() - 1);
        int endIndex = Math.min(page.getSize() * page.getPageNumber(), data.size());

        if (startIndex >= data.size()) {
            return List.of();
        }

        return data.subList(startIndex, endIndex);
    }
}
