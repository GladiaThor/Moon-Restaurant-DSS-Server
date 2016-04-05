
package mrsserver;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
    /**
     * Creates a JFrame with a specialized closing function. Should be used
     * instead of JFrame for all secondary frames for the server.
     * @author (>^_^)> Claxxess<(^_^<)
     * @version 1.0
     */
public class JFrameClose extends JFrame {
/**
 * JFrame with custom close function.
 */

    public JFrameClose() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}