public class FilterIn extends Filter {

    public FilterIn(String name, String value) {
        super(name, value);
    }

    @Override
    protected String getConditionString() {
        return this.getName() + " IN ("+ this.getValue() + ")";
    }

}
