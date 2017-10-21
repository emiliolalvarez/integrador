import java.util.ArrayList;
import java.util.List;

public class FilterGroup extends SearchFilter {

    private List<SearchFilter> filters;

    public FilterGroup() {
        filters = new ArrayList<>();
    }

    public FilterGroup addFilter(SearchFilter filter) {
        filters.add(filter);
        return this;
    }

    public FilterGroup addAndFilter(SearchFilter filter) {
        filter.setOperation(new FilterOperation("AND"));
        return this.addFilter(filter);
    }

    public FilterGroup addOrFilter(SearchFilter filter) {
        filter.setOperation(new FilterOperation("OR"));
        return this.addFilter(filter);
    }

    @Override
    public String getConditionString() {
        StringBuffer sb = new StringBuffer();
        sb.append("(");
        filters.forEach((filter) -> sb.append(filter.getFilterString()));
        sb.append(")");
        return sb.toString();
    }
}
