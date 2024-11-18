import java.awt.*;

class DrawableText implements Drawable {
    private final Point position;
    private final String text;
    private final Color color;

    public DrawableText(Point position, String text, Color color) {
        this.position = position;
        this.text = text;
        this.color = color;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawString(text, position.x, position.y);
    }
}
