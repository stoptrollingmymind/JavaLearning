import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public abstract class AutoUpdatableGUI extends JFrame {
    private final Queue<Component> components = new ConcurrentLinkedDeque<>();
    public AutoUpdatableGUI(String title, int width, int height) throws HeadlessException {
        super(title);
        setSize(width, height);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (e.getComponent() instanceof AutoUpdatableGUI) {
                    updateComponentsInGUI((AutoUpdatableGUI) e.getComponent());
                }
            }
        });
    }

    @Override
    public Component add(Component comp) {
        components.add(comp);
        return super.add(comp);
    }

    protected abstract void initComponents(int width, int height);

    private void updateComponentsInGUI(AutoUpdatableGUI gui) {
        components.forEach(this::remove);
        components.clear();
        initComponents(gui.getWidth(), gui.getHeight());
    }
}
