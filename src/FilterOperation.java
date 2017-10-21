public class FilterOperation {

    String name;

    public FilterOperation(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String apply(SearchFilter filter) {
        return " " + getName() + " " + filter.getConditionString();
    }

}
