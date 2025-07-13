import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class hesapmakinesi extends JFrame implements ActionListener {
    JTextField ekran;
    JPanel tusPanel;
    String[] tuslar = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "C", "+",
            "="
    };
    JButton[] butonlar = new JButton[tuslar.length];
    String ilkSayi = "", ikinciSayi = "", islem = "";
    boolean yeniIslem = true;

    public hesapmakinesi() {
        setTitle("Hesap Makinesi");
        setSize(400, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        ekran = new JTextField();
        ekran.setFont(new Font("Arial", Font.BOLD, 24));
        ekran.setHorizontalAlignment(JTextField.RIGHT);
        ekran.setEditable(false);
        add(ekran, BorderLayout.NORTH);

        tusPanel = new JPanel();
        tusPanel.setLayout(new GridLayout(5, 4, 5, 5));

        for (int i = 0; i < tuslar.length; i++) {
            butonlar[i] = new JButton(tuslar[i]);
            butonlar[i].setFont(new Font("Arial", Font.BOLD, 18));
            butonlar[i].addActionListener(this);
            tusPanel.add(butonlar[i]);
        }

        add(tusPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String tus = e.getActionCommand();

        if (tus.matches("[0-9\\.]")) {
            if (yeniIslem) {
                ekran.setText("");
                yeniIslem = false;
            }
            ekran.setText(ekran.getText() + tus);
        } else if (tus.matches("[\\+\\-\\*/]")) {
            ilkSayi = ekran.getText();
            islem = tus;
            yeniIslem = true;
        } else if (tus.equals("=")) {
            ikinciSayi = ekran.getText();
            double sonuc = hesapla(Double.parseDouble(ilkSayi), Double.parseDouble(ikinciSayi), islem);
            ekran.setText(String.valueOf(sonuc));
            yeniIslem = true;
        } else if (tus.equals("C")) {
            ekran.setText("");
            ilkSayi = ikinciSayi = islem = "";
            yeniIslem = true;
        }
    }

    private double hesapla(double a, double b, String op) {
        return switch (op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> b != 0 ? a / b : 0;
            default -> 0;
        };
    }

    public static void main(String[] args) {
        new hesapmakinesi();
    }
}

