class Circle {
    protected int radius;
    public Circle(int r) { radius = r; }
}
class Pizza extends Circle {
    private String chicago;

    public Pizza(int radius, String chicago) {
        super(radius);
        this.chicago = chicago;
    }

    public String getchicago() {
        return chicago;
    }

    public void setchicago(String chicago) {
        this.chicago = chicago;
    }


}
