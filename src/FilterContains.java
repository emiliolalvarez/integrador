public class FilterContains extends Filter {

    public FilterContains(String name, String value) {
        super(name, value);
    }

    @Override
    protected String getConditionString() {
        return this.getName() + " LIKE (%" + this.getValue() + "%)";
    }

}
