package sudoku;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.text.DocumentFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.AbstractDocument;

@SuppressWarnings("serial")
public class NumberField extends JTextField {
	private Color color;
	
	/**
	 * Skapar ett textfält för ett tecken med färgen color.
	 * 
	 * @param color textfältets färg
	 */
	public NumberField(Color color) {
		super("");
		this.color = color;
		((AbstractDocument) this.getDocument())
				.setDocumentFilter(new NumberFilter());
	}

	@Override
	/** Retunerar textfältets standardbakgrundsfärg. */
	public Color getBackground() {
		return color;
	}

	@Override
	/** Retunerar textfältets standardtextfärg. */
	public Color getForeground() {
		return Color.DARK_GRAY;
	}

	@Override
	/** Retunerar textfältets standardplacering. */
	public int getHorizontalAlignment() {
		return JTextField.CENTER;
	}

	@Override
	/** Retunerar en standardfont. */
	public Font getFont() {
		return new Font("Bauhaus 93", Font.PLAIN, 36);
	}

	/** Klasssen som skapar fälten där man kan skriva in sina siffror. */
	private class NumberFilter extends DocumentFilter {
		NumberFilter() {
			super();
		}

		/** Ersätter regionens text med ny text (str) */
		public void replace(FilterBypass fb, int offset, int length,
				String str, AttributeSet attr) throws BadLocationException {
			if ((fb.getDocument().getLength() + str.length() - length) > 1)
				return;
			if (!str.isEmpty() && !str.matches("[1-9]"))
				return;
			fb.replace(offset, length, str, attr);
		}
	}
}