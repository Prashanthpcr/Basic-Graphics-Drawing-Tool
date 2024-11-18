import java.awt.*;

class DrawableLine implements Drawable {
    private final Point startPoint;
    private final Point endPoint;
    private final Color color;

    public DrawableLine(Point startPoint, Point endPoint, Color color) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.color = color;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
    }
}
