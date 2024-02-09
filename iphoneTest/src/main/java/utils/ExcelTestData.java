package utils;

public class ExcelTestData {
    private final String searchItem ;
    private final String subtotal ;
    
    
    public ExcelTestData(String SearchItem, String subtotal) {
        this.searchItem = SearchItem;
        this.subtotal = subtotal;
    }

    public String getSearchItem() {
        return searchItem;
    }
    public String getSubtotal() {
        return subtotal;
    }

}
