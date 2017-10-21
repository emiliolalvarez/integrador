public class FilterEquals extends Filter {

    public FilterEquals(String name, String value) {
        super(name, value);
    }

    @Override
    protected String getConditionString() {
        return this.getName() + " = " + this.getValue();
    }

}
