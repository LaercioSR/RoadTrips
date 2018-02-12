package br.uefs.ecomp.RoadTrips.util;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

//Classe baseada do código de Paulo Henrique Luvisoto, encontrado em: "https://pastebin.com/HNzmC2tu"
public class MaskTextField {

    public static void maskNumeroReal(TextField textField) {
        textField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            newValue = newValue.replaceAll(",", ".");
            if (newValue.length() > 0) {
                try {
                    Double.parseDouble(newValue);
                    textField.setText(newValue.replaceAll(",", "."));
                } catch (NumberFormatException e) {
                    textField.setText(oldValue);
                }
            }
        });
    }

    public static void maskNumeroInteiro(TextField textField) {
        textField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (newValue.length() > 0) {
                try {
                    Integer.parseInt(newValue);
                } catch (NumberFormatException e) {
                    textField.setText(oldValue);
                }
            }
        });
    }

    public static void maskEmail(TextField textField) {
        textField.setOnKeyTyped((KeyEvent event) -> {
            if ("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz._-@".contains(event.getCharacter()) == false) {
                event.consume();
            }

            if ("@".equals(event.getCharacter()) && textField.getText().contains("@")) {
                event.consume();
            }

            if ("@".equals(event.getCharacter()) && textField.getText().length() == 0) {
                event.consume();
            }
        });
    }
}
