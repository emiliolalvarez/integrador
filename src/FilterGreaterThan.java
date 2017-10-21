public class FilterGreaterThan extends Filter {
    public FilterGreaterThan(String name, String value) {
        super(name, value);
    }

    @Override
    protected String getConditionString() {
        return this.getName() + " > " + this.getValue();
    }
}
