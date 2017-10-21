public class FilterLessThan extends Filter {
    public FilterLessThan(String name, String value) {
        super(name, value);
    }

    @Override
    protected String getConditionString() {
        return this.getName() + " < " + this.getValue();
    }
}
