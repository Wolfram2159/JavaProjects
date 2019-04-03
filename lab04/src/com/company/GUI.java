package com.company;

import org.mariuszgromada.math.mxparser.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.MessageFormat;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ScrollPaneConstants;

public class GUI extends JFrame {
    private JPanel jPanel;
    private JMenuBar jMenuBar;
    private JTextArea jTextArea;
    private JTextField jTextField;
    private JMenu jMenu;
    private JMenuItem resetItem;
    private JMenuItem exitItem;
    private ListModel<ListModelElement> listModel;
    private JList jList;
    private JPanel jPanelBottom;
    private JButton jButton;
    private String lastExpression = "";
    private String lastResult = "";

    public GUI() throws HeadlessException {
        jPanel = new JPanel(new BorderLayout(5, 5));
        jMenuBar = new JMenuBar();
        jTextArea = new JTextArea();
        jTextField = new JTextField();
        jMenu = new JMenu("Options");
        exitItem = new JMenuItem("Exit");
        resetItem = new JMenuItem("Reset");
        listModel = new DefaultListModel<>();
        jList = new JList(listModel);
        jPanelBottom = new JPanel(new BorderLayout());
        jButton = new JButton("Evaluate!");
    }

    public void makeGUI() {
        ListModelElement sinus = new ListModelElement("Sinus", "sin()");
        ListModelElement cosinus = new ListModelElement("Cosinus", "cos()");
        ListModelElement tanges = new ListModelElement("Tanges", "tg()");
        ListModelElement cotanges = new ListModelElement("Cotanges", "ctg()");
        ListModelElement secans = new ListModelElement("Secans", "sec()");
        ListModelElement pi = new ListModelElement("Number π", "pi");
        ListModelElement e = new ListModelElement("Number e", "e");
        ListModelElement golden = new ListModelElement("Golden Ratio", "[phi]");
        ListModelElement plus = new ListModelElement("Plus", "+");
        ListModelElement minus = new ListModelElement("Minus", "-");
        ListModelElement divide = new ListModelElement("Divide", "/");
        ListModelElement lastRes = new ListModelElement("Last Result", "lastResult");

        ((DefaultListModel<ListModelElement>) listModel).addElement(sinus);
        ((DefaultListModel<ListModelElement>) listModel).addElement(cosinus);
        ((DefaultListModel<ListModelElement>) listModel).addElement(tanges);
        ((DefaultListModel<ListModelElement>) listModel).addElement(cotanges);
        ((DefaultListModel<ListModelElement>) listModel).addElement(secans);

        ((DefaultListModel<ListModelElement>) listModel).addElement(pi);
        ((DefaultListModel<ListModelElement>) listModel).addElement(e);
        ((DefaultListModel<ListModelElement>) listModel).addElement(golden);

        ((DefaultListModel<ListModelElement>) listModel).addElement(plus);
        ((DefaultListModel<ListModelElement>) listModel).addElement(minus);
        ((DefaultListModel<ListModelElement>) listModel).addElement(divide);

        ((DefaultListModel<ListModelElement>) listModel).addElement(lastRes);


        resetItem.addActionListener(event -> {
            jTextArea.setText("");
            jTextField.setText("");
        });

        exitItem.addActionListener(event -> {
            System.exit(0);
        });
        jMenu.add(resetItem);
        jMenu.add(exitItem);
        jMenuBar.add(jMenu);
        jPanel.add(BorderLayout.PAGE_START, jMenuBar);

        JScrollPane scroll = new JScrollPane(jTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(250, 250));
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        jTextArea.setEditable(false);
        jPanel.add(scroll);

        jList.setFixedCellWidth(200);
        jList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList list = (JList) e.getSource();
                if (e.getClickCount() == 2) {
                    int index = list.locationToIndex(e.getPoint());
                    if (index != 11) {
                        jTextField.setText(jTextField.getText() + ((DefaultListModel<ListModelElement>) listModel).get(index).getExpressionName());
                        jTextField.requestFocusInWindow();
                        if(index<5){
                            jTextField.setCaretPosition(jTextField.getText().length()-1);
                        }
                    } else if (index == 11) {
                        jTextField.setText(jTextField.getText() + lastResult);
                    }
                }
            }
        });
        jPanel.add(BorderLayout.LINE_END, jList);

        jTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 38) {
                    jTextField.setText(lastExpression);
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    onEnter();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        jPanelBottom.add(BorderLayout.CENTER, jTextField);

        jButton.addActionListener(event -> {
            onEnter();
        });

        jPanelBottom.add(BorderLayout.LINE_END, jButton);
        jPanel.add(BorderLayout.PAGE_END, jPanelBottom);

        add(jPanel);
    }

    public void addText(String expression) {
        Expression exp = new Expression(expression);
        if(exp.checkSyntax()) {
            Double result = exp.calculate();
            jTextArea.setText(jTextArea.getText() +
                    MessageFormat.format("{0} = {1,number}\n\n", expression, result));
            lastResult = result.toString();
        }else{
            JOptionPane.showMessageDialog(null, "Błędnie wprowadzony format", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onEnter() {
        String expression = jTextField.getText();
        addText(expression);
        jTextField.setText("");
        lastExpression = expression;
    }
}
