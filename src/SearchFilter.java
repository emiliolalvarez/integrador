abstract public class SearchFilter {

    FilterOperation operation = null;

    abstract protected String getConditionString();

    public String getFilterString() {
        return (operation != null ? " " + operation.getName() + " " : "") + getConditionString();
    }

    public void setOperation(FilterOperation operation) {
        this.operation = operation;
    }

}
