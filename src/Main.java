
public class Main {

    public static void main(String [] args) {

        FilterGroup filter = new FilterGroup();
        filter.addFilter(new FilterEquals("category", "1"))
            .addOrFilter(
                    (new FilterGroup()).addFilter(new FilterLessThan("price", "180000"))
                    .addAndFilter(new FilterEquals("hasPhoto", "true"))
            );

        System.out.print(filter.getConditionString());
    }
}
