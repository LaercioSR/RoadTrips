package br.uefs.ecomp.RoadTrips.util;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
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

    public static void maskNumeroInteiroComPontos(final TextField textField) {
        textField.lengthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            String value = textField.getText();
            value = value.replaceAll("[^0-9]", "");
            value = value.replaceAll("([0-9])([0-9]{3})$", "$1.$2");
            value = value.replaceAll("([0-9]{1})([0-9]{6})$", "$1.$2");
            value = value.replaceAll("([0-9]{1})([0-9]{9})$", "$1.$2");
            value = value.replaceAll("([0-9]{1})([0-9]{12})$", "$1.$2");
            textField.setText(value);
            
            textField.textProperty().addListener((ObservableValue<? extends String> observableValue, String oldValue1, String newValue1) -> {
                if (newValue1.length() > 17) {
                    textField.setText(oldValue1);
                }
            });
        });

        /*
        textField.focusedProperty().addListener((ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean fieldChange) -> {
            if (!fieldChange) {
                final int length = textField.getText().length();
                if (length > 0 && length < 3) {
                    textField.setText(textField.getText() + "00");
                }
            }
        });
*/
    }
}
