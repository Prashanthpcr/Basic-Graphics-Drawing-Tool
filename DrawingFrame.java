import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


class DrawingFrame extends JFrame {
    private final DrawingCanvas canvas;
    private final JComboBox<String> shapeSelector;
    private final JButton colorPicker;
    private final JTextField textInput;
    private Color currentColor = Color.BLACK;

    public DrawingFrame() {
        setTitle("Basic Graphics Drawing Tool");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Canvas
        canvas = new DrawingCanvas();
        add(canvas, BorderLayout.CENTER);

        // Controls Panel
        JPanel controls = new JPanel();
        controls.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Shape Selector
        shapeSelector = new JComboBox<>(new String[]{"Line", "Rectangle", "Circle", "Text"});
        controls.add(new JLabel("Shape:"));
        controls.add(shapeSelector);

        // Color Picker
        colorPicker = new JButton("Pick Color");
        colorPicker.addActionListener(e -> pickColor());
        controls.add(colorPicker);

        // Text Input
        textInput = new JTextField(15);
        controls.add(new JLabel("Text:"));
        controls.add(textInput);

        // Clear Button
        JButton clearButton = new JButton("Clear Canvas");
        clearButton.addActionListener(e -> canvas.clearCanvas());
        controls.add(clearButton);

        add(controls, BorderLayout.NORTH);

        setVisible(true);
    }

    private void pickColor() {
        currentColor = JColorChooser.showDialog(this, "Pick a Color", currentColor);
        if (currentColor != null) {
            colorPicker.setBackground(currentColor);
        }
    }

    public String getSelectedShape() {
        return (String) shapeSelector.getSelectedItem();
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public String getInputText() {
        return textInput.getText();
    }

    class DrawingCanvas extends JPanel {
        private final ArrayList<Drawable> drawables = new ArrayList<>();
        private Point startPoint;

        public DrawingCanvas() {
            setBackground(Color.WHITE);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    startPoint = e.getPoint();
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    String shape = getSelectedShape();
                    if (shape.equals("Text")) {
                        String text = getInputText();
                        if (!text.isEmpty()) {
                            drawables.add(new DrawableText(startPoint, text, getCurrentColor()));
                        }
                    } else {
                        Point endPoint = e.getPoint();
                        switch (shape) {
                            case "Line" -> drawables.add(new DrawableLine(startPoint, endPoint, getCurrentColor()));
                            case "Rectangle" -> drawables.add(new DrawableRectangle(startPoint, endPoint, getCurrentColor()));
                            case "Circle" -> drawables.add(new DrawableCircle(startPoint, endPoint, getCurrentColor()));
                        }
                    }
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (Drawable drawable : drawables) {
                drawable.draw(g);
            }
        }

        public void clearCanvas() {
            drawables.clear();
            repaint();
        }
    }
}
