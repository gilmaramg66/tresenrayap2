package ec.edu.espol.tresenrayap2;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import java.io.OutputStream;

/**
 *
 * @author gilmar munoz g
 */
public class TextOutStream extends OutputStream{
    private final TextArea textArea;

    public TextOutStream(TextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void write(int b) {
        appendText(String.valueOf((char) b));
    }

    @Override
    public void write(byte[] b, int off, int len) {
        appendText(new String(b, off, len));
    }

    private void appendText(String text) {
        Platform.runLater(() -> textArea.appendText(text));
    }
}
