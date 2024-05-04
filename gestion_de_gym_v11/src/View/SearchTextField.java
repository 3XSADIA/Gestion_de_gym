package View;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;

public class SearchTextField extends JTextField {
	private String textPrompt;

	public SearchTextField(String textPrompt) {
		this.textPrompt = textPrompt;
		setForeground(Color.GRAY); // Couleur du texte d'invite
		setText(textPrompt);
		setFont(new Font("Californian FB", Font.PLAIN, 21));
		addFocusListener(new java.awt.event.FocusAdapter() {
			@Override
			public void focusGained(java.awt.event.FocusEvent evt) {
				if (getText().equals(textPrompt)) {
					setText("");
					setForeground(Color.BLACK); // Couleur du texte entré
				}
			}

			@Override
			public void focusLost(java.awt.event.FocusEvent evt) {
				if (getText().isEmpty()) {
					setText(textPrompt);
					setForeground(Color.GRAY); // Retour à la couleur du texte d'invite
				}
			}
		});
	}
}
