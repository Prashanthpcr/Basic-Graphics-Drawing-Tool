import java.awt.*;

class DrawableRectangle implements Drawable {
    private final Point startPoint;
    private final Point endPoint;
    private final Color color;

    public DrawableRectangle(Point startPoint, Point endPoint, Color color) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.color = color;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        int x = Math.min(startPoint.x, endPoint.x);
        int y = Math.min(startPoint.y, endPoint.y);
        int width = Math.abs(startPoint.x - endPoint.x);
        int height = Math.abs(startPoint.y - endPoint.y);
        g.drawRect(x, y, width, height);
    }
}
